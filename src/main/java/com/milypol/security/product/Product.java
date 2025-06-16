package com.milypol.security.product;

import com.milypol.security.car.Car;
import com.milypol.security.cart.Cart;
import com.milypol.security.place.Place;
import com.milypol.security.stockPosition.StockPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String description;
    private Double price;
    private String quantity;
    @ManyToMany(mappedBy = "products")
    private List<Car> cars;
    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;
    @ManyToOne
    private StockPosition stockPosition;
}
