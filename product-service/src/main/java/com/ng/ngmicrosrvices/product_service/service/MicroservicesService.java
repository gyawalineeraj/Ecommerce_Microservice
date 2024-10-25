package com.ng.ngmicrosrvices.product_service.service;


import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MicroservicesService {

    private final ProductRepository productRepository;
    public int getInventoryOfProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product with the " + productId + " does not exist"));
        return product.getInventoryLevel();
    }

}
