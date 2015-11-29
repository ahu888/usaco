import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class scode {
    
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("scode.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));

        String input = r.readLine();
        if (input.length() == 2) {
            w.println(0);
        } else 
            w.println(count(input.toCharArray()) - 1);
        w.flush();
        w.close();
    }
    
    public static int count(char[] chars) {
        if (chars.length == 2) {
            return 1;
        }
        if (chars.length % 2 == 0) {
            return 1;
        }
        if (chars.length == 1) {
            return 0;
        }
        
        char[] half = new char[chars.length / 2];
        char[] otherHalf = new char[(chars.length / 2) + 1];
        char[] thirdHalf = new char[(chars.length / 2) + 1];
        char[] fourthHalf = new char[half.length];
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length / 2) {
                half[i] = chars[i];
            } else if (i >= chars.length / 2) {
                otherHalf[i - (chars.length / 2)] = chars[i];
            }
            
            if (i < thirdHalf.length) {
                thirdHalf[i] = chars[i];
            } else if (i >= thirdHalf.length) {
                fourthHalf[i - thirdHalf.length] = chars[i];
            }
        }
        
        //check if it is possible to generate this combination, for example BAC cannot be generated in any way
        //if half is equal to otherHalf minus a char
        boolean good = true;
        boolean good2 = true;
        for (int i = 0; i < half.length; i++) {
            if (half[i] != otherHalf[i]) {
                good = false;
            }
            if (half[i] != otherHalf[i + 1]) {
                good2 = false;
            }
        }
        
        boolean good3 = true;
        boolean good4 = true;
        
        for (int i = 0; i < fourthHalf.length; i++) {
            if (fourthHalf[i] != thirdHalf[i]) {
                good3 = false;
            }
            if (fourthHalf[i] != thirdHalf[i + 1]) {
                good4 = false;
            }
        }
        
        if (!good && !good2 && !good3 && !good4) {
            return 1;
        }
        
        int sum = 1;
        
        
        if (good) {
            sum += count(otherHalf);
        }
        if (good2) {
            sum += count(otherHalf);
        }
        if (good3) {
            sum += count(thirdHalf);
        }
        if (good4) {
            sum += count(thirdHalf);
        }
        
        return sum;
    }

}
