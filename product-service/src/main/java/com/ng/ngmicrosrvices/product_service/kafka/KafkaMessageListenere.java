package com.ng.ngmicrosrvices.product_service.kafka;

import com.ng.library.dto.ProductDto;
import com.ng.library.event.ProductEvent;
import com.ng.library.util.ActionEnum;
import com.ng.ngmicrosrvices.product_service.entities.Product;
import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import com.ng.ngmicrosrvices.product_service.service.ImageService;
import com.ng.ngmicrosrvices.product_service.util.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KafkaMessageListenere {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private  final ImageService imageService;


    @KafkaListener(topics = "product")
    @Transactional
    public void deleteProduct(ProductEvent productEvent) {
        if (productEvent.getAction() == ActionEnum.DELETE) {
           Product product =
                    productRepository.findById(
                             productEvent.getProductId()).orElseThrow(() -> new RuntimeException("Could not find the product with the associated vendor Email and produtId"));
           productRepository.deleteProductById(productEvent.getProductId());

        }
    }

    @KafkaListener(topics = "add-product")
    public void addProduct(ProductDto productDto, MultipartFile image, String vendorEmail) {
        Product product = productMapper.toProduct(productDto,vendorEmail);
        String imagePath = imageService.saveImage(image, vendorEmail, product.getId());
        product.setProductImage(imagePath);
        productRepository.save(product);
    }
}
