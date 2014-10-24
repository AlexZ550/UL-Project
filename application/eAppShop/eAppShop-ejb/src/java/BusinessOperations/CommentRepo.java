/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessOperations;

import Models.Comment;
import Models.Product;
import java.util.Collection;
import java.util.Date;
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
@DeclareRoles( {"Customer", "Administrator"})
public class CommentRepo {

    @PersistenceContext(unitName = "eAppShop-ejbPU")
    private EntityManager em;

    @RolesAllowed("Administrator")
    public void persist(Object object) {
        em.persist(object);
    }
    
    public Collection<Comment> getCommentsByProduct(Product product) {
        return em.createNamedQuery( "Comment.findByProductid" )
                 .setParameter("productid", product)
                 .getResultList();
    }

    public void commentProduct(Comment comment) {
        comment.setTimestamp(new Date());
        persist(comment);
    }
}
