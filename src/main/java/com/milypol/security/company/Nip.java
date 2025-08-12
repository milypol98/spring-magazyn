package com.milypol.security.company;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NipValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Nip {
    String message() default "Nieprawidłowy NIP";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean allowEmpty() default true;
}
