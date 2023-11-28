package com.example.bandShop.exception;

public class CartEmptyException extends Exception{
    public CartEmptyException(String message) {
        super(message);
    }
}
