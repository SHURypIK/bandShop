package com.example.bandShop.model;

import com.example.bandShop.entity.UserEntity;

import java.util.Date;


public class PersonalInformation {

    private String firstName;
    private String lastName;
    private Date birthday;
    private String mobile;

    public static  PersonalInformation toModel(UserEntity entity){
        PersonalInformation model = new PersonalInformation();
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setBirthday(entity.getBirthday());
        model.setMobile(entity.getMobile());
        return model;
    }

    public PersonalInformation() {
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
