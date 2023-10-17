package com.renish.productservice.repository;

import com.renish.productservice.model.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<product, String> {

}
