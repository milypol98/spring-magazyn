package com.milypol.security.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // Adnotacja będzie używana na klasie
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class) // Logika walidacji znajduje się w tej klasie
public @interface PasswordsMatch {
    String message() default "Hasła nie są zgodne lub nie spełniają wymagań.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
