import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;


public class flowers {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        //int mod = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        
        Cow[] cows = new Cow[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            int d = Integer.parseInt(inputs[1]);
            cows[i] = new Cow(Integer.parseInt(inputs[0]), d);
            sum += d;
        }
        Arrays.sort(cows);
        
        long destroyed = 0;
        
        for (int i = 0; i < n; i++) {
            sum -= cows[i].d;
            destroyed += cows[i].t * sum;
            //destroyed %= mod;
        }

        w.println(destroyed);
        w.flush();
    }
    
    public static class Cow implements Comparable<Cow> {
        int t, d;
        double ratio;
        
        public Cow(int t, int d) {
            this.t = t;
            this.d = d;
            ratio = ((double) d)/t;
        }
        
        public int compareTo(Cow c) {
            if (c.ratio > this.ratio) {
                return 1;
            } else if (c.ratio == ratio) {
                return 0;
            }
            return -1;
        }
        
        public String toString() {
            return t + " " + d;
        }
    }

}
