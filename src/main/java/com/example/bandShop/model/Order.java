package com.example.bandShop.model;

import com.example.bandShop.entity.OrderEntity;

import java.util.Date;

public class Order {

    private int id;
    private String complitied;
    private Date date;
    private Cart cart;
    private String shop;

    public static Order toModel(OrderEntity entity){
        Order model = new Order();
        model.setCart(Cart.toModel(entity.getCart()));
        model.setId(entity.getId());
        model.setComplitied(entity.getComplitied());
        model.setDate(entity.getDate());
        model.setShop(Integer.toString(entity.getShop().getId()));
        return model;
    }

    public Order() {
    }

    public Date isDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public String getShop() {
        return shop;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
