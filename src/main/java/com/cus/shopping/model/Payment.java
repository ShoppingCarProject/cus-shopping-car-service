package com.cus.shopping.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Isaias
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpayment", nullable = false)
    private Integer idpayment;
    
    @Basic(optional = false)
    @Column(name = "totalsum", nullable = false)
    private Double totalsum;
    
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 800)
    private String description;
    
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created;   
    @Column(name = "orderid", nullable = false)
    private Integer oderid;
    
    @Column(name = "user", nullable = false)
    private Integer user;
    
   
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Payment() {
		super();
	}

	public Integer getIdPayment() {
		return idpayment;
	}

	public void setIdPayment(Integer idPayment) {
		this.idpayment = idPayment;
	}

	public Payment(Integer idPayment, Double total, String description, Date created, Integer oderId) {
		super();
		this.idpayment = idPayment;
		this.totalsum = total;
		this.description = description;
		this.created = created;
		this.oderid = oderId;
	}

	public Double getTotal() {
		return totalsum;
	}

	public void setTotal(Double total) {
		this.totalsum = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getOderid() {
		return oderid;
	}

	public void setOderid(Integer oderId) {
		this.oderid = oderId;
	}
    
    
}
