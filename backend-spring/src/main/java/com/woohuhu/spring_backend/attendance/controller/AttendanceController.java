package com.woohuhu.spring_backend.attendance.controller;

import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import com.woohuhu.spring_backend.attendance.service.AttendanceService;
import com.woohuhu.spring_backend.global.dto.Response;
import com.woohuhu.spring_backend.global.dto.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class AttendanceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/v1/attendance")
    public ResponseEntity createAttendance(@RequestBody AttendanceDto attendanceDto) throws Exception {
        try {
            logger.info("in!!");
            attendanceService.createAttendance(attendanceDto);
            return new ResponseEntity(Response.response(StatusCode.OK, "출석체크 성공"), HttpStatus.CREATED);
        }catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.NOT_FOUND, "출석체크 실패"), HttpStatus.NOT_FOUND);
        }
    }

}
