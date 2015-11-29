import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class distant {
    //who said floyd was 2 slow you're wrong gg
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("distant.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("distant.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int a = Integer.parseInt(inputs[1]);
        int b = Integer.parseInt(inputs[2]);
        
        char[][] graph = new char[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = r.readLine().toCharArray();
        }
        
        int[][] distances = new int[n*n][n*n];
        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j < n*n; j++) {
                distances[i][j] = infinity;
                if (i == j) {
                    distances[i][j] = 0;
                }
            }
        }
        
        int[][] numbers = new int[n*n][n*n];
        int number = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                numbers[i][j] = number++;
            }
        }
        
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    if (graph[i][j] == graph[i - 1][j]) {
                        distances[numbers[i][j]][numbers[i - 1][j]] = a;
                    } else {
                        distances[numbers[i][j]][numbers[i - 1][j]] = b;
                    }
                }
                if (i + 1 < n) {
                    if (graph[i][j] == graph[i + 1][j]) {
                        distances[numbers[i][j]][numbers[i + 1][j]] = a;
                    } else {
                        distances[numbers[i][j]][numbers[i + 1][j]] = b;
                    }
                }
                if (j - 1 >= 0) {
                    if (graph[i][j] == graph[i][j - 1]) {
                        distances[numbers[i][j]][numbers[i][j - 1]] = a;
                    } else {
                        distances[numbers[i][j]][numbers[i][j - 1]] = b;
                    }
                }
                if (j + 1 < n) {
                    if (graph[i][j] == graph[i][j + 1]) {
                        distances[numbers[i][j]][numbers[i][j + 1]] = a;
                    } else {
                        distances[numbers[i][j]][numbers[i][j + 1]] = b;
                    }
                }
            }
        }
        
        for (int k = 0; k < n*n; k++) {
            for (int i = 0; i < n*n; i++) {
                for (int j = i + 1; j < n*n; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        distances[j][i] = distances[i][j];
                    }
                }
            }
        }
        
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
            }
        }
        int max = 0;
        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j < n*n; j++) {
                max = Math.max(distances[i][j], max);
            }
        }
        
        w.println(max);
        w.flush();
    }

}
