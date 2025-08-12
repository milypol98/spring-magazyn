package com.milypol.security.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.cartItem.CartItem;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore                    // ignoruj pole podczas serializacji JSON (REST, Thymeleaf)
    @ToString.Exclude              // wyklucz z generowanego toString()
    @EqualsAndHashCode.Exclude     // wyklucz z equals() i hashCode()
    private List<CartItem> items;
}