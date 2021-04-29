package com.woohuhu.spring_backend.attendance.service;

import com.woohuhu.spring_backend.attendance.Entity.Attendance;
import com.woohuhu.spring_backend.attendance.dao.AttendanceRepository;
import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

@Override
public AttendanceDto createAttendance(AttendanceDto attendanceDto) throws Exception {
    Attendance attendance = Attendance.builder()
            .state(attendanceDto.getState())
            .id(attendanceDto.getId())
            .date(new Timestamp(attendanceDto.getDate().getTime()))
            .build();
    attendanceRepository.save(attendance);
    return  attendanceDto;
}
}