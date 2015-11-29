import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class assign {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("assign.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("assign.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        
        int[] values = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            values[i] = 3; //3 possible breeds
        }
        
        for (int i = 0; i < k; i++) {
            inputs = r.readLine().split(" ");
            
            if (inputs[0].equals("S")) {
                values[Integer.parseInt(inputs[2])] = 1;
            } else {
                values[Integer.parseInt(inputs[2])]--;
            }
        }
        
        long answer = 1;
        for (int i = 1; i < n + 1; i++) {
            answer *= values[i];
        }
        
        w.println(answer);
        w.flush();
    }

}
