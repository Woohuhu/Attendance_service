package com.woohuhu.spring_backend.attendance.service;

import com.woohuhu.spring_backend.attendance.dao.AttendanceRepository;
import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) throws Exception {
        AttendanceDto attendance = AttendanceDto.builder()
                .state(attendanceDto.getState())
                .id(attendanceDto.getId())
                .date(attendanceDto.getDate())
                .build();

        return  attendanceRepository.save(attendance);
    }
}