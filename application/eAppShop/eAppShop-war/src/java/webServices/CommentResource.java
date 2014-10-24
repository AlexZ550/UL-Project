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
import Models.Comment;
import Models.Product;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author BZX5RY
 */
@Path("comment")
public class CommentResource {

    
    @EJB
    ProductRepo _products;

    @EJB
    CommentRepo _comments;

    @EJB
    UserRepo _users;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CommentResource
     */
    public CommentResource() {
    }

    /**
     * Retrieves representation of an instance of webServices.CommentResource
     * @return an instance of Models.Comment
     */
    @GET
    @Produces("application/json")
    @Path("{productId}")
    public Collection<Comment> getCommentsByProductId(@PathParam("productId") Integer productId) {
        Product product = _products.getProductById(productId);
        Collection<Comment> comments = _comments.getCommentsByProduct(product);
        return comments;
    }

    /**
     * PUT method for updating or creating an instance of CommentResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Comment  createComment(@Context final SecurityContext securityContext, Comment comment) {
        Integer productId = comment.getProductid().getProductid();
        Product product = _products.getProductById(productId);
        comment.setProductid(product);
        String userName = securityContext.getUserPrincipal().getName();
        Appuser user = _users.findUser(userName);
        comment.setUserid(user);
        _comments.commentProduct(comment);
        return comment;
    }
}
