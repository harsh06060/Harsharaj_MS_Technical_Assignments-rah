import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApproachB {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        long itCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                if (line.split(",")[2].equals("IT")) {
                    itCount++;
                }
            }
            System.out.println("Employees in IT: " + itCount);
            
            // Pause to keep process alive for JConsole
            System.out.println("Press Enter to exit...");
            System.in.read();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}