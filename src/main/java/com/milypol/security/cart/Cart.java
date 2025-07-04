package com.milypol.security.cart;

import com.milypol.security.place.Place;
import com.milypol.security.product.Product;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private String name;


    @ManyToMany
    @JoinTable(
            name = "cart_cart_details",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_detail_id")
    )
    private List<CartDetails> cartDetails;

}
