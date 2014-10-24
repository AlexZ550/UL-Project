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
@Table(name = "LOG", catalog = "", schema = "APP13199358")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByLogid", query = "SELECT l FROM Log l WHERE l.logid = :logid"),
    @NamedQuery(name = "Log.findByMessage", query = "SELECT l FROM Log l WHERE l.message = :message"),
    @NamedQuery(name = "Log.findByTimestamp", query = "SELECT l FROM Log l WHERE l.timestamp = :timestamp")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LOGID")
    private Integer logid;
    @Size(max = 1000)
    @Column(name = "MESSAGE")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Appuser userid;

    public Log() {
    }

    public Log(Integer logid) {
        this.logid = logid;
    }

    public Log(Integer logid, Date timestamp) {
        this.logid = logid;
        this.timestamp = timestamp;
    }

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        hash += (logid != null ? logid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logid == null && other.logid != null) || (this.logid != null && !this.logid.equals(other.logid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Log[ logid=" + logid + " ]";
    }
    
}
