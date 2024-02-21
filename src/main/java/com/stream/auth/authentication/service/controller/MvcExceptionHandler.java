package com.stream.auth.authentication.service.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleCustomerValidationExceptions(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        System.out.println(exception.getMessage());
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String property = violation.getPropertyPath().toString();
            if (violation.getMessage().contains("must not be null")) {
                errorMessage.append(property + " must not be empty; ");
            } else if (violation.getMessage().contains("UniqueConstraint")) {
                errorMessage.append(property + " already exists; ");
                status = HttpStatus.CONFLICT;
            }
        }

        return new ResponseEntity<>(errorMessage.toString(), status);
    }

    @ExceptionHandler(AuthenticationException.class)
    public  ResponseEntity<Object> handleAuthenticationException( AuthenticationException exception)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityException(DataIntegrityViolationException exception)
    {
        if(exception.getMessage().contains("null"))
            return new ResponseEntity<>("Unsigned value for required field", HttpStatus.BAD_REQUEST);

        else if ( exception.getMessage().contains("email") )
            return new ResponseEntity<>("The email is already used", HttpStatus.CONFLICT);

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialException(BadCredentialsException exception)
    {

        return new ResponseEntity<>("Invalid username or password",HttpStatus.UNAUTHORIZED);

    }


}
