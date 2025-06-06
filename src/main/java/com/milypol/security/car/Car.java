package com.milypol.security.car;

import com.milypol.security.carCost.CarCost;
import com.milypol.security.carHistory.CarHistory;
import com.milypol.security.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @ManyToMany
    @JoinTable(
            name = "car_product",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarHistory> history;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarCost> costs = new ArrayList<>();
}
