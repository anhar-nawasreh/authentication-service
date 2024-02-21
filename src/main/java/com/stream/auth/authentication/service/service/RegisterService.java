package com.stream.auth.authentication.service.service;

import com.stream.auth.authentication.service.domain.User;
import com.stream.auth.authentication.service.model.RegisteredUserDTO;

public interface RegisterService {
    RegisteredUserDTO register(User user);
}
