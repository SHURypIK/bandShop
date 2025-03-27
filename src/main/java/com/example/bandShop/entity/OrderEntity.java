package com.example.bandShop.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String complitied;
    private Date date;
    private String userEmail;


    private List<Integer> amounts = new ArrayList<>();

    @ManyToMany
    private List<ProductEntity> prducts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shopId")
    private  ShopEntity shop;

    public OrderEntity() {
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }

    public List<ProductEntity> getPrducts() {
        return prducts;
    }

    public void setPrducts(List<ProductEntity> prducts) {
        this.prducts = prducts;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplitied() {
        return complitied;
    }

    public void setComplitied(String complitied) {
        this.complitied = complitied;
    }


    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
