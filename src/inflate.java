import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: inflate
 */

/*
 * Solution: Keep track of the highest score for a certain number of minutes in an array. Whenever you read in a new combination, you update the array.
 */
public class inflate {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("inflate.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");

        int minutes = Integer.parseInt(inputs[0]);
        int classCount = 0;
        int counter = 1;
        while (classCount == 0) {
            try {
                classCount = Integer.parseInt(inputs[counter]);
            } catch (Exception e) {
                counter++;
            }

        }

        
        int[] scores = new int[minutes + 1];

        for (int i = 0; i < classCount; i++) {
            input = r.readLine();
            inputs = input.split(" ");
            
            int points = Integer.parseInt(inputs[0]);
            int minute = Integer.parseInt(inputs[1]);
            
            for (int j = 0; j + minute <= minutes; j++) {
                scores[j + minute] = Math.max(scores[j] + points, scores[j + minute]);
            }
        }

        w.println(scores[minutes]);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
}
