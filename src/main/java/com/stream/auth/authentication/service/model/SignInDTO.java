package com.stream.auth.authentication.service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank
    private String password;


}
