import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;


public class explore {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");

        int minutes = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        
        Int[] locations = new Int[n];
        
        for (int i = 0; i < n; i++) {
            locations[i] = new Int(Integer.parseInt(r.readLine()));
        }
        
        Arrays.sort(locations);
        
        int time = 0;
        int current = 0;
        int index = 0;
        while (time <= minutes) {
            if (index < n) {
                time += Math.abs(locations[index].val - current);
                current = locations[index].val;
                index++;
            } else {
                index++;
                break;
            }
            
        }
        
        w.println(index - 1);
        w.flush();
    }
    
    public static class Int implements Comparable<Int>{
        int val;
        int absval;
        
        public Int(int v) {
            val = v;
            absval = Math.abs(v);
        }
        
        public int compareTo(Int i) {
            return absval - i.absval;
        }
        
        public String toString() {
            return val + "";
        }
    }

}
