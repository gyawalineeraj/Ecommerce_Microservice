package com.ng.ngmicrosrvices.cart_service.repository;


import com.ng.ngmicrosrvices.cart_service.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserProductRepository extends JpaRepository<UserProduct,Integer> {

    @Query("""
            SELECT up FROM UserProduct up
            WHERE up.email = :userEmail
            AND up.productId = :productId
            """)
    Optional<UserProduct> findByUserEmailAndProductId(String userEmail,int productId);
}
