package com.milypol.security.carHistory;

import com.milypol.security.car.Car;
import com.milypol.security.windFarm.WindFarm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CarHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @OneToOne
    private Car car;
    @OneToOne
    private WindFarm windFarm;
}
