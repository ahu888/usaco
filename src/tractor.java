import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;


public class tractor {

    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {

      //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("tractor.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("tractor.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int x = Integer.parseInt(inputs[1]);
        int y = Integer.parseInt(inputs[2]);
        
        //int[][] points = new int[1002][1002]; //stores costs
        
        
        boolean[][] haybales = new boolean[1002][1002];
        int maxx = x;
        int maxy = y;
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            //points[Integer.parseInt(inputs[0])][Integer.parseInt(inputs[1])] = infinitybales; //hale bale there
            int currentx = Integer.parseInt(inputs[0]);
            int currenty = Integer.parseInt(inputs[1]);
            haybales[currentx][currenty] = true;
            maxx = Math.max(maxx, currentx);
            maxy = Math.max(maxy, currenty);
        }
        
        int[][] points = new int[maxx + 2][maxy + 2];
        for (int i = 0; i < maxx + 2; i++) {
            for (int j = 0; j < maxy + 2; j++) {
                points[i][j] = infinity;
            }
        }
        
        Comparator<Point> comparator = new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                // TODO Auto-generated method stub
                return o1.cost - o2.cost;
            }
            
        };
        PriorityQueue<Point> queue = new PriorityQueue<>(10, comparator);
        
        queue.add(new Point(x, y, 0));
        points[x][y] = 0;
        Point current = queue.remove();
        while (points[0][0] == infinity) {
            int cx = current.x;
            int cy = current.y;
            
            int cost = current.cost;
            if (cy + 1 < maxy + 2 && haybales[current.x][current.y + 1] && points[cx][cy + 1] > cost + 1) {
                queue.add(new Point(cx, cy + 1, cost + 1));
                points[cx][cy + 1] = cost + 1;
            } else if (cy + 1 < maxy + 2 && points[cx][cy + 1] > cost && !haybales[current.x][current.y + 1]) {
                queue.add(new Point(cx, cy + 1, cost));
                points[cx][cy + 1] = cost;
            }
            
            if (cy - 1 > -1 && haybales[current.x][current.y - 1] && points[cx][cy - 1] > cost + 1) {
                queue.add(new Point(cx, cy - 1, cost + 1));
                points[cx][cy - 1] = cost + 1;
            } else if (cy - 1 > -1 && points[cx][cy - 1] > cost && !haybales[current.x][current.y - 1]) {
                queue.add(new Point(cx, cy - 1, cost));
                points[cx][cy - 1] = cost;
            }
            
            if (cx + 1 < maxx + 2 && haybales[current.x + 1][current.y] && points[cx + 1][cy] > cost + 1) {
                queue.add(new Point(cx + 1, cy, cost + 1));
                points[cx + 1][cy] = cost + 1;
            } else if (cx + 1 < maxx + 2 && points[cx + 1][cy] > cost && !haybales[current.x + 1][current.y]) {
                queue.add(new Point(cx + 1, cy, cost));
                points[cx + 1][cy] = cost;
            }
            
            if (cx - 1 > -1 && haybales[current.x - 1][current.y] && points[cx - 1][cy] > cost + 1) {
                queue.add(new Point(cx - 1, cy, cost + 1));
                points[cx - 1][cy] = cost + 1;
            } else if (cx - 1 > -1 && points[cx - 1][cy] > cost && !haybales[current.x - 1][current.y]) {
                queue.add(new Point(cx - 1, cy, cost));
                points[cx - 1][cy] = cost;
            }
            
            current = queue.remove();
        }
        
        w.println(points[0][0]);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static class Point implements Comparable<Point> {
        int x, y;
        int cost = 0;
        
        public Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            cost = c;
        }

        @Override
        public int compareTo(Point o) {
            return cost - o.cost;
        }
        
        public String toString() {
            return "(" + x + ", " + y + ")" + cost;
        }
    }

}