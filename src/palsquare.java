import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: palsquare
 PROG: palsquare
 */
public class palsquare {

	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("palsquare.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new
		//FileWriter("palsquare.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);

		int base = Integer.parseInt(r.readLine());
		
		int diff = 'A'-'a';
		
		for (int i = 1; i <= 300; i++) {
			long number = i;
			long numberSquare = number*number;
			String numberS = toBase(number, base);
			numberS = numberS.toUpperCase();
			
			String numberSquareS = toBase(numberSquare, base);
			numberSquareS = numberSquareS.toUpperCase();
			
			int length = numberSquareS.length();
			
			/*char[] numberD = numberS.toCharArray();

			for (int j = 0; j < numberD.length; j++) {
				if (numberD[j] >= 'a' && numberD[j] <= 'j') {
					numberD[j] += diff;
				}
				/*if (numberD[j] == 'a') numberD[j] = 'A';
				if (numberD[j] == 'b') numberD[j] = 'B';
				if (numberD[j] == 'c') numberD[j] = 'C';
				if (numberD[j] == 'd') numberD[j] = 'D';
				if (numberD[j] == 'e') numberD[j] = 'E';
				if (numberD[j] == 'f') numberD[j] = 'F';
				if (numberD[j] == 'g') numberD[j] = 'G';
				if (numberD[j] == 'h') numberD[j] = 'H';
				if (numberD[j] == 'i') numberD[j] = 'I';
				if (numberD[j] == 'j') numberD[j] = 'J';
			}
			numberS = new String(numberD);
			
			char[] digits = numberSquareS.toCharArray(); 
			for (int j = 0; j < length; j++) {
				if (digits[j] == 'a') digits[j] = 'A';
				if (digits[j] == 'b') digits[j] = 'B';
				if (digits[j] == 'c') digits[j] = 'C';
				if (digits[j] == 'd') digits[j] = 'D';
				if (digits[j] == 'e') digits[j] = 'E';
				if (digits[j] == 'f') digits[j] = 'F';
				if (digits[j] == 'g') digits[j] = 'G';
				if (digits[j] == 'h') digits[j] = 'H';
				if (digits[j] == 'i') digits[j] = 'I';
				if (digits[j] == 'j') digits[j] = 'J';
			}
			numberSquareS = new String(digits);
			*/
			char[] digits = numberSquareS.toCharArray();
			char[] reverseDigits = new char[length];
			for (int j = 0; j < length; j++) {
				reverseDigits[j] = digits[length - 1 - j];
			}
			if (Arrays.equals(reverseDigits, digits)) {
				w.print(numberS + " ");
				w.println(numberSquareS);
			}
			
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
