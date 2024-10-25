package com.ng.ngmicrosrvices.product_service.util;



import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.product_service.entities.Product;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Data
public class CustomMapper {

    private final ProductMapper productMapper;

    public ProductPaginationResponse toProductPaginationResponse(Page<Product> productPage) {
        return ProductPaginationResponse.builder()
                .productResponseDtos(
                        productPage.getContent().stream().map(p ->productMapper.toProductResponseDto(p))
                                .toList())
                .currentPageNumber(productPage.getNumber()+1)
                .totalPages(productPage.getTotalPages())
                .size(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .build();
    }


}
