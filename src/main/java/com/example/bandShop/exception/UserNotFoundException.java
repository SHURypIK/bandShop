package com.example.bandShop.exception;

import java.sql.SQLException;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
    }
}
