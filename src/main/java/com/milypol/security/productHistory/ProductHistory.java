package com.milypol.security.productHistory;

import com.milypol.security.car.Car;
import com.milypol.security.product.Product;
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
public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @OneToOne
    private Product products;
    @OneToOne
    private WindFarm windFarm;
}
