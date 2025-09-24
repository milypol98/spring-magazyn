package com.milypol.security.productEvent;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductEventServiceImpl implements ProductEventService{
    private final ProductEventRepo productEventRepo;

    public ProductEventServiceImpl(ProductEventRepo productEventRepo) {
        this.productEventRepo = productEventRepo;
    }

    @Override
    public List<ProductEvent> getProductEventsByProduct(Integer id) {
        return productEventRepo.findAllByProductPosition_Id(id);
    }
    @Override
    public ProductEvent saveProductEvent(ProductEvent productEvent) {
        Integer productId = productEvent.getProduct().getId();
        if (productEvent.getFromLocationType() != null) {
            System.out.println(productEvent);
            switch (productEvent.getFromLocationType()) {
                case WAREHOUSE:
                    if (productEvent.getQuantity() > productEventRepo.countProductBalanceInWarehouses(productId) ) {
                        throw new IllegalArgumentException("Nie ma takiej ilości w magazynie");
                    }
                    break;

                case CAR:
                    if (productEvent.getQuantity() > productEventRepo.countProductBalanceInCar(productId, productEvent.getFromLocationCode())) {
                        throw new IllegalArgumentException("Nie ma takiej ilości na aucie");
                    }
                    break;
            }
        }
        return productEventRepo.save(productEvent);
    }

    @Override
    public Map<Integer, Long> getAllProductCountInWarehouse() {
        return productEventRepo.calculateProductBalanceInWarehouse().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));
    }

    @Override
    public Map<Integer, Long> getAllProductCountInWarehouseAndCar() {
        Map<Integer, Long> warehouseCount = getAllProductCountInWarehouse();
        Map<Integer, Long> carCount = productEventRepo.calculateProductBalanceInAllCar().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));

        carCount.forEach((key, value) ->
                warehouseCount.merge(key, value, Long::sum));

        return warehouseCount;
    }

    @Override
    public Map<Integer, Long> getAllProductCountInCar(Integer carId) {
        return productEventRepo.calculateProductBalanceInCar(carId).stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));
    }

    @Override
    public List<ProductEvent> findUsedForTask(Integer taskId) {
        if (taskId == null) return List.of();
        return productEventRepo.findByEventTypeAndToLocationTypeAndToLocationCodeOrderByTimestampDesc(
                ProductEventType.USED,
                LocationType.TASK,
                taskId
        );
    }
}