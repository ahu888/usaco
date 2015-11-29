import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/*
 * Floyd warshall can run in time, with only 100 distinct islands
 */
public class danger {
    static final int infinity = 1000000000;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        
        n = Integer.parseInt(inputs[0]); //number of islands
        int m = Integer.parseInt(inputs[1]); //length of sequence

        int[] sequence = new int[m];
        
        for (int i = 0; i < m; i++) {
            sequence[i] = Integer.parseInt(r.readLine());
        }
        
        int[][] paths = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                paths[i][j] = infinity;
            }
        }
        for (int i = 0; i < n + 1; i++) {
            paths[i][i] = 0;
        }
        
        for (int i = 1; i < n + 1; i++) {
            inputs = r.readLine().split(" ");
            for (int j = 1; j < n + 1; j++) {
                paths[i][j] = Integer.parseInt(inputs[j - 1]);
                //paths[j][i] = paths[i][j];
            }
        }
        
        //floyd warshall
        
        for (int k = 0; k < n + 1; k++) {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    if (paths[i][j] > paths[i][k] + paths[k][j]) {
                        paths[i][j] = paths[i][k] + paths[k][j];
                    }
                }
            }
        }
        
        
        /*
        int[] segmentDists = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            int distance = dijkstra(paths, sequence[i], sequence[i + 1]);
            if (distance == infinity) {
                System.out.println();
            }
            segmentDists[i] = distance;
        }*/
        
        long sum = 0;
        
        for (int i = 0; i < sequence.length - 1; i++) {
            sum += paths[sequence[i]][sequence[i + 1]];
        }
        
        
        w.println(sum);
        w.flush();
        
    }
    //unused
    public static int dijkstra(int[][] paths, int root, int target) {
        int[] mindists = new int[paths.length];
        
        int[][] dist = new int[paths.length][paths.length];
        
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                dist[i][j] = paths[i][j];
            }
        }
        
        boolean[] visited = new boolean[n + 1];
        visited[root] = true;
        
        int nodesVisited = 1;
        
        while (nodesVisited < n) {
            int current = 0;
            for (int i = 0; i < dist.length; i++) {
                if (dist[root][i] < dist[root][current] && !visited[current] && i != root) 
                    current = i;
            }
            //if (u.visited == false) {
            visited[current] = true;
            nodesVisited++;
            //}
            
            for (int i = 0; i < paths.length; i++) {
                if (dist[root][current] + dist[current][i] < dist[root][i] && !visited[i]) {
                   dist[root][i] = dist[root][current] + dist[current][i];
                    //u.adjacent[i].parent = u;
                }
            }
        }
        return dist[root][target];
    }
}
