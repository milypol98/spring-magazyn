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

    @NotBlank(message = "Ulica jest wymagana")
    private String street;

    @NotBlank(message = "Numer budynku jest wymagany")
    private String buildingNumber;

    private String apartmentNumber;

    @NotBlank(message = "Kod pocztowy jest wymagany")
    // Format polski 00-000 (możesz dostosować)
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Nieprawidłowy kod pocztowy (oczekiwano format 00-000)")
    private String postalCode;

    @NotBlank(message = "Miejscowość jest wymagana")
    private String city;

    @NotBlank(message = "Kraj jest wymagany")
    private String country;
}
