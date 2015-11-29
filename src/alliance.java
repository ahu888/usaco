import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class alliance {
    static int groupID = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        Farm[] farms = new Farm[n + 1]; //index 0 not used
        for (int i = 0; i < n; i++) {
            farms[i + 1] = new Farm(i + 1);
        }
        
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            farms[a].connect(b);
            farms[b].connect(a);
        }
        
        ArrayList<Component> components = new ArrayList<Component>();
        for (int i = 0; i < n; i++) {
            if (!farms[i + 1].visited) {
                Component c = new Component(groupID++);
                components.add(c);
                fill(farms, i + 1, c, -1);
            }
        }
        
        long totalWays = 1;
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).circular) {
                totalWays *= 2;
                totalWays %= 1000000007;
            } else if (components.get(i).farms.size() != 1) {
                totalWays *= components.get(i).farms.size();
                totalWays %= 1000000007;
            }
        }
        w.println(totalWays);
        w.flush();
    }
    
    public static void fill(Farm[] farms, int farm, Component c, int from) {
        c.add(farm);
        farms[farm].visited = true;
        int size = farms[farm].connectedFarms.size();
        if (!c.circular)
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (farms[farm].get(i) == farms[farm].get(j)) {
                        c.circular = true;
                    }
                }
            }
        for (int i = 0; i < size; i++) {
            if (from != -1 && farms[farms[farm].get(i)].visited && farms[farm].get(i) != from) {
                c.circular = true;
            } else if (!farms[farms[farm].get(i)].visited){
                fill(farms, farms[farm].get(i), c, farm);
            }
        }
    }
    
    public static class Farm {
        int number;
        ArrayList<Integer> connectedFarms;
        boolean visited;
        
        public Farm(int n) {
            number = n;
            connectedFarms = new ArrayList<Integer>();
        }
        
        public void connect(int n) {
            connectedFarms.add(n);
        }
        
        public int get(int n) {
            return connectedFarms.get(n);
        }
    }
    
    public static class Component {
        int number;
        ArrayList<Integer> farms = new ArrayList<Integer>();
        boolean circular = false;
        
        public Component(int n) {
            number = n;
        }
        
        public void add(int n) {
            farms.add(n);
        }
        
        public String toString() {
            String farmContents = "";
            for (int i = 0; i < farms.size(); i++) {
                farmContents += farms.get(i) + " ";
            }
            return number + " " + circular + " " + farmContents;
        }
    }

}
