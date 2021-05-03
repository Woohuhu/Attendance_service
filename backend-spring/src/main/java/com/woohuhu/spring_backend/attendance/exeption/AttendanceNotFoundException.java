package com.woohuhu.spring_backend.attendance.exeption;

public class AttendanceNotFoundException extends RuntimeException {

    private static final String ATTANDANCE_NOT_FOUND_EXCEPTION_MESSAGE = "해당날짜의 출석 기록을 찾을 수 없습니다.";

    public AttendanceNotFoundException() {
        super(ATTANDANCE_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
