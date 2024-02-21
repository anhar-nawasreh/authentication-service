package com.stream.auth.authentication.service.validator;


import com.stream.auth.authentication.service.annotation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {


    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid= password != null && password.matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

        if ( ! isValid )
            constraintValidatorContext.buildConstraintViolationWithTemplate("The password must at least one lowercase letter, one uppercase letter, one digit, and one special character.\n" +
                            "Has a minimum length of 8 characters.")
                    .addConstraintViolation();
        return isValid;
    }
}
