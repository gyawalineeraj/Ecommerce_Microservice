package com.ng.ngmicrosrvices.product_service.kafka;

import com.ng.library.dto.ProductDto;
import com.ng.library.event.AddProductEvent;
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

import java.util.Base64;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KafkaMessageListenere {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private  final ImageService imageService;


    @KafkaListener(topics = "delete-product")
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
    @Transactional
    public void addProduct(AddProductEvent addProductEvent) {
        Product product = productMapper.toProduct(addProductEvent.getProductDto(),
                addProductEvent.getVendorEmail());
        byte[] image = Base64.getDecoder().decode(addProductEvent.getBase64Image());
        productRepository.save(product);
        String imagePath = imageService.saveImage(image,
                addProductEvent.getVendorEmail(), product.getId(),addProductEvent.getImageFileExtension());
        product.setProductImage(imagePath);
        productRepository.save(product);
    }
}
