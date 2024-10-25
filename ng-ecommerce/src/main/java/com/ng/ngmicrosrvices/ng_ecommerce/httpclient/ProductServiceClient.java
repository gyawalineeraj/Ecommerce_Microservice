package com.ng.ngmicrosrvices.ng_ecommerce.httpclient;


import com.ng.library.dto.ProductDto;

import com.ng.library.response.ProductPaginationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "Product-Service",url = "http://localhost:8000")
public interface ProductServiceClient {

    @PostMapping(value = "/add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> addProduct(@RequestPart("productDto") ProductDto productDto,
                                        @RequestPart("image") MultipartFile image,
                                        @RequestPart("vendorEmail") String vendorEmail) ;


    @GetMapping("/get-product")
    public ProductPaginationResponse getVendorProducts();
}
