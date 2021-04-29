package com.woohuhu.spring_backend.attendance.dao;

import com.woohuhu.spring_backend.attendance.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}
