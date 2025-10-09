package com.milypol.security.car;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Integer id);
    void saveCar(Car car);
    void deleteCar(Integer id);

    @Transactional
    void applyQuickUpdate(CarUpdateDto dto);
}
