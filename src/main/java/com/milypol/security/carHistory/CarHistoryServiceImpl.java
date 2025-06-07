package com.milypol.security.carHistory;

import org.springframework.stereotype.Service;

@Service
public class CarHistoryServiceImpl implements CarHistoryService{
    private final CarHistoryRepo carHistoryRepo;

    public CarHistoryServiceImpl(CarHistoryRepo carHistoryRepo) {
        this.carHistoryRepo = carHistoryRepo;
    }

    @Override
    public CarHistory getCarHistoryById(Integer id) {
        return carHistoryRepo.findById(id).orElseThrow(() -> new RuntimeException("CarHistory not found"));
    }

    @Override
    public CarHistory saveCarHistory(CarHistory carHistory) {
        return carHistoryRepo.save(carHistory);
    }

    @Override
    public void deleteCarHistory(Integer id) {
        carHistoryRepo.deleteById(id);
    }
}
