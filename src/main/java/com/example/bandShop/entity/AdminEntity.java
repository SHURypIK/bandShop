package com.example.bandShop.entity;

import jakarta.persistence.*;

@Entity
public class AdminEntity {

    @Id
    private int id;
    private String password;
    private String role;



    @OneToOne
    @JoinColumn(name = "shopId")
    private  ShopEntity shop;
    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public AdminEntity() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
