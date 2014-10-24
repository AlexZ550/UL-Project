import BusinessOperations.CartRepo;
import BusinessOperations.UserRepo;
import Models.Appuser;
import Models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends HttpServlet {

    @EJB
    UserRepo _users;
    
    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "shoppingCart";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get cart request");
        CartRepo cartBean = getCartRepoInstance(request);
        Collection<Product> products = cartBean.getProductsInCart();
        Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        String jsonResponse = gson.toJson(products);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(jsonResponse);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Update cart");
        CartRepo cartBean = getCartRepoInstance(request);
        Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        Product product = gson.fromJson(request.getReader(), Product.class);
        cartBean.updateInCart(product);
        System.out.println("product " + product.getProductid() + " added");
        Collection<Product> products = cartBean.getProductsInCart();
        System.out.println("products: " + products.size());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        CartRepo cartBean = getCartRepoInstance(request);
        Appuser user = _users.findUser(request.getUserPrincipal().getName());
        cartBean.cancel(user);
        System.out.println("Shopping cart canceled ");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
            CartRepo cartBean = getCartRepoInstance(request);
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
            Appuser user = _users.findUser(request.getUserPrincipal().getName());
            Collection<Product> products = cartBean.checkOut(user);
            String jsonResponse = gson.toJson(products);
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println(jsonResponse);
            }

            if (products.isEmpty())
                System.out.println("Shopping cart checked out ");
            else
                System.out.println("Product out of stock ");
            
    }

    @Override
    public String getServletInfo() {
        return "User Cart";
    }

    protected CartRepo getCartRepoInstance(HttpServletRequest request) throws ServletException
    {
        CartRepo cartBean = (CartRepo) request.getSession().getAttribute(CART_SESSION_KEY);
        if (cartBean == null) {
            try {
                InitialContext ic = new InitialContext();
                cartBean = (CartRepo) ic.lookup("java:global/eAppShop/eAppShop-ejb/CartRepo!"
                        + "BusinessOperations.CartRepo");
                request.getSession().setAttribute(CART_SESSION_KEY, cartBean);
                System.out.println("shoppingCart created");
            } catch (NamingException ex) {
                throw new ServletException(ex);
            }
        }    
        return cartBean;
    }

}
