import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;


public class hideseek {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]); //number of barns
        int m = Integer.parseInt(inputs[1]); //number of paths
        
        //boolean [][] is out of the question because too much memory, therefore we must use objects
        
        ArrayList[] barns = new ArrayList[n + 1]; //if barns[i].contains(j) then i can go to j
        int[] distances = new int[n + 1];
        
        for (int i = 0; i < barns.length; i++) {
            barns[i] = new ArrayList<Integer>();
            distances[i] = infinity;
        }
        distances[1] = 0;
        
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            barns[a].add(b); //type safety yellow , begone
            barns[b].add(a);
        }
        
        //bfs to find the farthest barn
        
        LinkedList<Entry> queue = new LinkedList<Entry>();
        
        queue.add(new Entry(1, 0));
        
        while (!queue.isEmpty()) {
            Entry current = queue.remove();
            
            for (int i = 0; i < barns[current.barn].size(); i++) {
                if (distances[(int) barns[current.barn].get(i)] > current.cost + 1) {
                    distances[(int) barns[current.barn].get(i)] = current.cost + 1;
                    queue.add(new Entry((int) barns[current.barn].get(i), current.cost + 1));
                }
            }
        }
        
        int farthest = 0;
        int index = -1;
        int total = 0;
        
        for (int i = 2; i < n + 1; i++) {
            if (distances[i] > farthest) {
                index = i;
                farthest = distances[i];
                total = 1;
            } else if (distances[i] == farthest) {
                total++;
            }
        }
        
        w.println(index + " " + farthest + " " + total);
        w.flush();
        
    }
    
    public static class Entry {
        int barn, cost;
        
        public Entry(int b, int c) {
            barn = b;
            cost = c;
        }
    }

}
