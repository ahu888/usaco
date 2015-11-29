import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
ID: farmersrice
LANG: JAVA
TASK: fence
*/
public class fence {
    static int circuitPos = 0;
    static int[] circuit;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("fence.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
        
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        int f = Integer.parseInt(r.readLine());
        
        Vertex[] vertices = new Vertex[501];
        //Fence[] fences = new Fence[f + 1];
        
        int[][] paths = new int[f][2];
        for (int i = 0; i < f; i++) {
            String[] inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            paths[i][0] = a;
            paths[i][1] = b;
            if (vertices[a] == null) {
                vertices[a] = new Vertex(a);
            }
            if (vertices[b] == null) {
                vertices[b] = new Vertex(b);
            }
            
            vertices[a].addAdjacent(vertices[b]);
            vertices[b].addAdjacent(vertices[a]);
        }
        
        for (int i = 0; i < 501; i++) {
            if (vertices[i] != null) {
                Collections.sort(vertices[i].adjacents);
            }
        }
        
        boolean allEven = true;
        int start = 0;
        
        for (int i = 0; i < 501; i++) {
            if (vertices[i] != null && vertices[i].adjacents.size() % 2 == 1) {
                start = i;
                allEven = false;
                break;
            }
        }
        
        if (allEven) {
            for (int i = 0; i < 501; i++) {
                if (vertices[i] != null) {
                    start = i;
                    break;
                }
            }
        }
        
        
        circuit = new int[f + 1];
        findCircuit(vertices[start]);
       
        
        for (int i = 0; i < circuit.length; i++) {
            w.println(circuit[circuit.length - 1 - i]);
        }
        w.flush();
    }
    
    //eulerian tour finder
    public static void findCircuit(Vertex i) {
        if (i.adjacents.size() == 0) { //if it has no neighbors
            circuit[circuitPos++] = i.number;
        } else {
            //find biggest neighbor of i and search it
            while (i.adjacents.size() != 0) {
                Vertex j = i.adjacents.remove(0);
                j.adjacents.remove(i);
                findCircuit(j);
            }
            circuit[circuitPos++] = i.number;
        }
    }
    
    public static class Vertex implements Comparable<Vertex> {
        int number;
        ArrayList<Vertex> adjacents = new ArrayList<Vertex>();
        
        public int compareTo(Vertex v) {
            return number - v.number;
        }
        
        public Vertex(int n) {
            number = n;
        }
        
        public void addAdjacent(Vertex v) {
            adjacents.add(v);
        }
        
        public String toString() {
            String answer = number + " ";
            for (int i = 0; i < adjacents.size(); i++) {
                answer += adjacents.get(i).number + " ";
            }
            return answer;
        }
    }

}
