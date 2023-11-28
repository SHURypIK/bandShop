package com.example.bandShop.model;

import com.example.bandShop.entity.ShopEntity;

public class Shop {

    private int id;
    private String address;
    private String workTime;
    private  String phone;

    public static Shop toModel(ShopEntity entity){
        Shop model = new Shop();
        model.setAddress(entity.getAddress());
        model.setPhone(entity.getPhone());
        model.setId(entity.getId());
        model.setWorkTime(entity.getWorkTime());
        return model;
    }

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
