package com.peace.challenge.controllers;


import com.peace.challenge.dto.request.product.CreateProductRequest;
import com.peace.challenge.dto.request.product.UpdateProductRequest;
import com.peace.challenge.dto.response.product.ProductDTO;
import com.peace.challenge.model.Product;
import com.peace.challenge.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/products")

public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> productDTOS = productService.getAllProducts();

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable final Long id) {

        ProductDTO product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }
    @GetMapping("/check-stock/{id}/{quantity}")
    public ResponseEntity<String> checkStock(@PathVariable Long id, @PathVariable Integer quantity) {

        boolean isAvailable = productService.checkStock(id, quantity);

        return isAvailable ? ResponseEntity.ok("Stock is sufficient") :

                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock");
    }

    @PostMapping("/reduce-stock/{id}/{quantity}")
    public ResponseEntity<String> reduceStock(@PathVariable Long id, @PathVariable Integer quantity) {

        try {

            productService.reduceStock(id, quantity);
            return ResponseEntity.ok("Stock reduced successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid final CreateProductRequest createProductRequest) {

        ProductDTO productDTO = productService.addProduct(createProductRequest);

        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable final Long id, @RequestBody @Valid final UpdateProductRequest updateProductRequest) {

        ProductDTO productDTO = productService.updateProduct(id, updateProductRequest);

        return ResponseEntity.ok(productDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable final Long id) {

        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }
}

