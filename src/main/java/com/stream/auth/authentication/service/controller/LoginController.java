package com.stream.auth.authentication.service.controller;

import com.stream.auth.authentication.service.model.SignInDTO;
import com.stream.auth.authentication.service.service.LoginInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class LoginController {

    private final LoginInService loginInService;

    @GetMapping("/users")
    public ResponseEntity login (@Valid @RequestBody SignInDTO signInDTO) throws AuthenticationException {

        String newToken = loginInService.authenticate(signInDTO);
        return  ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + newToken)
                .body(loginInService.getRegisteredUser(signInDTO));
    }



}
