package com.ng.ngmicrosrvices.product_service.util;



import com.ng.ngmicrosrvices.product_service.entities.ProductCategory;
import com.ng.ngmicrosrvices.product_service.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class Misc {

    private final ProductCategoryRepository productCategoryRepository;


    public List<ProductCategory> toProductCategory(List<String> productCategoriesStringList) {

        List<ProductCategoryEnum> categoryEnums =
                Arrays
                        .stream(ProductCategoryEnum.values())
                        .toList();

        List<String> upperCaseNames =
                productCategoriesStringList
                        .stream()
                        .map(a -> a.toUpperCase())
                        .toList();

        List<ProductCategoryEnum> filteredEnum =
                categoryEnums
                        .stream()
                        .filter(e -> productCategoriesStringList.contains(e.name()))
                        .toList();

        List<ProductCategory> productCateogriesList = new LinkedList<>();

        filteredEnum.forEach((p) -> {
            try {

                ProductCategory productCateogry = productCategoryRepository.findByName(p).get();
                productCateogriesList.add(productCateogry);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        });
        return productCateogriesList;
    }
}
