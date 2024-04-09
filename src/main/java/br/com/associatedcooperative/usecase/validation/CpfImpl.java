package br.com.associatedcooperative.usecase.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfImpl implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!value.matches("\\d{11}")) return false;
        if (value.matches("(\\d)\\1{10}")) return false;

        int sumForFirstDigit = 0, sumForSecondDigit = 0;
        for (int i = 0; i < 9; i++) {
            int num = value.charAt(i) - '0';
            sumForFirstDigit += num * (10 - i);
            sumForSecondDigit += num * (11 - i);
        }

        sumForSecondDigit += (value.charAt(9) - '0') * 2;

        int firstDigit = calculateDigit(sumForFirstDigit);
        int secondDigit = calculateDigit(sumForSecondDigit);

        return firstDigit == value.charAt(9) - '0' && secondDigit == value.charAt(10) - '0';
    }

    private static int calculateDigit(int sum) {
        int digit = 11 - (sum % 11);
        return digit > 9 ? 0 : digit;
    }
}

