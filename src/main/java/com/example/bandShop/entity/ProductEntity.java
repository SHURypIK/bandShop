package com.example.bandShop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductEntity {

    @Id
    private String id;
    private String title;
    private Double price;
    private String description;
    private boolean hit;
    private int strorage;
    private List<String> pictures;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ReviewEntity> reviews;

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public ProductEntity() {
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

    public int getStrorage() {
        return strorage;
    }

    public void setStrorage(int strorage) {
        this.strorage = strorage;
    }
}
