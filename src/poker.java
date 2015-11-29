import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class poker {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("poker.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("poker.out")));

        int n = Integer.parseInt(r.readLine());
        
        int[] values = new int[n + 1];
        long total = 0;
        for (int i = 0; i < n; i++) {
            values[i + 1] = Integer.parseInt(r.readLine());
            total += values[i + 1];
        }
        
        long answer = 0;
        while (total > 0) {
            //find next sequence to remove
            
            int start = -1;
            int end = -1;
            int min = 1000000000;
            for (int i = 1; i < values.length; i++) {
                if (values[i] != 0) {
                    if (start == -1) {
                        start = i;
                    }
                    end = i;
                    if (values[i] < min) {
                        min = values[i];
                    }
                } else { //values[i] == 0
                    if (start == -1) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
            
            for (int i = start; i <= end; i++) {
                values[i] -= min;
                total -= min;
            }
            
            answer += min;
        }
        
        w.println(answer);
        w.flush();
        
    }

}
