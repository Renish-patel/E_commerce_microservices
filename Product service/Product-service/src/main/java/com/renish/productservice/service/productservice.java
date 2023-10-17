package com.renish.productservice.service;

import com.renish.productservice.Dto.ProductRequest;
import com.renish.productservice.Dto.ProductResponse;
import com.renish.productservice.model.product;
import com.renish.productservice.repository.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class productservice {
    @Autowired
    private final Repository productRepository;
    private ProductResponse mapToProductResponse(product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public void createProduct(ProductRequest productRequest){
        product product = com.renish.productservice.model.product.builder().name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} is saved", product.getId());

    }

    public List<ProductResponse> getallProducte() {
    List<product> products = productRepository.findAll();
    return products.stream().map(this::mapToProductResponse).toList();
    }

}
