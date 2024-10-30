package com.ng.ngmicrosrvices.ng_ecommerce.httpclient;

import com.ng.library.dto.UserProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "cart-service",url = "http://localhost:8001/cart")
public interface CartServiceClient {

    @PutMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestHeader int productId,
                                       @RequestHeader String userEmail);

    @PutMapping("/increase-product-in-cart")
    public ResponseEntity<?> increaseTheProductInCart(@RequestHeader int productId,
                                                      @RequestHeader String userEmail);

    @PutMapping("/decrease-product-in-cart")
    public ResponseEntity<?> decreaseTheProductInCart(@RequestHeader int productId,
                                                      @RequestHeader String userEmail);


    @PutMapping("/get-products-in-cart")
    public List<UserProductDto> getProductsinCart(String userEmail);

}
