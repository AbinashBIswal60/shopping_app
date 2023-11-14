package com.cleancode.shopping.exception;

import com.cleancode.shopping.entity.User;
import lombok.Data;

@Data
public class UserServiceCustomException extends RuntimeException{
    public UserServiceCustomException(String message) {
        super(message);
        //this.errorCode = errorCode;
    }
}
