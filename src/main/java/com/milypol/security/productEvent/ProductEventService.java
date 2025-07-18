package com.milypol.security.productEvent;

import java.util.List;
import java.util.Map;

public interface ProductEventService {
    ProductEvent getProductEventById(Integer id);
    ProductEvent saveProductEvent(ProductEvent productEvent);
    void deleteProductEvent(Integer id);
    List<ProductEvent> getAllProductByType(ProductEventType type);
    Map<Integer, Long> getProductCountInWarehouse();
    Map<Integer, Long> getProductCountInWarehouseAndCar();
}
