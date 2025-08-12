package com.milypol.security.car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.carCost.CarCost;
import com.milypol.security.cart.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{car.name.notBlank}")
    @Size(max = 100, message = "{car.name.size}")
    private String name;

    @Size(max = 2000, message = "{car.description.size}")
    private String description;

    @NotBlank(message = "{car.model.notBlank}")
    @Size(max = 100, message = "{car.model.size}")
    private String model;

    @NotBlank(message = "{car.color.notBlank}")
    @Size(max = 50, message = "{car.color.size}")
    private String color;

    @Size(max = 100, message = "{car.owner.size}")
    private String owner;

    @Size(max = 30, message = "{car.engineCapacity.size}")
    private String engineCapacity;

    @NotBlank(message = "{car.brand.notBlank}")
    @Size(max = 100, message = "{car.brand.size}")
    private String brand;

    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "{car.vin.pattern}")
    private String vin;

    @Pattern(regexp = "^[A-Z0-9\\-]{4,12}$", message = "{car.registration.pattern}")
    private String registration;

    @Min(value = 0, message = "{car.course.min}")
    @Max(value = 3000000, message = "{car.course.max}")
    private Integer course;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "{car.courseDate.pastOrPresent}")
    private LocalDate courseDate;

    @Min(value = 0, message = "{car.timingSystemIntervalKm.min}")
    @Max(value = 1000000, message = "{car.timingSystemIntervalKm.max}")
    private Integer timingSystemIntervalKm;

    @Min(value = 0, message = "{car.timingSystemCourse.min}")
    @Max(value = 3000000, message = "{car.timingSystemCourse.max}")
    private Integer timingSystemCourse;

    @Min(value = 0, message = "{car.oilChangeCourse.min}")
    @Max(value = 3000000, message = "{car.oilChangeCourse.max}")
    private Integer oilChangeCourse;

    @Min(value = 0, message = "{car.oilChangeIntervalKm.min}")
    @Max(value = 1000000, message = "{car.oilChangeIntervalKm.max}")
    private Integer oilChangeIntervalKm;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "{car.year.pastOrPresent}")
    private LocalDate year;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "{car.review.futureOrPresent}")
    private LocalDate review;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "{car.insured.futureOrPresent}")
    private LocalDate insured;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CarCost> costs;

    @ManyToMany
    @JoinTable(
            name = "car_cart",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> cart;
}
