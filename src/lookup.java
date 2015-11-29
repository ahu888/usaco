import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class lookup {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        int n = Integer.parseInt(r.readLine());

        int[] heights = new int[n];
        int[] lookups = new int[n];
        
        for (int i = 0; i < n; i++) {
            lookups[i] = -1;
        }
        //long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(r.readLine());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (heights[i] < heights[j]) {
                    lookups[i] = j + 1;
                    break;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (lookups[i] == -1) {
                w.println(0);
            } else
                w.println(lookups[i]);
        }
        
        w.flush();
    }

}
