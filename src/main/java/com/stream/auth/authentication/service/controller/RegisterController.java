package com.stream.auth.authentication.service.controller;

import com.stream.auth.authentication.service.domain.User;
import com.stream.auth.authentication.service.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/users")
    public ResponseEntity register(@Valid @RequestBody User user)
    {
       return new ResponseEntity<>( registerService.register(user), HttpStatus.CREATED);
    }


}
