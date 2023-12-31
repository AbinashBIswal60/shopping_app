package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.User;
import com.cleancode.shopping.exception.ShoppingCustomException;
import com.cleancode.shopping.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepostory userRepostory;

    private final Logger log = LoggerFactory.getLogger("general");


    @Override
    public String addUser(User user) {
        log.info("UserServiceImpl | addUser is called: ");

        user = userRepostory.save(user);

        log.info("UserServiceImpl | addUser | User Created with Id: " + user.getUserId());
        return "User: " + user.getUserName() + " has been added with User Id:" + user.getUserId();

    }

    @Override
    public String removeUser(long userId) {
        log.info("UserServiceImpl | removeUser is called: ");

        log.info("User id: "+ userId);

        if (!userRepostory.existsById(userId)) {
            throw new ShoppingCustomException(
                    "User with given with Id: " + userId + " not found:","USER_NOT_FOUND");
        }
        log.info("Deleting User with id: "+ userId);
        userRepostory.deleteById(userId);

        return  "User has been removed with User Id: " + userId;
    }

    @Override
    public User getUserById(long userId) {
        log.info("UserServiceImpl | getUserById is called");
        log.info("UserServiceImpl | getUserById | Get the product for productId: "+ userId);

        User user
                = userRepostory.findById(userId)
                .orElseThrow(
                        () -> new ShoppingCustomException("User with given Id not found","USER_NOT_FOUND"));
        
        log.info("UserServiceImpl | getProductById | User :" + user.toString());

        return user;
    }

}
