package com.milypol.security.user;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, PasswordProvider> {

    @Override
    public boolean isValid(PasswordProvider  request, ConstraintValidatorContext context) {
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();

        if (newPassword == null || newPassword.trim().isEmpty()) {
            return true;
        }

        boolean isValid = true;

        if (!newPassword.equals(confirmPassword)) {
            addConstraintViolation(context, "Hasła muszą być identyczne.", "confirmPassword");
            isValid = false;
        }

        if (newPassword.length() < 8) {
            addConstraintViolation(context, "Nowe hasło musi mieć co najmniej 8 znaków.", "newPassword");
            isValid = false;
        }

        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=_*-])(?=\\S+$).{8,}$";
        if (!newPassword.matches(passwordPattern)) {
            addConstraintViolation(context, "Hasło musi zawierać cyfrę, małą i dużą literę oraz znak specjalny.", "newPassword");
            isValid = false;
        }

        return isValid;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message, String fieldName) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
    }
}