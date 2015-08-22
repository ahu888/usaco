import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: crypt1
 PROG: crypt1
 */

public class crypt1 {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("crypt1.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		int digitCount = Integer.parseInt(r.readLine());
		String digitString = r.readLine();
		String[] digitStrings = digitString.split(" ");
		char[] digits = new char[digitCount];

		for (int i = 0; i < digitCount; i++) {
			digits[i] = digitStrings[i].charAt(0);
		}

		int solutions = 0;
		StringBuilder num1 = new StringBuilder();
		StringBuilder num2 = new StringBuilder();
		String a;
		String b;
		for (int i = 0; i < digits.length; i++) {
			if (digits[i] != 0)
				num1.append(digits[i]);
			for (int j = 0; j < digits.length; j++) {
				num1.append(digits[j]);
				for (int k = 0; k < digits.length; k++) {
					num1.append(digits[k]);
					for (int l = 0; l < digits.length; l++) {
						if (digits[l] != 0)
							num2.append(digits[l]);

						for (int m = 0; m < digits.length; m++) {
							num2.append(digits[m]);
							outerloop: for (int test = 0; test < 1; test++) {
								a = num1.toString();
								b = num2.toString();
								
								

								int first = Integer.parseInt(a);
								int second = Integer.parseInt(b);
								int product = first * second;
								
								

								String secondc = Integer.toString(second);
								String p = Integer.toString(product);

								char[] secondChars = secondc.toCharArray();
								char[] productChars = p.toCharArray();

								int units = secondChars[1] - '0';
								int tens = secondChars[0] - '0';

								int firstProduct = first * units;
								int secondProduct = first * tens;

								String firstp = Integer.toString(firstProduct);
								String secondp = Integer.toString(secondProduct);
								char[] firstP = firstp.toCharArray();
								char[] secondP = secondp.toCharArray();

								if (firstP.length != 3 || secondP.length != 3) {
									num2.deleteCharAt(1);
									break;
								}
								for (int num = 0; num < 3; num++) {
									int counter1 = 0;
									int counter2 = 0;
									for (int numb = 0; numb < digits.length; numb++) {
										if (firstP[num] == digits[numb]) {
											counter1++;
										}
										if (secondP[num] == digits[numb]) {
											counter2++;
										}
									}
									if (counter1 == 0 || counter2 == 0) {
										num2.deleteCharAt(1);
										break outerloop;
									}
								}
								for (int num = 0; num < 4; num++) {
									int counter = 0;
									for (int numb = 0; numb < digits.length; numb++) {
										if (productChars[num] == digits[numb]) {
											counter++;
										}
									}
									if (counter == 0) {
										num2.deleteCharAt(1);
										break outerloop;
									}
								}
								solutions++;
								num2.deleteCharAt(1);
							}
							
						}
						num2.deleteCharAt(0);
					}
					num1.deleteCharAt(2);
				}
				num1.deleteCharAt(1);
			}
			num1.deleteCharAt(0);
		}

		w.println(solutions);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}

}
