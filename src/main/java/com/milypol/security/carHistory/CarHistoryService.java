package com.milypol.security.carHistory;

public interface CarHistoryService {
    CarHistory getCarHistoryById(Integer id);
    CarHistory saveCarHistory(CarHistory carHistory);
    void deleteCarHistory(Integer id);
}
