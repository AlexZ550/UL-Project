/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessOperations;

import Models.Appuser;
import Models.Log;
import Models.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
@DeclareRoles({"Customer", "Administrator"})
public class CartRepo {

    @Resource(mappedName = "LogQueue")
    private Queue logQueue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    
    
    @PersistenceContext(unitName = "eAppShop-ejbPU")
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    private Collection<Product> _productsInCart;

    @PostConstruct
    private void initializeBean() {
        _productsInCart = new ArrayList<>();
    }

    public Collection<Product> getProductsInCart() {
        return _productsInCart;
    }

    public void updateInCart(Product product) {
        if (_productsInCart.contains(product)) {
            _productsInCart.remove(product);
        }
        if (product.getQty() > 0) {
            _productsInCart.add(product);
        }
    }

    public Collection<Product> checkOut(Appuser user) throws EJBException {
        ArrayList<Product> outOfStock = new ArrayList();
        try {
            utx.begin();
            for (Product productInCart : _productsInCart) {
                Product product = (Product) em.createNamedQuery("Product.findByProductid")
                        .setParameter("productid", productInCart.getProductid())
                        .getSingleResult();
                
                if (product.getQty() < productInCart.getQty()) {
                    outOfStock.add(product);
                }
                else {
                    product.setQty(product.getQty() - productInCart.getQty());
                    em.merge(product);
                }
            }
            
            if (!outOfStock.isEmpty()) {
                utx.rollback();
            }
            else {
                _productsInCart.clear();
                logMessage("Order has been placed", user);
                utx.commit();
            }
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | 
                SecurityException | IllegalStateException | NotSupportedException | SystemException ex) {
            try {
                System.err.println(ex);
                utx.rollback();
                throw new EJBException("Check out failed", ex);
            } catch (IllegalStateException | SecurityException | SystemException innerEx) {
                System.err.println(innerEx);
                throw new EJBException("Check out failed", ex);
            }
        } 
        return outOfStock;
    }

    public void cancel(Appuser user) {
        _productsInCart.clear();
        logMessage("Order has been canceled", user);
    }
    
    
    private void logMessage(String message, Appuser user){
            Log log = new Log();
            log.setMessage(message);
            log.setTimestamp(new Date());
            log.setUserid(user);
            sendJMSMessageToLogQueue(log);        
    }
    
    
    private void sendJMSMessageToLogQueue(Log logEntry) {
        context.createProducer().send(logQueue, logEntry);
    }
    

}
