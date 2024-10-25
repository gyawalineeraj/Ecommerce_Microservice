package com.ng.ngmicrosrvices.cart_service.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "",path = "http://locahhost:8000")
public interface ProductServiceProxy {

    @GetMapping("/getInventoryOfProduct")
    public int getInventoryOfProduct(int productId);
}
