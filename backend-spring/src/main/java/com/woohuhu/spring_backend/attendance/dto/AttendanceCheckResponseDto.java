package com.woohuhu.spring_backend.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceCheckResponseDto {
    private final String id;
    private final String result;
}
