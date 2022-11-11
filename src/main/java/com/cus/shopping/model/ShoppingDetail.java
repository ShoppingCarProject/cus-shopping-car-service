/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cus.shopping.model;

import java.io.Serializable;
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

/**
 *
 * @author Isaias
 */
@Entity
@Table(name = "shoppingdetail")
@NamedQueries({
    @NamedQuery(name = "ShoppingDetail.findAll", query = "SELECT s FROM ShoppingDetail s")})
public class ShoppingDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetail", nullable = false)
    private Integer iddetail;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 400)
    private String title;
    @Basic(optional = false)
    @Column(name = "image", nullable = false, length = 500)
    private String image;
    @Basic(optional = false)
    @Column(name = "productid", nullable = false)
    private Integer productid;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "paid", nullable = false)
    private Boolean paid;
    @JoinColumn(name = "idorders", referencedColumnName = "idorders", nullable = false)
    @ManyToOne(optional = false)
    private Orders idorders;

    public ShoppingDetail() {
    }

    public ShoppingDetail(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ShoppingDetail(Integer iddetail, int productid, Double price, String title, String image, Boolean paid) {
        this.iddetail = iddetail;
        this.productid = productid;
        this.price = price;
        this.title = title;
        this.image = image;
        this.paid = paid;
    }

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIddetail() {
        return iddetail;
    }

    public void setIddetail(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Orders getIdorders() {
        return idorders;
    }

    public void setIdorders(Orders idorders) {
        this.idorders = idorders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetail != null ? iddetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ShoppingDetail)) {
            return false;
        }
        ShoppingDetail other = (ShoppingDetail) object;
        if ((this.iddetail == null && other.iddetail != null) || (this.iddetail != null && !this.iddetail.equals(other.iddetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cus.shopping.model.ShoppingDetail[ iddetail=" + iddetail + " ]";
    }
    
}
