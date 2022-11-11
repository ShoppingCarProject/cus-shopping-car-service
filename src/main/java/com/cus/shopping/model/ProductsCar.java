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
@Table(name = "productscar")
@NamedQueries({
    @NamedQuery(name = "ProductsCar.findAll", query = "SELECT p FROM ProductsCar p")})
public class ProductsCar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductscar", nullable = false)
    private Integer idproductscar;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Basic(optional = false)
    @Column(name = "category", nullable = false, length = 45)
    private String category;
    @Basic(optional = false)
    @Column(name = "idapi", nullable = false)
    private int idapi;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private Double price;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 800)
    private String description;
    @Basic(optional = false)
    @Column(name = "image", nullable = false, length = 200)
    private String image;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User iduser;

    public ProductsCar() {
    }

    public ProductsCar(Integer idproductscar) {
        this.idproductscar = idproductscar;
    }

    public ProductsCar(Integer idproductscar, String title, String category, int idapi, Double price, String description, String image) {
        this.idproductscar = idproductscar;
        this.title = title;
        this.category = category;
        this.idapi = idapi;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Integer getIdproductscar() {
        return idproductscar;
    }

    public void setIdproductscar(Integer idproductscar) {
        this.idproductscar = idproductscar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIdapi() {
        return idapi;
    }

    public void setIdapi(int idapi) {
        this.idapi = idapi;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }    
}
