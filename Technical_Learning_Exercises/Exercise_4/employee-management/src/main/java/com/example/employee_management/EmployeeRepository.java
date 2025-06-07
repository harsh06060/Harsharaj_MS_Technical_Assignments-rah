package com.example.employee_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT e FROM Employee e WHERE e.id IN " +
           "(SELECT a.employee.id FROM Attendance a WHERE a.date >= :startDate " +
           "GROUP BY a.employee.id HAVING AVG(CASE WHEN a.present THEN 1.0 ELSE 0.0 END) < :threshold)")
    List<Employee> findEmployeesWithLowAttendance(@Param("startDate") LocalDate startDate, 
                                                @Param("threshold") double threshold);
}