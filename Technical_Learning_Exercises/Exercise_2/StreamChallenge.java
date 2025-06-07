/*
 * Must Required for execution of the output : -
 * Compilation -
cd "C:\Users\HarsharajMS\Desktop\Technical_Assignments\Technical_Learning_Exercises"
javac Exercise_2/*.java

 * Running the code -
java -cp "C:\Users\HarsharajMS\Desktop\Technical_Assignments\Technical_Learning_Exercises" Exercise_2.StreamChallenge
 */




package Exercise_2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamChallenge {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.getEmployees();

        // Print dataset as a table
        System.out.println("=== Employee Dataset ===");
        System.out.printf("%-20s %-15s %-10s %-12s %-30s%n", 
            "Name", "Department", "Salary", "Start Date", "Skills");
        System.out.println("-".repeat(80));
        for (Employee emp : employees) {
            System.out.printf("%-20s %-15s %-10.2f %-12s %-30s%n",
                emp.getName(),
                emp.getDepartment(),
                emp.getSalary(),
                emp.getStartDate(),
                String.join(", ", emp.getSkills()));
        }
        System.out.println("-".repeat(80));


        try (java.io.PrintWriter writer = new java.io.PrintWriter("employees_output.csv")) {
            writer.println("Name,Department,Salary,Start Date,Skills");
            employees.forEach(emp -> 
            writer.println(String.format("%s,%s,%.2f,%s,%s",
            emp.getName(), emp.getDepartment(), emp.getSalary(),
            emp.getStartDate(), String.join("; ", emp.getSkills()))));
        System.out.println("Dataset exported to employees_output.csv");
    } catch (java.io.FileNotFoundException e) {
        System.err.println("Error exporting CSV: " + e.getMessage());
}


        // Challenge Set A: Basic Operations
        System.out.println("\n=== Challenge Set A ===");
        List<Employee> highEarners = employees.stream()
            .filter(e -> e.getSalary() > 75000)
            .collect(Collectors.toList());
        System.out.println("Employees earning > $75,000: " + highEarners);

        List<String> departments = employees.stream()
            .map(Employee::getDepartment)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Unique departments: " + departments);

        double totalSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
        System.out.println("Total salary expenditure: $" + totalSalary);

        // Challenge Set B: Intermediate Analysis
        System.out.println("\n=== Challenge Set B ===");
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, 
                     Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average salary by department: " + avgSalaryByDept);

        Set<String> uniqueSkills = employees.stream()
            .flatMap(e -> e.getSkills().stream())
            .distinct()
            .sorted()
            .collect(Collectors.toSet());
        System.out.println("Unique skills: " + uniqueSkills);

        List<String> topEarners = employees.stream()
            .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
            .limit(3)
            .map(Employee::getName)
            .collect(Collectors.toList());
        System.out.println("Top 3 highest-paid employees: " + topEarners);

        // Challenge Set C: Advanced Reporting
        System.out.println("\n=== Challenge Set C ===");
        Map<String, String> deptEmployeeNames = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                     Collectors.mapping(Employee::getName, 
                     Collectors.joining(", "))));
        System.out.println("Department employee names: " + deptEmployeeNames);

        Map<String, List<Employee>> salaryBands = employees.stream()
            .collect(Collectors.groupingBy(e -> {
                if (e.getSalary() <= 50000) return "0-50k";
                else if (e.getSalary() <= 80000) return "50k-80k";
                else return "80k+";
            }));
        System.out.println("Salary bands: ");
        salaryBands.forEach((band, list) -> 
            System.out.println(band + ": " + list.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "))));

        Map<String, Optional<Employee>> topByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                     Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println("Highest-paid per department: ");
        topByDept.forEach((dept, emp) -> 
            System.out.println(dept + ": " + emp.map(Employee::toString).orElse("None")));
    }
}

// See Comment Above *Required*