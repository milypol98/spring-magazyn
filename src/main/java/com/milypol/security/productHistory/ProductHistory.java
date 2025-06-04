package com.milypol.security.productHistory;

import com.milypol.security.place.Place;
import com.milypol.security.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    @OneToOne
    private Product product;

    @OneToOne
    private Place place;
}
