package com.milypol.security.productEvent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEventRepo extends JpaRepository<ProductEvent, Integer> {
    List<ProductEvent> findAllByType(ProductEventType type);

    Integer countByTypeAndProduct_Id(ProductEventType type, Integer productId);
}
