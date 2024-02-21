package com.stream.auth.authentication.service.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface JwtService {

    String getToken(HttpServletRequest request);

    boolean validateToken(String token);

    String extractUsername(String token);

    String generateToken(UserDetails userDetails);
}
