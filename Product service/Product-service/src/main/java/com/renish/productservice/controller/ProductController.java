package com.renish.productservice.controller;
import com.renish.productservice.Dto.ProductRequest;
import com.renish.productservice.Dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.renish.productservice.service.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final productservice ProductService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createproduct(@RequestBody ProductRequest productRequest){
           ProductService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
    return ProductService.getallProducte();
    }
}
