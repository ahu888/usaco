import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
/*
ID: farmersrice
LANG: JAVA
TASK: ariprog
*/

public class ariprog {

	static ArrayList<Pair<?>> pairs = new ArrayList<Pair<?>>();
	static int counter = 0;
	
	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("ariprog.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);
		
		ariprog ap = new ariprog();
		int n = Integer.parseInt(r.readLine());
		int m = Integer.parseInt(r.readLine());
		
		
		int highest = 2*m*m;
		boolean[] values = new boolean[highest*2];
		
		//long startTime = System.currentTimeMillis();
		for (int i = 0; i <= m; i++) { //generate all bisquares
			for (int j = i; j <= m; j++) {
				values[i*i + j*j] = true;
			}
		}

		
		long afterBiSquaresTime = System.currentTimeMillis();
		//System.out.println("time to geenrate bisquares" + (afterBiSquaresTime-startTime));

		for (int i = 0; i <= highest - n + 1; i++) { //i is starting number
			if (values[i]) {
				for (int j = highest/(n-1); j>0; j--) {
					int k;
					for (k = n - 1; k >= 0; k--) {
						if (values[i + j * k] == false) break;
					}
					if (k == -1) {
						pairs.add(ap.new Pair<>(i, j));
					}
				}
			}
			
		}
		
		long afterRecursiveTime = System.currentTimeMillis();
		System.out.println("time to recursive" + (afterRecursiveTime-afterBiSquaresTime));
		
		Object[] pairA = pairs.toArray();
		Pair<?>[] pairArray = new Pair[pairA.length];
		for (int i = 0; i < pairArray.length; i++) {
			pairArray[i] = (Pair<?>) pairA[i];
		}
		Arrays.sort(pairArray);
		for (int i = 0; i < pairArray.length; i++) {
			w.println(pairArray[i].toString()); 
		}
		//long afterSort = System.currentTimeMillis();
		//System.out.println("time to sort" + (afterSort - afterRecursiveTime));
		
		if (pairArray.length == 0) {
			w.println("NONE");
		}
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		
		

	}
	
	public class Pair<T extends Comparable<T>> implements Comparable<Pair<?>>{
		int start;
		int difference;
		
		public Pair(int s, int d) {
			start = s;
			difference = d;
		}
		
		public int compareTo(Pair<?> pair) {
			if (pair.difference < difference) return 1;
			if (pair.difference > difference) return -1;
			if (pair.difference == difference) return 0;
			return 0;
		}
		
		public String toString() {
			return start + " " + difference;
		}
		
		public boolean equals(Object o) {
			Pair<?> pair = (Pair<?>) o;
			if (pair.difference == difference && pair.start == start) return true;
			return false;
		}
	}

}
