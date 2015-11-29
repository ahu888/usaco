import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class mst {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int v = Integer.parseInt(inputs[0]);
        int e = Integer.parseInt(inputs[1]);
        
        Vertex[] vertices = new Vertex[v + 1];
        
        for (int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        
        for (int i = 0; i < e; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);
            vertices[a].addPath(b, c);
            vertices[b].addPath(a, c);
        }
        
        vertices[1].distance = 0;
        vertices[1].intree = true;
        
        int treesize = 1;
        int cost = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        for (int i = 0; i < vertices[1].adjacents.size(); i++) {
            vertices[vertices[1].adjacents.get(i)].distance = vertices[1].costs.get(i);
        }
        
        for (int i = 1; i < vertices.length; i++) {
            queue.add(vertices[i]);
        }
        //queue.remove();
        while (treesize < v) {
            Vertex closest = queue.remove();
            
            
            
            if (closest != null)
                closest.intree = true;
            else break;
            if (closest.number != 1)
                treesize++;
            cost += closest.distance;
            
            for (int i = 0; i < closest.adjacents.size(); i++) {
                boolean valid = !vertices[closest.adjacents.get(i)].intree;
                
                if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i) && valid) {
                    queue.remove(vertices[closest.adjacents.get(i)]);
                    vertices[closest.adjacents.get(i)].distance = closest.costs.get(i);
                    queue.add(vertices[closest.adjacents.get(i)]);
                }
                
            }
        }
        
        w.println(cost);
        w.flush();
    }
    
    public static class Vertex implements Comparable<Vertex> {
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
        
        public String toString() {
            return number + " " + distance;
        }
        
        public int compareTo(Vertex v) {
            return distance - v.distance;
        }
    }
}
