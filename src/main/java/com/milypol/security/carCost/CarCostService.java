package com.milypol.security.carCost;

public interface CarCostService {
    CarCost getCarCostById(Integer id);
    CarCost saveCarCost(CarCost carCost);
    void deleteCarCost(Integer id);
}
