import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;


public class baler {
    static Integer[] path = null;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int xt = Integer.parseInt(inputs[1]);
        int yt = Integer.parseInt(inputs[2]);

        Roller[] rollers = new Roller[n];
        int startindex = 0;
        int endindex = 0;
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            int radius = Integer.parseInt(inputs[2]);
            
            if (x == 0 && y == 0) {
                rollers[i] = new Roller(x, y, radius, true, false, i);
                startindex = i;
            } else if (x == xt && y == yt) {
                rollers[i] = new Roller(x, y, radius, false, true, i);
                endindex = i;
            } else 
                rollers[i] = new Roller(x, y, radius, i);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.pow(rollers[i].r + rollers[j].r, 2) == Math.pow(rollers[i].x - rollers[j].x, 2) + Math.pow(rollers[i].y - rollers[j].y, 2)) {
                    rollers[i].addAdjacent(rollers[j]);
                }
            }
        }
        /*
        //bfs to find the path
        LinkedList<Roller> queue = new LinkedList<Roller>();
        queue.add(rollers[startindex]);
        
        Roller current = null;
        while (current != rollers[endindex]) {
            current = queue.pop();
            
            for (int i = 0; i < current.adjacent.size(); i++) {
                queue.add(current.adjacent.get(i));
            }
        }*/
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(startindex);
        search(rollers, list, rollers[startindex], endindex);
        
        double sumSpeeds = 10000;
        double prevSpeed = 10000;
        //Integer[] path = baler.path.toArray(new Integer[baler.path.size()]);
        for (int i = 1; i < path.length; i++) {
            sumSpeeds += prevSpeed*((double) rollers[path[i - 1]].r/rollers[path[i]].r);
            prevSpeed = prevSpeed*((double) rollers[path[i - 1]].r/rollers[path[i]].r);
        }
        sumSpeeds += 0.000000000000001;
        w.println((long) sumSpeeds);
        w.flush();
    }

    
    public static class Roller {
        int x, y, r, number;
        boolean start, drive, visited;
        ArrayList<Roller> adjacent = new ArrayList<Roller>();
        int speed;
        
        public Roller(int x, int y, int r, int n) {
            number = n;
            this.x = x;
            this.y = y;
            this.r = r;
            //calculateTouching();
        }
        
        public Roller(int x, int y, int r, boolean s, boolean d, int n) {
            number = n;
            this.x = x;
            this.y = y;
            this.r = r;
            //calculateTouching();
            start = s;
            drive = d;
            if (s) {
                speed = 10000;
            }
        }
        
        public void addAdjacent(Roller adj) {
            adjacent.add(adj);
        }
        
        public String toString() {
            return x + " " + y + " " + r;
        }
    }
    
    public static void search(Roller[] rollers, ArrayList<Integer> path, Roller current, int endindex) {
        if (baler.path != null) return;
        if (current == rollers[endindex]) {
            baler.path = path.toArray(new Integer[path.size()]);
            return;
        }
        current.visited = true;
        for (int i = 0; i < current.adjacent.size(); i++) {
            if (baler.path != null)
                return;
            if (current.adjacent.get(i).visited)
                continue;
            path.add((Integer) current.adjacent.get(i).number);
            search(rollers, path, current.adjacent.get(i), endindex);
            path.remove((Integer) current.adjacent.get(i).number);
        }
    }
}
