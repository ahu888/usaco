import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class trapped {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("trapped.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));

        int n = Integer.parseInt(r.readLine());
        
        Bale[] bales = new Bale[n];
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            bales[i] = new Bale(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        Arrays.sort(bales);
        
        for (int i = 0; i < bales.length; i++) {
            bales[i].index = i;
        }
        //brute force
        
        int area = 0;
        outer:
        for (int i = 0; i < n - 1; i++) { //check area between i and i + 1
            Bale lowerBound = bales[i];
            Bale upperBound = bales[i + 1];
            
            while (lowerBound != null && upperBound != null) {
                int power = upperBound.x - lowerBound.x;
                //check if we can break left or right
                boolean changed = false;
                if (power > lowerBound.size) {
                    if (lowerBound.index == 0) {
                        lowerBound = null;
                    } else {
                        lowerBound = bales[lowerBound.index - 1];
                    }
                    changed = true;
                }
                
                if (power > upperBound.size) {
                    if (upperBound.index == bales.length - 1) {
                        upperBound = null;
                    } else {
                        upperBound = bales[upperBound.index + 1];
                    }
                    changed = true;
                }
                
                if (!changed) {
                    area += bales[i + 1].x - bales[i].x;
                    continue outer;
                }
            }
            
        }
        
        w.println(area);
        w.flush();
    }
    
    public static class Bale implements Comparable<Bale> {
        int x, size, index;
        
        public Bale(int a, int s) {
            size = a;
            x = s;
        }
        
        public int compareTo(Bale b) {
            return x - b.x;
        }
        
        public String toString() {
            return size + " " + x;
        }
    }

}
