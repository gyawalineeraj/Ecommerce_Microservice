package com.ng.ngmicrosrvices.product_service.service;

import com.ng.library.dto.ProductResponseDto;
import com.ng.library.response.CommonResponse;
import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import com.ng.ngmicrosrvices.product_service.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public List<ProductResponseDto> findProducts(List<Integer> productIdlist) {
        final List<ProductResponseDto> productResponseDtoList = new LinkedList<>();
        List<CompletableFuture<Product>> completableFutureList =
                productIdlist.stream().map(i ->
                        CompletableFuture.supplyAsync(() -> productRepository.findById(i)
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "invalid product id " + i)))).toList();

        completableFutureList.stream().forEach((future -> {
            Product product = future.join();
            ProductResponseDto productResponseDto =
                    productMapper.toProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }));

        return productResponseDtoList;

    }
}
