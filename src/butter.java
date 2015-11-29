import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;


/*
ID: farmersrice
LANG: JAVA
TASK: butter
*/

public class butter {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("butter.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int v = Integer.parseInt(inputs[1]);
        int e = Integer.parseInt(inputs[2]);
        int s = 0;
        
        int[] totalCost = new int[v + 1];
        
        int[] cowPastures = new int[n];
        
        for (int i = 0 ; i < n; i++) {
            cowPastures[i] = Integer.parseInt(r.readLine());
        }
        

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
        
        
        for (int a = 0; a < cowPastures.length; a++) {
            
            for (int i = 1; i < vertices.length; i++) {
                vertices[i].distance = infinity;
                vertices[i].intree = false;
            }
            
            s = cowPastures[a];
            
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
                    
                    if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i) + closest.distance && !valid) {
                        queue.remove(vertices[closest.adjacents.get(i)]);
                        vertices[closest.adjacents.get(i)].distance = closest.costs.get(i) + closest.distance;
                        queue.add(vertices[closest.adjacents.get(i)]);
                    }
                        
                }
            }
            
            for (int i = 1; i < vertices.length; i++) {
                totalCost[i] += vertices[i].distance;
            }
        }
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 1; i < totalCost.length; i++) {
            if (totalCost[i] < min) {
                min = totalCost[i];
            }
        }
        
        w.println(min);
        w.flush();
        
        r.close();
        w.close();
        System.exit(0);
        /*
        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int p = Integer.parseInt(inputs[1]);
        int c = Integer.parseInt(inputs[2]);
        
        int[] cowfields = new int[n];
        for (int i = 0; i < n; i++) {
            cowfields[i] = Integer.parseInt(r.readLine());
        }
        
        int[][] distances = new int[p + 1][p + 1];
        for (int i = 0; i < p + 1; i++) {
            for (int j = 0; j < p + 1; j++) {
                if (i != j)
                    distances[i][j] = Integer.MAX_VALUE;
                else
                    distances[i][j] = 0;
            }
        }
        
        for (int i = 0; i < c; i++) {
            input = r.readLine();
            inputs = input.split(" ");
            
            int firstpath = Integer.parseInt(inputs[0]);
            int secondpath = Integer.parseInt(inputs[1]);
            int dist = Integer.parseInt(inputs[2]);
            
            distances[firstpath][secondpath] = dist;
            distances[secondpath][firstpath] = dist;
            //paths[firstpath - 1].addAdjacent(paths[secondpath - 1], dist);
            //paths[secondpath - 1].addAdjacent(paths[firstpath - 1], dist);
        }
        
        long start = System.currentTimeMillis();
        
        int[] vertexTimes = new int[p];
        //run priorityqueue dijkstra's algorithm on all the pastures w/cows in them, sum it all in vertextimes and find the least sum
        //this is p*n log n complexity
        
        
        
        
        
        
        
        
        
        
        
        /* floyd is too slow. impossible.
        for (int k = 1; k < p + 1; k++) {
            for (int i = 1; i < p + 1; i++) {
                for (int j = i + 1; j < p + 1; j++) {
                    if (distances[i][j] > distances[i][k] + distances[k][j] && distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        distances[j][i] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        
        int bestvertex = 0;
        int shortestdist = Integer.MAX_VALUE;
        for (int i = 1; i < p + 1; i++) {
            int totalsum = 0;
            for (int j = 0; j < n; j++) {
                totalsum += distances[i][cowfields[j]];
            }
            if (totalsum < shortestdist) {
                shortestdist = totalsum;
                bestvertex = i;
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        w.println(shortestdist);
        w.flush();
        r.close();
        w.close();
        System.exit(0);*/
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
