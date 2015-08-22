import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


/*
ID: farmersrice
LANG: JAVA
TASK: money
*/

public class money {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("money.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		
		//v is the number of coins and n is the value you are trying to reach
		int v = Integer.parseInt(inputs[0]);
		int n = Integer.parseInt(inputs[1]);
		
		int[] coins = new int[v];
		
		//read in the inputs, which are on an indefinite number of lines
		String line = r.readLine();
		int index = 0;
		while (line != null) {
			String[] coinList = line.split(" ");
			for (int i = 0; i < coinList.length; i++) {
				coins[index++] = Integer.parseInt(coinList[i]);
			}
			line = r.readLine();
		}
		Arrays.sort(coins);

		long[] ways = new long[n + 1]; //number of ways to sum to the index, 0 is not used
		ways[0] = 1; //there is one way to sum to 0, the empty set
		
		
		for (int i = 0; i < coins.length; i++) { //iterate through all possible coins
			for (int j = coins[i]; j <= n; j++) { //add the number of ways of all possible previous sums
				ways[j] += ways[j - coins[i]];
			}
		}
		
		/* ways for 0
		 * ways for 1 is 1
		 * ways for 2 is 1 1 or 2
		 * ways for 3 is 1 1 1, 2 1
		 * ways for 4 is 1 1 1 1, 2 2, 2 1 1
		 * ways for 5 is 1 1 1 1 1, 2 2 1, 2 1 1 1, 5
		 */
		w.println(ways[n]);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}

}
