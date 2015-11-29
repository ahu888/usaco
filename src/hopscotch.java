import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class hopscotch {
    //richie aidan soumil daniel nicholas
    //anish arya kavya
    static final int mod = 1000000007;
    //solution is 2d dp gg
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));

        String[] inputs = r.readLine().split(" ");
        
        int rows = Integer.parseInt(inputs[0]);
        int cols = Integer.parseInt(inputs[1]);
        int number = Integer.parseInt(inputs[2]); //range of numbers each pad is labeled with
        
        int[][] data = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            inputs = r.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                data[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        
        int[][] dp = new int[rows][cols];
        dp[0][0] = 1;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < i; k++) {
                    for (int l = 0; l < j; l++) {
                        if (data[i][j] != data[k][l]) {
                            dp[i][j] += dp[k][l];
                            dp[i][j] %= mod;
                        }
                        
                    }
                }
            }
        }
        
        w.println(dp[rows - 1][cols - 1]);
        w.flush();
    }

}
