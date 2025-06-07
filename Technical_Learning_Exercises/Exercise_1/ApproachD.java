import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ApproachD {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            long itCount = lines
                .skip(1) // Skip header
                .filter(line -> line.split(",")[2].equals("IT"))
                .count();
            System.out.println("Employees in IT: " + itCount);
            
            // Pause to keep process alive for JConsole
            System.out.println("Press Enter to exit...");
            System.in.read();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}