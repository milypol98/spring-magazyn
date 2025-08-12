package com.milypol.security.invoice;

import com.milypol.security.company.Company;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

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

    @Column(name = "invoice_number", nullable = false, length = 128)
    private String invoiceNumber;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Plik
    @Column(name = "stored_file_name", nullable = false, length = 255)
    private String storedFileName;

    @Column(name = "original_file_name", length = 255)
    private String originalFileName;

    @Column(name = "content_type", length = 128)
    private String contentType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "uploaded_at", nullable = false)
    private Instant uploadedAt;

    // Wartości faktury
    @Column(name = "total_net", precision = 19, scale = 2)
    private BigDecimal totalNet;

    @Column(name = "total_vat", precision = 19, scale = 2)
    private BigDecimal totalVat;

    @Column(name = "total_gross", precision = 19, scale = 2)
    private BigDecimal totalGross;

    @Column(name = "currency", length = 8)
    private String currency;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_invoice_company"))
    private Company company;

}