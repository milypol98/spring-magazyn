package com.milypol.security.carHistory;

import com.milypol.security.car.Car;
import com.milypol.security.place.Place;
import com.milypol.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTo;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Place place;
    @ManyToOne
    private User user;
}
