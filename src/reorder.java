import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class reorder {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("reorder.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("reorder.out")));

        
        //sample case
        //5 1 4 2 3
        //2 5 3 1 4
        int n = Integer.parseInt(r.readLine());
        int[] initial = new int[n];
        
        for (int i = 0; i < n; i++) {
            initial[i] = Integer.parseInt(r.readLine());
        }
        
        int[] end = new int[n];
        int[] mustBe = new int[n + 1]; //which position item must be in at the end, for example mustBe[5] == 1 and mustBe[1] == 3;
        for (int i = 0; i < n; i++) {
            end[i] = Integer.parseInt(r.readLine());
            mustBe[end[i]] = i;
        }
        
        int longestCycle = 0;
        int shiftCount = 0;
        for (int i = 0; i < n; i++) {
            if (initial[i] != end[i]) { //we perform a cyclic shift
                shiftCount++;
                int current = initial[i];
                int displaced = current;
                int times = 0;
                while (current != end[i]) {
                    displaced = initial[mustBe[current]];
                    initial[mustBe[current]] = current;
                    current = displaced;
                    times++;
                }
                initial[i] = displaced;
                times++;
                if (times > longestCycle) {
                    longestCycle = times;
                }
            }
        }
        
        if (shiftCount == 0) {
            w.println(0 + " " + -1);
        } else {
            w.println(shiftCount + " " + longestCycle);
        }
        w.flush();
    }

}
