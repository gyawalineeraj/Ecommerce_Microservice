package com.ng.ngmicrosrvices.product_service.controller;


import com.ng.library.dto.ProductDto;
import com.ng.library.response.CommonResponse;
import com.ng.library.response.ProductPaginationResponse;
import com.ng.ngmicrosrvices.product_service.service.VendorService;
import com.ng.ngmicrosrvices.product_service.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorContoller {

    private final ProductMapper productMapper;
    private final VendorService vendorService;


    @GetMapping("/get-product")
    public ProductPaginationResponse getVendorProducts(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = true) int page,
            @RequestHeader String vendorEmail) {

        return vendorService.getVendorProducts(size, page,vendorEmail);
    }


}
