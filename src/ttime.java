import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class ttime {
    static Cow[] cows;
    static int groupid = 0;
    static boolean[][] knows;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(r.readLine());

        n = Integer.parseInt(line.nextToken()); //number of cows
        int m = Integer.parseInt(line.nextToken()); //number of pairs given
        int q = Integer.parseInt(line.nextToken()); //number of queries
        
        knows = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            line = new StringTokenizer(r.readLine());
            
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            
            knows[a][b] = true;
            knows[b][a] = true;
        }
        
        cows = new Cow[n];
        
        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(i + 1, -1);
        }
        
        for (int i = 0; i < n; i++) {
            if (cows[i].groupid != -1) continue;
            search(i);
            groupid++;
        }
        
        for (int i = 0; i < q; i++) {
            line = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            if (cows[x - 1].groupid == cows[y - 1].groupid) {
                w.println("Y");
            } else {
                w.println("N");
            }
        }
        w.flush();
    }
    
    public static void search(int current) {
        //if (cows[current].groupid != -1) return;
        cows[current].groupid = groupid;
        for (int i = 0; i < n; i++) {
            if (knows[current + 1][i + 1] && cows[i].groupid == -1) {
                cows[i].groupid = groupid;
                search(i);
            }
        }
    }
    
    public static class Cow {
        int number;
        int groupid;
        
        public Cow(int n, int g) {
            number = n;
            groupid = g;
        }
        
        public String toString() {
            return number + " " + groupid;
        }
    }

}
