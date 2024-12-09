package com.peace.enoca.controller;

import com.peace.enoca.dto.request.product.CreateProductRequest;
import com.peace.enoca.dto.request.product.StockRequest;
import com.peace.enoca.dto.request.product.UpdateProductRequest;
import com.peace.enoca.model.Product;
import com.peace.enoca.service.ProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> product = productService.getAllProducts();

        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final Long id) {

        Product product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/check-stock/{id}")
    public ResponseEntity<Integer> checkStock(@PathVariable final Long id) {


        final Integer stock = productService.checkStock(id);

        return ResponseEntity.ok(stock);


    }

    @PutMapping("/reduce-stock/{id}")
    public ResponseEntity<Product> reduceStock(@PathVariable final Long id, @RequestBody @Valid final StockRequest stockRequest) {

        Product product =  productService.reduceStock(id, stockRequest);

        return ResponseEntity.ok(product);

    }

    @PutMapping("/increase-stock/{id}")
    public ResponseEntity<Product> increaseStock(@PathVariable final Long id, @RequestBody @Valid final StockRequest stockRequest) {

        Product product =  productService.increaseStock(id, stockRequest);

        return ResponseEntity.ok(product);
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid final CreateProductRequest createProductRequest) {

        Product product = productService.addProduct(createProductRequest);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable final Long id, @RequestBody @Valid final UpdateProductRequest updateProductRequest) {

        Product product = productService.updateProduct(id, updateProductRequest);

        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable final Long id) {

        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }
}