import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class pogocow {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("pogocow.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        int n = Integer.parseInt(r.readLine());
        
        Target[] targets = new Target[n];
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            targets[i] = new Target(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        Arrays.sort(targets);
        long points = 0;
        for (int i = 0; i < n; i++) {
            int leftJump = 0;
            int current = i;
            long leftPoints = targets[current].p;
            while (current - 1 > -1) {
                if (targets[current].x - targets[current - 1].x >= leftJump) {
                    leftJump = targets[current].x - targets[current - 1].x;
                    leftPoints += targets[--current].p;
                } else {
                    current--;
                }
            }
            
            int rightJump = 0;
            current = i;
            long rightPoints = targets[current].p;
            while (current + 1 < n) {
                if (targets[current + 1].x - targets[current].x >= rightJump) {
                    rightJump = targets[current + 1].x - targets[current].x;
                    rightPoints += targets[++current].p;
                } else {
                    current++;
                }
            }
            points = Math.max(points, Math.max(leftPoints, rightPoints));
        }
        
        w.println(points);
        w.flush();
    }

    public static class Target implements Comparable<Target> {
        int x, p;
        
        public Target(int x, int p) {
            this.x = x;
            this.p = p;
        }
        
        public int compareTo(Target t) {
            return x - t.x;
        }
        
        public String toString() {
            return x + " " + p;
        }
    }
}
