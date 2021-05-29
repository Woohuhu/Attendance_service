package com.woohuhu.spring_backend.attendance.dto;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceStartResponseDto {
    private Boolean isAttendance;
    private String starter;
    private Integer time;
    private ArrayList attendanceUserList;
}
