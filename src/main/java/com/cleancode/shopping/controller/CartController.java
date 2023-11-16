package com.cleancode.shopping.controller;

import com.cleancode.shopping.entity.CartDetails;
import com.cleancode.shopping.payload.CartDetailsResponse;
import com.cleancode.shopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private  final CartService cartService;
    private final Logger log = LoggerFactory.getLogger("general");
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody CartDetails cartDetails) {

        log.info("CartController | addToCart is called");

        log.info("CartController | addToCart | cartDetails : " + cartDetails.toString());

        return new ResponseEntity<>(cartService.addToCart(cartDetails), HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<CartDetailsResponse> getCartDetails(@PathVariable long userid){
        log.info("CartController | getCartDetails is called");

        log.info("CartController | getCartDetails | viewCart for User : " + userid);

        return new ResponseEntity<>(cartService.viewCart(userid), HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<String> reduceQuantity(
            @PathVariable("productId") long productId,
            @RequestParam long userId
    ) {

        log.info("CartController | reduceQuantity is called");

        log.info("CartController | reduceQuantity | userId : " + userId);
        log.info("CartController | reduceQuantity | productId : " + productId);

        return new ResponseEntity<>(
                cartService.reduceQuantityOfAProduct(userId,productId),HttpStatus.OK);
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<String> removeProduct(
            @PathVariable("productId") long productId,
            @RequestParam long userId
    ){

        log.info("CartController | removeProduct is called");

        log.info("CartController | removeProduct | userId : " + userId);
        log.info("CartController | removeProduct | productId : " + productId);

        return new ResponseEntity<>(
                cartService.removeAProduct(userId,productId),HttpStatus.OK);
    }

    @DeleteMapping("/removeAllFromCart/{userId}")
    public ResponseEntity<String> removeAllFromCart(
            @PathVariable("userId") long userId
    ){

        log.info("CartController | removeAllFromCart is called");

        log.info("CartController | removeAllFromCart | userId : " + userId);

        return new ResponseEntity<>(
                cartService.removeAllFromCart(userId),HttpStatus.OK);
    }

}
