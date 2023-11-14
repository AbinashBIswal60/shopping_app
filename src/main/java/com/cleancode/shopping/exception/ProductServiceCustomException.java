package com.cleancode.shopping.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{
    //private String errorCode;

    public ProductServiceCustomException(String message) {
        super(message);
        //this.errorCode = errorCode;
    }
}
