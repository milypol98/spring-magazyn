package com.milypol.security.user;

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
public class RegistrationRequest {
    @NotBlank(message = "Imie jest wymagana")
    private String firstname;
    @NotBlank(message = "Nazwisko jest wymagana")
    private String lastName;
    @NotBlank(message = "Adres e-mail nie może być pusty")
    @Email(message = "Podaj poprawny adres e-mail")
    private String email;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=_*-])(?=\\S+$).{8,}$",
            message = "Hasło musi zawierać co najmniej jedną cyfrę, jedną małą literę, jedną dużą literę, jeden znak specjalny i nie może zawierać spacji")
    private String password;

    @NotBlank(message = "Potwierdzenie hasła nie może być puste")
    private String confirmPassword;
}
