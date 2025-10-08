package com.milypol.security.car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Integer id);
    void saveCar(Car car);
    void deleteCar(Integer id);

}
