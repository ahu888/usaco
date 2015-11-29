import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;


public class farmoff {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int a = Integer.parseInt(inputs[1]);
        int b = Integer.parseInt(inputs[2]);
        int c = Integer.parseInt(inputs[3]);
        int d = Integer.parseInt(inputs[4]);
        int e = Integer.parseInt(inputs[5]);
        int f = Integer.parseInt(inputs[6]);
        int g = Integer.parseInt(inputs[7]);
        int h = Integer.parseInt(inputs[8]);
        int m = Integer.parseInt(inputs[9]);
        
        Cow[] cows = new Cow[3*n];
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < 3*n; i++) {
            long weight = ((a * powerOf(i, 5, d)) + (b * powerOf(i, 2, d)) + c) % d;
            long utility = ((e * powerOf(i, 5, h)) + (f * powerOf(i, 3, h)) + g) % h;
            cows[3 * n - i - 1] = new Cow(weight, (int) utility);
        }
        //System.out.println(System.currentTimeMillis() - start);
        Arrays.sort(cows);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += cows[i].weight;
            sum %= m;
        }
        w.println(sum);
        //System.out.println(System.currentTimeMillis() - start);
        w.flush();
    }
    
    public static long powerOf(long base, long exponent, long mod) {
        long answer = 1;
        for (long i = 0; i < exponent; i++) {
            answer = (answer * base) % mod;
        }
        return answer;
    }

    public static class Cow implements Comparable<Cow> {
        long weight;
        int utility;
        
        public Cow(long w, int u) {
            weight = w;
            utility = u;
        }
        
        public int compareTo(Cow c) {
            return c.utility - utility;
            /*
            if (c.utility - this.utility > 0) {
                return 1;
            } else if (c.utility == utility) {
                return 0;
            }
            return -1;*/
            //return c.utility - this.utility;
        }
        /*
        public String toString() {
            return weight + " " + utility;
        }*/
    }
}
