import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;


public class hshoe {
    static int n;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("hshoe.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("hshoe.out")));

        n = Integer.parseInt(r.readLine());
        
        char[][] data = new char[n][];
        
        for (int i = 0; i < n; i++) {
            data[i] = r.readLine().toCharArray();
        }
        
        //bfs
        
        LinkedList<Entry> queue = new LinkedList<Entry>();
        
        //start in uper left
        if (data[0][0] == '(')
            queue.add(new Entry(0, 0, 1, 0));
        else {
            w.println(0);
            w.flush();
            System.exit(0);
        }
        
        Entry current = null;
        int highest = 0;
        while (!queue.isEmpty()) {
            current = queue.pop();
            int row = current.row;
            int col = current.col;
            if (current.rights == current.lefts && current.lefts != 0) {
                highest = Math.max(highest, current.lefts * 2);
            }
            current.visited[current.row][current.col] = true;
            if (current.rights != 0) { //we can only pick up rights
                if (row - 1 > -1 && !current.visited[row - 1][col] && data[row - 1][col] == ')') {
                    Entry e = new Entry(row - 1, col, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (row + 1 < n && !current.visited[row + 1][col] && data[row + 1][col] == ')') {
                    Entry e = new Entry(row + 1, col, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (col - 1 > -1 && !current.visited[row][col - 1] && data[row][col - 1] == ')') {
                    Entry e = new Entry(row, col - 1, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (col + 1 < n && !current.visited[row][col + 1] && data[row][col + 1] == ')') {
                    Entry e = new Entry(row, col + 1, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
            } else { //we can pick left or right
                if (row - 1 > -1 && !current.visited[row - 1][col] && data[row - 1][col] == '(') {
                    Entry e = new Entry(row - 1, col, current.lefts + 1, current.rights);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (row + 1 < n && !current.visited[row + 1][col] && data[row + 1][col] == '(') {
                    Entry e = new Entry(row + 1, col, current.lefts + 1, current.rights);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (col - 1 > -1 && !current.visited[row][col - 1] && data[row][col - 1] == '(') {
                    Entry e = new Entry(row, col - 1, current.lefts + 1, current.rights);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (col + 1 < n && !current.visited[row][col + 1] && data[row][col + 1] == '(') {
                    Entry e = new Entry(row, col + 1, current.lefts + 1, current.rights);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (row - 1 > -1 && !current.visited[row - 1][col] && data[row - 1][col] == ')') {
                    Entry e = new Entry(row - 1, col, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (row + 1 < n && !current.visited[row + 1][col] && data[row + 1][col] == ')') {
                    Entry e = new Entry(row + 1, col, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (col - 1 > -1 && !current.visited[row][col - 1] && data[row][col - 1] == ')') {
                    Entry e = new Entry(row, col - 1, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
                if (col + 1 < n && !current.visited[row][col + 1] && data[row][col + 1] == ')') {
                    Entry e = new Entry(row, col + 1, current.lefts, current.rights + 1);
                    e.visited = clone(current.visited);
                    queue.add(e);
                }
            }
        }
        
        w.println(highest);
        w.flush();
    }
    
    public static class Entry {
        int lefts, rights;
        int row, col;
        boolean[][] visited = new boolean[n][n];
        
        public Entry(int r, int c, int l, int ri) {
            row = r;
            col = c;
            lefts = l;
            rights = ri;
        }
        
        public String toString() {
            return row + " " + col + " " + lefts + " " + rights;
        }
    }

    public static boolean[][] clone (boolean[][] data) {
        boolean[][] ret = new boolean[data.length][data.length];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = data[i][j];
            }
        }
        
        return ret;
    }
}
