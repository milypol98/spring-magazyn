package com.milypol.security.productEvent;

import java.util.List;
import java.util.Map;

public interface ProductEventService {
    List<ProductEvent> getProductEventsByProduct(Integer productId);
    ProductEvent saveProductEvent(ProductEvent productEvent);
    Map<Integer, Long> getAllProductCountInWarehouse();
    Map<Integer, Long> getAllProductCountInWarehouseAndCar();
    Map<Integer, Long> getAllProductCountInCar(Integer carId);
    List<ProductEvent> findUsedForTask(Integer taskId);

}
