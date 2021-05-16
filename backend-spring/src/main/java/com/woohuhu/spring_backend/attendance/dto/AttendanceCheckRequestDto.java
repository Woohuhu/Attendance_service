package com.woohuhu.spring_backend.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AttendanceCheckRequestDto {
    private Integer code;
    private String id;
    private String name;
}
