package com.example.bandShop.exception;

public class OrderNotFoundedException extends Exception{
    public OrderNotFoundedException(String message) {
        super(message);
    }
}
