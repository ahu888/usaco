import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class cowfind {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("cowfind.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("cowfind.out")));

        char[] data = r.readLine().toCharArray();
        
        int leftCount = 0; //number of ((
        int possible = 0; //when we find a )) we add the number of leftcounts to the possible
        
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] == '(' && data[i + 1] == '(') {
                leftCount++;
            } else if (data[i] == ')' && data[i + 1] == ')') {
                possible += leftCount;
            }
        }
        
        w.println(possible);
        w.flush();
        
    }

}
