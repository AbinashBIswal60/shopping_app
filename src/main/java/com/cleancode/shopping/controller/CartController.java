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

}
