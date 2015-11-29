import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class flow {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        int n = Integer.parseInt(r.readLine());
        
        HashMap<String, Vertex> temp = new HashMap<String, Vertex>();
        
        
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            
            int capacity = Integer.parseInt(inputs[2]);
            
            if (!temp.containsKey(inputs[0])) {
                temp.put(inputs[0], new Vertex(inputs[0]));
            }
            if (!temp.containsKey(inputs[1])) {
                temp.put(inputs[1], new Vertex(inputs[0]));
            }
            
            temp.get(inputs[0]).addPipe(inputs[1], capacity);
            temp.get(inputs[1]).addPipe(inputs[0], capacity);
        }
        
        ArrayList<Vertex> vertices = temp.values();
        temp.v
        
        

    }
    
    public static class Vertex {
        String name;
        ArrayList<String> adjacents = new ArrayList<String>();
        ArrayList<Integer> distances = new ArrayList<Integer>();
        
        public Vertex(String n) {
            name = n;
        }
        
        public void addPipe(String adj, int dist) {
            adjacents.add(adj);
            distances.add(dist);
        }
    }

}
