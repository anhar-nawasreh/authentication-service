package com.stream.auth.authentication.service.service;

import com.stream.auth.authentication.service.model.RegisteredUserDTO;
import com.stream.auth.authentication.service.model.SignInDTO;

import javax.security.sasl.AuthenticationException;

public interface LoginInService {


    String authenticate(SignInDTO signInDTO) throws AuthenticationException;
    RegisteredUserDTO getRegisteredUser(SignInDTO signInDTO);
}
