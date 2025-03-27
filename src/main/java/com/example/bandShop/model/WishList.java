package com.example.bandShop.model;

import com.example.bandShop.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class WishList {

    private List<ProductCard> wishlist;

    public static WishList toModel(UserEntity entity){
        WishList model = new WishList();
        model.setWishlist(entity.getWishlist().stream().map(ProductCard :: toModel).collect(Collectors.toList()));
        return model;
    }

    public WishList() {
    }

    public List<ProductCard> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<ProductCard> wishlist) {
        this.wishlist = wishlist;
    }
}
