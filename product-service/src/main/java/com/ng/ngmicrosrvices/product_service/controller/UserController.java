package com.ng.ngmicrosrvices.product_service.controller;


import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.product_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-products")
    public ProductPaginationResponse getVendorProducts(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = true) int page) {

        return userService.getUserProducts(size,page);

    }




}
