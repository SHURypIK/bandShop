package com.example.bandShop.model;

import com.example.bandShop.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Product {

    private String id;
    private String title;
    private Double price;
    private String description;
    private List<String> pictures;
    private int storage;
    private  boolean hit;

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    private List<Review> reviews;

    public static Product toModel(ProductEntity entity){
        Product model = new Product();
        model.setId(entity.getId());
        model.setStorage(entity.getStrorage());
        model.setDescription(entity.getDescription());
        model.setTitle(entity.getTitle());
        model.setPrice(entity.getPrice());
        model.setPictures(entity.getPictures());
        model.setReviews(entity.getReviews().stream().map(Review :: toModel).collect(Collectors.toList()));
        model.setHit(entity.isHit());
        return model;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
