package com.milypol.security.warehouse;

import java.util.Optional;

public interface WarehouseService {
    Optional<Warehouse> findById(Integer id);
}
