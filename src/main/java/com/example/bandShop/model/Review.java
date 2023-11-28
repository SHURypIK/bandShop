package com.example.bandShop.model;

import com.example.bandShop.entity.ReviewEntity;


public class Review {

//  private int id;
    private double grade;
    private String text;
    private String user;

    public static Review toModel(ReviewEntity entity){
        Review model = new Review();
        model.setGrade(entity.getGrade());
        model.setText(entity.getText());
        model.setUser(entity.getUser().getLogin());
        return model;
    }

    public Review() {
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
