package com.example.bandShop.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double TotalPrice;

//    @ElementCollection
//    @MapKey
//    private HashMap<ProductEntity, Integer> products;


    private List<Integer> amounts = new ArrayList<>();

    @ManyToMany
    private List<ProductEntity> prducts = new ArrayList<>();


    @OneToOne
    private UserEntity user;


    public CartEntity() {
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
