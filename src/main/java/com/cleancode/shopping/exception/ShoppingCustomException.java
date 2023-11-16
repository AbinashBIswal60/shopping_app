package com.cleancode.shopping.exception;

import lombok.Data;

@Data
public class ShoppingCustomException extends RuntimeException{

    private String errorCode;
    public ShoppingCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
