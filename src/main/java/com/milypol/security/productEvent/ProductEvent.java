package com.milypol.security.productEvent;

import com.milypol.security.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductEvent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Integer quantity;


    @Enumerated(EnumType.STRING)
    private LocationType locationType;


    private String locationCode;

    @Enumerated(EnumType.STRING)
    private ProductEventType type;

    private LocalDateTime timestamp;

    private String comment;
    private Double unitPrice;
}
