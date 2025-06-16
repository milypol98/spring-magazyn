package com.milypol.security.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAllByStockPosition_Id(Integer stockPositionId);
}
