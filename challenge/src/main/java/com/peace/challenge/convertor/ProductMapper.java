package com.peace.challenge.convertor;


import com.peace.challenge.dto.request.product.CreateProductRequest;
import com.peace.challenge.dto.request.product.UpdateProductRequest;
import com.peace.challenge.dto.response.product.ProductDTO;
import com.peace.challenge.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public Product toProduct(final CreateProductRequest createProductRequest){

        Product  product = new Product();
        product.setName(createProductRequest.name());
        product.setDescription(createProductRequest.description());
        product.setPrice(createProductRequest.price());
        product.setStock(createProductRequest.stock());
        return product;
    }

    public Product updateEntityToProduct(final Product product, final UpdateProductRequest updateProductRequest){

        product.setName(updateProductRequest.name());
        product.setDescription(updateProductRequest.description());
        product.setPrice(updateProductRequest.price());
        product.setStock(updateProductRequest.stock());

        return product;
    }

    public ProductDTO toProductDTO(final Product product){
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
    public Product toEntity(final ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.id());
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        product.setStock(productDTO.stock());
        return product;
    }

}
