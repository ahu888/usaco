import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/*
ID: farmersrice
LANG: JAVA
TASK: frac1
*/

public class frac1 {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		int num = Integer.parseInt(r.readLine());
		frac1 f = new frac1();
		
		ArrayList<Fraction> fracs = new ArrayList<Fraction>();
		for (int i = 1; i <= num; i++) { //cycle thru denom
			for (int j = 0; j <= i; j++) { //cycle thru num
				Fraction temp = f.new Fraction(j, i);
				if (fracs.contains(temp) == false)
				fracs.add(f.new Fraction (j, i));
			}
		}
		
		Object[] answers = fracs.toArray();
		Arrays.sort(answers);
		for (int i = 0; i < answers.length; i++) {
			w.println(answers[i]);
		}
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public class Fraction implements Comparable<Fraction> {
		double num;
		double denom;
		Double approx;
		
		public int compareTo(Fraction f) {
			return (int) ((approx - f.approx)*10000000);
		}
		public Fraction(int n, int d) {
			num = n;
			denom = d;
			approx = (double) (num/denom);
		}
		
		public boolean equals(Object o) {
			Fraction f = null;
			try {
				f = (Fraction) o;
			} catch (Exception e) {}
			if (approx.equals(f.approx)) return true;
			else return false;
		}
		
		public String toString() {
			int n = (int) num;
			int d = (int) denom;
			return n + "/" + d;
		}
	}

}
