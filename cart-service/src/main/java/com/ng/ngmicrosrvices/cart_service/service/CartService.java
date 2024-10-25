package com.ng.ngmicrosrvices.cart_service.service;


import com.ng.ngmicrosrvices.cart_service.entity.Cart;
import com.ng.ngmicrosrvices.cart_service.entity.UserProduct;
import com.ng.ngmicrosrvices.cart_service.repository.CartReposiotory;
import com.ng.ngmicrosrvices.cart_service.repository.UserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartReposiotory cartReposiotory;
    private final UserProductRepository userProductRepository;



    public void addToCart(int productId, String userEmail)
            throws ExecutionException, InterruptedException {


        CompletableFuture<Optional<UserProduct>> optionalCompletableFutureUserProduct =
                CompletableFuture.supplyAsync(
                        () -> userProductRepository.findByUserEmailAndProductId(userEmail,
                                productId));

        CompletableFuture<Optional<Cart>> optionalCompletableFutureCart =
                CompletableFuture.supplyAsync(() -> cartReposiotory.findByUserEmail(userEmail));


        //if user does not have the cart, it is his first time , then we create his cart
        Optional<Cart> optionalCart = optionalCompletableFutureCart.get();
        final Cart cart;
        if (optionalCart.isEmpty()) {
            Cart newCart = new Cart(userEmail, null);
            cart = newCart;
            cartReposiotory.save(newCart);
        } else {
            cart = optionalCart.get();
        }

        Optional<UserProduct> userProduct = optionalCompletableFutureUserProduct.get();


        //if user has already added the product to cart previous time, we only increase
        //the quantity of the userProduct and return
        if (userProduct.isPresent()) {

            int quantity = userProduct.get().getQuantity();
            userProduct.get().setQuantity(++quantity);
            userProductRepository.save(userProduct.get());
            return;
        } else {
            //otherwise we create the product and add to the cart
            UserProduct newUserProduct = UserProduct.builder()
                    .quantity(1)
                    .email(userEmail)
                    .cart(cart)
                    .build();
            userProductRepository.save(newUserProduct);
        }


    }

    public void increaseTheProductInCart(int productId, String userEmail) {
        UserProduct userProduct = userProductRepository.findByUserEmailAndProductId(userEmail,
                productId).orElseThrow(() -> new RuntimeException("Please first add the product " +
                "the cart before increasing the quanity"));
        int quantity = userProduct.getQuantity();
        userProduct.setQuantity(++quantity);
        userProductRepository.save(userProduct);
    }

    public void decreaseTheProductInCart(int productId, String userEmail) {
        UserProduct userProduct = userProductRepository.findByUserEmailAndProductId(userEmail,
                productId).orElseThrow(() -> new RuntimeException("Please first add the product " +
                "the cart before increasing the quanity"));
        int quantity = userProduct.getQuantity();
        if(quantity == 0){
            throw new RuntimeException("The quantity of product in cart is 0");
        }else {
            userProduct.setQuantity(--quantity);
            userProductRepository.save(userProduct);
        }
    }

    public List<UserProduct> getProductsIncart(String userEmail) {

        Optional<Cart> optionalCart= cartReposiotory.findByUserEmail(userEmail);
        if(optionalCart.isEmpty()){
            return new ArrayList<>();
        }else {
            return optionalCart.get().getUserProducts();
        }


    }
}
