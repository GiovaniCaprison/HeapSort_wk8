import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PQ pq = new PQ();
        String fp = "/Users/louisgrennell/Documents/GitHub/lab8/text.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                switch (words[0]) {
                    case "INSERT":
                        pq.insert(words[1]);
                        //System.out.println("INSERTING: " + words[1]);
                        break;
                    case "REMOVE":
                        pq.remove();
                        //System.out.println("REMOVING: " + pq.remove());
                        break;
                    case "PEEK":
                        System.out.println("PEEKING: " + pq.peek());
                        break;
                    default:
                        throw new RuntimeException("Invalid command: " + words[0]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
