import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Create a priority queue object.
        PQ pq = new PQ();
        // File path for the input file containing commands.
        String fp = "/example/file/path";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            String line;
            // Read the file line by line.
            while ((line = reader.readLine()) != null) {
                // Split each line into words.
                String[] words = line.split("\\s+");
                // Process commands based on the first word of each line.
                switch (words[0]) {
                    case "INSERT":
                        // Insert the word into the priority queue.
                        pq.insert(words[1]);
                        // Uncomment below line for debugging purposes to see insertions.
                        //System.out.println("INSERTING: " + words[1]);
                        break;
                    case "REMOVE":
                        // Remove the element with the highest priority from the queue.
                        pq.remove();
                        // Uncomment below line for debugging purposes to see removals.
                        //System.out.println("REMOVING: " + pq.remove());
                        break;
                    case "PEEK":
                        // Peek at the element with the highest priority without removing it.
                        System.out.println("PEEKING: " + pq.peek());
                        break;
                    default:
                        // Throw an exception if an unrecognized command is encountered.
                        throw new RuntimeException("Invalid command: " + words[0]);
                }
            }
        } catch (IOException e) {
            // Handle exceptions related to file operations.
            throw new RuntimeException(e);
        }
    }
}
