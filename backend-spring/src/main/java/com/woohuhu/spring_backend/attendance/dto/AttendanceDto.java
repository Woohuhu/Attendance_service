package com.woohuhu.spring_backend.attendance.dto;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "attendance")
public class AttendanceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @Column
    private String state;


}
