package com.example.employee_management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulingService {
    
    private static final Logger logger = LoggerFactory.getLogger(SchedulingService.class);
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Scheduled(cron = "0 0 6 * * ?") // Daily at 6 AM
    @Transactional
    public void generateDailyAttendanceReport() {
        try {
            LocalDate yesterday = LocalDate.now().minusDays(1);
            List<Object[]> deptRates = attendanceRepository.findAttendanceRateByDepartment(
                yesterday, yesterday);
            
            logger.info("Daily Attendance Report for {}:", yesterday);
            for (Object[] rate : deptRates) {
                logger.info("Department: {}, Attendance Rate: {}", rate[0], rate[1]);
            }
        } catch (Exception e) {
            logger.error("Error in daily report generation", e);
        }
    }
    
    @Scheduled(cron = "0 0 0 * * SUN") // Weekly on Sunday
    @Transactional
    public void archiveOldData() {
        try {
            LocalDate threshold = LocalDate.now().minusMonths(6);
            attendanceRepository.deleteByDateBefore(threshold);
            logger.info("Archived attendance data older than {}", threshold);
        } catch (Exception e) {
            logger.error("Error in data archiving", e);
        }
    }
    
    @Scheduled(fixedRate = 15 * 60 * 1000) // Every 15 minutes
    public void checkAnomalies() {
        try {
            LocalDate startDate = LocalDate.now().minusDays(7);
            List<Employee> lowAttendance = employeeRepository.findEmployeesWithLowAttendance(
                startDate, 0.8);
            if (!lowAttendance.isEmpty()) {
                logger.warn("Found {} employees with low attendance", lowAttendance.size());
            }
        } catch (Exception e) {
            logger.error("Error in anomaly detection", e);
        }
    }
    
    @Scheduled(cron = "0 0 0 1 * ?") // Monthly on 1st
    public void calculateSalaryAdjustments() {
        try {
            LocalDate startDate = LocalDate.now().minusMonths(1);
            LocalDate endDate = LocalDate.now();
            PageRequest page = PageRequest.of(0, 10);
            attendanceRepository.findAttendanceTrends(startDate, endDate, page);
            logger.info("Monthly salary adjustments calculated");
        } catch (Exception e) {
            logger.error("Error in salary adjustment calculation", e);
        }
    }
}