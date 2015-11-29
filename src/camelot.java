
/*
ID: farmersrice
LANG: JAVA
TASK: camelot
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

//IT WORKKKKSKSKSSSKSKKSKS1!!!2!@!!11!21
public class camelot {
    static Square[][] squares;
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("camelot.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
        
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        int rows = Integer.parseInt(inputs[0]);
        int columns = Integer.parseInt(inputs[1]);
        
        inputs = r.readLine().split(" ");
        
        int cking = inputs[0].charAt(0) - 'A';
        int rking = Integer.parseInt(inputs[1]) - 1;
        
        squares = new Square[rows][columns];

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
        
        for (int i = 1; i < inputs.length/2; i++) {
            int cknight = inputs[i * 2].charAt(0) - 'A';
            int rknight = Integer.parseInt(inputs[i * 2 + 1]);
            
            squares[rknight - 1][cknight].hasKnight = true;
        }
        
        String input = "";
        while (input != null) {
            
            inputs = input.split(" ");
            for (int i = 0; i < inputs.length/2; i++) {
                int cknight = inputs[i * 2].charAt(0) - 'A';
                int rknight = Integer.parseInt(inputs[i * 2 + 1]);
                
                squares[rknight - 1][cknight].hasKnight = true;
            }
            input = r.readLine();
        }
        
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                search(squares[i][j]);
            }
        }
        
        int[][] kingDistances = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                kingDistances[i][j] = Math.max(Math.abs(rking - i), Math.abs(cking - j));
            }
        }
        
        
        
        int lowest = infinity;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //i and j are the coordinates of the destination square
                int sumKnights = 0;
                
                for (int k = 0; k < rows; k++) {
                    for (int l = 0; l < columns; l++) {
                        if (squares[k][l].hasKnight) {
                            sumKnights += squares[k][l].distanceTo[i][j];
                        }
                    }
                }
                if (sumKnights >= infinity) {
                    continue;
                }
                
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < columns; col++) {
                        if (Math.max(Math.abs(rking - row), Math.abs(cking - col)) > kingDistances[i][j] || Math.max(Math.abs(rking - row), Math.abs(cking - col)) > 3) {
                            continue;
                        }

                        //row and col are the locations where the king is going to get picked up, if row == i && col == j then king does not get picked up
                        
                        if (row == i && col == j) {
                            sumKnights += kingDistances[row][col];
                            if (sumKnights < lowest && sumKnights >= 0) {
                                lowest = sumKnights;
                            }
                            sumKnights -= kingDistances[row][col];
                            continue;
                        }
                        
                        //find closest knight that can go to row, col
                        int closestDistance = infinity;
                        int x = 0;
                        int y = 0;
                        
                        for (int k = 0; k < rows; k++) {
                            for (int l = 0; l < columns; l++) {
                                //choose knight [k][l] to pick up
                                if (squares[k][l].hasKnight && squares[k][l].distanceTo[row][col] <= closestDistance) {
                                    closestDistance = squares[k][l].distanceTo[row][col];
                                    x = k;
                                    y = l;
                                    if (closestDistance != infinity) {
                                        sumKnights -= squares[x][y].distanceTo[i][j];
                                        sumKnights += closestDistance + squares[row][col].distanceTo[i][j];
                                        sumKnights += kingDistances[row][col];
                                        if (sumKnights < lowest && sumKnights < infinity) {
                                            lowest = sumKnights;
                                        }
                                        sumKnights -= kingDistances[row][col];
                                        sumKnights -= closestDistance + squares[row][col].distanceTo[i][j];
                                        sumKnights += squares[x][y].distanceTo[i][j];
                                    }
                                }
                            }
                        }
                        
                        if (closestDistance != infinity) {
                            sumKnights -= squares[x][y].distanceTo[i][j];
                            sumKnights += closestDistance + squares[row][col].distanceTo[i][j];
                            sumKnights += kingDistances[row][col];
                            if (sumKnights < lowest && sumKnights < infinity) {
                                lowest = sumKnights;
                            }
                            sumKnights -= kingDistances[row][col];
                            sumKnights -= closestDistance + squares[row][col].distanceTo[i][j];
                            sumKnights += squares[x][y].distanceTo[i][j];
                        }
                    }
                }
            }
        }
        
        System.out.println("end " + (System.currentTimeMillis() - start));
        
        if (lowest != infinity)
            w.println(lowest);
        else
            w.println(0);
        w.flush();
    }
    
    
    //knight search, bfs 
    public static void search(Square start) {
        
        Queue<Entry> queue = new LinkedList<Entry>();
        //boolean[][] seen = new boolean[squares.length][squares[0].length];
        
        queue.add(new Entry(start.r, start.c, 0));
        
        while (!queue.isEmpty()) {
            Entry current = queue.remove();
            int r = current.r;
            int c = current.c;
            if (r - 2 > -1 && c - 1 > -1 && start.distanceTo[r - 2][c - 1] > current.distance + 1) {
                start.distanceTo[r - 2][c - 1] = current.distance + 1;
                queue.add(new Entry(r - 2, c - 1, current.distance + 1));
            }
            if (r - 2 > -1 && c + 1 < squares[0].length && start.distanceTo[r - 2][c + 1] > current.distance + 1) {
                start.distanceTo[r - 2][c + 1] = current.distance + 1;
                queue.add(new Entry(r - 2, c + 1, current.distance + 1));
            }
            if (r - 1 > -1 && c - 2 > -1 && start.distanceTo[r - 1][c - 2] > current.distance + 1) {
                start.distanceTo[r - 1][c - 2] = current.distance + 1;
                queue.add(new Entry(r - 1,  c - 2, current.distance + 1));
            }
            if (r + 1 < squares.length && c - 2 > -1 && start.distanceTo[r + 1][c - 2] > current.distance + 1) {
                start.distanceTo[r + 1][c - 2] = current.distance + 1;
                queue.add(new Entry(r + 1, c - 2, current.distance + 1));
            }
            if (r - 1 > -1 && c + 2 < squares[0].length && start.distanceTo[r - 1][c + 2] > current.distance + 1) {
                start.distanceTo[r - 1][c + 2] = current.distance + 1;
                queue.add(new Entry(r - 1, c + 2, current.distance + 1));
            }
            if (r + 1 < squares.length && c + 2 < squares[0].length && start.distanceTo[r + 1][c + 2] > current.distance + 1) {
                start.distanceTo[r + 1][c + 2] = current.distance + 1;
                queue.add(new Entry(r + 1, c + 2, current.distance + 1));
            }
            if (r + 2 < squares.length && c + 1 < squares[0].length && start.distanceTo[r + 2][c + 1] > current.distance + 1) {
                start.distanceTo[r + 2][c + 1] = current.distance + 1;
                queue.add(new Entry(r + 2, c + 1, current.distance + 1));
            }
            if (r + 2 < squares.length && c - 1 > -1 && start.distanceTo[r + 2][c - 1] > current.distance + 1) {
                start.distanceTo[r + 2][c - 1] = current.distance + 1;
                queue.add(new Entry(r + 2, c - 1, current.distance + 1));
            }
        }
    }
    
    public static class Entry {
        int r, c;
        int distance;
        
        public Entry(int x, int y, int d) {
            r = x;
            c = y;
            distance = d;
        }
        
        public String toString() {
            return r + " " + " " + c + " " + distance;
        }
    }
    
    public static class Square {
        int r, c;
        int[][] distanceTo; //using the knight class
        boolean hasKnight = false;
        
        public Square(int r, int c) {
            this.r = r;
            this.c = c;
            distanceTo = new int[squares.length][squares[0].length];
            
            for (int i = 0; i < squares.length; i++) {
                for (int j = 0; j < squares[0].length; j++) {
                    distanceTo[i][j] = infinity;
                }
            }
            distanceTo[r][c] = 0;
        }
        
        public String toString() {
            return r  + " " + c + " " + hasKnight;
        }
    }

}
