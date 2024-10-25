package com.ng.library.event;

import com.ng.library.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class AddProductEvent {

    private ProductDto productDto;
    private MultipartFile image;
    private String vendorEmail;
}
