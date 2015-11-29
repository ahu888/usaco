import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class greetings {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("greetings.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("greetings.out")));

        String[] inputs = r.readLine().split(" ");
        int b = Integer.parseInt(inputs[0]);
        int e = Integer.parseInt(inputs[1]);
        
        Move[] bessieMoves = new Move[b];
        Move[] elsieMoves = new Move[e];
        
        int btime = 0;
        for (int i = 0; i < b; i++) {
            inputs = r.readLine().split(" ");
            bessieMoves[i] = new Move(inputs[1].equals("R"), Integer.parseInt(inputs[0]));
            btime += Integer.parseInt(inputs[0]);
        }
        
        int etime = 0;
        
        for (int i = 0; i < e; i++) {
            inputs = r.readLine().split(" ");
            elsieMoves[i] = new Move(inputs[1].equals("R"), Integer.parseInt(inputs[0]));
            etime += Integer.parseInt(inputs[0]);
        }
        
        
        Cow bessie = new Cow(Math.max(btime, etime));
        Cow elsie = new Cow(Math.max(btime, etime));
        
        btime = 0;
        etime = 0;
        
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < bessieMoves[i].distance; j++) {
                if (bessieMoves[i].direction)
                    bessie.atTime[btime + j + 1] = bessie.atTime[btime + j] + 1;
                else
                    bessie.atTime[btime + j + 1] = bessie.atTime[btime + j] - 1;
            }
            btime += bessieMoves[i].distance;
        }
        for (int i = btime + 1; i < bessie.atTime.length; i++) {
            bessie.atTime[i] = bessie.atTime[btime];
        }
        btime = 0;
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < elsieMoves[i].distance; j++) {
                if (elsieMoves[i].direction)
                    elsie.atTime[btime + j + 1] = elsie.atTime[btime + j] + 1;
                else
                    elsie.atTime[btime + j + 1] = elsie.atTime[btime + j] - 1;
            }
            btime += elsieMoves[i].distance;
        }
        for (int i = btime + 1; i < elsie.atTime.length; i++) {
            elsie.atTime[i] = elsie.atTime[btime];
        }
        
        int sum = 0;
        for (int i = 1; i < bessie.atTime.length; i++) {
            if (bessie.atTime[i] == elsie.atTime[i] && bessie.atTime[i - 1] != elsie.atTime[i - 1]) {
                sum++;
            }
        }
        
        w.println(sum);
        w.flush();
    }
    
    public static class Cow {
        int[] atTime;
        
        public Cow(int time) {
            atTime = new int[time + 1];
        }
    }

    public static class Move {
        boolean direction; //false means left true means right
        int distance;
        
        public Move(boolean dir, int dist) {
            direction = dir;
            distance = dist;
        }
        
        public String toString() {
            return distance + " " + direction;
        }
    }
}
