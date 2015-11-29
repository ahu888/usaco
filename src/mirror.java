import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class mirror {
    static boolean[][] mirrors;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("mirror.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("mirror.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        mirrors = new boolean[n][m]; //true if \ false if /
        for (int i = 0; i < n; i++) {
            char[] chars = r.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                mirrors[i][j] = chars[j] == '\\';
            }
        }
        
        int highest = 0;
        
        for (int i = 0; i < n; i++) {
            highest = Math.max(search(i, 0, 3), highest);
            highest = Math.max(search(i, m - 1, 1), highest);
        }
        
        for (int i = 0; i < m; i++) {
            highest = Math.max(search(0, i, 0), highest);
            highest = Math.max(search(n - 1, i, 2), highest);
        }
        
        w.println(highest);
        w.flush();
    }
    
    /*
     * direction means 0 going down, 1 going left, 2 going up, 3 going right
     * 
     * ex ----> /     is 3
     */
    public static int search(int r, int c, int direction) { 
        int value = 0;
        
        while (r >= 0 && r < mirrors.length && c >= 0 && c < mirrors[0].length) {
            value++;
            //bounce
            if (!mirrors[r][c]) {
                if (direction == 0) {
                    c--;
                    direction = 1;
                } else if (direction == 1) {
                    r++;
                    direction = 0;
                } else if (direction == 2) {
                    c++;
                    direction = 3;
                } else if (direction == 3) {
                    r--;
                    direction = 2;
                }
            } else {
                if (direction == 0) {
                    c++;
                    direction = 3;
                } else if (direction == 1) {
                    r--;
                    direction = 2;
                } else if (direction == 2) {
                    c--;
                    direction = 1;
                } else if (direction == 3) {
                    r++;
                    direction = 0;
                }
            }
        }
        
        return value;
    }

}
