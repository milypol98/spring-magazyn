package com.milypol.security.carCost;

import com.milypol.security.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarCostRepo extends JpaRepository<CarCost, Integer> {
}
