import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class pwrfail {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int v = Integer.parseInt(inputs[0]); //number of poles
        int e = Integer.parseInt(inputs[1]); //number of cables
        int s = 1;
        
        double m = Double.parseDouble(r.readLine());
        
        
        Vertex[] vertices = new Vertex[v + 1];
        
        for (int i = 1; i < vertices.length; i++) {
            inputs = r.readLine().split(" ");
            vertices[i] = new Vertex(i, Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        for (int i = 0; i < e; i++) {
            inputs = r.readLine().split(" ");
            
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            vertices[a].addPath(b, 0);
            vertices[b].addPath(a, 0);
        }
        
        for (int i = 1; i < v + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                double distance = Math.sqrt(Math.pow(vertices[i].x - vertices[j].x, 2) + Math.pow(vertices[i].y - vertices[j].y, 2));
                if (distance <= m && !vertices[i].adjacents.contains(j) && !vertices[j].adjacents.contains(i) && i != j) {
                    vertices[i].addPath(j, distance);
                    vertices[j].addPath(i, distance);
                }
            }
        }
        
        vertices[s].distance = 0;
        vertices[s].intree = true;
        
        int treesize = 1;
        
        for (int i = 0; i < vertices[s].adjacents.size(); i++) {
            vertices[vertices[s].adjacents.get(i)].distance = vertices[s].costs.get(i);
        }
        
        while (treesize < v) {
            Vertex closest = null;
            double closestDist = infinity;
            for (int i = 1; i < vertices.length; i++) {
                if (vertices[i].distance < closestDist && !vertices[i].intree) {
                    closest = vertices[i];
                    closestDist = closest.distance;
                }
            }
            
            treesize++;
            //cost += closestDist;
            if (closest != null)
            closest.intree = true;
            else break;
            
            for (int i = 0; i < closest.adjacents.size(); i++) {
                if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i) + closest.distance) {
                    vertices[closest.adjacents.get(i)].distance = closest.costs.get(i) + closest.distance;
                }
            }
        }
        if (vertices[v].distance == infinity) {
            w.println(-1);
        } else
        w.println((long) (vertices[v].distance * 1000));
        w.flush();
    }

    public static class Vertex {
        int number;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Double> costs = new ArrayList<Double>();
        double distance = infinity;
        boolean intree = false;
        int x, y;
        
        public Vertex(int n, int a, int b) {
            number = n;
            x = a;
            y = b;
        }
        
        public void addPath(int to, double cost) {
            adjacents.add(to);
            costs.add(cost);
        }
        
        public String toString() {
            return number + " " + distance;
        }
    }

}
