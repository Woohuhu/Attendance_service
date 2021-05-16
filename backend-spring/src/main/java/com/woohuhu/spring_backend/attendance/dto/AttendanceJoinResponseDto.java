package com.woohuhu.spring_backend.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceJoinResponseDto {
    private final ArrayList<String> attendanceUserList;
    private final Boolean isAttendance;
    private final String adminId;
    private final Integer time;
}
