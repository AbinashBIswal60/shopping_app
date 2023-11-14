package com.cleancode.shopping.controller;

import com.cleancode.shopping.entity.User;
import com.cleancode.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;
    private final Logger log = LoggerFactory.getLogger("general");

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {

        log.info("UserController | addUser is called");

        log.info("UserController | addUser | user : " + user.toString());

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {

        log.info("UserController | getUserById is called");

        log.info("UserController | getUserById | userId : " + id);

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long userId) {
        userService.removeUser(userId);
    }
}
