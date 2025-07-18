package com.milypol.security.stockPosition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.product.Product;
import com.milypol.security.tool.Tool;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class StockPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String name;

    @OneToMany(mappedBy = "stockPosition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore                    // ignoruj pole podczas serializacji JSON (REST, Thymeleaf)
    @ToString.Exclude              // wyklucz z generowanego toString()
    @EqualsAndHashCode.Exclude     // wyklucz z equals() i hashCode()
    private List<Tool> tools;
    private Integer minStock;
}
