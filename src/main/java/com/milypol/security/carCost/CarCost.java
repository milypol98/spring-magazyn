package com.milypol.security.carCost;

import com.milypol.security.car.Car;
import com.milypol.security.product.Product;
import com.milypol.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CarCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Double cost;
    private Date dateFrom;
    private Date dateTo;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;
}
