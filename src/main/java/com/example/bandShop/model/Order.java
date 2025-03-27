package com.example.bandShop.model;

import com.example.bandShop.entity.OrderEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private String complitied;
    private Date date;
    private List<ProductCardCart> products = new ArrayList<>();
    private String shop;
    private  String userEmail;

    public static Order toModel(OrderEntity entity){
        Order model = new Order();
        for(int i = 0; i< entity.getPrducts().size(); i++)
            model.products.add(ProductCardCart.toModel(entity.getPrducts().get(i), entity.getAmounts().get(i)));
        model.setId(entity.getId());
        model.setComplitied(entity.getComplitied());
        model.setDate(entity.getDate());
        model.setShop(Integer.toString(entity.getShop().getId()));
        model.setUserEmail(entity.getUserEmail());
        return model;
    }

    public List<ProductCardCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCardCart> products) {
        this.products = products;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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


}
