import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class contest {
    static Cow[] cows;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("palpath.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        cows = new Cow[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            cows[i] = new Cow(i);
        }
        
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            
            cows[a].addBelow(b);
            cows[b].addAbove(a);
        }
        
        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (cows[i].above.size() + cows[i].below.size() == n - 1) {
                answer++;
            }
        }
        
        w.println(answer);
        w.flush();
    }
    
    public static class Cow {
        int number;
        ArrayList<Integer> above = new ArrayList<Integer>();
        ArrayList<Integer> below = new ArrayList<Integer>();
        
        public Cow(int n) {
            number = n;
        }
        
        public void addAbove(int a) {
            if (above.contains(a)) {
                return;
            }
            above.add(a);
            for (int i = 0; i < cows[a].above.size(); i++) {
                addAbove(cows[a].above.get(i));
                cows[cows[a].above.get(i)].addBelow(number);
            }
            
        }
        
        public void addBelow(int a) {
            if (below.contains(a)) {
                return;
            }
            below.add(a);
            for (int i = 0; i < cows[a].below.size(); i++) {
                addBelow(cows[a].below.get(i));
                cows[cows[a].below.get(i)].addAbove(number);
            }
        }
    }

}
