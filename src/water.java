import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class water {
    //add a WATER node to the graph with distances as the cost to build a well to the rest of the nodes
    //then prim/dijkstra
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        int n = Integer.parseInt(r.readLine());
        
        //int[][] distances = new int[n + 1][n + 1]; //position 0 is the water source
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        
        for (int i = 1; i < n + 1; i++) {
            int wellCost = Integer.parseInt(r.readLine());
            vertices[0].addPath(i, wellCost);
            vertices[i].addPath(0, wellCost);
        }
        
        for (int i = 1; i < n + 1; i++) {
            String[] inputs = r.readLine().split(" ");
            for (int j = 1; j < n + 1; j++) {
                vertices[i].addPath(j, Integer.parseInt(inputs[j - 1]));
            }
        }
        
        vertices[0].distance = 0;
        vertices[0].intree = true;
        
        int treesize = 1;
        int cost = 0;
        
        for (int i = 0; i < vertices[0].adjacents.size(); i++) {
            vertices[vertices[0].adjacents.get(i)].distance = vertices[0].costs.get(i);
        }
        
        while (treesize < n + 1) {
            Vertex closest = null;
            int closestDist = infinity;
            for (int i = 1; i < vertices.length; i++) {
                if (vertices[i].distance < closestDist && !vertices[i].intree) {
                    closest = vertices[i];
                    closestDist = closest.distance;
                }
            }
            
            treesize++;
            cost += closestDist;
            
            closest.intree = true;
            
            for (int i = 0; i < closest.adjacents.size(); i++) {
                if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i)) {
                    vertices[closest.adjacents.get(i)].distance = closest.costs.get(i);
                }
            }
        }
        
        w.println(cost);
        w.flush();
        
    }
    
    public static class Vertex {
        int number;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Integer> costs = new ArrayList<Integer>();
        int distance = infinity;
        boolean intree = false;
        
        public Vertex(int n) {
            number = n;
        }
        
        public void addPath(int to, int cost) {
            adjacents.add(to);
            costs.add(cost);
        }
    }
}
