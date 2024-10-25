package com.ng.ngmicrosrvices.product_service.entities;


import com.ng.library.entities.BaseEntity;
import com.ng.ngmicrosrvices.product_service.util.ProductCategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum name;

    @ManyToMany(mappedBy = "productCategories",fetch = FetchType.LAZY)
    private List<Product> products;


}
