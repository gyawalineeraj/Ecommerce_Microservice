package com.ng.library.response;


import com.ng.library.dto.ProductResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPaginationResponse {

    private List<ProductResponseDto> productResponseDtos;
    private long totalElements;
    private int totalPages;
    private int currentPageNumber;
    private int size;
}
