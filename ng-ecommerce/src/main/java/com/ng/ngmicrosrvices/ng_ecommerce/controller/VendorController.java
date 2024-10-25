package com.ng.ngmicrosrvices.ng_ecommerce.controller;

import com.ng.library.dto.ProductDto;
import com.ng.library.event.AddProductEvent;
import com.ng.library.event.ProductEvent;
import com.ng.library.response.CommonResponse;
import com.ng.library.util.ActionEnum;
import com.ng.ngmicrosrvices.ng_ecommerce.service.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorController {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    @PostMapping("/delete-product")
    public ResponseEntity<?> deleteProduct(
            @RequestHeader int productId,
            @RequestHeader String vendorEmail) {
        ProductEvent productEvent = new ProductEvent(productId, ActionEnum.DELETE);
        return kafkaMessagePublisher.sendDeleteProductMessage(productEvent);
    }

    @PostMapping(value = "/add-product",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(@RequestPart ProductDto productDto,
                                        @RequestPart MultipartFile image,
                                        @RequestPart String vendorEmail){
        AddProductEvent event = new AddProductEvent(productDto,image,vendorEmail);
        return kafkaMessagePublisher.addProductMessage(event);

    }
}
