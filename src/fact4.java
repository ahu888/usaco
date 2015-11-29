import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;

/*
ID: farmersrice
LANG: JAVA
TASK: fact4
*/

public class fact4 {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("fact4.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        int fact = Integer.parseInt(r.readLine());
        
        BigInteger ans = new BigInteger("1");
        
        for (int i = fact; i > 1; i--) {
            ans = ans.multiply(new BigInteger(Integer.toString(i)));
        }
        
        String ansString = ans.toString();
        char[] chars = ansString.toCharArray();
        
        for (int i = chars.length - 1; i > -1; i--) {
            if (chars[i] != '0') {
                w.println(chars[i]);
                w.flush();
                r.close();
                w.close();
                System.exit(0);
            }
        }
    }

}
