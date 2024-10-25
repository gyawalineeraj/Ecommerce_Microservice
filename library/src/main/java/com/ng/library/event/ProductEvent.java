package com.ng.library.event;


import com.ng.library.util.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEvent {

    private int productId;
    private ActionEnum action;

}
