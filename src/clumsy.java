import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class clumsy {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("clumsy.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("clumsy.out")));

        char[] data = r.readLine().toCharArray();
        
        int mustChange = 0;
        
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            
            if (leftCount < rightCount) {
                mustChange++;
                rightCount--;
                leftCount++;
            }
        }
        
        if (leftCount > rightCount) {
            mustChange += (leftCount - rightCount) / 2;
        }
        
        w.println(mustChange);
        w.flush();
    }
}
