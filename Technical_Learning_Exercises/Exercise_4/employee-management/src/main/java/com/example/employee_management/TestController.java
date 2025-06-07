package com.example.employee_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @Autowired
    private SchedulingService schedulingService;
    
    @GetMapping("/test/archive")
    public String triggerArchive() {
        schedulingService.archiveOldData();
        return "Archive task triggered";
    }

    @GetMapping("/test/salary-adjustments")
    public String triggerSalaryAdjustments() {
        schedulingService.calculateSalaryAdjustments();
        return "Salary adjustments triggered";
    }
}