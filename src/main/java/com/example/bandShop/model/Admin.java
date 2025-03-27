package com.example.bandShop.model;

import com.example.bandShop.entity.AdminEntity;

public class Admin {

    private String password;
    private String role;
    private int id;

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    private int shop;



    public static Admin toModel(AdminEntity entity){
        Admin model = new Admin();
        model.setPassword(entity.getPassword());
        model.setRole(entity.getRole());
        model.setId(entity.getId());
        if(entity.getShop() != null)
        model.setShop(entity.getShop().getId());
        else model.setShop(0);
        return model;
    }

    public Admin() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
