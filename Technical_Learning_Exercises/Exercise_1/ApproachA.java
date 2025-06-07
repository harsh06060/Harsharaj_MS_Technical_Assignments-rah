import java.io.IOException;
   import java.nio.file.Files;
   import java.nio.file.Paths;
   import java.util.List;

   public class ApproachA {
       public static void main(String[] args) throws IOException {
           String fileName = "employees.csv";
           try {
               // Read all lines into memory
               List<String> lines = Files.readAllLines(Paths.get(fileName));
               System.out.println("Read " + lines.size() + " lines");

               // Process: Count employees in IT department
               long itCount = lines.stream()
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