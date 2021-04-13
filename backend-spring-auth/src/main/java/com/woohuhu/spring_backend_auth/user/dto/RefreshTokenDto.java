package com.woohuhu.spring_backend_auth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class RefreshTokenDto {
    @NotEmpty
    @NotBlank
    @NotNull
    private String token;
    @NotEmpty
    @NotBlank
    @NotNull
    private String id;
}
