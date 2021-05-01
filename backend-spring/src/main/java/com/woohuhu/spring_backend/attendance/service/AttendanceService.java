package com.woohuhu.spring_backend.attendance.service;

import com.woohuhu.spring_backend.attendance.Entity.Attendance;
import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    AttendanceDto createAttendance(AttendanceDto attendanceDto) throws Exception;

    List<Attendance> getAttendanceById(String id) throws Exception;
}
