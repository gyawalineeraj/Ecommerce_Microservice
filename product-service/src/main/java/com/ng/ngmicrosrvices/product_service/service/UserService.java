package com.ng.ngmicrosrvices.product_service.service;


import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import com.ng.ngmicrosrvices.product_service.util.CustomMapper;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {


    private final ProductRepository productRepository;
    private final CustomMapper customMapper;


    public ProductPaginationResponse getUserProducts(int size, int page) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Product> userProducts = productRepository.getUserProducts(pageable);
        return customMapper.toProductPaginationResponse(userProducts);
    }

}
