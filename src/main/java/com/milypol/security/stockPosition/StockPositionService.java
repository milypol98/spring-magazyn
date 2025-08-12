package com.milypol.security.stockPosition;

import java.util.List;

public interface StockPositionService {
    List<StockPosition> getAllStockPositions();
    StockPosition getStockPositionById(Integer id);
    StockPosition saveStockPosition(StockPosition stockPosition);
    void deleteStockPosition(Integer id);
    List<StockPosition> getAllStockPositionsByWarehouseId(Integer warehouseId);
}
