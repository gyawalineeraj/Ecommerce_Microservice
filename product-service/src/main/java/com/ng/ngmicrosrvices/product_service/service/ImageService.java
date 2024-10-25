package com.ng.ngmicrosrvices.product_service.service;

import com.ng.ngmicrosrvices.product_service.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    @Value("${spring.application.imageFolder}")
    private String imageFolderPath;
    private final ProductRepository productRepository;

    public String saveImage(@NonNull MultipartFile image, @NonNull String vendorEmail,
                           int productId) {
        String vendorEmailName = getVendorEmailName(vendorEmail);
        final String finalFolderPath = imageFolderPath + separator + vendorEmailName;
        File targetFolder = new File(finalFolderPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
        }
        return uploadImageToServer(image,finalFolderPath,productId);

    }

    private String uploadImageToServer(@NonNull MultipartFile image, String finalFolderPath,
                                     int productId) {
        String fileExtenstion = getFileExtenstion(image.getOriginalFilename());
        String finalFilePath = finalFolderPath + separator + productId + "." + fileExtenstion;
        Path targetPath = Paths.get(finalFilePath);
        try {
            Files.write(targetPath,image.getBytes());
            return finalFilePath;
        } catch (IOException e) {
            throw new RuntimeException("Could not save the product Image , Please Try again");
        }

    }

    private String getFileExtenstion(String originalFilename) {
        int lastIndexOf = originalFilename.lastIndexOf(".");
        return originalFilename.substring(lastIndexOf + 1);
    }

    private String getVendorEmailName(String vendorEmail) {

        int lastIndexof = vendorEmail.lastIndexOf("@");
        return vendorEmail.substring(0,lastIndexof);
    }


    public byte[] getImage(String productImage) {
        if(productImage == null){
            return null;
        }
        try{
            Path path = Paths.get(productImage);
            return Files.readAllBytes(path);
        }catch (IOException exception){
            log.warn("There is a IO error ");
            return null;

        }

    }
}
