import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class haybales {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        int n = Integer.parseInt(r.readLine());
        
        int[] values = new int[n];
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(r.readLine());
            sum += values[i];
        }
        
        int average = sum/n;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += Math.abs(average - values[i]);
        }
        
        w.println(answer/2);
        w.flush();
    }

}
