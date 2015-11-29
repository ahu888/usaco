import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;


public class mud {

    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new FileReader("mud.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("mud.out")));

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int x = Integer.parseInt(inputs[0]);
        int y = Integer.parseInt(inputs[1]);
        int n = Integer.parseInt(inputs[2]);
        
        //Puddle[] puddles = new Puddle[n];
        int[][] plane = new int[1004][1004]; //from -501 to 0 back to 501 = 501 + 501 + 1 = 1003, and item [0][0] is not used
        //point 0, 0 is at 502, 502
        
        for (int i = 0; i < 1004; i++) {
            for (int j = 0; j < 1004; j++) {
                plane[i][j] = infinity; //infinite distance from bessie to that point
            }
        }
        
        for (int i = 0; i < n; i++) {
            input = r.readLine();
            inputs = input.split(" ");
            
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            
            plane[a + 502][b + 502] = -1; //occupied by puddle
            //puddles[i] = new Puddle(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        plane[x + 502][y + 502] = 0;
        
        boolean tofarmer = false;
        
        //Point current = new Point(502, 502);
        //Point[] prev = new Point[4];
        
        /*
        while (!tofarmer) {
            Point[] adjacent = {new Point(x)
        }*/
        
        LinkedList<Point> currentvalid = new LinkedList<Point>();
        currentvalid.add(new Point(x + 502, y + 502));
        //updateNear(plane, x + 502, y + 502);
        
        while (plane[502][502] == infinity) {
            Point current = currentvalid.pop();
            int currentx = current.x;
            int currenty = current.y;
            if (currentx + 1 < 1004 && plane[currentx + 1][currenty] != -1 && plane[currentx + 1][currenty] > plane[currentx][currenty] + 1) {
                plane[currentx + 1][currenty] = plane[currentx][currenty] + 1;
                currentvalid.add(new Point(currentx + 1, currenty));
            }
            if (currentx - 1 >= 0 && plane[currentx - 1][currenty] != -1 && plane[currentx - 1][currenty] > plane[currentx][currenty] + 1) {
                plane[currentx - 1][currenty] = plane[currentx][currenty] + 1;
                currentvalid.add(new Point(currentx - 1, currenty));
            }
            if (currenty + 1 < 1004 && plane[currentx][currenty + 1] != -1 && plane[currentx][currenty + 1] > plane[currentx][currenty] + 1) {
                plane[currentx][currenty + 1] = plane[currentx][currenty] + 1;
                currentvalid.add(new Point(currentx, currenty + 1));
            }
            if (currenty - 1 >= 0 && plane[currentx][currenty - 1] != -1 && plane[currentx][currenty - 1] > plane[currentx][currenty] + 1) {
                plane[currentx][currenty - 1] = plane[currentx][currenty] + 1;
                currentvalid.add(new Point(currentx, currenty - 1));
            }
        }
        
        
        w.println(plane[502][502]);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static void updateNear(int[][] plane, int x, int y) {
        if (x + 1 < 1004 && plane[x + 1][y] != -1 && plane[x + 1][y] > plane[x][y] + 1) {
            plane[x + 1][y] = plane[x][y] + 1;
            updateNear(plane, x + 1, y);
        }
        if (x - 1 >= 0 && plane[x - 1][y] != -1 && plane[x - 1][y] > plane[x][y] + 1) {
            plane[x - 1][y] = plane[x][y] + 1;
            updateNear(plane, x - 1, y);
        }
        if (y + 1 < 1004 && plane[x][y + 1] != -1 && plane[x][y + 1] > plane[x][y] + 1) {
            plane[x][y + 1] = plane[x][y] + 1;
            updateNear(plane, x, y + 1);
        }
        if (y - 1 >= 0 && plane[x][y - 1] != -1 && plane[x][y - 1] > plane[x][y] + 1) {
            plane[x][y - 1] = plane[x][y] + 1;
            updateNear(plane, x, y - 1);
        }
    }
    
    public static class Point {
        int x,y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public String toString() {
            return x + " " + y;
        }
    }
}
