package com.ng.ngmicrosrvices.cart_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ng.library.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProduct extends BaseEntity {


    private String email;

    private int quantity;

    private int productId;

    @ManyToOne
    @JsonIgnore
    private Cart cart;
}
