

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: farmersrice
LANG: JAVA
TASK: hamming
*/

public class hamming {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		
		n = Integer.parseInt(inputs[0]);
		b = Integer.parseInt(inputs[1]);
		d = Integer.parseInt(inputs[2]);

		solutions = new Integer[n];
		calculate(n, 0, new ArrayList<Integer>());
		
		for (int i = 1; i < n + 1; i++) {
			if (i % 10 == 0) {
				w.println(solutions[i - 1]);
			} else if (i != n) {
				w.print(solutions[i - 1] + " ");
			} else {
				w.println(solutions[i - 1]);
			}
		}
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	static int n;
	static int b;
	static int d;
	static boolean solution = false;
	static Integer[] solutions;
	
	public static void calculate(int repeated, int index, ArrayList<Integer> values) {
		if (repeated == 0) {
			if (checkHamming(values, d)) {
				solution = true;
				solutions = values.toArray(new Integer[values.size()]);
			}
		}
		for (int i = index; i < Math.pow(2, b); i++) {
			if (solution == false) {
				values.add(i);
				//check for hamming distance, if it is valid then we continue recursion
				if (checkHamming(values, d)) {
					calculate(repeated - 1, i + 1, values);
				}
				values.remove((Integer) i);
			}
			
			
		}
	}
	
	public static boolean checkHamming(ArrayList<Integer> values, int minDistance) {
		if (values.size() == 1) return true;
		
		int valid = 0;
		int combinations = 0;
		for (int i = 0; i < values.size(); i++) {
			for (int j = i + 1; j < values.size(); j++) {
				int least = values.get(i);
				int greatest = values.get(j); //we know array is sorted because of its generation
				
				int xor = least ^ greatest;
				String diff = Integer.toBinaryString(xor);
				char[] digits = diff.toCharArray();
				
				int counter = 0;
				for (int k = 0; k < digits.length; k++) {
					if (digits[k] == '1') counter++;
				}
				if (counter >= minDistance) valid++;
				combinations++;
			}
		}
		if (valid == combinations) return true;
		else return false;
		
	}

}
