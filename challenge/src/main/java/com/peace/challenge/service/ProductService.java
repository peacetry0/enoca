package com.peace.challenge.service;


import com.peace.challenge.convertor.ProductMapper;
import com.peace.challenge.dto.request.product.CreateProductRequest;
import com.peace.challenge.dto.request.product.UpdateProductRequest;
import com.peace.challenge.dto.response.product.ProductDTO;
import com.peace.challenge.exception.InsufficientStockException;
import com.peace.challenge.exception.ProductNotFoundException;
import com.peace.challenge.model.Product;
import com.peace.challenge.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {

    private final ProductRepository productRepository ;
    private final ProductMapper productMapper ;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts(){

        List<Product> products = productRepository.findAll() ;

        return products.stream()
                .map(productMapper::toProductDTO)
                .toList() ;
    }

    public ProductDTO getProductById(final Long id){

        final Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found")) ;


        return  productMapper.toProductDTO(product) ;
    }

    public ProductDTO addProduct(final CreateProductRequest createProductRequest){

        final Product product = productMapper.toProduct(createProductRequest) ;

        final Product saveProduct = productRepository.save(product) ;

        return productMapper.toProductDTO(saveProduct) ;
    }

    public ProductDTO updateProduct(final Long id,final UpdateProductRequest updateProductRequest){

        final Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found")) ;

        productMapper.updateEntityToProduct(product,updateProductRequest) ;

        return productMapper.toProductDTO(productRepository.save(product)) ;
    }

    public void deleteProductById(final Long id){

        if (productRepository.existsById(id)){

            productRepository.deleteById(id) ;
        }else {
            throw new ProductNotFoundException("Product not found") ;
        }
    }

     public boolean checkStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return product.getStock() >= quantity;
    }
    public void reduceStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() >= quantity) {

            product.setStock(product.getStock() - quantity);

            productRepository.save(product);
        } else {
            throw new InsufficientStockException("Not enough stock for product ID: " + productId);
        }
    }
}
