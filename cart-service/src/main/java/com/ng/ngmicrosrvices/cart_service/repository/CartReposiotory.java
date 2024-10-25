package com.ng.ngmicrosrvices.cart_service.repository;


import com.ng.ngmicrosrvices.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartReposiotory extends JpaRepository<Cart,Integer> {


    Optional<Cart> findByUserEmail(String userEmail);
}
