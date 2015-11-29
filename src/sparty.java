import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//why was the excellent software engineer fired from his top secret government job?















//no comment.
public class sparty {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int v = Integer.parseInt(inputs[0]); //number of farms
        int e = Integer.parseInt(inputs[1]); //number of paths
        int s = Integer.parseInt(inputs[2]); //farm at which the party at
        
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
        }
        
        vertices[s].distance = 0;
        vertices[s].intree = true;
        
        int treesize = 1;
        //dijkstra's to the farm
        for (int i = 0; i < vertices[s].adjacents.size(); i++) {
            vertices[vertices[s].adjacents.get(i)].distance = vertices[s].costs.get(i);
        }
        
        while (treesize < v) {
            Vertex closest = null;
            int closestDist = infinity;
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
        
        int[] toTimes = new int[v];
        
        int[][] dist = new int[v + 1][v + 1];
        for (int i = 0; i < v + 1; i++) {
            for (int j = 0; j < v + 1; j++) {
                dist[i][j] = infinity;
            }
        }
        
        for (int i = 1; i < v + 1; i++) {
            toTimes[i - 1] = vertices[i].distance;
            vertices[i].distance = infinity;
            vertices[i].intree = false;
            for (int j = 0; j < vertices[i].adjacents.size(); j++) {
                dist[vertices[i].adjacents.get(j)][i] = vertices[i].costs.get(j);
            }
            vertices[i].adjacents = new ArrayList<Integer>();
            vertices[i].costs = new ArrayList<Integer>();
        }
        
        //dijkstra's back from the farm, so we switch the from things
        for (int i = 1; i < v + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (dist[i][j] != infinity)
                    vertices[i].addPath(j, dist[i][j]);
            }
        }
        
        vertices[s].distance = 0;
        vertices[s].intree = true;
        
        treesize = 1;
        
        for (int i = 0; i < vertices[s].adjacents.size(); i++) {
            vertices[vertices[s].adjacents.get(i)].distance = vertices[s].costs.get(i);
        }
        
        while (treesize < v) {
            Vertex closest = null;
            int closestDist = infinity;
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
        int max = 0;
        for (int i = 1; i < v + 1; i++) {
            max = Math.max(toTimes[i - 1] + vertices[i].distance, max);
        }
        
        w.println(max);
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
        
        public String toString() {
            return number + " " + distance;
        }
    }
    
}
