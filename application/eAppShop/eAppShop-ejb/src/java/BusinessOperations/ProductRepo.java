/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessOperations;

import Models.Appuser;
import Models.Log;
import Models.Product;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BZX5RY
 */
@Stateless
@LocalBean
@DeclareRoles( {"Customer", "Administrator"})
public class ProductRepo {
    @Resource(mappedName = "LogQueue")
    private Queue logQueue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    final String DEFULT_URL = "thumbs/noimage.jpg";
    
    @PersistenceContext(unitName = "eAppShop-ejbPU")
    private EntityManager em;

    public Collection<Product> getProducts()
    {
        return em.createNamedQuery( "Product.findAll" ).getResultList();
    }

    public Product getProductById(Integer productId) {
        return (Product)em.createNamedQuery( "Product.findByProductid" )
                 .setParameter("productid", productId)
                 .getSingleResult();
    }

    @RolesAllowed("Administrator")
    public void updateProduct(Product product) {
        if (product.getQty() < 0)
            throw new InvalidParameterException();

        if (product.getPrice()< 0)
            throw new InvalidParameterException();
        
        product.setTimestamp(new Date());
        em.merge(product);
    }

    @RolesAllowed("Administrator")
    public void removeProduct(Integer productid, Appuser user) {
        Product product = getProductById(productid);
        em.remove(product);
        logMessage("Product " + product.getDescription() + " has been removed"  , user);
    }

    @RolesAllowed("Administrator")
    public void createProduct(Product product, Appuser user) {
            if (product.getImageurl() == null)
                product.setImageurl(DEFULT_URL);
            product.setTimestamp(new Date());
            em.persist(product);
            logMessage("Product " + product.getDescription() + " has been created" , user);
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
