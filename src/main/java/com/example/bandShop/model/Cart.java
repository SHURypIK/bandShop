package com.example.bandShop.model;

import com.example.bandShop.entity.CartEntity;

import java.util.HashMap;

public class Cart {

    private double TotalPrice;
    private HashMap<Product, Integer> products;


    public static Cart toModel(CartEntity entity){
        Cart model = new Cart();
        for(int i = 0; i< entity.getPrducts().size(); i++)
            model.products.put(Product.toModel(entity.getPrducts().get(i)), entity.getAmounts().get(i));
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

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }
}
