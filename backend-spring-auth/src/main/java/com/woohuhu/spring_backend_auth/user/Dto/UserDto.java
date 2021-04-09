package com.woohuhu.spring_backend_auth.user.Dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder

public class UserDto {
    private String id;
    private String password;
    private String name;
}