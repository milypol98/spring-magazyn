package com.milypol.security.productEvent;

import java.util.List;
import java.util.Map;

public interface ProductEventService {
    ProductEvent getProductEventById(Integer id);
    ProductEvent saveProductEvent(ProductEvent productEvent);
    Map<Integer, Long> getProductCountInWarehouse();
    Map<Integer, Long> getProductCountInWarehouseAndCar();
    Map<Integer, Long> getProductCountInCar(Integer carId);

    void saveBulkFromCarEvents(Integer carId,
                               Map<String, String> allParams,
                               String type,
                               String comment);
    List<ProductEvent> findUsedForTask(Integer taskId);

}
