package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.User;
import com.cleancode.shopping.payload.ProductResponse;

public interface UserService {
    String addUser(User user);
    String removeUser(long userId);

    User getUserById(long userId);


}
