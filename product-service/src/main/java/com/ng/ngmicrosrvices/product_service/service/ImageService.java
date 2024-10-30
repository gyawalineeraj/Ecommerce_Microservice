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

    public String saveImage(@NonNull byte[] image,
                            @NonNull String vendorEmail,
                            @NonNull Integer productId,
                            @NonNull String fileExtension ) {
        String vendorEmailName = getVendorEmailName(vendorEmail);
        final String finalFolderPath = imageFolderPath + separator + vendorEmailName;
        File targetFolder = new File(finalFolderPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
        }
        return uploadImageToServer(image,finalFolderPath,productId,fileExtension);

    }

    private String uploadImageToServer( byte[] image, String finalFolderPath,
                                     int productId,String fileExtension) {
        String finalFilePath = finalFolderPath + separator + productId + "." + fileExtension;
        Path targetPath = Paths.get(finalFilePath);
        try {
            Files.write(targetPath,image);
            return finalFilePath;
        } catch (IOException e) {
            throw new RuntimeException("Could not save the product Image , Please Try again");
        }

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
