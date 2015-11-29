import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class typo {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("typo.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("typo.out")));

        char[] data = r.readLine().toCharArray();
        
        int leftCount = 0;
        int rightCount = 0;
        
        int difference = 0; ///number of unbalanced lefts
        int answer = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '(') {
                leftCount++;
                difference++;
            } else {
                rightCount++;
                difference--;
            }
            
            if (difference <= 1) {
                leftCount = 0; //get rid of the number of balanced ones
            }
            if (difference == -1) {
                answer = rightCount; //at this point we just flip a right
                break;
            }
        }
        
        if (difference > 0) { //if we have too many unbalanced lefts we flip a left
            answer = leftCount;
        }
        w.println(answer);
        /*
        if (Math.abs(leftCount - rightCount) > 2 || leftCount - rightCount == 0 || data.length % 2 == 1) {
            w.println(0);
            w.flush();
            System.exit(0);
        }
        
        int valid = 0;
        for (int i = 0; i < data.length; i++) {
            if (leftCount > rightCount && differences[i] > 0) { //too many left
                if (data[i] == '(') {
                    data[i] = ')';
                    
                    if (balanced(data)) {
                        valid++;
                    }
                    data[i] = '(';
                }
            } else if (rightCount > leftCount && differences[i] < 0) {
                if (data[i] == ')') {
                    data[i] = '(';
                    if (balanced(data)) {
                        valid++;
                    }
                    data[i] = ')';
                }
            }
        }
        
        w.println(valid);
        */w.flush();
    }
    
    public static boolean balanced(char[] data) {
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            
            if (leftCount < rightCount) {
                return false;
            }
        }
        
        return true;
    }

}
