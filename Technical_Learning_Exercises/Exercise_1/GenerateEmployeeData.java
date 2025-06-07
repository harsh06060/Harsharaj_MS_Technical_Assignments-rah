import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class GenerateEmployeeData {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        String[] departments = {"HR", "IT", "Finance", "Marketing"};
        String[] firstNames = {"John", "Jane", "Alex", "Emily", "Michael"};
        String[] lastNames = {"Smith", "Doe", "Brown", "Wilson", "Taylor"};
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write CSV header
            writer.write("ID,Name,Department,Salary,Email,Address\n");

            // Generate ~1 million records (~100MB)
            for (int i = 0; i < 1_000_000; i++) {
                String id = UUID.randomUUID().toString();
                String name = firstNames[random.nextInt(firstNames.length)] + " " + 
                             lastNames[random.nextInt(lastNames.length)];
                String dept = departments[random.nextInt(departments.length)];
                double salary = 30000 + random.nextDouble() * 120000; // 30K-150K
                String email = name.replace(" ", ".").toLowerCase() + "@company.com";
                String address = "123" + i + " Main St, City" + random.nextInt(100);

                // Write one row
                writer.write(String.format("%s,%s,%s,%.2f,%s,%s\n", 
                    id, name, dept, salary, email, address));

                if (i % 100000 == 0) {
                    System.out.println("Generated " + i + " records");
                }
            }
            System.out.println("CSV file generated: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}