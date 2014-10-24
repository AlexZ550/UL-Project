/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import BusinessOperations.LogRepo;
import Models.Log;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author BZX5RY
 */
@Path("log")
public class LogResource {

    @Context
    private UriInfo context;

    @EJB
    LogRepo _logs;
    
    
    @GET
    @Produces("application/json")
    public Collection<Log> getLogs() {
        return _logs.getLogs();
    }

}
