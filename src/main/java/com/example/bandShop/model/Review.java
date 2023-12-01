package com.example.bandShop.model;

import com.example.bandShop.entity.ReviewEntity;


public class Review {

    //private int id;
    private double grade;
    private String text;
    private String user;
    private String product;

    public static Review toModel(ReviewEntity entity){
        Review model = new Review();
        model.setGrade(entity.getGrade());
        model.setText(entity.getText());
        model.setUser(entity.getUser().getLogin());
        model.setProduct(entity.getProduct().getTitle());
        return model;
    }

    public Review() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
