import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
/*
ID: farmersrice
LANG: JAVA
TASK: runround
*/

public class runround {

	static ArrayList<Integer> possible = new ArrayList<Integer>();
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("runround.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		int m = Integer.parseInt(r.readLine());
		for (int i = Integer.toString(m).toCharArray().length; i < Integer.toString(m).toCharArray().length + 2; i++) {
			recursiveGenerate(m, new boolean[10], i - 1, 0, w, r);
		}
		/*
		Integer[] possibleArray = possible.toArray(new Integer[possible.size()]);
		outer:
		for (int i = 0; i < possibleArray.length; i++) {
			if (checkRunaround(Integer.toString(possibleArray[i]).toCharArray())) {
				w.println(possibleArray[i]);
				break outer;
			}
		}*/
		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}
	public static void recursiveGenerate(int m, boolean[] used, int iteration, int number, PrintWriter w, BufferedReader r) throws Exception {
		if (iteration == 0) {
			for (int i = 0; i < 10; i++) {
				if (used[i] == false) {
					used[i] = true;
					number *= 10;
					number += i;
					if (number > m) {
						//possible.add(number);
						if (checkRunaround(Integer.toString(number).toCharArray())) {
							w.println(number);
							w.flush();
							r.close();
							w.close();
							System.exit(0);
						}
					}
					
					used[i] = false;
					number -= i;
					number /= 10;
				}
			}
			return;
		}
		if (iteration == Integer.toString(m).toCharArray().length) {
			for (int i = 1; i < 10; i++) {
				if (used[i] == false) {
					used[i] = true;
					number *= 10;
					number += i;
					if (number > m) {
						possible.add(number);
					}
					recursiveGenerate(m, used, iteration - 1, number, w, r);
					used[i] = false;
					number -= i;
					number /= 10;
				}
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (used[i] == false) {
				used[i] = true;
				number *= 10;
				number += i;
				recursiveGenerate(m, used, iteration - 1, number, w, r);
				used[i] = false;
				number -= i;
				number /= 10;
			}
		}
	}
	public static boolean checkRunaround(char[] digitChars) {
		if (digitChars == new char[] {'0'}) {
			return false;
		}
		int[] digits = new int[digitChars.length];
		for (int i = 0; i < digitChars.length; i++) {
			digits[i] = digitChars[i] - '0';
		}
		int index = 0;
		boolean[] visited = new boolean[digits.length];
		
		while (visited[index] == false) {
			visited[index] = true;
			index += digits[index];
			if (index > digits.length - 1) {
				while (index > digits.length - 1) 
				index = index - digits.length;
			}
		}
		if (index != 0) return false;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				return false;
			}
		}
		return true;
		
	}

}
