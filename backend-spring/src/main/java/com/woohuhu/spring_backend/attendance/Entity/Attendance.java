package com.woohuhu.spring_backend.attendance.Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    @Column
    private String state;
}
