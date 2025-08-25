package com.milypol.security.warehouse;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService{
    private final WarehouseRepo warehouseRepo;

    public WarehouseServiceImpl(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public Optional<Warehouse> findById(Integer id) {
        return warehouseRepo.findById(id);
    }
}
