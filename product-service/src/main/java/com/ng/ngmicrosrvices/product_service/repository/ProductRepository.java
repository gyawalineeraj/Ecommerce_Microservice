package com.ng.ngmicrosrvices.product_service.repository;


import com.ng.ngmicrosrvices.product_service.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("""
            SELECT p FROM Product p
            WHERE p.vendorEmail = :vendorEmail
            """)
    Page<Product> getVendorProducts(String vendorEmail, Pageable pageable);
    @Query("""
            SELECT p FROM Product p
            """)
    Page<Product> getUserProducts(Pageable pageable);;


    @Modifying
    @Query("""
            DELETE FROM Product p
            WHERE p.id = :productId
            """)
    void deleteProductById(int productId);
}
