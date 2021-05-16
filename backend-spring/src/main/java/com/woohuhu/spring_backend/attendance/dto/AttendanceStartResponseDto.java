package com.woohuhu.spring_backend.attendance.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceStartResponseDto {
    private Boolean isAttendance;
    private String adminId;
    private Integer time;
}
