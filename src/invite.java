import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class invite {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("invite.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("invite.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int g = Integer.parseInt(inputs[1]);
        
        ArrayList<int[]> groups = new ArrayList<int[]>(); //use arraylist because we will be removing entries that we have fulfilled
        
        for (int i = 0; i < g; i++) {
            inputs = r.readLine().split(" ");
            int[] current = new int[inputs.length - 1];
            
            for (int j = 0; j < current.length; j++) {
                current[j] = Integer.parseInt(inputs[j + 1]);
            }
            
            groups.add(current);
        }
        
        boolean[] invited = new boolean[n + 1];
        invited[1] = true;
        boolean changed = true;
        
        int sum = 1;
        while (changed) {
            changed = false;
            for (int i = 0; i < groups.size(); i++) {
                int[] current = groups.get(i);
                
                boolean[] inGroup = new boolean[current.length];
                int count = 0;
                for (int j = 0; j < current.length; j++) {
                    if (invited[current[j]]) {
                        count++;
                        inGroup[j] = true;
                    }
                }
                if (count == current.length - 1) {
                    for (int j = 0; j < inGroup.length; j++) {
                        if (!inGroup[j]) {
                            invited[current[j]] = true;
                            sum++;
                            break;
                        }
                    }
                    changed = true;
                    groups.remove(i);
                } else if (count == current.length) {
                    groups.remove(i);
                }
            }
        }
        
        w.println(sum);
        w.flush();
    }

}
