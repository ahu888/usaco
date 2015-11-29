import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class bbreeds {
    static int possiblepairs = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("bbreeds.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("bbreeds.out")));

        char[] data = r.readLine().toCharArray();
        
        int pairs = 0;
        int rights = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '(') {
                pairs++;
            }
            if (data[i] == ')') {
                rights++;
            }
        }
        
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '(') {
                for (int j = i + 1; j < data.length; j++) {
                    if (data[j] == ')') {
                        possiblepairs++;
                    }
                }
            }
            
        }
        
        if (data.length % 2 == 1 || pairs != rights) {
            w.println(0);
            w.flush();
            System.exit(0);
        }
        
        int answer = 0;
        //for some reason the answer seems to follow pascal's triangle????
        //answer == n choose 0 + n choose 1 + n choose 2 ... + n choose n;
        //where n is the number of pairs
        for (int i = 0; i < pairs + 1; i++) {
            if (pairs == possiblepairs)
            answer += choose(pairs, i, true);
            else
                answer += choose(pairs, i, false);
            answer %= 2012;
        }
        
        w.println(answer);
        w.flush();
    }
    
    public static int choose(int n, int k, boolean inner) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (inner) {
            int num = 1;
            int denom = 1;
            for (int i = n; i > (n - k); i--) {
                num *= i;
                num %= 2012;
            }
            
            for (int i = 2; i <= k; i++) {
                denom *= i;
                denom %= 2012;
            }
            
            return (num/denom) % 2012; 
        } else {
            int num = choose(possiblepairs, k, true);
            int denom = 1;
            
            for (int i = 2; i <= k; i++) {
                denom *= i;
                denom %= 2012;
            }
            
            return (num/denom) % 2012;
        }
    }

}
