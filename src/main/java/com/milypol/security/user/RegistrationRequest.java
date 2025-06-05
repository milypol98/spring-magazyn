package com.milypol.security.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Adres e-mail nie może być pusty")
    @Email(message = "Podaj poprawny adres e-mail")
    private String email;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 6, message = "Hasło musi mieć conajmniej 6 znaków")
    private String password;

    @NotBlank(message = "Potwierdzenie hasła nie może być puste")
    private String confirmPassword;
}
