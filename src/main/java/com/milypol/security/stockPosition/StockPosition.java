package com.milypol.security.stockPosition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milypol.security.warehouse.Warehouse;
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
    private String name;
    private String category;
    private String description;
    private Integer minStock;
    private Integer alarmStock;
    private String position;
    @OneToMany(mappedBy = "stockPosition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore                    // ignoruj pole podczas serializacji JSON (REST, Thymeleaf)
    @ToString.Exclude              // wyklucz z generowanego toString()
    @EqualsAndHashCode.Exclude     // wyklucz z equals() i hashCode()
    private List<Tool> tools;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

}
