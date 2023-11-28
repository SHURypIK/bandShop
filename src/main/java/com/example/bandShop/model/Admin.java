package com.example.bandShop.model;

import com.example.bandShop.entity.AdminEntity;

public class Admin {

    private String password;
    private String role;
    private int id;



    public static Admin toModel(AdminEntity entity){
        Admin model = new Admin();
        model.setPassword(entity.getPassword());
        model.setRole(entity.getRole());
        model.setId(entity.getId());
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
