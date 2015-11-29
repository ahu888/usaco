import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;


public class proximity {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("proximity.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("proximity.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        
        int[] cows = new int[n];
        
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(r.readLine());
        }
        
        
        int highest = 0;
        HashMap<Integer, Integer> contained = new HashMap<Integer, Integer>(); //key is the number, value is the number of occurences of that number
        for (int i = 0; i <= k; i++) {
            if (!contained.containsKey(cows[i])) {
                contained.put(cows[i], 1);
            } else {
                contained.put(cows[i], contained.get(cows[i]) + 1);
                if (contained.get(cows[i]) >= 2 && cows[i] > highest) {
                    highest = cows[i];
                }
            }
        }
        
        for (int i = 1; i < n - k; i++) {
            //i represents the start of the group
            //remove i - 1

            if (!contained.containsKey(cows[i - 1])) {
                contained.put(cows[i - 1], 1);
            } else {
                contained.put(cows[i  - 1], contained.get(cows[i - 1]) - 1);
                if (contained.get(cows[i - 1]) >= 2 && cows[i - 1] > highest) {
                    highest = cows[i - 1];
                }
            }
            //contained.put(cows[i - 1], contained.get(cows[i - 1]) - 1);
            
            if (!contained.containsKey(cows[i + k])) {
                contained.put(cows[i + k], 1);
            } else {
                contained.put(cows[i + k], contained.get(cows[i + k]) + 1);
                if (contained.get(cows[i + k]) >= 2 && cows[i + k] > highest) {
                    highest = cows[i + k];
                }
            }
        }
        
        w.println(highest);
        w.flush();
    }

}
