package com.milypol.security.carCost;

import org.springframework.stereotype.Service;

@Service
public class CarCostServiceImpl implements CarCostService {
    private final CarCostRepo carCostRepo;

    public CarCostServiceImpl(CarCostRepo carCostRepo) {
        this.carCostRepo = carCostRepo;
    }

    @Override
    public CarCost getCarCostById(Integer id) {
        return carCostRepo.findById(id).orElseThrow(() -> new RuntimeException("CarCost not found"));
    }

    @Override
    public CarCost saveCarCost(CarCost carCost) {
        return carCostRepo.save(carCost);
    }

    @Override
    public void deleteCarCost(Integer id) {
        carCostRepo.deleteById(id);
    }
}
