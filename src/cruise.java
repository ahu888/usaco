import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class cruise {
    static int[][] adj;
    static boolean[] sequence;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("cruise.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]); //number of directed paths/rivers
        int m = Integer.parseInt(inputs[1]); //num of directions in sequence
        int k = Integer.parseInt(inputs[2]); //num of repetitions of sequence
        
        adj = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            inputs = r.readLine().split(" ");
            adj[i][0] = Integer.parseInt(inputs[0]); //l
            adj[i][1] = Integer.parseInt(inputs[1]); //r
        }
        
        inputs = r.readLine().split(" ");
        sequence = new boolean[m]; //false means go left, true means go right
        for (int i = 0; i < inputs.length; i++) {
            sequence[i] = inputs[i].equals("R");
        }
        
        int[] visited = new int[1001];
        visited[0] = 1;
        int current = 1;
        current = move(current);
        int index = 1;
        while (!contains(current, visited) && index <= k) {
            visited[index++] = current;
            current = move(current);
        }
        
        int prevIndex = find(current, visited);
        
        int cycleLength = index - prevIndex;
        
        int toGo = k - index + 1;
        toGo %= cycleLength;
        
        
        int answer = visited[index - 1];
        if (prevIndex != -1)
        for (int i = 0; i < toGo; i++) {
            answer = visited[i + prevIndex];
        }
        
        w.println(answer);
        w.flush();
    }

    public static int move(int current) {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i]) { //moving right
                current = adj[current][1];
            } else {
                current = adj[current][0];
            }
        }
        return current;
    }
    
    public static boolean contains(int needle, int[] haystack) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return true;
            }
        }
        return false;
    }
    
    public static int find (int needle, int[] haystack) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return i;
            }
        }
        return -1;
    }
}
