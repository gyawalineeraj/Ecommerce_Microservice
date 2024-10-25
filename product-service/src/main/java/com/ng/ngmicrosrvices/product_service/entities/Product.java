package com.ng.ngmicrosrvices.product_service.entities;

import com.ng.library.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
public class Product extends BaseEntity {

    private String productName;
    private String productImage;
    private double price;
    private String description;
    private int inventoryLevel;
    @Version
    private int version;

    @ManyToMany
   private List<ProductCategory> productCategories;


    private String vendorEmail;



}
