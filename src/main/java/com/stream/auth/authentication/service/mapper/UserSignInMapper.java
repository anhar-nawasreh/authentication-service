package com.stream.auth.authentication.service.mapper;

import com.stream.auth.authentication.service.domain.User;
import com.stream.auth.authentication.service.model.SignInDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserSignInMapper {


    User signInToUser(SignInDTO signInDTO);
}
