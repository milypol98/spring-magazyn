package com.milypol.security.company;

import com.milypol.security.address.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies", indexes = {
        @Index(name = "idx_companies_name", columnList = "name"),
        @Index(name = "idx_companies_nip", columnList = "nip", unique = false)
})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa jest wymagana")
    @Size(max = 255, message = "Nazwa jest za długa")
    @Column(nullable = false)
    private String name;

    @Size(max = 64)
    private String phoneNumber;

    @Email(message = "Nieprawidłowy e-mail")
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String website;

    @Nip
    @Size(max = 32)
    private String nip;

    @Regon
    @Size(max = 32)
    private String regon;

    @Size(max = 2000)
    private String description;

    @Valid
    @Embedded
    private Address address;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = Instant.now();
    }
}