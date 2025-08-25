package com.milypol.security.tool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.carCost.CarCost;
import com.milypol.security.stockPosition.StockPosition;
import com.milypol.security.toolCost.ToolCost;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tool")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    private StockPosition stockPosition;
    @Enumerated(EnumType.STRING)
    private ToolStatus status;
    @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ToolCost> costs;
}
