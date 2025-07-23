package com.milypol.security.productEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductEventRepo extends JpaRepository<ProductEvent, Integer> {
    List<ProductEvent> findAllByType(ProductEventType type);
    @Query(value = """
    SELECT 
        pe.product_id AS productId,
        SUM(CASE 
            WHEN pe.type IN ('DELIVERY','RETURN') THEN pe.quantity
            WHEN pe.type IN ('TRANSFER') THEN -pe.quantity
            ELSE 0
        END) AS quantity
    FROM product_event pe
    GROUP BY pe.product_id
    """, nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInWarehouse();
    @Query(value = """
    SELECT 
        pe.product_id AS productId,
        SUM(CASE 
            WHEN pe.type IN ('DELIVERY') THEN pe.quantity
            WHEN pe.type IN ( 'CONSUMPTION') THEN -pe.quantity
            ELSE 0
        END) AS quantity
    FROM product_event pe
    GROUP BY pe.product_id
    """, nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInWarehouseAndCar();
    @Query(value = """
    SELECT
        pe.product_id AS productId,
        SUM(
          CASE\s
            WHEN pe.type = 'TRANSFER' THEN pe.quantity
            WHEN pe.type IN ('RETURN', 'CONSUMPTION') THEN -pe.quantity
            ELSE 0
          END
        ) AS quantity
    FROM product_event pe
    GROUP BY pe.product_id
    """, nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInCar();

}

