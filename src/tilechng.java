import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class tilechng {
    //static ArrayList<Combination> combinations = new ArrayList<Combination>(); //we never have duplicates
    static int[] squares;
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("tilechng.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("tilechng.out")));

        String[] inputs = r.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);
        
        
        squares = new int[(int) Math.sqrt(m) + 1];
        
        for (int i = 0; i < squares.length; i++) {
            squares[i] = i * i;
        }
        
        //combine(0, new int[n], 0, -1);
        
        
        int[] current = new int[n];
        long currentSum = 0;
        for (int i = 0; i < n; i++) {
            current[n] = Integer.parseInt(r.readLine());
            currentSum += squares[current[n]];
        }
        
        Arrays.sort(current);

        int[][] dp = new int[10000][n]; //dp[cost][contents] = area
    }
    /*
    public static void combine(int currentSum, int[] values, int depth, int previous) {
        if (depth == n) {
            if (currentSum == m) {
                combinations.add(new Combination(values));
            }
            return;
        }
        
        for (int i = previous + 1; i < squares.length; i++) {
            values[depth] = squares[i];
            if (currentSum + values[depth] < m) {
                combine(currentSum + values[depth], values, depth + 1, i);
            }
            values[depth] = 0;
        }
    }
    
    public static class Combination {
        int[] squares;
        
        public Combination(int[] s) {
            squares = s;
        }
        
        public String toString() {
            return Arrays.toString(squares);
        }
        
        public boolean equals(Object o) {
            if (o instanceof Combination) {
                return Arrays.equals(squares, ((Combination) o).squares);
            }
            return false;
        }
    }*/

}
