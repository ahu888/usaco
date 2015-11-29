import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;


public class trough {
    static int possible = 0;
    static String answer = null;
    static Condition[] conditions;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        //LinkedList<int[]> possible = new LinkedList<int[]>();
        
        conditions = new Condition[m];
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            conditions[i] = new Condition(inputs[0], Integer.parseInt(inputs[1]));
        }
        
        recursiveGenerate(new StringBuilder(), 0, n);
        
        if (possible == 0) {
            w.println("IMPOSSIBLE");
        } else if (possible == 1) {
            w.println(answer);
        } else if (possible > 1) {
            w.println("NOT UNIQUE");
        }
        
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static void recursiveGenerate(StringBuilder current, int depth, int n) {
        if (depth == n) {
            String data = current.toString();
            int passedCount = 0;
            for (int i = 0; i < conditions.length; i++) {
                //check if the current string passes conditions
                char[] currentdata = data.toCharArray();
                
                int numTrue = 0;
                for (int j = 0; j < conditions[i].data.length; j++) {
                    if (conditions[i].data[j] == '1' && currentdata[j] == '1') {
                        numTrue++;
                    }
                }
                if (numTrue != conditions[i].numFilled) {
                    return;
                } else {
                    passedCount++;
                }
            }
            if (passedCount == conditions.length) {
                possible++;
                answer = new String(data);
            }
            return;
        }
        
        current.append("0");
        recursiveGenerate(current, depth + 1, n);
        current.deleteCharAt(current.length() - 1);
        current.append("1");
        recursiveGenerate(current, depth + 1, n);
        current.deleteCharAt(current.length() - 1);
    }
    
    public static class Condition {
        char[] data;
        int numFilled;
        
        public Condition(String d, int n) {
            data = d.toCharArray();
            numFilled = n;
        }
    }

}
