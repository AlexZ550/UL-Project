/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BZX5RY
 */
@Entity
@Table(name = "PRODUCT", catalog = "", schema = "APP13199358")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductid", query = "SELECT p FROM Product p WHERE p.productid = :productid"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByQty", query = "SELECT p FROM Product p WHERE p.qty = :qty"),
    @NamedQuery(name = "Product.findByImageurl", query = "SELECT p FROM Product p WHERE p.imageurl = :imageurl"),
    @NamedQuery(name = "Product.findByTimestamp", query = "SELECT p FROM Product p WHERE p.timestamp = :timestamp")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCTID")
    private Integer productid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private int qty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IMAGEURL")
    private String imageurl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid")
    private Collection<Comment> commentCollection;

    public Product() {
    }

    public Product(Integer productid) {
        this.productid = productid;
    }

    public Product(Integer productid, String description, int price, int qty, String imageurl, Date timestamp) {
        this.productid = productid;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.imageurl = imageurl;
        this.timestamp = timestamp;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Product[ productid=" + productid + " ]";
    }
    
}
