import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: farmersrice
LANG: JAVA
TASK: humble
*/

public class humble {
    static int[] primes;
    static boolean found = false;
    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        BufferedReader r = new BufferedReader(new FileReader("humble.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        int k = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        
        input = r.readLine();
        inputs = input.split(" ");
        
        primes = new int[k];
        
        for (int i = 0; i < k; i++) {
            primes[i] = Integer.parseInt(inputs[i]);
        }
        Arrays.sort(primes);

        long start = System.currentTimeMillis();
        
        //int counter = (int) primes[0]; //the number we are trying to find
        int index = 1; //the number of the humble counter
        
        int[] humbles = new int[n + 1];
        humbles[0] = 1;
        
        int[] indices = new int[primes.length];
        
        int[] values = new int[primes.length];
        
        for (int i = 0; i < k; i++) {
            values[i] = primes[i];
        }
        
        while (humbles[n] == 0) {
            //find min value in values
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < k; i++) {
                if (values[i] < min) {
                    min = values[i];
                    minIndex = i;
                } else if (values[i] == min) {
                    indices[i]++;
                    values[i] = humbles[indices[i]] * primes[i];
                }
            }
            humbles[index++] = min;
            indices[minIndex]++;
            values[minIndex] = humbles[indices[minIndex]] * primes[minIndex];
        }
        w.println(humbles[n]);
        System.out.println("Cost: " + (System.currentTimeMillis() - start));
        
        w.flush();
        r.close();
        w.close();
        
        System.exit(0);
        /*
4 19
2 3 5 7

6 25000
2 3 5 7 11 13
         */
    }
    
    /*
    public static boolean possible(int n) {
        //brute force division, bad speed
        //Boolean possible = poss.get(n);
        //boolean possible = poss[n];
        /*
        boolean possible = poss.get(n);
        //if (possible != null) {
            if (possible) {
                found = true;
                return 1;
            }
                
            //else if (!possible)
            //    found = false;
        //}
        
        //boolean[] good = new boolean[primes.length];
        double quotient = 0;
        for (int i = 0; i < primes.length; i++) {
            quotient = n/primes[i];
            int intDown = (int) quotient;
            if (quotient == intDown) {
                //return (poss.get(intDown));
            }
        }
        return false;
        
        
        
        
        
        
        
        
        
        
        //the brute force multiplication solution, terrible speed
        /*
        Boolean possible = poss.get(n);
        if (possible != null && possible) {
            found = true;
            return;
        }

        if (possible != null && !possible) {
            found = false;
            return;
        }
        
        if (found == false) {
            boolean[] good = new boolean[primes.length];
            for (int i = previousIndex; i < primes.length; i++) {
                int newValue = value * primes[i];
                poss.put(newValue, true);
                if (newValue < n)
                    good[i] = true;
                if (newValue == n) {
                    found = true;
                    poss.put(n, true);
                    return;
                }
            }
            for (int i = previousIndex; i < primes.length; i++) {
                int newValue = value * primes[i];
                
                if (good[i] && found == false) {
                    possible(n, newValue, i);
                } else if (found)
                    return;
            }
        }
    }*/

}
