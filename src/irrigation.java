import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class irrigation {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("irrigation.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));

        String[] inputs = r.readLine().split(" ");
        
        int v = Integer.parseInt(inputs[0]);
        int c = Integer.parseInt(inputs[1]);
        
        Vertex[] vertices = new Vertex[v + 1];
        
        for (int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        
        for (int i = 0; i < v; i++) {
            inputs = r.readLine().split(" ");
            vertices[i + 1].x = Integer.parseInt(inputs[0]);
            vertices[i + 1].y = Integer.parseInt(inputs[1]);
        }
        long start = System.currentTimeMillis();
        //2m ops
        int entered = 0;
        for (int i = 1; i < vertices.length; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                //find distance of path between i and j, if it is greater than or equal to cost c we add it as a path
                
                int distance = (vertices[i].x - vertices[j].x) * (vertices[i].x - vertices[j].x) + (vertices[i].y - vertices[j].y) * (vertices[i].y - vertices[j].y);
                
                if (distance >= c) {
                    vertices[i].addPath(j, distance);
                    vertices[j].addPath(i, distance);
                    entered++;
                }
            }
        }
        //System.out.println(entered);
        System.out.println(System.currentTimeMillis() - start);
        
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
            
            if (closest != null && closest.distance != infinity)
                closest.intree = true;
            else if (closest == null || closest.distance == infinity){
                w.println(-1);
                w.flush();
                System.exit(0);
                break;
            }
            if (closest.number != 1)
                treesize++;
            cost += closest.distance;
            
            for (int i = 0; i < closest.adjacents.size(); i++) {
                boolean valid = vertices[closest.adjacents.get(i)].intree;
                
                if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i) && !valid) {
                    queue.remove(vertices[closest.adjacents.get(i)]);
                    vertices[closest.adjacents.get(i)].distance = closest.costs.get(i);
                    queue.add(vertices[closest.adjacents.get(i)]);
                }
                
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        w.println(cost);
        w.flush();
    }
    
    public static class Vertex implements Comparable<Vertex> {
        int number;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Integer> costs = new ArrayList<Integer>();
        int distance = infinity;
        boolean intree = false;
        int x,y;
        
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
