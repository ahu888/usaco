import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;


public class ccski {
    static int bestVal = 0;
    static int canVisit = 0;
    static int n;
    static int m;
    static int x,y;
    static int numCheckpoints = 0;
    static boolean[][] checkpoints;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("ccski.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
        
        String[] inputs = r.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);
        int min = Integer.MAX_VALUE;
        int max = 0;
        int[][] heights = new int[n][m];
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                heights[i][j] = Integer.parseInt(inputs[j]);
                if (heights[i][j] < min) {
                    min = heights[i][j];
                }
                if (heights[i][j] > max) {
                    max = heights[i][j];
                }
            }
        }
        
        
        checkpoints = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                checkpoints[i][j] = inputs[j].equals("1");
                x = i;
                y = j;
                if (checkpoints[i][j])
                    numCheckpoints++;
            }
        }
        
        //binary search
        
        binarySearch(heights, min, max);
        
        w.println(bestVal);
        w.flush();

    }
    
    public static void binarySearch(int[][] heights, int minr, int maxr) {
        if (minr == maxr) {
            return;
        }
        int current = (minr + maxr)/2;
        //search to see if current works
        boolean[][] visited = new boolean[n][m];
        int highestVisited = 0;
        search(heights, visited, x, y, current);
        if (canVisit > highestVisited) {
            highestVisited = canVisit;
        }
        canVisit = 0;
        if (highestVisited == numCheckpoints) {
            bestVal = current;
            binarySearch(heights, minr, current);
        } else if (highestVisited < numCheckpoints) {
            binarySearch(heights, current + 1, maxr);
        }
    }
    
    public static void search(int[][] heights, boolean[][] visited, int x, int y, int cost) {
        LinkedList<Point> queue = new LinkedList<Point>();
        Point current = new Point(x, y);
        queue.add(current);
        while (queue.peek() != null) {
            current = queue.pop();
            x = current.x;
            y = current.y;
            visited[x][y] = true;
            if (checkpoints[x][y])
                canVisit++;
            if (x - 1 >= 0 && !visited[x - 1][y] && Math.abs(heights[x - 1][y] - heights[x][y]) <= cost) {
                queue.add(new Point(x - 1, y));
                visited[x - 1][y] = true;
                //search(heights, visited, x - 1, y, cost);
            }
            if (x + 1 < n && !visited[x + 1][y] && Math.abs(heights[x + 1][y] - heights[x][y]) <= cost) {
                queue.add(new Point(x + 1, y));
                visited[x + 1][y] = true;
                //search(heights, visited, x + 1, y, cost);
            }
            if (y - 1 >= 0 && !visited[x][y - 1] && Math.abs(heights[x][y - 1] - heights[x][y]) <= cost) {
                queue.add(new Point(x, y - 1));
                visited[x][y - 1] = true;
                //search(heights, visited, x, y - 1, cost);
            }
            if (y + 1 < m && !visited[x][y + 1] && Math.abs(heights[x][y + 1] - heights[x][y]) <= cost) {
                queue.add(new Point(x, y + 1));
                visited[x][y + 1] = true;
                //search(heights, visited, x, y + 1, cost);
            }
        }
        
    }
    
    public static class Point {
        int x, y;
        
        public Point(int a, int b) {
            x = a;
            y = b;
        }
    }

}
