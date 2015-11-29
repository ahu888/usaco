import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;


public class gravity {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("gravity.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("gravity.out")));
        
        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        char[][] grid = new char[n][m];
        
        int startr = 0;
        int startc = 0;
        int endr = 0;
        int endc = 0;
        for (int i = 0; i < n; i++) {
            char[] line = r.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line[j];
                if (line[j] == 'C') {
                    startr = i;
                    startc = j;
                }
                if (line[j] == 'D') {
                    endr = i;
                    endc = j;
                }
            }
        }
        
        PriorityQueue<Location> queue = new PriorityQueue<Location>();
        
        queue.add(new Location(startr, startc, 0, false));
        
        //Location previous = new Location(-1, -1, 0, false);
        Location current = queue.remove(); //fall first
        for (int i = 0; i < n; i++) {
            if (grid[i][current.c] == '#') {
                current = new Location(i - 1, current.c, 0, false);
            }
        }
        
        boolean[][] visited = new boolean[n][m];
        boolean impossible = true;
        for (int i = 0; i < n; i++) {
            if (grid[i][current.c] == '#')
                impossible = false;
        }
        if (impossible) {
            w.println(-1);
            w.flush();
            r.close();
            w.close();
            System.exit(0);
        }
        while (current.r != endr || current.c != endc) {
            if (current.gravity) { //we can flip gravity downwards, or go left or right
                boolean canFlip = false;
                int flipr = 0;
                for (int i = current.r + 1; i < grid.length; i++) {
                    if (grid[i][current.c] == '#') {
                        canFlip = true;
                        flipr = i - 1;
                        break;
                    }
                }
                
                Location flipLocation = new Location(flipr, current.c, current.cost + 1, false);
                if (canFlip && !visited[flipr][current.c]) {
                    queue.add(flipLocation);
                }
                
                
                boolean canLeft = false;
                int leftr = 0;
                for (int i = 0; i < current.r; i++) {
                    if (current.c - 1 > -1 && grid[i][current.c - 1] == '#') {
                        canLeft = true;
                        leftr = i + 1;
                    }
                }
                if (current.c - 1 > -1 && grid[current.r][current.c - 1] == '#') {
                    canLeft = false;
                }
                
                Location leftLocation = new Location(leftr, current.c - 1, current.cost, true);
                if (canLeft && !visited[leftr][current.c - 1]) {
                    queue.add(leftLocation);
                }
                
                
                boolean canRight = false;
                int rightr = 0;
                for (int i = 0; i < current.r; i++) {
                    if (current.c + 1 < m && grid[i][current.c + 1] == '#') {
                        canRight = true;
                        rightr = i + 1;
                    }
                }
                if (current.c + 1 < m && grid[current.r][current.c + 1] == '#') {
                    canRight = false;
                }
                
                Location rightLocation = new Location(rightr, current.c + 1, current.cost, true);
                if (canRight && !visited[rightr][current.c + 1]) {
                    queue.add(rightLocation);
                }
            }
            
            else if (!current.gravity) { //we can flip gravity upwards, or go left or right
                boolean canFlip = false;
                int flipr = 0;
                for (int i = 0; i < current.r; i++) {
                    if (grid[i][current.c] == '#') {
                        canFlip = true;
                        flipr = i + 1;
                    }
                    if (grid[i][current.c] == 'D') {
                        w.println(current.cost + 1);
                        w.flush();
                        r.close();
                        w.close();
                        System.exit(0);
                    }
                }
                
                Location flipLocation = new Location(flipr, current.c, current.cost + 1, true);
                if (canFlip && !visited[flipr][current.c]) {
                    queue.add(flipLocation);
                }
                
                
                boolean canLeft = false;
                int leftr = 0;
                for (int i = current.r; i < grid.length; i++) {
                    if (current.c - 1 > -1 && grid[i][current.c - 1] == '#') {
                        canLeft = true;
                        leftr = i - 1;
                        break;
                    }
                }
                if (current.c - 1 > -1 && grid[current.r][current.c - 1] == '#') {
                    canLeft = false;
                }
                
                Location leftLocation = new Location(leftr, current.c - 1, current.cost, false);
                if (canLeft && !visited[leftr][current.c - 1]) {
                    queue.add(leftLocation);
                }
                
                
                boolean canRight = false;
                int rightr = 0;
                for (int i = current.r; i < grid.length; i++) {
                    if (current.c + 1 < m && grid[i][current.c + 1] == '#') {
                        canRight = true;
                        rightr = i - 1;
                        break;
                    }
                }
                if (current.c + 1 < m && grid[current.r][current.c + 1] == '#') {
                    canRight = false;
                }
                
                Location rightLocation = new Location(rightr, current.c + 1, current.cost, false);
                if (canRight && !visited[rightr][current.c + 1]) {
                    queue.add(rightLocation);
                }
            }
            visited[current.r][current.c] = true;
            //previous = current;
            current = queue.remove();
        }
        
        if (!impossible)
            w.println(current.cost);
        else
            w.println(-1);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }

    /*
5 5
#####
#...#
#...D
#C...
##.##
     */
    public static class Location implements Comparable<Location> {
        int r, c;
        int cost;
        boolean gravity = false; //false if we going downwards, true if gravity is going upwards
        
        public Location(int row, int column, int cost, boolean gravity) {
            r = row;
            c = column;
            this.cost = cost;
            this.gravity = gravity;
        }
        
        @Override
        public int compareTo(Location o) {
            return cost - o.cost;
        }

        public boolean equals(Location o) {
            
            return o.r == r && o.c == c;
        }
        
        @Override
        public String toString() {
            return r + " " + c + " " + cost;
        }
    }
}
