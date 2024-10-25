package com.ng.library.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse {

    private  String message;
    private Object object;
}
