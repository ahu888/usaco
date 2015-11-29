import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class lazy {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("rblock.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        
        int[][] values = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                values[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //we are currently testing bessie's location at [i][j];
                
            }
        }
    }

}
