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
import javax.persistence.Table;

/**
 *
 * @author Isaias
 */
@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorders", nullable = false)
    private Integer idorders;
    @JoinColumn(name = "idstatus", referencedColumnName = "idstatus", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderStatus idstatus;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userIduser;

    public Orders() {
    }

    public Orders(Integer idorders) {
        this.idorders = idorders;
    }

    public Integer getIdorders() {
        return idorders;
    }

    public void setIdorders(Integer idorders) {
        this.idorders = idorders;
    }

    public OrderStatus getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(OrderStatus idstatus) {
        this.idstatus = idstatus;
    }

    public User getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(User userIduser) {
        this.userIduser = userIduser;
    }
    
}
