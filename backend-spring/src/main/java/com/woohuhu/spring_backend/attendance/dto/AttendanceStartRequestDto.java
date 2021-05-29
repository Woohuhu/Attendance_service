package com.woohuhu.spring_backend.attendance.dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter

public class AttendanceStartRequestDto {
    private Integer code;
    private String starter;
}
