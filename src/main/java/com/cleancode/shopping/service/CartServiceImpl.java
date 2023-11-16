package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.CartDetailPK;
import com.cleancode.shopping.entity.CartDetails;
import com.cleancode.shopping.entity.Product;
import com.cleancode.shopping.exception.ProductServiceCustomException;
import com.cleancode.shopping.exception.ShoppingCustomException;
import com.cleancode.shopping.payload.CartDetailsResponse;
import com.cleancode.shopping.payload.ProductResponse;
import com.cleancode.shopping.repository.CartRepository;
import com.cleancode.shopping.repository.ProductRepository;
import com.cleancode.shopping.repository.UserRepostory;
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

    private final Logger log = LoggerFactory.getLogger("general");
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepostory userRepostory;

    @Override
    public CartDetailsResponse viewCart(long userId) {

        log.info("CartServiceImpl | viewCart for userId: "+ userId);

        List<CartDetails> cartDetailsList = cartRepository.findByUserId(userId);

        if(cartDetailsList == null || cartDetailsList.size() == 0){
            throw new ShoppingCustomException("User with given Id not found","USER_NOT_FOUND");
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

        if(cartDetails.getQuantity() <= 0){
            throw new ProductServiceCustomException("Product quantity can't be less than 1.","INVALID_PRODUCT_QUANTITY");
        }

        if(userRepostory.existsById(cartDetails.getUserId()) && productRepository.existsById(cartDetails.getProductId())){

            cartRepository.save(cartDetails);

            log.info("CartServiceImpl | addToCart | cartDetails :" + cartDetails);
            return "Product has been added to your cart Successfully.";
        }else {
            throw new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND");            //Obviously user will be there.
        }

    }

    @Override
    public String reduceQuantityOfAProduct(long userId, long productId) {
        log.info("Reduce Quantity for user " +userId+" for productId: "+productId);

        CartDetailPK cartDetailPK = CartDetailPK.builder()
                .userId(userId)
                .productId(productId).build();

        CartDetails cartDetails
                = cartRepository.findById(cartDetailPK)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found","PRODUCT_NOT_FOUND"));

        if(cartDetails.getQuantity() == 1) {
            cartRepository.deleteById(cartDetailPK);
            log.info( "Removed the product from cart.");
            return "removed the product: " +productId+" from cart as product quantity was 1.";
        } else {
            cartDetails.setQuantity(cartDetails.getQuantity() - 1);
            cartRepository.save(cartDetails);
            log.info("Product Quantity updated Successfully");
            return "Product Quantity has been updated for cart Successfully";
        }

    }

    @Override
    public String removeAProduct(long userId, long productId) {
        log.info("Remove product "+productId +" for user: "+userId);

        CartDetailPK cartDetailPK = CartDetailPK.builder()
                .userId(userId)
                .productId(productId).build();

        if(!cartRepository.existsById(cartDetailPK)){
            throw new ProductServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: "+ productId);
        cartRepository.deleteById(cartDetailPK);

        return  "Product has been removed from cart: " + productId;
    }

    @Override
    public String removeAllFromCart(long userId) {

        log.info("Remove all products from cart for user: " + userId);

        List<CartDetails> cartProductList = cartRepository.findByUserId(userId);

        if(cartProductList == null || cartProductList.size() ==0){
            throw new ProductServiceCustomException("No products in the cart.","EMPTY_CART");
        }

        log.info("Removing Products from cart ");
        long noOfProductsRemoved = cartRepository.deleteByUserId(userId);
        /*
        for (CartDetails cartProduct:cartProductList) {
            CartDetailPK cartDetailPK = CartDetailPK.builder()
                    .userId(userId)
                    .productId(cartProduct.getProductId()).build();

            cartRepository.deleteById(cartDetailPK);
            log.info("Product has been removed from cart: " + cartProduct.getProductId());
        }

         */

        return  noOfProductsRemoved+" Products have been removed from cart. Your cart is empty now.";
    }
}
