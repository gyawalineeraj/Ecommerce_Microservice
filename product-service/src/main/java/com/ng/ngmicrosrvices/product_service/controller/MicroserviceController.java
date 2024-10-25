package com.ng.ngmicrosrvices.product_service.controller;


import com.ng.ngmicrosrvices.product_service.service.MicroservicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/microservices/product-service")
@RequiredArgsConstructor
public class MicroserviceController {

    private final MicroservicesService microservicesService;

    @GetMapping("/getInventoryOfProduct")
    public int getInventoryOfProduct(int productId){
        return microservicesService.getInventoryOfProduct(productId);
    }
}
