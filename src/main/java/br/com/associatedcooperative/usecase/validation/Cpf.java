package br.com.associatedcooperative.usecase.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {CpfImpl.class})
public @interface Cpf {
    String message() default "Invalid CPF, only enter the numbers of a valid CPF";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

