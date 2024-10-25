package com.ng.ngmicrosrvices.product_service.repository;



import com.ng.ngmicrosrvices.product_service.entities.ProductCategory;
import com.ng.ngmicrosrvices.product_service.util.ProductCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {

    Optional<ProductCategory> findByName(ProductCategoryEnum name);
}
