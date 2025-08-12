package com.milypol.security.stockPosition;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

public interface StockPositionRepo extends JpaRepository<StockPosition, Integer> {
    List<StockPosition> findAllByWarehouse_Id(Integer warehouseId);
}
