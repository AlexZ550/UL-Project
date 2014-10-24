/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BZX5RY
 */
@Entity
@Table(name = "COMMENT", catalog = "", schema = "APP13199358")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCommentid", query = "SELECT c FROM Comment c WHERE c.commentid = :commentid"),
    @NamedQuery(name = "Comment.findByText", query = "SELECT c FROM Comment c WHERE c.text = :text"),
    @NamedQuery(name = "Comment.findByProductid", query = "SELECT c FROM Comment c WHERE c.productid = :productid"),
    @NamedQuery(name = "Comment.findByTimestamp", query = "SELECT c FROM Comment c WHERE c.timestamp = :timestamp")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMMENTID")
    private Integer commentid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "TEXT")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne(optional = false)
    private Product productid;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Appuser userid;

    public Comment() {
    }

    public Comment(Integer commentid) {
        this.commentid = commentid;
    }

    public Comment(Integer commentid, String text, Date timestamp) {
        this.commentid = commentid;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    public Appuser getUserid() {
        return userid;
    }

    public void setUserid(Appuser userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentid != null ? commentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentid == null && other.commentid != null) || (this.commentid != null && !this.commentid.equals(other.commentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Comment[ commentid=" + commentid + " ]";
    }
    
}
