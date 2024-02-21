package com.stream.auth.authentication.service.mapper;

import com.stream.auth.authentication.service.domain.User;
import com.stream.auth.authentication.service.model.RegisteredUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRegisteredMapper {

    UserRegisteredMapper INSTANCE = Mappers.getMapper(UserRegisteredMapper.class);

    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    @Mapping(target = "email",source = "user.email")
    @Mapping(target = "createdTime",source = "user.createdTime")
    RegisteredUserDTO registeredUserToUser(User user);
}
