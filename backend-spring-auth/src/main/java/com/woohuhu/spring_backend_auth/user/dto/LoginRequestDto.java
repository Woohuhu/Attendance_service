package com.woohuhu.spring_backend_auth.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder

public class LoginRequestDto {
    @NotEmpty @NotBlank @NotNull
    private String id;
    @NotEmpty @NotBlank @NotNull
    private String password;
}
