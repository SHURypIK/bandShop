package com.example.bandShop.model;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.entity.ReviewEntity;


public class ProductCard {
    private String id;
    private String title;
    private double price;
    private double avgGrade;
    private String pictures;
    private String storage;
    private boolean hit;



    public static ProductCard toModel(ProductEntity entity){
        ProductCard model = new ProductCard();
        model.setId(entity.getId());
        if(entity.getStrorage() > 0)
            model.setStorage("Есть на складе");
        else
            model.setStorage("Нет на складе");
        double avgGrade = 0;
        int amount = 0;
        if (entity.getReviews().isEmpty()){
            model.setAvgGrade(-1);
        }else{
        for(ReviewEntity r : entity.getReviews()){
            avgGrade += r.getGrade();
            amount++;
        }
        avgGrade /= amount;
        avgGrade*=10;
        avgGrade=Math.round(avgGrade);
        model.setAvgGrade(avgGrade/10);}
        model.setTitle(entity.getTitle());
        model.setPrice(entity.getPrice());
        model.setPictures(entity.getPictures().get(0));
        model.setHit(entity.isHit());
        return model;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public ProductCard() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}


