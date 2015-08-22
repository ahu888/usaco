import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: farmersrice
LANG: JAVA
TASK: pprime
*/

public class pprime {

	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("pprime.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);

		//ArrayList<Integer> answers = new ArrayList<Integer>();
		String input = r.readLine();
		String[] inputs = input.split(" ");
		int a = Integer.parseInt(inputs[0]);
		int b = Integer.parseInt(inputs[1]);
		int adigits = inputs[0].length();
		int bdigits = inputs[1].length();
		ArrayList<Integer> palindromes = new ArrayList<Integer>();
		
		long start = System.currentTimeMillis();
		
		for (int i = adigits; i <= bdigits; i++) {
			switch(i) {
			case 1:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 1 digit
					palindromes.add(d1);
				}
				break;
			case 2:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 2 digits
					palindromes.add(10 * d1 + d1);
				}
				break;
			case 3:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 3 digits
					for (int d2 = 0; d2 < 10; d2++) {
						palindromes.add(100 * d1 + 10 * d2 + d1);
					}
				}
				break;
			case 4:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 4 digits
					for (int d2 = 0; d2 < 10; d2++) {
						palindromes.add(1000 * d1 + 100 * d2 + 10 * d2 + d1);
					}
				}
				break;
			case 5:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 5 digits
					for (int d2 = 0; d2 < 10; d2++) {
						for (int d3 = 0; d3 < 10; d3++) {
							palindromes.add(10000 * d1 + 1000 * d2 + 100 * d3 + 10 * d2 + d1);
						}
					}
				}
				break;
			case 6:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 6 digits
					for (int d2 = 0; d2 < 10; d2++) {
						for (int d3 = 0; d3 < 10; d3++) {
							palindromes.add(100000 * d1 + 10000 * d2 + 1000 * d3 + 100 * d3 + 10 * d2 + d1);
						}
					}
				}
				break;
			case 7:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 7 digits
					for (int d2 = 0; d2 < 10; d2++) {
						for (int d3 = 0; d3 < 10; d3++) {
							for (int d4 = 0; d4 < 10; d4++) {
								palindromes.add(1000000 * d1 + 100000 * d2 + 10000 * d3 + 1000 * d4 + 100 * d3 + 10 * d2 + d1);
							}
						}
					}
				}
				break;
			case 8:
				for (int d1 = 1; d1 < 10; d1 += 2) { // 8 digits, ignore the 9 digit 100,000,000 because it is not a palindrome
					for (int d2 = 0; d2 < 10; d2++) {
						for (int d3 = 0; d3 < 10; d3++) {
							for (int d4 = 0; d4 < 10; d4++) {
								palindromes.add(10000000 * d1 + 1000000 * d2 + 100000 * d3 + 10000 * d4 + 1000 * d4 + 100 * d3 + 10 * d2 + d1);
							}
						}
					}
				}
				break;
			}
		}
		
		for (int i = 0; i < palindromes.size(); i++) {
			int num = palindromes.get(i);
		
			if ((num >= a && num <= b) && isPrime(num)) {
				w.println(num);
				//answers.add(num);
			}
				
		}

		long end = System.currentTimeMillis();
		
		w.flush();
		System.out.println("Total time spent: " + (end - start));
		r.close();
		w.close();
		System.exit(0);
	}
	
	private static boolean isPrime(int num) {
		if (num % 2 == 0) return false;
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) return false;
		}
		return true;
	}

	public static boolean isPalindrome(int num) {
		String numString = Integer.toString(num);
		int i;
		int length = numString.length();
		for (i = 0; i < length/2; i++) {
			if (numString.charAt(i) != numString.charAt(length - 1 - i)) break;
		}
		if (i == length/2) return true;
		else return false;
	}

}
