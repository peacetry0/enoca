package com.peace.enoca.service;


import com.peace.enoca.dto.request.product.StockRequest;
import com.peace.enoca.model.Cart;
import com.peace.enoca.model.Customer;
import com.peace.enoca.model.Product;
import com.peace.enoca.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {


     private final CartRepository cartRepository ;
     private final CustomerService customerService ;
     private final ProductService productService ;


    public CartService(CartRepository cartRepository, CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public Cart createCartForCustomer(Long customerId) {

        Customer customer = customerService.getCustomerById(customerId);

        Cart cart = new Cart();
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productService.getProductById(productId);

        StockRequest stockRequest = new StockRequest(quantity);
        productService.reduceStock(productId, stockRequest);

        Product existingItem = cart.getProducts().stream()
                .filter(item -> item.getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            product.setQuantity(quantity);
            cart.getProducts().add(product);
        }

        cart.setTotalPrice(getTotalPrice(cart));

        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product productToRemove = cart.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        StockRequest stockRequest = new StockRequest(productToRemove.getQuantity());
        productService.increaseStock(productId, stockRequest);

        cart.getProducts().remove(productToRemove);

        cart.setTotalPrice(getTotalPrice(cart));



        return cartRepository.save(cart);
    }

    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getProducts().forEach(product -> {
            StockRequest stockRequest = new StockRequest(product.getQuantity());
            productService.increaseStock(product.getId(), stockRequest);
        });

        cart.getProducts().clear();
        cart.setTotalPrice(BigDecimal.ZERO);

        cartRepository.save(cart);
    }
    public BigDecimal getTotalPrice(Cart cart) {
        return cart.getProducts().stream()
                .map(product -> product.getPrice().multiply(new BigDecimal(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
