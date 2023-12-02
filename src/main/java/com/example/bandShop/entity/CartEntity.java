package com.example.bandShop.entity;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double TotalPrice;

    @ElementCollection
    @MapKeyColumn(name = "product")
    @Column(name = "amount")
    private HashMap<ProductEntity, Integer> products;


//    private List<Integer> amounts;
//
//    @OneToMany
//    private List<ProductEntity> prducts;


    @OneToOne
    private UserEntity user;


    public CartEntity() {
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<ProductEntity, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<ProductEntity, Integer> products) {
        this.products = products;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

//    public List<Integer> getAmounts() {
//        return amounts;
//    }
//
//    public void setAmounts(List<Integer> amounts) {
//        this.amounts = amounts;
//    }
//
//    public List<ProductEntity> getPrducts() {
//        return prducts;
//    }
//
//    public void setPrducts(List<ProductEntity> prducts) {
//        this.prducts = prducts;
//    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
