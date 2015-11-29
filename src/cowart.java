import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class cowart {
    static int n;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("cowart.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("cowart.out")));
        
        //solution is 2 search thru 
        
        n = Integer.parseInt(r.readLine());
        
        char[][] values = new char[n][];
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            values[i] = r.readLine().toCharArray();
        }
        
        //calculate h00man

        int humanRegions = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    humanRegions++;
                    search(values, i, j, visited);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
                if (values[i][j] == 'G') {
                    values[i][j] = 'R';
                }
            }
        }
        
        int cowRegions = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    cowRegions++;
                    search(values, i, j, visited);
                }
            }
        }
        
        w.println(humanRegions + " " + cowRegions);
        
        w.flush();
    }
    
    public static void search(char[][] values, int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        char current = values[r][c];
        
        if (r + 1 < n && !visited[r + 1][c] && values[r + 1][c] == current) {
            search(values, r + 1, c, visited);
        }
        if (r - 1 > -1 && !visited[r - 1][c] && values[r - 1][c] == current) {
            search(values, r - 1, c, visited);
        }
        if (c + 1 < n && !visited[r][c + 1] && values[r][c + 1] == current) {
            search(values, r, c + 1, visited);
        }
        if (c - 1 > -1 && !visited[r][c - 1] && values[r][c - 1] == current) {
            search(values, r, c - 1, visited);
        }
    }

}
