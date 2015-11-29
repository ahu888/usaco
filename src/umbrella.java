import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;


public class umbrella {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(r.readLine());
        }
        
        Arrays.sort(cows);
        
        int[] umbrellaCosts = new int[m + 1];
        for (int i = 0; i < m; i++) {
            umbrellaCosts[i + 1] = Integer.parseInt(r.readLine());
        }
        
        int[] dp = new int[n + 1]; //index is number of cows covered, value is cost
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = 1000000000;
        }
        for (int i = 0; i < n; i++) {
            //cover from last cow to the next
            for (int j = i; j < n; j++) {
                int distance = cows[j] - cows[i] + 1;
                
                dp[j + 1] = Math.min(dp[j + 1], dp[i] + umbrellaCosts[distance]);
            }
        }
        
        w.println(dp[dp.length - 1]);
        w.flush();
    }

}
