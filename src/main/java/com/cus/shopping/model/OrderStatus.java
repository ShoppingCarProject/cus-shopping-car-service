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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Isaias
 */
@Entity
@Table(name = "orderstatus")
@NamedQueries({
    @NamedQuery(name = "OrderStatus.findAll", query = "SELECT o FROM OrderStatus o")})
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstatus", nullable = false)
    private Integer idstatus;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public OrderStatus() {
    }

    public OrderStatus(Integer idstatus) {
        this.idstatus = idstatus;
    }

    public OrderStatus(Integer idstatus, String name) {
        this.idstatus = idstatus;
        this.name = name;
    }

    public Integer getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(Integer idstatus) {
        this.idstatus = idstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
