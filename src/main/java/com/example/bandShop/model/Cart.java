package com.example.bandShop.model;

import com.example.bandShop.entity.CartEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {

    private double TotalPrice;
    private List<ProductCardCart> products = new ArrayList<>();


    public static Cart toModel(CartEntity entity){
        Cart model = new Cart();
        for(int i = 0; i< entity.getPrducts().size(); i++) {
            model.products.add(ProductCardCart.toModel(entity.getPrducts().get(i), entity.getAmounts().get(i)));
        }
        double tot = entity.getTotalPrice();
        tot *= 100;
        tot = Math.round(tot);
        tot /= 100;
        model.setTotalPrice(tot);
        return model;
    }

    public Cart() {
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public List<ProductCardCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCardCart> products) {
        this.products = products;
    }

}
