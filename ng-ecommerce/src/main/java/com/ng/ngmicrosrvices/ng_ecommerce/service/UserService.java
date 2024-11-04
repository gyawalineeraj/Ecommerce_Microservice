package com.ng.ngmicrosrvices.ng_ecommerce.service;

import com.ng.library.exception.CircuitBreakerException;
import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.ng_ecommerce.httpclient.CartServiceClient;
import com.ng.ngmicrosrvices.ng_ecommerce.httpclient.ProductServiceClient;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductServiceClient productServiceClient;
    private final CartServiceClient cartServiceClient;

//    @CircuitBreaker(name = "userGetProduct",fallbackMethod = "getProductFallback")
    @CircuitBreaker(name= "getUserProduct",fallbackMethod = "getProductFallback")
    public ProductPaginationResponse getProducts(int size, int page) {
        return productServiceClient.getUserProducts(size,page);
    }



    public ProductPaginationResponse getProductFallback(int size, int page,
                                                        CallNotPermittedException exception) {
        System.out.println("Fallback method has been called");
        throw new CircuitBreakerException("Is this the result");
    }



}
