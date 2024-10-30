package com.ng.ngmicrosrvices.product_service.controller;

import com.ng.library.dto.ProductDto;
import com.ng.library.dto.ProductResponseDto;
import com.ng.library.response.CommonResponse;
import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import com.ng.ngmicrosrvices.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/find-products")
    public List<ProductResponseDto> findProducts(List<Integer> productIdlist) {
        return productService.findProducts(productIdlist);
    }
}
