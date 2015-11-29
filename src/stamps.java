import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: farmersrice
LANG: JAVA
TASK: stamps
*/

public class stamps {
    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        BufferedReader r = new BufferedReader(new FileReader("stamps.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int k = Integer.parseInt(inputs[0]); //max number of stamps
        int n = Integer.parseInt(inputs[1]); //number of different stamp values
        
        int[] values = new int[n]; //values of stamps
        
        int counter = 0;
        String line = r.readLine();
        while (line != null) {
            inputs = line.split(" ");
            for (int i = 0; i < inputs.length; i++) {
                values[counter++] = Integer.parseInt(inputs[i]);
            }
            line = r.readLine();
        }
        
        Arrays.sort(values);
        
        int maxValue = values[values.length - 1] * k + 1;
        
        int[] leastStamps = new int[maxValue + 1];
        
        for (int i = 1; i < leastStamps.length; i++) {
            leastStamps[i] = k + 1;
        }
        
        for (int i = 0; i < values.length; i++) {
            leastStamps[values[i]] = 1;
        }
        
        int i;
        for (i = 1; i < maxValue; i++) {
            for (int j = 0; j < values.length; j++) {
                if (i + values[j] < leastStamps.length)
                    leastStamps[i + values[j]] = Math.min(leastStamps[i + values[j]], leastStamps[i] + 1);
            }
            if (leastStamps[i] > k) break;
        }
        
        w.println(i - 1);
        w.flush();
        r.close();
        w.close();
        //System.out.println(System.currentTimeMillis() - start);
        System.exit(0);
    }
}
