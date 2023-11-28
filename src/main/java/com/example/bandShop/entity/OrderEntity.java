package com.example.bandShop.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String complitied;
    private Date date;


    @OneToOne
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "shopId")
    private  ShopEntity shop;

    public OrderEntity() {
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

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
}
