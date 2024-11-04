package com.ng.ngmicrosrvices.ng_ecommerce.controller;

import com.ng.library.dto.ProductDto;
import com.ng.library.dto.ProductResponseDto;
import com.ng.library.dto.UserProductDto;
import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.ng_ecommerce.httpclient.CartServiceClient;
import com.ng.ngmicrosrvices.ng_ecommerce.httpclient.ProductServiceClient;
import com.ng.ngmicrosrvices.ng_ecommerce.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CartServiceClient cartServiceClient;
    private final ProductServiceClient productServiceClient;

    @GetMapping("/get-products")
    public ProductPaginationResponse getUserProducts(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = true) int page){
        return userService.getProducts(size,page);

    }

    @PutMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestHeader int productId,
                                       @RequestHeader String userEmail){
        return cartServiceClient.addToCart(productId,userEmail);
    }

    @PutMapping("/increase-product-in-cart")
    public ResponseEntity<?> increaseTheProductInCart(@RequestHeader int productId,
                                                      @RequestHeader String userEmail){
        return cartServiceClient.increaseTheProductInCart(productId,userEmail);
    }

    @PutMapping("/decrease-product-in-cart")
    public ResponseEntity<?> decreaseTheProductInCart(@RequestHeader int productId,
                                                      @RequestHeader String userEmail){
        return cartServiceClient.decreaseTheProductInCart(productId,userEmail);
    }


    @PutMapping("/get-products-in-cart")
    public List<ProductResponseDto> getProductsinCart(String userEmail){

        List<Integer> productIdListOfCart =
                cartServiceClient.getProductsinCart(userEmail).stream()
                .map((userProductDto -> userProductDto.getProductId())).toList();
        return productServiceClient.findProducts(productIdListOfCart);
    }





}
