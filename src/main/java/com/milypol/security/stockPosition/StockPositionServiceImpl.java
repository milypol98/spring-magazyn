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
}
