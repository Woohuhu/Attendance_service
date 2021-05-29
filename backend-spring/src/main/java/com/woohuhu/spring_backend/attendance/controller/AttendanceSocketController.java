package com.woohuhu.spring_backend.attendance.controller;

import com.woohuhu.spring_backend.attendance.dto.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Controller
public class AttendanceSocketController {
    @MessageMapping("/join")
    @SendTo("/join")
    public AttendanceJoinResponseDto joinAttendanceCheck() throws Exception {
        //TODO 넘겨받은 데이터 Redis 저장 과정
        try {
            AttendanceJoinResponseDto attendanceJoinResponseDto = AttendanceJoinResponseDto.builder()
                    .isAttendance(false)
                    .attendanceUserList(new ArrayList<>())
                    .starter("hschoi1104")
                    .time(360001)
                    .build();
            return attendanceJoinResponseDto;
        }catch(Exception e){
            throw e;
        }
    }

    @MessageMapping("/start")
    @SendTo("/start")
    public AttendanceStartResponseDto startAttendanceCheck(AttendanceStartRequestDto attendanceStartRequestDto) throws Exception {
        //TODO 넘겨받은 데이터 Redis 저장 과정
        AttendanceStartResponseDto attendanceStartResponseDto = AttendanceStartResponseDto.builder()
                .isAttendance(true)
                .starter(attendanceStartRequestDto.getStarter())
                .time(360000)
                .build();
        return attendanceStartResponseDto;
    }

    @MessageMapping("/attendance")
    @SendTo("/attendance")
    public AttendanceCheckResponseDto attendanceCheck(AttendanceCheckRequestDto attendanceCheckRequestDto) throws Exception {
        //TODO 코드 확인 후 출석 기록 과정
        AttendanceCheckResponseDto attendanceCheckResponseDto = AttendanceCheckResponseDto.builder()
                .result("success")
                .id(attendanceCheckRequestDto.getId())
                .build();
        return attendanceCheckResponseDto;
    }

    @MessageMapping("/stop")
    @SendTo("/stop")
    public AttendanceStopResponseDto stopAttendance() throws Exception {
        AttendanceStopResponseDto attendanceStopResponseDto = AttendanceStopResponseDto.builder()
                .isAttendance(false)
                .build();
        return attendanceStopResponseDto;
    }
}
