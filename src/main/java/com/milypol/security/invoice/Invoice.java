package com.milypol.security.invoice;

import com.milypol.security.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import jakarta.persistence.*;
import lombok.*;
import java.math.RoundingMode;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String invoiceNumber;

    @Column(nullable = false, length = 255)
    private String name;

    private String storedFileName;
    private String originalFileName;
    private String contentType;
    private Long fileSize;
    private Instant uploadedAt;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalNet;

    private Integer vatRate;

    @Column(precision = 19, scale = 2)
    private BigDecimal calculatedVat;

    @Column(precision = 19, scale = 2)
    private BigDecimal totalGross;

    @Column(length = 8)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @PrePersist
    @PreUpdate
    public void calculateTotals() {
        if (totalNet != null && vatRate != null) {
            BigDecimal rate = BigDecimal.valueOf(vatRate).divide(new BigDecimal("100"));
            this.calculatedVat = totalNet.multiply(rate).setScale(2, RoundingMode.HALF_UP);
            this.totalGross = totalNet.add(this.calculatedVat);
        } else if (totalNet != null) {
            this.totalGross = totalNet;
        }
    }
}