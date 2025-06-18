package com.milypol.security.carCost;

import com.milypol.security.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarCostRepo extends JpaRepository<CarCost, Integer> {
}
