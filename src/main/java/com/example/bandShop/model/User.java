package com.example.bandShop.model;

import com.example.bandShop.entity.UserEntity;

public class User {

    private int id;
    private String login;
    private String password;
    private String email;


    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());
        model.setPassword(entity.getPassword());
        model.setEmail(entity.getEmail());
        return model;
    }


    public User() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
