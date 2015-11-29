import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;


public class scales {

    static int totalused = 0;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new FileReader("scales.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("scales.out")));
              //System.out.println(System.getProperty("java.runtime.version"));
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int c = Integer.parseInt(inputs[1]);
        
        ArrayList<Integer> weights = new ArrayList<Integer>();
        
        //BitSet possible = new BitSet(c + 1);
        
        for (int i = 0; i < n; i++) {
            //weights[i] = Integer.parseInt(r.readLine());
            int temp = Integer.parseInt(r.readLine());
            if (temp <= c) 
                weights.add(temp);
            //possible.set(weights[i]);
        }
        /*
        ArrayList<Integer> weights2 = new ArrayList<Integer>();
        ArrayList<Integer> weights3 = new ArrayList<Integer>();
        for (int i = 0; i < weights.size(); i++) {
            weights2.add(weights.get(i));
            weights3.add(weights.get(i));
        }*/
        
        int required = c;
        
        int a = weights.get(weights.size() - 2);
        int b = weights.get(weights.size() - 1);
        /*
        int first = weights.remove(0);
        
        totalused += first;
        calculate(weights, a, b, required - first);
        
        int firstResult = totalused;
        totalused = 0;
        //weights.add(0, first);
        
        int second = weights.remove(0);
        totalused += second;
        calculate(weights2, a, b, required - second);
        
        if (totalused > firstResult)
            firstResult = totalused;
        
        totalused = 0;
        calculate(weights3, a, b, required);
        
        if (totalused > firstResult)
            firstResult = totalused;*/
        /*
        for (int i = 0; i < c + 1; i++) {
            if (possible.get(i)) {
                for (int j = 0; j < n; j++) {
                    possible.set(i + weights[j]);
                }
            }
        }
        int ans = 0;
        for (int i = c; i >= 0; i++) {
            if (possible.get(i)) {
                ans = i;
                break;
            }
        }*/
        
        w.println(calculate(weights, a, b, required));
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static int calculate(ArrayList<Integer> weights, int a, int b, int required) {
        if (required == 0 || weights.size() < 2)
            return 0;
        if (weights.size() == 2) {
            if (a == required) {
                totalused += a;
                return a;
            }
            if (b == required) {
                totalused += b;
                return b;
            }
            if (a + b < required) {
                totalused += a + b;
                return a + b;
            }
            if (a + b > required) {
                if (b < required && b >= a)
                    return b;
                else if (a < required)
                    return a;
                return 0;
            }
            if (a + b == required) {
                totalused += a + b;
                return a + b;
            }
            return 1000000000; //should never get to here
        }
        if (a + b == required) {
            totalused += a + b;
            return a + b;
        }
        if (a == required) {
            totalused += a;
            return a;
        }
        if (b == required) {
            totalused += b;
            return b;
        }
        if (b > required) {
            weights.remove(weights.size() - 1);
            int calc = calculate(weights, weights.get(weights.size() - 2), a, required);
            weights.add(b);
            return calc;
        }
        if (a + b > required && a < required && b < required) {
            weights.remove(weights.size() - 2);
            int choosea = a + calculate(weights, weights.get(weights.size() - 2), b, required - a);
            
            weights.add(weights.size() - 1, a); //if arraylist is a, b, c, and we add d at position 2 we get a, b, d, c
            weights.remove(weights.size() - 1);
            
            int chooseb = b + calculate(weights, weights.get(weights.size() - 2), a, required - b);
            
            weights.add(b);
            return Math.max(choosea, chooseb);
        }
        if (a + b < required) { //choose b
            totalused += b;
            weights.remove(weights.size() - 1);
            int calc = calculate(weights, weights.get(weights.size() - 2), a, required - b);
            weights.add(b);
            return b + calc;
        }
        return 1000000000;
    }

}
