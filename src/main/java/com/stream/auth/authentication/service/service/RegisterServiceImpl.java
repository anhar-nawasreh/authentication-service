package com.stream.auth.authentication.service.service;

import com.stream.auth.authentication.service.domain.User;
import com.stream.auth.authentication.service.mapper.UserRegisteredMapper;
import com.stream.auth.authentication.service.model.RegisteredUserDTO;
import com.stream.auth.authentication.service.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterServiceImpl implements RegisterService{

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisteredUserDTO register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Mappers.getMapper(UserRegisteredMapper.class).registeredUserToUser( userRepo.save(user));
    }

}
