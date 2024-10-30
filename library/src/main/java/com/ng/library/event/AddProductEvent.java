package com.ng.library.event;

import com.ng.library.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductEvent {

    private ProductDto productDto;
    private String base64Image;
    private String vendorEmail;
    private String imageFileExtension;
}
