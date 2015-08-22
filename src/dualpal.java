import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: dualpal
 PROG: dualpal
 */
public class dualpal {

	public int n = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("dualpal.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		dualpal dp = new dualpal();
		String input = r.readLine();
		String[] inputs = input.split(" ");
		dp.n = Integer.parseInt(inputs[0]);
		int s = Integer.parseInt(inputs[1]);

		int i = 0;
		int j = s + 1;
		while (i < dp.n) {
			int counter = 0;
			outerloop:
			for (int k = 2; k <= 10; k++) {
				String numberS = toBase(j, k);

				int length = numberS.length();

				char[] digits = numberS.toCharArray();

				char[] reverseDigits = new char[length];
				for (int l = 0; l < length; l++) {
					reverseDigits[l] = digits[length - 1 - l];
				}
				if (Arrays.equals(reverseDigits, digits)) {
					
					
					counter++;
					if (counter > 1) {
						w.println(j);
						i++;
						break outerloop;
					}
				}
			}
			j++;
			
		}

		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}

	public static String toBase(long number, int convertBase) {
		return Long.toString(number, convertBase);
	}

}
