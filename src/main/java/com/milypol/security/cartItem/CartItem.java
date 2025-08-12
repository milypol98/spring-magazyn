package com.milypol.security.cartItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.cart.Cart;
import com.milypol.security.product.Product;
import com.milypol.security.stockPosition.StockPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private StockPosition stockPosition;
    @ManyToOne
    private Product product;
    private Integer quantity;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
}
