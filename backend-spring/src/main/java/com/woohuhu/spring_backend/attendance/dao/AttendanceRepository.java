package com.woohuhu.spring_backend.attendance.dao;

import com.woohuhu.spring_backend.attendance.dto.AttendanceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceDto, String> {
}
