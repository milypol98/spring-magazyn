package com.milypol.security.car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Integer id);
    Car saveCar(Car car);
    void deleteCar(Integer id);
    Car updateCar(Integer id, Car updatedCar);

}
