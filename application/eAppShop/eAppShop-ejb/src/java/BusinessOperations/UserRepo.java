/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessOperations;

import Models.Appuser;
import Models.Role;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
@DeclareRoles( {"Customer", "Administrator"})
public class UserRepo {
    @PersistenceContext(unitName = "eAppShop-ejbPU")
    private EntityManager em;
    
    
    @Resource SessionContext ctx;
    

    @RolesAllowed("Administrator")
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Collection<Appuser> getUsers()
    {
        return em.createNamedQuery( "Appuser.findAll" ).getResultList();
    }

    public Boolean isUserValid(String userName, String password)
    {

        return em.createNamedQuery("Appuser.findByNameAndPassword")
                .setParameter("name", userName)
                .setParameter("password", password).getResultList().size() > 0;
    }

    public Appuser findUser(String userName) {
        
        List users = em.createNamedQuery("Appuser.findByName")
                .setParameter("name", userName).getResultList();
        
        if (users.isEmpty())
        {
            Appuser newUser = new Appuser();
            newUser.setName(userName);
            Role customerRole = (Role)em.createNamedQuery("Role.findByName")
                                        .setParameter("name", isAdminstrator()?"Administrator":"Customer")
                                        .getSingleResult();
            Integer roleId = customerRole.getRoleid();
            newUser.setRoleid(roleId);
            newUser.setPassword("Password");
            newUser.setTimestamp(new Date());
            em.persist(newUser);
        }
        
        return (Appuser)em.createNamedQuery("Appuser.findByName")
                .setParameter("name", userName).getSingleResult();
    }

    public Boolean isAdminstrator(){
        
        return ctx.isCallerInRole("Administrator");
    }
    
}
