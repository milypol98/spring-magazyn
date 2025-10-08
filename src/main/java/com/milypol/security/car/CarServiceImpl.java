package com.milypol.security.car;


import org.springframework.data.domain.Sort;
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
        return carRepo.findAll(Sort.by("id"));
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public void saveCar(Car car) {
        carRepo.save(car);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepo.deleteById(id);
    }




}
