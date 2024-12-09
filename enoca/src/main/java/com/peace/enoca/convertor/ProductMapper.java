package com.peace.enoca.convertor;

import com.peace.enoca.dto.request.product.CreateProductRequest;
import com.peace.enoca.dto.request.product.StockRequest;
import com.peace.enoca.dto.request.product.UpdateProductRequest;
import com.peace.enoca.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(final CreateProductRequest createProductRequest){

        Product  product = new Product();
        product.setName(createProductRequest.name());
        product.setDescription(createProductRequest.description());
        product.setPrice(createProductRequest.price());
        product.setStock(createProductRequest.stock());
        product.setQuantity(createProductRequest.quantity());
        return product;
    }

    public Product updateEntityToProduct(final Product product, final UpdateProductRequest updateProductRequest){

        product.setName(updateProductRequest.name());
        product.setDescription(updateProductRequest.description());
        product.setPrice(updateProductRequest.price());
        product.setStock(updateProductRequest.stock());

        return product;
    }


    public Product toProduct(final Long id, final StockRequest stockRequest){

        Product product = new Product() ;
        product.setId(id);
        product.setStock(stockRequest.quantity());

        return product ;
    }


}
