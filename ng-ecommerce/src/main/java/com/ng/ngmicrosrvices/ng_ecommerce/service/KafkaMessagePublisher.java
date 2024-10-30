package com.ng.ngmicrosrvices.ng_ecommerce.service;

import com.ng.library.event.AddProductEvent;
import com.ng.library.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, ProductEvent> template;
    private final KafkaTemplate<String, AddProductEvent> addProductEventKafkaTemplate;


    public ResponseEntity<?> sendDeleteProductMessage(ProductEvent productEvent) {
        CompletableFuture<SendResult<String, ProductEvent>> future =
                template.send("delete-product", productEvent);
        return future.thenApply((result) -> {
                    return ResponseEntity.ok("Delete message successfuly sent to broker");
                }).exceptionally((exception) -> {
                    return ResponseEntity.badRequest()
                            .body("Failed to send delete product message to broker");
                })
                .join();
    }

    public ResponseEntity<?> addProductMessage(AddProductEvent addProductEvent) {
        CompletableFuture<SendResult<String, AddProductEvent>> future =
                addProductEventKafkaTemplate.send("add-product", addProductEvent);
        return future.thenApply((result) -> {
                    return ResponseEntity.ok("ADd message successfuly sent to broker");
                }).exceptionally((exception) -> {
                    return ResponseEntity.badRequest()
                            .body("Failed to send Add product message to broker");
                })
                .join();
    }

}

