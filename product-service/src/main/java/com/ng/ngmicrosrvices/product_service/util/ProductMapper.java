package com.ng.ngmicrosrvices.product_service.util;



import com.ng.library.dto.ProductDto;
import com.ng.library.dto.ProductResponseDto;

import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {


    private final Misc misc;
    private final ImageService imageService;

public Product toProduct(ProductDto productDto, String vendorEmail){
     return Product.builder()
             .productName(productDto.getProductName())
             .price(productDto.getPrice())
             .productCategories(misc.toProductCategory(productDto.getProductCateogriesList())) //Todo
             .description(productDto.getDescription())
             .inventoryLevel(productDto.getInventoryLevel())
             .vendorEmail(vendorEmail)
             .build();
 }


    public ProductResponseDto toProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .productName(product.getProductName())
                .inventoryLevel(product.getInventoryLevel())
                .productImage(imageService.getImage(product.getProductImage()))
                .build();
    }

}
