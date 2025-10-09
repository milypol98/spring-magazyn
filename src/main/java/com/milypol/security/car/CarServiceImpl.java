package com.milypol.security.car;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Override
    public void applyQuickUpdate(CarUpdateDto dto) {
        Car car = carRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pojazdu o ID: " + dto.getId()));

        if (dto.getReview() != null) {
            car.setReview(dto.getReview());
        }
        if (dto.getInsured() != null) {
            car.setInsured(dto.getInsured());
        }
        if (dto.getTiresCheck() != null) {
            car.setTiresCheck(dto.getTiresCheck());
        }
        if (dto.getOilChangeCourse() != null) {
            car.setOilChangeCourse(dto.getOilChangeCourse());
        }
        if (dto.getTimingSystemCourse() != null) {
            car.setTimingSystemCourse(dto.getTimingSystemCourse());
        }
        if (dto.getReminderDate() != null) {
            car.setReminderDate(dto.getReminderDate());
        }
        if (dto.getReminderDateDescription() != null) {
            car.setReminderDateDescription(dto.getReminderDateDescription());
        }
        if (dto.getReminderKm() != null) {
            car.setReminderKm(dto.getReminderKm());
        }
        if (dto.getReminderKmDescription() != null) {
            car.setReminderKmDescription(dto.getReminderKmDescription());
        }

        carRepo.save(car);
    }


}
