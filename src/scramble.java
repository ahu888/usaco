import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;


public class scramble {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("scramble.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("scramble.out")));

        
        int n = Integer.parseInt(r.readLine());
        Character[][] contents = new Character[n][];
        String[] data = new String[n];
        
        for (int i = 0; i < n; i++) {
            char[] line = r.readLine().toCharArray();
            contents[i] = new Character[line.length];
            for (int j = 0; j < line.length; j++) {
                contents[i][j] = line[j];
            }
            Arrays.sort(contents[i]);
            
        }
        
        Arrays.sort(data);
        
        for (int i = 0; i < n; i++) {
            Arrays.sort(contents[i], Collections.reverseOrder());
            
        }
        
        
    }

}
