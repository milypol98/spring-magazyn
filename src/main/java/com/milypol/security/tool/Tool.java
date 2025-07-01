package com.milypol.security.tool;

import com.milypol.security.stockPosition.StockPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tool")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String code;
    private String description;
    private Double price;
    @ManyToOne
    private StockPosition stockPosition;
    @Enumerated(EnumType.STRING)
    private ToolStatus status;
}
