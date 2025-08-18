package com.milypol.security.productEvent;

import com.milypol.security.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductEventServiceImpl implements ProductEventService{
    private final ProductEventRepo productEventRepo;
    private final ProductService productService;

    public ProductEventServiceImpl(ProductEventRepo productEventRepo, ProductService productService) {
        this.productEventRepo = productEventRepo;
        this.productService = productService;
    }

    @Override
    public ProductEvent getProductEventById(Integer id) {
        return productEventRepo.findById(id).orElseThrow(() -> new RuntimeException("ProductEvent not found"));
    }

    @Override
    public ProductEvent saveProductEvent(ProductEvent productEvent) {
        return productEventRepo.save(productEvent);
    }

    @Override
    public Map<Integer, Long> getProductCountInWarehouse() {
        return productEventRepo.calculateProductBalanceInWarehouse().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));
    }

    @Override
    public Map<Integer, Long> getProductCountInWarehouseAndCar() {
        Map<Integer, Long> warehouseCount = getProductCountInWarehouse();
        Map<Integer, Long> carCount = productEventRepo.calculateProductBalanceInCar().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));

        carCount.forEach((key, value) ->
                warehouseCount.merge(key, value, Long::sum));

        return warehouseCount;
    }

    @Override
    public Map<Integer, Long> getProductCountInCar(Integer carId) {
        return productEventRepo.calculateProductBalanceInCar().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));
    }

    @Override
    @Transactional
    public void saveBulkFromCarEvents(Integer carId,
                                      Map<String, String> allParams,
                                      String typeString,
                                      String comment) {
        ProductEventType eventType = mapType(typeString);

        // opcjonalny targetCarId dla transferu auto->auto
        Integer targetCarId = parseIntOrNull(allParams.get("targetCarId"));
        if (eventType == ProductEventType.TRANSFER_CAR_TO_CAR) {
            // walidacja celu
            if (targetCarId == null || targetCarId.equals(carId)) {
                // brak prawidłowego auta docelowego – nic nie zapisujemy
                return;
            }
        }

        allParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("quantities[") && e.getKey().endsWith("]"))
                .forEach(e -> {
                    String key = e.getKey();
                    String productIdStr = key.substring("quantities[".length(), key.length() - 1);
                    Integer productId = parseIntOrNull(productIdStr);
                    Integer qty = parseIntOrNull(e.getValue());
                    if (productId == null || qty == null || qty == 0) {
                        return;
                    }

                    ProductEvent ev = new ProductEvent();
                    ev.setProduct(productService.getProductById(productId));
                    ev.setQuantity(qty);
                    ev.setComment(comment);
                    ev.setEventType(eventType);
                    applyLocationsForCarEvent(ev, carId, eventType, targetCarId);

                    productEventRepo.save(ev);
                });
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


    private Integer parseIntOrNull(String s) {
        try {
            return s == null ? null : Integer.valueOf(s);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private ProductEventType mapType(String type) {
        // DELIVERY nie jest dopuszczony z ekranu auta
        try {
            ProductEventType t = ProductEventType.valueOf(type);
            if (t == ProductEventType.DELIVERY) {
                return ProductEventType.TRANSFER; // fallback
            }
            return t;
        } catch (IllegalArgumentException ex) {
            return ProductEventType.TRANSFER;
        }
    }

    private void applyLocationsForCarEvent(ProductEvent ev,
                                           Integer sourceCarId,
                                           ProductEventType type,
                                           Integer targetCarId) {
        switch (type) {
            case TRANSFER -> {
                // magazyn -> auto
                ev.setFromLocationType(LocationType.WAREHOUSE);
                ev.setFromLocationCode(null);
                ev.setToLocationType(LocationType.CAR);
                ev.setToLocationCode(sourceCarId);
            }
            case RETURN -> {
                // auto -> magazyn
                ev.setFromLocationType(LocationType.CAR);
                ev.setFromLocationCode(sourceCarId);
                ev.setToLocationType(LocationType.WAREHOUSE);
                ev.setToLocationCode(null);
            }
            case USED, LOST, DELETE -> {
                // zmniejszenie stanu w aucie (bez celu)
                ev.setFromLocationType(LocationType.CAR);
                ev.setFromLocationCode(sourceCarId);
                ev.setToLocationType(null);
                ev.setToLocationCode(null);
            }
            case TRANSFER_CAR_TO_CAR -> {
                // auto -> auto
                ev.setFromLocationType(LocationType.CAR);
                ev.setFromLocationCode(sourceCarId);
                ev.setToLocationType(LocationType.CAR);
                ev.setToLocationCode(targetCarId);
            }
            default -> {
                // domyślnie traktuj jak transfer do auta
                ev.setFromLocationType(LocationType.WAREHOUSE);
                ev.setFromLocationCode(null);
                ev.setToLocationType(LocationType.CAR);
                ev.setToLocationCode(sourceCarId);
            }
        }
    }
}