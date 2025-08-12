package com.milypol.security.company;

import com.milypol.security.company.Nip;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NipValidator implements ConstraintValidator<Nip, String> {

    private boolean allowEmpty;

    @Override
    public void initialize(Nip constraintAnnotation) {
        this.allowEmpty = constraintAnnotation.allowEmpty();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return allowEmpty;
        }
        String digits = value.replaceAll("[^0-9]", "");
        if (digits.length() != 10) return false;
        int[] w = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        int sum = 0;
        for (int i = 0; i < 9; i++) sum += Character.getNumericValue(digits.charAt(i)) * w[i];
        int control = sum % 11;
        return control == Character.getNumericValue(digits.charAt(9));
    }
}