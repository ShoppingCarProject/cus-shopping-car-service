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
import javax.persistence.FetchType;
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
    @Column(name = "productid", nullable = false)
    private Integer productid;
    @Basic(optional = false)
    @Column(name = "price", nullable = false, length = 45)
    private String price;
    @JoinColumn(name = "idorders", referencedColumnName = "idorders", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orders idorders;

    public ShoppingDetail() {
    }

    public ShoppingDetail(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public ShoppingDetail(Integer iddetail, int productid, String price) {
        this.iddetail = iddetail;
        this.productid = productid;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
