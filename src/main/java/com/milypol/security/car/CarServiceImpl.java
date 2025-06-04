package com.milypol.security.car;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService{

    private final CarRepo carRepo;

    public CarServiceImpl(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public Car saveCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepo.deleteById(id);
    }

    @Override
    public Car updateCar(Integer id, Car updatedCar) {
        return null;
    }
}
