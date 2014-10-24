/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessOperations;

import Models.Log;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BZX5RY
 */
@Stateless
@LocalBean
@DeclareRoles( {"Administrator"})
public class LogRepo {

    @PersistenceContext(unitName = "eAppShop-ejbPU")
    private EntityManager em;
    
    @RolesAllowed("Administrator")
    public Collection<Log> getLogs(){
        return em.createNamedQuery( "Log.findAll" ).getResultList();        
    }
    
}
