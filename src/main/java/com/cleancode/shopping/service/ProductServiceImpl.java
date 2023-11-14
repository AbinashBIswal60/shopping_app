package com.cleancode.shopping.service;

import com.cleancode.shopping.exception.ProductServiceCustomException;
import com.cleancode.shopping.entity.Product;
import com.cleancode.shopping.exception.UserServiceCustomException;
import com.cleancode.shopping.payload.ProductRequest;
import com.cleancode.shopping.payload.ProductResponse;
import com.cleancode.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger("general");

    @Override
    public String addProduct(ProductRequest productRequest) {
        log.info("ProductServiceImpl | addProduct is called");

        Product product
                = Product.builder()
                .productName(productRequest.getProductName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        try{
            product = productRepository.save(product);

            log.info("ProductServiceImpl | addProduct | Product Id : " + product.getProductId());
            return "Product has been added with Product Id : " + product.getProductId();
        }catch (DataIntegrityViolationException de){
            String errorMsg = de.getMessage().toLowerCase();

            String value = "";
            String regex = "duplicate entry .{10,30}product\\.([^']+)\\'";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;
            if((matcher = pattern.matcher(errorMsg))!=null && matcher.find()){
                value = matcher.group(1);
                throw new ProductServiceCustomException("Product already exist with same: "+value,"DUPLICATE_ENTRY");
            }

            log.info("Exception occured." + Arrays.toString(de.getStackTrace()));
            throw new ProductServiceCustomException(errorMsg,"INVALID_ENTRY");
        }


    }

    @Override
    public ProductResponse getProductById(long productId) {

        log.info("ProductServiceImpl | getProductById is called");
        log.info("ProductServiceImpl | getProductById | Get the product for productId: "+ productId);

        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                = new ProductResponse();

        copyProperties(product, productResponse);

        log.info("ProductServiceImpl | getProductById | productResponse :" + productResponse);

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("ProductServiceImpl | getAllProducts is called");

        List<Product> products = productRepository.findAll();

        if(products == null || products.size() == 0){
            throw new ProductServiceCustomException("No products found in our Inventory.","PRODUCT_NOT_FOUND");
        }else {
            List<ProductResponse> productList = new ArrayList<>();
            for (Product product : products) {
                ProductResponse productResponse
                        = new ProductResponse();

                copyProperties(product, productResponse);
                productList.add(productResponse);
            }
            log.info("ProductServiceImpl | getAllProducts | productList: " + productList);
            return productList;
        }

    }

    @Override
    public ProductResponse getProductByName(String productName) {
        log.info("ProductServiceImpl | getProductByName is called");
        log.info("ProductServiceImpl | getProductByName | Get the product for productName: "+ productName);

        Product product
                = productRepository.findByProductName(productName);

        ProductResponse productResponse
                = new ProductResponse();

        copyProperties(product, productResponse);

        log.info("ProductServiceImpl | getProductByName | productResponse :" + productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {

        log.info("Reduce Quantity " +quantity+" for Id: "+productId);

        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found","PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity","INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }

    @Override
    public void deleteProductById(long productId) {
        log.info("Product id: "+ productId);

        if (!productRepository.existsById(productId)) {
            throw new ProductServiceCustomException(
                    "Product with given with Id: " + productId + " not found:","PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: "+ productId);
        productRepository.deleteById(productId);

    }
}
