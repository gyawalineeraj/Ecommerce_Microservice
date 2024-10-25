package com.ng.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private int id;
    private String productName;
    private byte[] productImage;
    private double price;
    private String description;
    private int inventoryLevel;
    private List<String> productCateogriesList;
}
