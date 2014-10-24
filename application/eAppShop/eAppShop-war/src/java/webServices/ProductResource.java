/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import BusinessOperations.CommentRepo;
import BusinessOperations.ProductRepo;
import BusinessOperations.UserRepo;
import Models.Appuser;
import Models.Product;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author BZX5RY
 */
@Path("product")
public class ProductResource {

    @EJB
    ProductRepo _products;

    @EJB
    CommentRepo _comments;

    @EJB
    UserRepo _users;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductResource
     */
    public ProductResource() {
    }

    /**
     * Retrieves representation of an instance of webServices.ProductResource
     * @return an instance of Models.Product
     */
    @GET
    @Produces("application/json")
    public Collection<Product> getProducts() {
        Collection<Product> products = _products.getProducts();
        return products;
    }

    @GET
    @Produces("application/json")
    @Path("{productId}")
    public Product getProduct(@PathParam("productId") Integer productId) {
        Product product = _products.getProductById(productId);
        product.setCommentCollection(_comments.getCommentsByProduct(product));
        return product;
    }
    
    @PUT
    @Consumes("application/json")
    public void updateProduct(Product product) {
        _products.updateProduct(product);
    }
    
    @POST
    @Consumes("application/json")
    public void createProduct(@Context final SecurityContext securityContext, Product product) {
        String userName = securityContext.getUserPrincipal().getName();
        Appuser user = _users.findUser(userName);
        _products.createProduct(product, user);
    }

    @DELETE
    @Consumes("application/json")
    public void removeProduct(@Context final SecurityContext securityContext, @QueryParam("productid") Integer productid) {
        String userName = securityContext.getUserPrincipal().getName();
        Appuser user = _users.findUser(userName);
        _products.removeProduct(productid, user);
    }
    
    
}
