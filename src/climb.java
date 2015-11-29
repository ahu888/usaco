import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class climb {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        int n = Integer.parseInt(r.readLine());
        
        //we have the fastest cows go up first, then the order going down doesn't matter
        
        Cow[] cows = new Cow[n];
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            int up = Integer.parseInt(inputs[0]);
            int down = Integer.parseInt(inputs[1]);
            cows[i] = new Cow(up, down);
        }
        
        Arrays.sort(cows);
        
        
        long time = 0;
        
        ArrayList<Cow> atTop = new ArrayList<Cow>();
        
        for (int i = 0; i < cows.length; i++) {
            time += cows[i].up;
            
            int timeUsed = cows[i].up;
            
            if (atTop.size() > 0) {
                Cow current = atTop.get(0);
                
                boolean changed = false;
                while (!changed) {
                    if (current.timeDown > 0) {
                        current.timeDown -= timeUsed;
                        changed = true;
                        if (current.timeDown <= 0) {
                            timeUsed = Math.abs(current.timeDown);
                            atTop.remove(0);
                            if (atTop.size() > 0) {
                                current = atTop.get(0);
                                changed = false;
                            }
                        }
                    }
                }
            }
            
            
            atTop.add(cows[i]);
        }
        
        if (atTop.size() > 0) {
            for (int i = 0; i < atTop.size(); i++) {
                time += atTop.get(i).timeDown;
            }
        }
        
        w.println(time);
        w.flush();
    
    }

    public static class Cow implements Comparable<Cow> {
        int up, down;
        int timeDown;
        
        public Cow(int u, int d) {
            up = u;
            down = d;
            timeDown = d;
        }

        @Override
        public int compareTo(Cow o) {
            if (o.up < o.down && up < down) {
                return up - o.up;
            } else if (o.up < o.down && up >= down) {
                return 100000;
            } else if (up < down && o.up >= o.down) {
                return -100000;
            } else if (o.up >= o.down && up >= down) {
                return o.down - down;
            }
            return 0;
        } 
        
        public String toString() {
            return up + " " + down + " " + timeDown;
        }
    }
}
