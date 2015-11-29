import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class baleshare {
    static int d1 = 0;
    static int d2 = 0;
    static int difference = 1000000000;
    static int[] combination;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        int n = Integer.parseInt(r.readLine());
        
        int[] bales = new int[n];
        
        for (int i = 0; i < n; i++) {
            bales[i] = Integer.parseInt(r.readLine());
        }
        
        //bales in each barn
        ArrayList<Integer> set1 = new ArrayList<Integer>();
        ArrayList<Integer> set2 = new ArrayList<Integer>();
        ArrayList<Integer> set3 = new ArrayList<Integer>();
        int[] sums = new int[3];
        
        Arrays.sort(bales);
        for (int i = bales.length - 1; i > -1; i--) {
            //find minimum set
            int lowest = Integer.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < 3; j++) {
                if (sums[j] < lowest) {
                    lowest = sums[j];
                    index = j;
                }
            }
            
            sums[index] += bales[i];
        }
        
        int highest = 0;
        for (int i = 0; i < 3; i++) {
            highest = Math.max(highest, sums[i]);
        }
        
        w.println(highest);
        w.flush();
        System.exit(0);
        
        //balance sets
        /*
         * all bales are size 1
         * sizes 10, 0, 0
         * balance 1 and 2
         * 5 5 0
         * balance 1 and 3
         * 3 5 2
         * balancec 2 and 3
         * 3 4 3
         * 
         * 6 0 0
         * balance 1 and 2
         * 4 2 0 //lol
         * balance 1 and 3
         * 2 2 2
         * balance 2 and 3
         * 2 2 2
         * 
         * values 22 (2, 2, 3, 7, 8), 0, 0
         * balance 1 and 2
         * 12 (2, 8, 2) 10 (3, 7) 0
         * balance 1 and 3
         * 8 (8) 10 (3, 7) 4(2, 2)
         * balance 2 and 3
         * 8 7 7
         * 
         * how to balance out 2 sets:
         * find minimum half
         * add to another set
         */
        
        //first balance 1 and 2, 2 is guaranteed to be empty
        
        for (int i = 0; i < n; i++) {
            totalSum += set1.get(i);
        }
        balance(set1, -1, 0, new int[n]);
        set1.clear();
        
        for (int i = 0; i < combination.length; i++) {
            if (combination[i] == 0) {
                set1.add(bales[i]);
            } else {
                set2.add(bales[i]);
            }
        }
        sums[0] = d2;
        sums[1] = d1;
        combination = null;
        totalSum = 0;
        
        //now balance 1 and 3, set3 is guaranteed to be empty
        
        for (int i = 0; i < set1.size(); i++) {
            totalSum += set1.get(i);
        }
        d1 = 0;
        d2 = 0;
        difference = 1000000000;
        balance(set1, -1, 0, new int[set1.size()]);
        
        for (int i = 0; i < combination.length; i++) {
            if (combination[i] == 1) {
                set3.add(set1.get(i));
                set1.set(i, 0);
            }
        }
        
        for (int i = 0; i < set1.size(); i++) {
            if (set1.get(i) == 0) {
                set1.remove(i);
                i--;
            }
        }
        sums[0] = d2;
        sums[2] = d1;
        combination = null;
        totalSum = 0;
        //now balance 2 and 3, 1v1 
        
        ArrayList<Integer> set = new ArrayList<Integer>();
        
        for (int i = 0; i < set2.size(); i++) {
            totalSum += set2.get(i);
            set.add(set2.get(i));
        }
        
        for (int i = 0; i < set3.size(); i++) {
            totalSum += set3.get(i);
            set.add(set3.get(i));
        }
        
        d1 = 0;
        d2 = 0;
        difference = 1000000000;
        
        balance(set, -1, 0, new int[set.size()]);
        set2.clear();
        set3.clear();
        for (int i = 0; i < combination.length; i++) {
            if (combination[i] == 0) {
                set2.add(set.get(i));
            } else {
                set3.add(set.get(i));
            }
        }
        
        sums[1] = d2;
        sums[2] = d1;
        
        //int highest = 0;
        for (int i = 0; i < 3; i++) {
            if (sums[i] > highest) highest = sums[i];
        }
        
        w.println(highest);
        w.flush();
        
    }
    
    static int totalSum = 0;
    public static void balance(ArrayList<Integer> set, int depth, int sum, int[] combination) {
        int length = set.size();
        
        if (depth == length - 1) {
            if (Math.abs(totalSum - sum - sum) < difference) {
                d1 = sum;
                d2 = totalSum - sum;
                difference = Math.abs(totalSum - sum - sum);
                baleshare.combination = new int[combination.length];
                
                for (int i = 0; i < combination.length; i++) {
                    baleshare.combination[i] = combination[i];
                }
            }
            
            return;
        }
        
        depth++;
        combination[depth] = 1;
        balance(set, depth, sum + set.get(depth), combination);
        combination[depth] = 0;
        balance(set, depth, sum, combination);
    }
    

}
