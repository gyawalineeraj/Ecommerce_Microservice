package com.ng.ngmicrosrvices.product_service.service;


import com.ng.library.dto.ProductDto;
import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import com.ng.ngmicrosrvices.product_service.util.CustomMapper;
import com.ng.ngmicrosrvices.product_service.util.Misc;
import com.ng.ngmicrosrvices.product_service.util.ProductMapper;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Data
@Service
public class VendorService {

    private final ProductRepository productRepository;
    private final Misc misc;
    private final ImageService imageService;
    private final ProductMapper productMapper;
    private final CustomMapper customMapper;





    public void deleteProduct(int productId, int deleteInventorySize) {
        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id"));
        if (deleteInventorySize > product.getInventoryLevel()) {
            throw new IllegalArgumentException("Invalid inventory Deletion size, You don't have " +
                    "sufficent inventory");

        }
        product.setInventoryLevel(product.getInventoryLevel() - deleteInventorySize);
        productRepository.save(product);
    }


    public ProductPaginationResponse getVendorProducts(int size, int page, String vendorEmail) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> vendorProducts = productRepository.getVendorProducts(vendorEmail, pageable);
        return customMapper.toProductPaginationResponse(vendorProducts);

    }

}
