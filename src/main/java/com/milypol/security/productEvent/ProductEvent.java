package com.milypol.security.productEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.car.Car;
import com.milypol.security.product.Product;
import com.milypol.security.task.Task;
import com.milypol.security.user.User;
import com.milypol.security.warehouse.Warehouse;
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
    private LocationType fromLocationType;
    private Integer fromLocationCode;

    @Enumerated(EnumType.STRING)
    private LocationType toLocationType;
    private Integer toLocationCode;

    @Enumerated(EnumType.STRING)
    private ProductEventType eventType;

    private LocalDateTime timestamp;

    private String comment;
    private Double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
