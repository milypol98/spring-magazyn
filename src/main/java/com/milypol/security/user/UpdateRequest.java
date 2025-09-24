package com.milypol.security.user;

import com.milypol.security.address.Address;
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
public class UpdateRequest implements PasswordProvider {

    private Integer id;
    @NotBlank(message = "Imię jest wymagane")
    private String firstname;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastName;

    private Long phone;
    private String description;

    @Valid
    private Address address;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
