import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: sprime
*/

public class sprime {
	static int n;
	
	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("sprime.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);
		
		
		n = Integer.parseInt(r.readLine());
		long start = System.currentTimeMillis();
		
		search(2, 1, w);
		search(3, 1, w);
		search(5, 1, w);
		search(7, 1, w); //first 4 1 digit primes
		w.flush();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		r.close();
		w.close();
		System.exit(0);
	}

	public static void search(int num, int depth, PrintWriter w) {
		if (depth == n) {     // recursive ending condition
			if (isPrime(num)) {
				w.println(num);
			}
			return;
		} else if (isPrime(num)) {
			for (int i = 1; i < 10; i += 2) { //we generate from the shortest up and check for primality each time, otherwise we discard
				if (i != 5) {
					search(num*10 + i, depth + 1, w);
				}
			}
		}
		
		
	}
	
	private static boolean isPrime(int num) {
		if (num == 2) {
			return true;
		}
		if (num % 2 == 0) {
			return false;
		}
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) return false;
		}
		return true;
	}
	/*
	private static boolean recursive(int num, int n) {
		if (n == 1) {
			return true;
		}
		String number = Integer.toString(num);
		String numt = number.substring(0, n - 1);
		int truncated = Integer.parseInt(numt);
		if (saves.containsKey(truncated)) {
			return saves.get(truncated);
		}
		if (isPrime(truncated)) {
			boolean ans = recursive(truncated, n - 1);
			saves.put(truncated, ans);
			return ans;
		} else return false;
		
		
	}
	*/

}
