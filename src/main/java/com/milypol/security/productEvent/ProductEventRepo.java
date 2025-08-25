package com.milypol.security.productEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductEventRepo extends JpaRepository<ProductEvent, Integer> {

    List<ProductEvent> findAllByEventType(ProductEventType type);
    List<ProductEvent> findAllByProductPosition_Id(Integer productId);
    @Query(value = """
    SELECT 
        pe.product_id AS productId,
        SUM(CASE 
            WHEN pe.to_location_type = 'WAREHOUSE' THEN pe.quantity
            WHEN pe.from_location_type = 'WAREHOUSE' THEN -pe.quantity
            ELSE 0
        END) AS quantity
    FROM product_event pe
    GROUP BY pe.product_id
    """, nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInWarehouse();


    @Query(value = """
    SELECT
        pe.product_id AS productId,
        SUM(
          CASE
                    WHEN pe.to_location_type = 'CAR' THEN pe.quantity
                    WHEN pe.from_location_type = 'CAR' THEN -pe.quantity
                    ELSE 0
          END
        ) AS quantity
    FROM product_event pe
    GROUP BY pe.product_id
    """, nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInCar();

    List<ProductEvent> findByEventTypeAndToLocationTypeAndToLocationCodeOrderByTimestampDesc(
            ProductEventType eventType,
            LocationType toLocationType,
            Integer toLocationCode
    );

}
