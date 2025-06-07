import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApproachC {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        int[] bufferSizes = {1024, 8192, 16384}; // 1KB, 8KB, 16KB

        for (int bufferSize : bufferSizes) {
            long itCount = 0;
            long startTime = System.nanoTime();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName), bufferSize)) {
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
                long endTime = System.nanoTime();
                System.out.println("Buffer Size: " + bufferSize + " bytes");
                System.out.println("Employees in IT: " + itCount);
                System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");
            
                // Pause to keep process alive for JConsole
                System.out.println("Press Enter to exit...");
                System.in.read();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}