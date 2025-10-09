package com.milypol.security.carCost;

import com.milypol.security.car.Car;
import com.milypol.security.invoice.Invoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CarCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private BigDecimal cost;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @ManyToOne
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

}
