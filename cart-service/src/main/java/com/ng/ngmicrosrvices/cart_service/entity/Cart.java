package com.ng.ngmicrosrvices.cart_service.entity;

import com.ng.library.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Cart extends BaseEntity {

    @Column(unique = true)
    private String userEmail;

    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
    private List<UserProduct> userProducts;



}
