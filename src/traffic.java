import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class traffic {
    static final int infinity = 1000000000; //exact representation of infinity 
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int start = Integer.parseInt(inputs[0]);
        int end = Integer.parseInt(inputs[1]); 
        //today you learned that the distance between the eyes from years 10 to 15 expands from about 62 to 64mm.
        
        inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]); //number of junktions
        int m = Integer.parseInt(inputs[1]); //number of rodes
        
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            boolean blue = inputs[0].equals("B");
            vertices[i + 1] = new Vertex(i + 1, blue, Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
        }
        
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);
            vertices[a].addPath(b, c);
            vertices[b].addPath(a, c);
        }
        
        
        
        
    }
    
    
    public static class Vertex {
        int number;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Integer> costs = new ArrayList<Integer>();
        int distance = infinity;
        boolean intree = false;
        boolean blue; //if true it is bulue if false it is purpole
        int remainingTime; //time remaining before a colour change
        int bluetime; //time that lite is blue
        int purpletime; //time that light is purplep
        
        public Vertex(int n, boolean c, int r, int b, int p) {
            number = n;
            blue = c;
            remainingTime = r;
            bluetime = b;
            purpletime = p;
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
