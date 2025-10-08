package com.milypol.security.car;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



public class CarStatusEvaluator {

    public CarStatus evaluate(Car car) {
        if (isCritical(car)) return CarStatus.CRITICAL;
        if (isWarning(car)) return CarStatus.WARNING;
        return CarStatus.NORMAL;
    }
    private boolean isCritical(Car car) {

        if (isIntervalExceeded(car.getCourse(), car.getTimingSystemCourse(),
                car.getTimingSystemKmCritical(), car.getTimingSystemIntervalKm())) return true;
        if (isIntervalExceeded(car.getCourse(), car.getOilChangeCourse(),
                car.getOilChangeIntervalKmCritical(), car.getOilChangeIntervalKm())) return true;

        if (isOver(car.getCourse(), car.getReminderKm(), car.getReminderKmCritical())) return true;

        if (isDateOver(car.getReview(), car.getReviewCriticalDay())) return true;
        if (isDateOver(car.getInsured(), car.getInsuredCriticalDay())) return true;
        if (isDateOver(car.getTiresCheck(), car.getTiresCheckCriticalDay())) return true;
        if (isDateOver(car.getReminderDate(), car.getReminderDateCriticalDay())) return true;

        return false;
    }
    private boolean isWarning(Car car) {
        if (isIntervalExceeded(car.getCourse(), car.getTimingSystemCourse(),
                car.getTimingSystemKmWarning(), car.getTimingSystemIntervalKm())) return true;
        if (isIntervalExceeded(car.getCourse(), car.getOilChangeCourse(),
                car.getOilChangeIntervalKmWarning(), car.getOilChangeIntervalKm())) return true;

        if (isOver(car.getCourse(), car.getReminderKm(), car.getReminderKmWarning())) return true;

        if (isDateOver(car.getReview(), car.getReviewWarningDay())) return true;
        if (isDateOver(car.getInsured(), car.getInsuredWarningDay())) return true;
        if (isDateOver(car.getTiresCheck(), car.getTiresCheckWarningDay())) return true;
        if (isDateOver(car.getReminderDate(), car.getReminderDateWarningDay())) return true;
        return false;
    }
    private boolean isIntervalExceeded(Integer currentMileage, Integer lastChangeMileage, Integer limit, Integer interval) {
        if (currentMileage == null || lastChangeMileage == null || limit == null || interval == null) return false;
        int distance = interval - (currentMileage - lastChangeMileage);
        return distance <= limit;
    }
    private boolean isOver(Integer currentMileage, Integer reminderKm, Integer limit) {
        if (currentMileage == null || limit == null || reminderKm == null) return false;
        int distance = reminderKm - currentMileage;
        return distance <= limit;
    }
    private boolean isDateOver(LocalDate date, Integer limit) {
        if (date == null || limit == null) return false;
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), date);
        return daysLeft <= limit;
    }

}

