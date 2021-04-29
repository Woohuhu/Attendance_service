package com.woohuhu.spring_backend.attendance.service;

import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {

    AttendanceDto createAttendance(AttendanceDto attendanceDto) throws Exception;
}
