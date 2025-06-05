package com.milypol.security.carHistory;

import com.milypol.security.car.Car;
import com.milypol.security.place.Place;
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
public class CarHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private double latitude;
    private double longitude;
    @ManyToOne
    private Car car;

    @ManyToOne
    private Place place;
    @ManyToOne
    private User user;
}
