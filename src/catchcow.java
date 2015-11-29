import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class catchcow {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new FileReader("catchcow.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("catchcow.out")));
              //System.out.println(System.getProperty("java.runtime.version"));
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        int[] points = new int[Math.max(n, k) + 2];
        for (int i = 0; i < points.length; i++) {
            points[i] = infinity;
        }
        
        //bred first se3rch
        
        LinkedList<Position> queue = new LinkedList<Position>();
        
        queue.add(new Position(n, 0));
        
        Position current = queue.remove();
        
        while (current.pos != k) {
            int currentpos = current.pos;
            int cost = current.cost;
            if (currentpos + 1 < points.length && points[currentpos + 1] > cost + 1) {
                queue.add(new Position(currentpos + 1, cost + 1));
                points[currentpos + 1] = cost + 1;
            }
            if (currentpos - 1 > - 1 && points[currentpos - 1] > cost + 1) {
                queue.add(new Position(currentpos - 1, cost + 1));
                points[currentpos - 1] = cost + 1;
            }
            if (currentpos * 2 < points.length && points[currentpos * 2] > cost + 1) {
                queue.add(new Position(currentpos * 2, cost + 1));
                points[currentpos * 2] = cost + 1;
            }
            current = queue.remove();
        }
        
        if (n != k)
            w.println(current.cost);
        else 
            w.println(0);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static class Position {
        int pos;
        int cost;
        
        public Position(int p, int c) {
            pos = p;
            cost = c;
        }
        
        public String toString() {
            return pos + " " + cost;
        }
    }

}
