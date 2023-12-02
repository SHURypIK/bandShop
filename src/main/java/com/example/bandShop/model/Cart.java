package com.example.bandShop.model;

import com.example.bandShop.entity.CartEntity;

import java.util.HashMap;

public class Cart {

    private double TotalPrice;
    private HashMap<ProductCard, Integer> products;


    public static Cart toModel(CartEntity entity){
        Cart model = new Cart();
        entity.getProducts().forEach((key,value)->model.products.put(ProductCard.toModel(key), value));
        model.setTotalPrice(entity.getTotalPrice());
        return model;
    }

    public Cart() {
        products = new HashMap<>();
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public HashMap<ProductCard, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<ProductCard, Integer> products) {
        this.products = products;
    }
}
