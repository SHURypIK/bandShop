package com.example.bandShop.exception;

public class ProductAlreadyExistException extends Exception{
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
