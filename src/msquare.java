import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;

/*
ID: farmersrice
LANG: JAVA
TASK: msquare
*/

public class msquare {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        int target = 0;
        
        for (int i = 0; i < 8; i++) {
            target *= 10;
            target += Integer.parseInt(inputs[i]);
        }
        
        long start = System.currentTimeMillis();
        //dfs
        LinkedList<Integer> arrayqueue = new LinkedList<Integer>();
        LinkedList<String> combinations = new LinkedList<String>();
        
        arrayqueue.add(12345678);
        combinations.add("");
        
        HashSet<Integer> seen = new HashSet<Integer>();
        
        int currentarr = arrayqueue.remove();
        String currentcombo = combinations.remove();
        
        int[] current = new int[8];
        while (currentarr != target) {
            //String currentstring = Arrays.toString(currentarr);
            if (seen.contains(currentarr)) {
                currentarr = arrayqueue.remove();
                currentcombo = combinations.remove();
                continue;
            } else {
                seen.add(currentarr);
            }
            int temp = currentarr;
            for (int i = 0; i < 8; i++) {
                current[8 - i - 1] = temp % 10;
                temp /= 10;
            }
            
            int a = current[7] * 10000000 + current[6] * 1000000 + current[5] * 100000 + current[4] * 10000 + current[3] * 1000 + current[2] * 100 + current[1] * 10 + current[0];
            arrayqueue.add(a);
            combinations.add(currentcombo + "A");
            
            
            
            int b = current[3] * 10000000 + current[0] * 1000000 + current[1] * 100000 + current[2] * 10000 + current[5] * 1000 + current[6] * 100 + current[7] * 10 + current[4];
            
            arrayqueue.add(b);
            combinations.add(currentcombo + "B");
            
            
            
            int c = current[0] * 10000000 + current[6] * 1000000 + current[1] * 100000 + current[3] * 10000 + current[4] * 1000 + current[2] * 100 + current[5] * 10 + current[7];
            
            arrayqueue.add(c);
            combinations.add(currentcombo + "C");
            
            currentarr = arrayqueue.remove();
            currentcombo = combinations.remove();
        }
        
        w.println(currentcombo.length());
        //print current combo
        char[] data = currentcombo.toCharArray();
        for (int i = 1; i <= data.length; i++) {
            if (i % 60 == 0) {
                w.println();
            }
            w.print(data[i - 1]);
        }
        
        //System.out.println(System.currentTimeMillis() - start);
        w.println();
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }

}
