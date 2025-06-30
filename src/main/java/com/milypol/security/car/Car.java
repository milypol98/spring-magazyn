package com.milypol.security.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.carCost.CarCost;
import com.milypol.security.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String model;
    private String color;
    private String brand;
    private String registration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  year;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate review;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  insured;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore                    // ignoruj pole podczas serializacji JSON (REST, Thymeleaf)
    @ToString.Exclude              // wyklucz z generowanego toString()
    @EqualsAndHashCode.Exclude     // wyklucz z equals() i hashCode()
    private List<CarCost> costs;
}
