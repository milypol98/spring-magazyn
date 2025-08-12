package com.milypol.security.stockPosition;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPositionServiceImpl implements StockPositionService{
    private final StockPositionRepo stockPositionRepo;

    public StockPositionServiceImpl(StockPositionRepo stockPositionRepo) {
        this.stockPositionRepo = stockPositionRepo;
    }

    @Override
    public List<StockPosition> getAllStockPositions() {
        return stockPositionRepo.findAll();
    }

    @Override
    public StockPosition getStockPositionById(Integer id) {
        return stockPositionRepo.findById(id).orElseThrow(() -> new RuntimeException("StockPosition not found"));
    }

    @Override
    public StockPosition saveStockPosition(StockPosition stockPosition) {
        return stockPositionRepo.save(stockPosition);
    }

    @Override
    public void deleteStockPosition(Integer id) {
        stockPositionRepo.deleteById(id);
    }

    @Override
    public List<StockPosition> getAllStockPositionsByWarehouseId(Integer warehouseId) {
        return stockPositionRepo.findAllByWarehouse_Id(warehouseId);
    }
}
