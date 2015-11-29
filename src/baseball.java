import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class baseball {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("baseball.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("baseball.out")));

        
        int n = Integer.parseInt(r.readLine());
        int[] positions = new int[n];
        
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.parseInt(r.readLine());
        }
        
        Arrays.sort(positions);
        
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int d1 = positions[j] - positions[i];
                    int d2 = positions[k] - positions[j];
                    if (d2 >= d1 && d2 <= 2*d1) {
                        count++;
                    }
                }
            }
        }
        
        w.println(count);
        w.flush();
    }

}
