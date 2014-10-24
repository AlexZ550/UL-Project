/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import BusinessOperations.UserRepo;
import Models.Appuser;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author BZX5RY
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    @EJB
    UserRepo _users;
    
    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of webServices.UserResource
     * @return an instance of Models.Appuser
     */
    @GET
    @Produces("application/json")
    public String isAdmin() {
        return String.format("{\"is_admin\":%s}", _users.isAdminstrator());
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(Appuser content) {
    }
    
    
}
