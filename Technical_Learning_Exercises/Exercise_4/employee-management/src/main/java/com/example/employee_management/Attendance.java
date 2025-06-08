// PS C:\Users\HarsharajMS\Desktop\Technical_Assignments\Technical_Learning_Exercises\exercise_4\employee-management>
// to clean and rebuild : mvn clean install
// to run application : mvn spring-boot:run
// Access application at link : http://localhost:8080/h2-console


package com.example.employee_management;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    private LocalDate date;
    private boolean present;
    private int hoursWorked;
}