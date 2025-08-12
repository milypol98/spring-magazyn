package com.milypol.security.company;

import com.milypol.security.company.Regon;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegonValidator implements ConstraintValidator<Regon, String> {

    private boolean allowEmpty;

    @Override
    public void initialize(Regon constraintAnnotation) {
        this.allowEmpty = constraintAnnotation.allowEmpty();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return allowEmpty;
        }
        String digits = value.replaceAll("[^0-9]", "");
        if (digits.length() == 9) {
            int[] w = {8, 9, 2, 3, 4, 5, 6, 7};
            int sum = 0;
            for (int i = 0; i < 8; i++) sum += Character.getNumericValue(digits.charAt(i)) * w[i];
            int control = sum % 11; if (control == 10) control = 0;
            return control == Character.getNumericValue(digits.charAt(8));
        } else if (digits.length() == 14) {
            int[] w9 = {8, 9, 2, 3, 4, 5, 6, 7};
            int sum9 = 0;
            for (int i = 0; i < 8; i++) sum9 += Character.getNumericValue(digits.charAt(i)) * w9[i];
            int control9 = sum9 % 11; if (control9 == 10) control9 = 0;
            if (control9 != Character.getNumericValue(digits.charAt(8))) return false;

            int[] w14 = {2, 4, 8, 5, 0, 9, 7, 3, 6, 1, 2, 4, 8};
            int sum14 = 0;
            for (int i = 0; i < 13; i++) sum14 += Character.getNumericValue(digits.charAt(i)) * w14[i];
            int control14 = sum14 % 11; if (control14 == 10) control14 = 0;
            return control14 == Character.getNumericValue(digits.charAt(13));
        }
        return false;
    }
}