package com.ng.ngmicrosrvices.ng_ecommerce.service;

import com.ng.library.exception.CircuitBreakerException;
import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.ng_ecommerce.httpclient.CartServiceClient;
import com.ng.ngmicrosrvices.ng_ecommerce.httpclient.ProductServiceClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductServiceClient productServiceClient;
    private final CartServiceClient cartServiceClient;

    @CircuitBreaker(name = "getUserProduct",fallbackMethod = "getProductFallback")
    @Retry(name = "product")
    public ProductPaginationResponse getProducts(int size, int page) {
        System.out.println("Method in ng ecommerce called");

        return productServiceClient.getUserProducts(size,page);
    }



    public ProductPaginationResponse getProductFallback(int size, int page,
                                                        Exception exception) {
        throw new CircuitBreakerException("Circuit  is Opened For the Moment " + exception.getLocalizedMessage()+ "    " + exception.getClass() + "    " + exception.getMessage());
    }



}
