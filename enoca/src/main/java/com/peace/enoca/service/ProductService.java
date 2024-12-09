package com.peace.enoca.service;

import com.peace.enoca.convertor.ProductMapper;
import com.peace.enoca.dto.request.product.CreateProductRequest;
import com.peace.enoca.dto.request.product.StockRequest;
import com.peace.enoca.dto.request.product.UpdateProductRequest;
import com.peace.enoca.exception.InsufficientStockException;
import com.peace.enoca.exception.ProductNotFoundException;
import com.peace.enoca.model.Product;
import com.peace.enoca.repository.ProductRepository;
import jakarta.transaction.Transactional;
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

    public List<Product> getAllProducts(){

        List<Product> products = productRepository.findAll() ;

        return products ;
    }


    public Product getProductById(final Long id){

        final Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found")) ;


        return  product ;
    }


    public Product addProduct(final CreateProductRequest createProductRequest){

        final Product product = productMapper.toProduct(createProductRequest) ;

        final Product saveProduct = productRepository.save(product) ;

        return saveProduct ;
    }

    public Product updateProduct(final Long id,final UpdateProductRequest updateProductRequest){

        final Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found")) ;

        productMapper.updateEntityToProduct(product,updateProductRequest) ;

        return productRepository.save(product) ;
    }

    public void deleteProductById(final Long id){

        if (productRepository.existsById(id)){


            productRepository.deleteById(id) ;

        }else {
            throw new ProductNotFoundException("Product not found") ;
        }
    }

    public Integer checkStock(final Long id) {

        return  productRepository.findStockById(id)

                .orElseThrow(() -> new ProductNotFoundException("Product not found")) ;
    }

    @Transactional
    public Product reduceStock(final Long id, final StockRequest stockRequest) {

        final Product product = getProductById(id) ;

        if (product.getStock() < stockRequest.quantity()) {

            throw new InsufficientStockException("Not enough stock for product ID: " + id);
        }

        product.setStock(product.getStock() - stockRequest.quantity());

        productRepository.save(product);

        return productRepository.save(product) ;
    }

    @Transactional
    public Product increaseStock(final Long id, final StockRequest stockRequest) {

        final Product product = productRepository.findById(id)

                .orElseThrow(() -> new ProductNotFoundException("Product not found"));


        product.setStock(product.getStock() + stockRequest.quantity());



        return productRepository.save(product);
    }

}