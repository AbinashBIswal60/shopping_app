package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.CartDetails;
import com.cleancode.shopping.entity.Product;
import com.cleancode.shopping.exception.UserServiceCustomException;
import com.cleancode.shopping.payload.CartDetailsResponse;
import com.cleancode.shopping.payload.ProductResponse;
import com.cleancode.shopping.repository.CartRepository;
import com.cleancode.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger("general");
    @Override
    public CartDetailsResponse viewCart(long userId) {

        log.info("CartServiceImpl | viewCart for userId: "+ userId);

        List<CartDetails> cartDetailsList = cartRepository.findByUserId(userId);

        if(cartDetailsList == null){
            throw new UserServiceCustomException("User with given Id not found","USER_NOT_FOUND");
        }else{
            final Double[] totalAmount = {0.0};
            HashMap<ProductResponse, Long> products = new HashMap<>();
            cartDetailsList.forEach(cartDetails -> {
                try{
                    Product product = productRepository.findById(cartDetails.getProductId()).get();
                    ProductResponse productResponse
                            = new ProductResponse();

                    copyProperties(product, productResponse);
                    products.put(productResponse,cartDetails.getQuantity());
                    totalAmount[0] = totalAmount[0] + productResponse.getPrice()*cartDetails.getQuantity();
                }catch (NoSuchElementException nse){
                    log.warn("Product with productId: " + cartDetails.getProductId() + " is not available in our Inventory.");
                }

            });
            CartDetailsResponse cartDetailsResponse
                    = CartDetailsResponse.builder()
                    .userId(userId)
                    .products(products)
                    .totalAmount(totalAmount[0]).build();

            log.info("CartServiceImpl | viewCart | cartDetailsResponse :" + cartDetailsResponse.toString());
            return cartDetailsResponse;
        }

    }

    @Override
    public String addToCart(CartDetails cartDetails) {
        log.info("CartServiceImpl | addToCart is called");

        cartRepository.save(cartDetails);

        log.info("CartServiceImpl | addToCart | cartDetails :" + cartDetails);
        return "Product has been added to your cart Successfully.";
    }

    @Override
    public String reduceQuantityOfAProduct(long userId, long productId) {
        return null;
    }

    @Override
    public String removeAProduct(long userId, long productId) {
        return null;
    }

    @Override
    public String removeAllFromCart(long userId) {
        return null;
    }
}
