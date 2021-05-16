package com.woohuhu.spring_backend.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class AttendanceStartRequestDto {
    private final Integer code;
    private final String adminId;
}
