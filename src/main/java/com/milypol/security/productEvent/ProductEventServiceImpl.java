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
    public ProductEvent getProductEventById(Integer id) {
        return productEventRepo.findById(id).orElseThrow(() -> new RuntimeException("ProductEvent not found"));
    }

    @Override
    public ProductEvent saveProductEvent(ProductEvent productEvent) {
        return productEventRepo.save(productEvent);
    }

    @Override
    public void deleteProductEvent(Integer id) {
        productEventRepo.deleteById(id);
    }

    @Override
    public List<ProductEvent> getAllProductByType(ProductEventType type) {
        return productEventRepo.findAllByType(type);
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
        return productEventRepo.calculateProductBalanceInWarehouseAndCar().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));
    }

    @Override
    public Map<Integer, Long> getProductCountInCar(Integer carId) {
        return productEventRepo.calculateProductBalanceInCar().stream()
                .collect(Collectors.toMap(
                        ProductEventCountProjection::getProductId,
                        ProductEventCountProjection::getQuantity
                ));

    }




}