package com.milypol.security.productEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductEventRepo extends JpaRepository<ProductEvent, Integer> {
//Zliczanie dla magazynu
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
//Zliczanie dla magazynu
    @Query(value = """
SELECT
    pe.product_id AS productId,
    COALESCE(SUM(CASE WHEN pe.to_location_type = 'CAR' THEN pe.quantity ELSE 0 END), 0) -
    COALESCE(SUM(CASE WHEN pe.from_location_type = 'CAR' THEN pe.quantity ELSE 0 END), 0) AS quantity
FROM
    product_event pe
GROUP BY
    pe.product_id
""", nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInAllCar();
//Zliczanie dla danego auta
    @Query(value = """
SELECT
    pe.product_id AS productId,
    SUM(
      CASE
          WHEN pe.to_location_code = :carId AND pe.to_location_type = 'CAR' THEN pe.quantity
          WHEN pe.from_location_code = :carId AND pe.from_location_type = 'CAR' THEN -pe.quantity
          ELSE 0
      END
    ) AS quantity
FROM product_event pe
WHERE (pe.to_location_code = :carId OR pe.from_location_code = :carId)
GROUP BY pe.product_id
""", nativeQuery = true)
    List<ProductEventCountProjection> calculateProductBalanceInCar(@Param("carId") Integer carId);


//zliczanie danego magazynu na auto zeby nie dalo sei na minus zrobic
    @Query(value = """
    SELECT
        COALESCE(
            SUM(
              CASE
                  WHEN pe.to_location_type = 'WAREHOUSE' THEN pe.quantity
                  WHEN pe.from_location_type = 'WAREHOUSE' THEN -pe.quantity
                  ELSE 0
              END
            ), 0
        ) AS quantity
    FROM product_event pe
    WHERE pe.product_id = :productId
    """, nativeQuery = true)
    long countProductBalanceInWarehouses(@Param("productId") Integer productId);
//zliczanie danego produktu na auto zeby nie dalo sei na minus zrobic
    @Query(value = """
    SELECT
        COALESCE(
            SUM(
              CASE
            WHEN pe.to_location_code = :carId AND pe.to_location_type = 'CAR' THEN pe.quantity
          WHEN pe.from_location_code = :carId AND pe.from_location_type = 'CAR' THEN -pe.quantity
                  ELSE 0
              END
            ), 0
        ) AS quantity
    FROM product_event pe
    WHERE pe.product_id = :productId
    AND (pe.to_location_code = :carId or pe.from_location_code = :carId)
    """, nativeQuery = true)
    long countProductBalanceInCar(@Param("productId") Integer productId, @Param("carId") Integer carId);

    List<ProductEvent> findByEventTypeAndToLocationTypeAndToLocationCodeOrderByTimestampDesc(
            ProductEventType eventType,
            LocationType toLocationType,
            Integer toLocationCode
    );

}
