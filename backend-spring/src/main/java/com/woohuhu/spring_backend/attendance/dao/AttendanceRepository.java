package com.woohuhu.spring_backend.attendance.dao;

import com.woohuhu.spring_backend.attendance.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findAllById(@Param("id") String id);

    @Query(value = "SELECT attendance.* FROM attendance WHERE date =:date", nativeQuery = true)
    List<Attendance> findAllByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "UPDATE attendance SET state =:state WHERE id=:id AND date=:date", nativeQuery = true)
    int updateAttendanceState(String id, LocalDate date, String state);
}
