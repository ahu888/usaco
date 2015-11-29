import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class crowded {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("crowded.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int d = Integer.parseInt(inputs[1]);
        
        Cow[] cows = new Cow[n];
        
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            cows[i] = new Cow(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        long start = System.currentTimeMillis();
        Arrays.sort(cows);
        System.out.println(System.currentTimeMillis() - start);
        int crowded = 0;
        boolean left = false;
        boolean right = false;
        long lex = 0;
        long rex = 0;
        for (int i = 0; i < n; i++) {
            //boolean left = false;
            //boolean right = false;
            
            for (int j = i - 1; i - j < d && j >= 0; j--) {
                lex++;
                if (cows[j].h >= cows[i].h << 1) {
                    left = true;
                    break;
                }
            }
            if (!left) continue;
            for (int j = i + 1; j - i < d && j < n; j++) {
                rex++;
                if (cows[j].h >= cows[i].h << 1) {
                    right = true;
                    break;
                }
            }
            
            if (right) {
                crowded++;
                cows[i].crowded = true;
            }
            left = false;
            right = false;
        }
        //System.out.println(lex + " " + rex);
        //System.out.println(System.currentTimeMillis() - start);
        w.println(crowded);
        w.flush();
    }

    public static class Cow implements Comparable<Cow> {
        int x, h;
        boolean crowded = false;
        public Cow(int x, int h) {
            this.x = x;
            this.h = h;
        }
        
        public int compareTo(Cow c) {
            return c.x - x;
        }
    }
}
