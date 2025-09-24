package com.milypol.security.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    private String street;

    private String buildingNumber;

    private String apartmentNumber;

    @Pattern(regexp = "^$|^\\d{2}-\\d{3}$", message = "Nieprawidłowy kod pocztowy (oczekiwano format 00-000)")
    private String postalCode;

    private String city;

    private String country;
}
