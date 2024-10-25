package com.ng.ngmicrosrvices.cart_service.controller;


import com.ng.ngmicrosrvices.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PutMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestHeader int productId,
                                       @RequestHeader String userEmail)
            throws ExecutionException, InterruptedException {
        cartService.addToCart(productId,userEmail);
        return ResponseEntity.ok("Product Added The Cart Successfully");
    }

    @PutMapping("/increase-product-in-cart")
    public ResponseEntity<?> increaseTheProductInCart(@RequestHeader int productId,
                                                      @RequestHeader String userEmail){
        cartService.increaseTheProductInCart(productId,userEmail);
        return ResponseEntity.ok("Product Quantity increased in the cart successfully");
    }
    @PutMapping("/decrease-product-in-cart")
 public ResponseEntity<?> decreaseTheProductInCart(@RequestHeader int productId,
                                                      @RequestHeader String userEmail){
        cartService.decreaseTheProductInCart(productId,userEmail);
        return ResponseEntity.ok("Product Quantity decreased  in the cart successfully");
    }
    @PutMapping("/get-products-in-cart")
    public List getProductsinCart(String userEmail){
        return cartService.getProductsIncart(userEmail);
    }


}
