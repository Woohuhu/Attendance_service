package com.woohuhu.spring_backend.attendance.dto;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceDto {
    private String id;
    private java.util.Date date;
    private String state;


}
