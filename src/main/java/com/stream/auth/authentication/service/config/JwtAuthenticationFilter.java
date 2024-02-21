package com.stream.auth.authentication.service.config;

import com.stream.auth.authentication.service.service.CustomerUserDetails;
import com.stream.auth.authentication.service.service.JwtService;
import com.stream.auth.authentication.service.service.LoginInService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final  JwtService jwtService;
    private  final  CustomerUserDetails userDetail;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ;
        if(request.getRequestURI().equals("/api/v1/authentication")) {
            String token = jwtService.getToken(request);
            System.out.println(token);
            if ( token != null && jwtService.validateToken(token) ) {
                String email = jwtService.extractUsername(token);
                UserDetails userDetails = userDetail.loadUserByUsername(email);

                if ( userDetails != null ) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    response.setStatus(HttpServletResponse.SC_ACCEPTED);
                    return;
                }
            }
            System.out.println("not");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Not authenticated");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
