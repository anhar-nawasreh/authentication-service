package com.stream.auth.authentication.service.model;


import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisteredUserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime createdTime;
}
