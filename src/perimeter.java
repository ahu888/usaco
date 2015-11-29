import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;


public class perimeter {

    public static void main(String[] args) throws Exception {
      //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        
        int n = Integer.parseInt(r.readLine());

        Coord[] coordinates = new Coord[n];
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxx = 0;
        int maxy = 0;
        int minxIndex = 0;
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            coordinates[i] = new Coord(x, y);
            if (x < minx) {
                minx = x;
                minxIndex = i;
            }
            if (y < miny) {
                miny = y;
            }
            if (x > maxx) {
                maxx = x;
            }
            if (y > maxy) {
                maxy = y;
            }
        }
        
        //HashMap<Integer, Byte> test = new HashMap<Integer, Byte>();
        //HashMap[] points = new HashMap[maxx - minx + 3];
        //BitSet[] points = new BitSet[maxx - minx + 3]
        Byte[][] points = new Byte[maxx - minx + 3][maxy - miny + 3]; //null values take 0 memory
        //0 means unvisited empty 1 means unvisited bale 2 means visited empty
        for (int i = 0; i < n; i++) {
            points[coordinates[i].x - minx + 1][coordinates[i].y - miny + 1] = 1;
        }
        
        
        LinkedList<Coord> queue = new LinkedList<Coord>();
        
        //boolean allgone = false;
        int total = 0;
        
        Coord current = new Coord(coordinates[minxIndex].x - minx, coordinates[minxIndex].y - miny + 1);
        queue.add(current);
        while (queue.peek() != null) {
            current = queue.pop();
            int x = current.x;
            int y = current.y;
            //points[x][y] = 2;
            //try {
                if ((x + 1 >= points.length || points[x + 1][y] == null || points[x + 1][y] == 2) && (x - 1 < 0 || points[x - 1][y] == null || points[x - 1][y] == 2) && (y + 1 >= points[0].length || points[x][y + 1] == null || points[x][y + 1] == 2) && (y - 1 < 0 || points[x][y - 1] == null || points[x][y - 1] == 2) && (x + 1 >= points.length || y + 1 >= points[0].length || points[x + 1][y + 1] == null || points[x + 1][y + 1] == 2) && (x + 1 >= points.length || y - 1 < 0 || points[x + 1][y - 1] == null || points[x + 1][y - 1] == 2) && (x - 1 < 0 || y + 1 >= points[0].length || points[x - 1][y + 1] == null || points[x - 1][y + 1] == 2) && (x - 1 < 0 || y - 1 < 0 || points[x - 1][y - 1] == null || points[x - 1][y - 1] == 2)) {
                    continue;
                }
            //} catch (ArrayIndexOutOfBoundsException e) {
                
            //}
            points[x][y] = 2;
            if (x >= 0 && y >= 0 && x + 1 < maxx - minx + 3 && y < maxy - miny + 3 && points[x + 1][y] == null) {
                x++;
                //if (!((x + 1 >= points.length || points[x + 1][y] == null || points[x + 1][y] == 2) && (x - 1 < 0 || points[x - 1][y] == null || points[x - 1][y] == 2) && (y + 1 >= points[0].length || points[x][y + 1] == null || points[x][y + 1] == 2) && (y - 1 < 0 || points[x][y - 1] == null || points[x][y - 1] == 2) && (x + 1 >= points.length || y + 1 >= points[0].length || points[x + 1][y + 1] == null || points[x + 1][y + 1] == 2) && (x + 1 >= points.length || y - 1 < 0 || points[x + 1][y - 1] == null || points[x + 1][y - 1] == 2) && (x - 1 < 0 || y + 1 >= points[0].length || points[x - 1][y + 1] == null || points[x - 1][y + 1] == 2) && (x - 1 < 0 || y - 1 < 0 || points[x - 1][y - 1] == null || points[x - 1][y - 1] == 2))) {
                    queue.add(new Coord(x, y));
                    points[x][y] = 2;
                //}
                x--;
            } else if (x + 1 < maxx - minx + 3 && points[x + 1][y] == 1) {
                total++;
                //System.out.println("Current " + x + " " + y + " Adjacent " + (x + 1) + " " + y);
                //points[x + 1][y] = 2;
            }
            
            if (x - 1 >= 0 && y >= 0 && x - 1 < maxx - minx + 3 && y < maxy - miny + 3 && points[x - 1][y] == null) {
                x--;
                //if (!((x + 1 >= points.length || points[x + 1][y] == null || points[x + 1][y] == 2) && (x - 1 < 0 || points[x - 1][y] == null || points[x - 1][y] == 2) && (y + 1 >= points[0].length || points[x][y + 1] == null || points[x][y + 1] == 2) && (y - 1 < 0 || points[x][y - 1] == null || points[x][y - 1] == 2) && (x + 1 >= points.length || y + 1 >= points[0].length || points[x + 1][y + 1] == null || points[x + 1][y + 1] == 2) && (x + 1 >= points.length || y - 1 < 0 || points[x + 1][y - 1] == null || points[x + 1][y - 1] == 2) && (x - 1 < 0 || y + 1 >= points[0].length || points[x - 1][y + 1] == null || points[x - 1][y + 1] == 2) && (x - 1 < 0 || y - 1 < 0 || points[x - 1][y - 1] == null || points[x - 1][y - 1] == 2))) {
                    queue.add(new Coord(x, y));
                    points[x][y] = 2;
                //}
                x++;
            } else if (x - 1 >= 0 && points[x - 1][y] == 1) {
                total++;
                //System.out.println("Current " + x + " " + y + " Adjacent " + (x - 1) + " " + y);
                //points[x - 1][y] = 2;
            }
            
            if (x >= 0 && y >= 0 && x < maxx - minx + 3 && y + 1 < maxy - miny + 3 && points[x][y + 1] == null) {
                y++;
                //if (!((x + 1 >= points.length || points[x + 1][y] == null || points[x + 1][y] == 2) && (x - 1 < 0 || points[x - 1][y] == null || points[x - 1][y] == 2) && (y + 1 >= points[0].length || points[x][y + 1] == null || points[x][y + 1] == 2) && (y - 1 < 0 || points[x][y - 1] == null || points[x][y - 1] == 2) && (x + 1 >= points.length || y + 1 >= points[0].length || points[x + 1][y + 1] == null || points[x + 1][y + 1] == 2) && (x + 1 >= points.length || y - 1 < 0 || points[x + 1][y - 1] == null || points[x + 1][y - 1] == 2) && (x - 1 < 0 || y + 1 >= points[0].length || points[x - 1][y + 1] == null || points[x - 1][y + 1] == 2) && (x - 1 < 0 || y - 1 < 0 || points[x - 1][y - 1] == null || points[x - 1][y - 1] == 2))) {
                    queue.add(new Coord(x, y));
                    points[x][y] = 2;
                //}
                y--;
            } else if (y + 1 < maxy - miny + 3 && points[x][y + 1] == 1) {
                total++;
                //System.out.println("Current " + x + " " + y + " Adjacent " + x + " " + (y + 1));
                //points[x][y + 1] = 2;
            }
            
            if (x >= 0 && y - 1 >= 0 && x < maxx - minx + 3 && y < maxy - miny + 3 && points[x][y - 1] == null) {
                y--;
                //if (!((x + 1 >= points.length || points[x + 1][y] == null || points[x + 1][y] == 2) && (x - 1 < 0 || points[x - 1][y] == null || points[x - 1][y] == 2) && (y + 1 >= points[0].length || points[x][y + 1] == null || points[x][y + 1] == 2) && (y - 1 < 0 || points[x][y - 1] == null || points[x][y - 1] == 2) && (x + 1 >= points.length || y + 1 >= points[0].length || points[x + 1][y + 1] == null || points[x + 1][y + 1] == 2) && (x + 1 >= points.length || y - 1 < 0 || points[x + 1][y - 1] == null || points[x + 1][y - 1] == 2) && (x - 1 < 0 || y + 1 >= points[0].length || points[x - 1][y + 1] == null || points[x - 1][y + 1] == 2) && (x - 1 < 0 || y - 1 < 0 || points[x - 1][y - 1] == null || points[x - 1][y - 1] == 2))) {
                    queue.add(new Coord(x, y));
                    points[x][y] = 2;
                //}
                y++;
            } else if (y - 1 >= 0 && points[x][y - 1] == 1) {
                total++;
                //System.out.println("Current " + x + " " + y + " Adjacent " + x + " " + (y - 1));
                //points[x][y - 1] = 2;
            }
        }
        w.println(total);
        w.flush();
        /*
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (coordinates[j].x == coordinates[i].x) {
                    if (coordinates[j].y == coordinates[i].y + 1) {
                        coordinates[i].north = coordinates[j];
                    }
                    if (coordinates[j].y == coordinates[i].y - 1) {
                        coordinates[i].south = coordinates[j];
                    }
                } else if (coordinates[j].y == coordinates[i].y) {
                    if (coordinates[j].x == coordinates[i].x + 1) {
                        coordinates[i].east = coordinates[j];
                    }
                    if (coordinates[j].x == coordinates[i].x - 1) {
                        coordinates[i].west = coordinates[j];
                    }
                }
            }
        }*/
        
        
        /*
        HashMap<Point, Boolean> points = new HashMap<Point, Boolean>();
        
        for (int i = 0; i < n; i ++) {
            points.put(coordinates[i], true);
        }
        
        for (int i = 0; i < n; i++) {
            int x = coordinates[i].x;
            int y = coordinates[i].y;
            Boolean north = points.get(new Point(x, y + 1, false));
            Boolean south = points.get(new Point(x, y - 1, false));
            Boolean east = points.get(new Point(x + 1, y, false));
            Boolean west = points.get(new Point(x - 1, y, false));
            Boolean nw = points.get(new Point(x - 1, y + 1, false));
            Boolean ne = points.get(new Point(x + 1, y + 1, false));
            Boolean sw = points.get(new Point(x - 1, y - 1, false));
            Boolean se = points.get(new Point(x + 1, y - 1, false));
            
            if (north != true) {
                points.put(new Point(x, y + 1, false), false);
            }
            if (south != true) {
                points.put(new Point(x, y - 1, false), false);
            }
            if (east != true) {
                points.put(new Point(x + 1, y, false), false);
            }
            if (west != true) {
                points.put(new Point(x - 1, y, false), false);
            }
            if (nw != true) {
                points.put(new Point(x - 1, y + 1, false), false);
            }
            if (ne != true) {
                points.put(new Point(x + 1, y + 1, false), false);
            }
            if (sw != true) {
                points.put(new Point(x - 1, y - 1, false), false);
            }
            if (se != true) {
                points.put(new Point(x + 1, y - 1, false), false);
            }
        }
        
        Point current = new Point(minx - 1, miny - 1, false);
        
        current.traversed = true;
        
        boolean allgone = false;*/
    }
    
    public static class Coord {
        int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public String toString() {
            return x + " " + y;
        }
    }
    /*
    public static class Point {
        int x, y;
        boolean empty;
        boolean traversed = false;
        //Point north, south, east, west;
        
        
        public Point(int x, int y, boolean e) {
            this.x = x;
            this.y = y;
            empty = e;
        }
        
        public boolean equals(Object o) {
            return o.toString().equals(toString());
        }
        
        public String toString() {
            return x + " " + y;
        }
    }*/

}
