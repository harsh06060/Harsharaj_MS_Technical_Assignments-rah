package Exercise_2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees() {
        return Arrays.asList(
            new Employee("John Doe", "IT", 85000, LocalDate.of(2020, 1, 15), 
                Arrays.asList("Java", "Python", "SQL")),
            new Employee("Jane Smith", "HR", 65000, LocalDate.of(2019, 3, 10), 
                Arrays.asList("Communication", "HRIS")),
            new Employee("Alex Brown", "IT", 95000, LocalDate.of(2021, 6, 20), 
                Arrays.asList("Java", "Spring", "Docker")),
            new Employee("Emily Davis", "Finance", 72000, LocalDate.of(2020, 9, 5), 
                Arrays.asList("Excel", "Accounting")),
            new Employee("Michael Chen", "IT", 120000, LocalDate.of(2018, 11, 1), 
                Arrays.asList("Java", "Kubernetes", "AWS")),
            new Employee("Sarah Wilson", "Marketing", 60000, LocalDate.of(2022, 2, 25), 
                Arrays.asList("SEO", "Content Creation")),
            new Employee("David Lee", "Finance", 80000, LocalDate.of(2019, 7, 14), 
                Arrays.asList("Accounting", "Financial Analysis")),
            new Employee("Lisa Taylor", "HR", 70000, LocalDate.of(2021, 4, 12), 
                Arrays.asList("Recruiting", "HRIS"))
        );
    }
}