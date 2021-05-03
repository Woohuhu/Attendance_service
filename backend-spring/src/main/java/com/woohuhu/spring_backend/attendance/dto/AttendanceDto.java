package com.woohuhu.spring_backend.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceDto {
    private String id;
    private LocalDate date;
    private String state;
}
