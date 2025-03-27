package com.example.bandShop.model;

import com.example.bandShop.entity.ProductEntity;
import com.example.bandShop.entity.ReviewEntity;

public class ProductCardCart {

        private String id;
        private String title;
        private double price;
        private double avgGrade;
        private String pictures;
        private String storage;
        private boolean hit;
        private int amount;

        public static ProductCardCart toModel(ProductEntity entity, int amount){
            ProductCardCart model = new ProductCardCart();
            model.setId(entity.getId());
            if(entity.getStrorage() > 0)
                model.setStorage("Есть на складе");
            else
                model.setStorage("Нет на складе");
            double avgGrade = 0;
            int am = 0;
            if (entity.getReviews().isEmpty()){
                model.setAvgGrade(-1);
            }else{
            for(ReviewEntity r : entity.getReviews()){
                avgGrade += r.getGrade();
                am++;
            }
            avgGrade /= am;
            avgGrade*=10;
            avgGrade=Math.round(avgGrade);
            model.setAvgGrade(avgGrade/10);
            }
            model.setTitle(entity.getTitle());
            model.setPrice(entity.getPrice());
            model.setPictures(entity.getPictures().get(0));
            model.setHit(entity.isHit());
            model.setAmount(amount);
            return model;
        }

        public boolean isHit() {
            return hit;
        }

        public void setHit(boolean hit) {
            this.hit = hit;
        }

        public ProductCardCart() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAmount() {
        return amount;
    }

        public void setAmount(int amount) {this.amount = amount;
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
