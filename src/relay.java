import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;


public class relay {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("relay.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("relay.out")));

        int n = Integer.parseInt(r.readLine());
        
        int[] forwards = new int[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            forwards[i] = Integer.parseInt(r.readLine());
        }
        
        int total = 0;
        for (int i = 1; i < n + 1; i++) {
            //see if cow gets in a loop
            HashSet<Integer> seen = new HashSet<Integer>();
            
            int current = i;
            
            while (!seen.contains(current)) {
                if (current != 0) {
                    seen.add(current);
                    current = forwards[current];
                } else break;
                
            }
            
            if (!seen.contains(current)) { //no loop
                total++;
            } //else nothing
        }
        
        w.println(total);
        w.flush();
    }

}
