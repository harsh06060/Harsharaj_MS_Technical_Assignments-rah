package com.example.employee_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
    @Query("SELECT a.employee.department.name, AVG(CASE WHEN a.present THEN 1.0 ELSE 0.0 END) " +
           "FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate " +
           "GROUP BY a.employee.department.name")
    List<Object[]> findAttendanceRateByDepartment(@Param("startDate") LocalDate startDate, 
                                                @Param("endDate") LocalDate endDate);
    
    @Query("SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate")
    Page<Attendance> findAttendanceTrends(@Param("startDate") LocalDate startDate, 
                                        @Param("endDate") LocalDate endDate, 
                                        Pageable pageable);
    
    void deleteByDateBefore(LocalDate date);
}