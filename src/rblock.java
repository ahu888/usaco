import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class rblock {
    static final int infinity = 1000000000;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("rblock.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));

        String[] inputs = r.readLine().split(" ");
        
        int v = Integer.parseInt(inputs[0]);
        int e = Integer.parseInt(inputs[1]);
        int s = 1;
        
        Vertex[] vertices = new Vertex[v + 1];
        
        for (int i = 1; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        
        for (int i = 0; i < e; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);
            if (a == b) continue;
            vertices[a].addPath(b, c);
            vertices[b].addPath(a, c);
        }

        vertices[s].distance = 0;
        vertices[s].intree = true;
        
        int treesize = 1;
        //dijkstra's, priority queue implementation
        
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        for (int i = 0; i < vertices[s].adjacents.size(); i++) {
            vertices[vertices[s].adjacents.get(i)].distance = vertices[s].costs.get(i);
            vertices[vertices[s].adjacents.get(i)].parent = vertices[s];
        }
        
        vertices[s].distance = 0;
        for (int i = 1; i < vertices.length; i++) {
            queue.add(vertices[i]);
        }
        
        while (treesize < v) {
            Vertex closest = queue.remove();
            
            treesize++;
            if (closest != null)
                closest.intree = true;
            else break;
            
            for (int i = 0; i < closest.adjacents.size(); i++) {
                boolean valid = queue.remove(vertices[closest.adjacents.get(i)]);
                if (valid) {
                    if (vertices[closest.adjacents.get(i)].distance > closest.costs.get(i) + closest.distance) {
                        vertices[closest.adjacents.get(i)].distance = closest.costs.get(i) + closest.distance;
                        vertices[closest.adjacents.get(i)].parent = closest;
                    }
                    queue.add(vertices[closest.adjacents.get(i)]);
                }
                
            }
        }
        
        //retrace path to find the pairs that we must alter
        ArrayList<Integer> path = new ArrayList<Integer>();
        Vertex curr = vertices[v];
        while (curr.parent != null) {
            path.add(curr.number);
            curr = curr.parent;
        }
        path.add(1);
        
        
        int initialValue = vertices[v].distance;
        
        int highest = initialValue;
        HashSet<Pair> pathsSeen = new HashSet<Pair>();
        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = 0; j < vertices[path.get(i)].adjacents.size(); j++) {
                if (vertices[path.get(i)].adjacents.get(j) == path.get(i + 1)) {
                    for (int k = 1; k < vertices.length; k++) {
                        vertices[k].distance = infinity;
                        vertices[k].intree = false;
                    }
                    
                    vertices[path.get(i)].doublePath(j);
                    int doublingReverse = -1;
                    for (int k = 0; k < vertices[path.get(i + 1)].adjacents.size(); k++) {
                        if (vertices[path.get(i + 1)].adjacents.get(k) == path.get(i)) {
                            doublingReverse = k;
                        }
                    }
                    vertices[path.get(i + 1)].doublePath(doublingReverse);
                    
                    vertices[s].distance = 0;
                    vertices[s].intree = true;
                    
                    treesize = 1;
                    //dijkstra's, priority queue implementation
                    
                    queue = new PriorityQueue<Vertex>();
                    for (int a = 0; a < vertices[s].adjacents.size(); a++) {
                        vertices[vertices[s].adjacents.get(a)].distance = vertices[s].costs.get(a);
                    }
                    vertices[s].distance = 0;
                    for (int a = 1; a < vertices.length; a++) {
                        queue.add(vertices[a]);
                    }
                    
                    while (treesize < v) {
                        Vertex closest = queue.remove();
                        
                        treesize++;
                        if (closest != null)
                            closest.intree = true;
                        else break;
                        
                        for (int a = 0; a < closest.adjacents.size(); a++) {
                            boolean valid = queue.remove(vertices[closest.adjacents.get(a)]);
                            if (valid) {
                                if (vertices[closest.adjacents.get(a)].distance > closest.costs.get(a) + closest.distance) {
                                    vertices[closest.adjacents.get(a)].distance = closest.costs.get(a) + closest.distance;
                                }
                                queue.add(vertices[closest.adjacents.get(a)]);
                            }
                            
                        }
                    }
                    
                    if (vertices[v].distance > highest) {
                        highest = vertices[v].distance;
                    }
                    
                    vertices[path.get(i)].halfPath(j);
                    vertices[path.get(i + 1)].halfPath(doublingReverse);
                }
            }
            
        }
        w.println(highest - initialValue);
        w.flush();
    }

    public static class Vertex implements Comparable<Vertex> {
        int number;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Integer> costs = new ArrayList<Integer>();
        int distance = infinity;
        boolean intree = false;
        Vertex parent = null;
        
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
        
        //doubles the path to
        public void doublePath(int to) {
            costs.set(to, costs.get(to) * 2);
        }
        
        public void halfPath(int to) {
            costs.set(to, costs.get(to) / 2);
        }
    }
    
    public static class Pair {
        int a, b;
        
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        public int hashCode() {
            return Integer.valueOf((a * b) + "" + (a + b)); //probably safe... right?
        }
        
        public boolean equals(Object o) {
            return o.hashCode() == hashCode();
            
        }
    }

}
