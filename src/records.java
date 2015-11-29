import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;


public class records {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("records.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("records.out")));

        int n = Integer.parseInt(r.readLine());
        
        //String[] groups = new String[n];
        HashMap<Group, Integer> counts = new HashMap<Group, Integer>();
        
        int biggest = 1;
        for (int i = 0; i < n; i++) {
            Group g = new Group(r.readLine());
            if (counts.get(g) != null) {
                counts.put(g, counts.get(g) + 1);
                if (counts.get(g) > biggest) {
                    biggest = counts.get(g);
                }
            } else
                counts.put(g, 1);
        }
        
        w.println(biggest);
        w.flush();
    }

    public static class Group {
        String[] names = new String[3];
        
        public Group(String s) {
            names = s.split(" ");
            Arrays.sort(names);
        }
        
        public boolean equals(Object o) {
            Group g = null;
            
            try {
                g = (Group) o;
            } catch (Exception e) {
                return false;
            }
            
            return Arrays.equals(g.names, names);
        }
        
        public int hashCode() {
            int returnVal = 0;
            char[][] chars = new char[3][];
            for (int i = 0; i < 3; i++) {
                chars[i] = names[i].toCharArray();
                for (int j = 0; j < chars[i].length; j++) {
                    returnVal += chars[i][j] - '0';
                }
            }
            return returnVal;
        }
    }
}
