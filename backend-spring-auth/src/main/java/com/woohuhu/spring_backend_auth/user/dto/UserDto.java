package com.woohuhu.spring_backend_auth.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder

public class UserDto {
    @NotEmpty
    @NotBlank
    @NotNull
    private String id;
    private String password;
    private String name;
}