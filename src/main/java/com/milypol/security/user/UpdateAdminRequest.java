package com.milypol.security.user;

import com.milypol.security.address.Address;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordsMatch
public class UpdateAdminRequest implements PasswordProvider {
    private Integer id;
    @NotBlank(message = "Imię jest wymagane")
    private String firstname;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastName;

    @NotBlank(message = "Adres e-mail nie może być pusty")
    @Email(message = "Podaj poprawny adres e-mail")
    private String email;

    private Long phone;
    private String description;

    @Valid
    private Address address;

    private String newPassword;

    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Role role;
}
