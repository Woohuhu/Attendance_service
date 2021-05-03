package com.woohuhu.spring_backend.attendance.service;

import com.woohuhu.spring_backend.attendance.Entity.Attendance;
import com.woohuhu.spring_backend.attendance.dao.AttendanceRepository;
import com.woohuhu.spring_backend.attendance.dao.UserRepository;
import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import com.woohuhu.spring_backend.attendance.exeption.AttendanceNotFoundException;
import com.woohuhu.spring_backend.attendance.exeption.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) throws Exception {
        Attendance attendance = Attendance.builder()
                .state(attendanceDto.getState())
                .id(attendanceDto.getId())
                .date(attendanceDto.getDate())
                .build();
        attendanceRepository.save(attendance);
        return attendanceDto;
    }

    @Override
    public List<Attendance> getAttendanceById(String id) throws Exception {
        Boolean user = userRepository.existsById(id);
        if (!user) {
            throw new UserNotFoundException();
        }
        List<Attendance> attendances = attendanceRepository.findAllById(id);
        return attendances;
    }

    @Override
    public List<Attendance> getAttendanceByDate(String date) throws Exception {
        List<Attendance> attendances = attendanceRepository.findAllByDate(LocalDate.parse(date));
        if (attendances.isEmpty()) {
            throw new AttendanceNotFoundException();
        }
        return attendances;
    }

    @Override
    public AttendanceDto updateAttendanceState(AttendanceDto attendanceDto) throws Exception {
        Boolean user = userRepository.existsById(attendanceDto.getId());
        if (!user) {
            throw new UserNotFoundException();
        }
        attendanceRepository.updateAttendanceState(attendanceDto.getId(), attendanceDto.getDate(), attendanceDto.getState());
        return attendanceDto;
    }
}