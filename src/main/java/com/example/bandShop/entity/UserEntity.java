package com.example.bandShop.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String mobile;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductEntity> wishlist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ReviewEntity> reviews;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private CartEntity cart;


    @OneToMany (cascade = CascadeType.ALL)
    private  List<OrderEntity> orderHistory;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductEntity> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<ProductEntity> wishlist) {
        this.wishlist = wishlist;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }


    public List<OrderEntity> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderEntity> orderHistory) {
        this.orderHistory = orderHistory;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
