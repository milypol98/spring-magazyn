package com.milypol.security.task;

import com.milypol.security.car.Car;
import com.milypol.security.cart.Cart;
import com.milypol.security.place.Place;
import com.milypol.security.product.Product;
import com.milypol.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String name;
    private String dateFrom;
    private String dateTo;
    @ManyToOne
    private Place place;
    @ManyToMany
    @JoinTable(
            name = "task_product",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @ManyToMany
    @JoinTable(
            name = "task_product_used",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productsUsed;
    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    @ManyToMany
    @JoinTable(
            name = "task_car",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> car;
    @ManyToOne
    private Cart cart;
}
