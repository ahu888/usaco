import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class mroute {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int v = Integer.parseInt(inputs[0]);
        int e = Integer.parseInt(inputs[1]);
        int s = 1;
        
        int needed = Integer.parseInt(inputs[2]);
        
        Vertex[] vertices = new Vertex[v + 1];
        
        for (int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        
        for (int i = 0; i < e; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int l = Integer.parseInt(inputs[2]);
            int c = Integer.parseInt(inputs[3]);
            vertices[a].addPath(b, l);
            vertices[b].addPath(a, l);
        }
        
        vertices[s].distance = 0;
        vertices[s].intree = true;
        
        int treesize = 1;
        //dijkstra's, priority queue implementation
        
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        for (int i = 0; i < vertices[s].adjacents.size(); i++) {
            vertices[vertices[s].adjacents.get(i)].distance = vertices[s].costs.get(i);
        }
        
        for (int i = 1; i < vertices.length; i++) {
            queue.add(vertices[i]);
        }
        
        while (treesize < v) {
            Vertex closest = queue.remove();
            
            treesize++;
            //cost += closestDist;
            if (closest != null)
                closest.intree = true;
            else break;
            
            for (int i = 0; i < closest.adjacents.size(); i++) {
                boolean valid = vertices[closest.adjacents.get(i)].intree;
                
                if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i) + closest.distance && valid) {
                    queue.remove(vertices[closest.adjacents.get(i)]);
                    vertices[closest.adjacents.get(i)].distance = closest.costs.get(i) + closest.distance;
                    queue.add(vertices[closest.adjacents.get(i)]);
                }
                    
            }
        }
        
        w.println((long) vertices[vertices.length - 1].distance);
        w.flush();
    }

    public static class Vertex implements Comparable<Vertex> {
        int number;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Double> costs = new ArrayList<Double>();
        double distance = infinity;
        boolean intree = false;
        
        public Vertex(int n) {
            number = n;
        }
        
        public void addPath(int to, double cost) {
            adjacents.add(to);
            costs.add(cost);
        }
        
        public String toString() {
            return number + " " + distance;
        }
        
        public int compareTo(Vertex v) {
            if (distance - v.distance > 0) {
                return 1;
            } else if (distance == v.distance) {
                return 0;
            } else {
                return -1;
            }
            //return distance - v.distance;
        }
    }

}
