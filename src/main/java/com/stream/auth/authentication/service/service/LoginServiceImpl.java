package com.stream.auth.authentication.service.service;

import com.stream.auth.authentication.service.domain.User;
import com.stream.auth.authentication.service.mapper.UserRegisteredMapper;
import com.stream.auth.authentication.service.model.RegisteredUserDTO;
import com.stream.auth.authentication.service.model.SignInDTO;
import com.stream.auth.authentication.service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginInService  {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    public String authenticate(SignInDTO signInDTO) throws UsernameNotFoundException {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return jwtService.generateToken(userDetails);
    }
    public RegisteredUserDTO getRegisteredUser(SignInDTO signInDTO)
    {
        return UserRegisteredMapper.INSTANCE.registeredUserToUser(userRepo.findByEmail(signInDTO.getEmail()).get());
    }



}
