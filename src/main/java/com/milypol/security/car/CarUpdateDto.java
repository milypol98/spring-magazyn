package com.milypol.security.car;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CarUpdateDto {

    private Integer id;
    private LocalDate review;
    private LocalDate insured;
    private LocalDate tiresCheck;
    private Integer oilChangeCourse;
    private Integer timingSystemCourse;
    private LocalDate reminderDate;
    private Integer reminderKm;
    private String reminderKmDescription;
    private String reminderDateDescription;

}
