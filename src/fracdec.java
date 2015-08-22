import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;


/*
ID: farmersrice
LANG: JAVA
TASK: fracdec
*/

/*
 * The solution is long division. Keep track of ratios. Once you encounter a ratio you have seen before, you know the repeating part.
 */
public class fracdec {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		String input = r.readLine();
		String[] inputs = input.split(" ");
		
		int num = Integer.parseInt(inputs[0]);
		int denom = Integer.parseInt(inputs[1]);
		
		//String number = "";
		long st = System.currentTimeMillis();
		StringBuilder number = new StringBuilder();
		
		//int intPart = num/denom; //get the part to the left of the decimal point
		//num = (num % denom) * 10;
		//number += Integer.toString(intPart) + "."; //add it to the number
		
		HashMap<String,Fraction> seen = new HashMap<String, Fraction>();
		
		char[] digits = Integer.toString(num).toCharArray();
		
		int dividend = digits[0] - '0';
		int divisor = denom;
		
		int index = 1;
		
		boolean decimal = false;
		
		int fractionNum = 0;
		
		Fraction next = new Fraction(dividend, divisor, dividend%divisor * 10, 0);
		while (seen.containsKey(next.toString()) == false) { //while we have not seen the ratio before and there is no digit ahead of it
			//seen.add(new Fraction(dividend, divisor));
			number.append(dividend/divisor);
			
			if (index < digits.length) {
				if (decimal == true) {
					Fraction current = new Fraction(dividend, divisor, (dividend % divisor) * 10 + digits[index] - '0', fractionNum);
					seen.put(current.toString(), current);
					fractionNum++;
				}
				
				dividend = (dividend % divisor) * 10 + digits[index++] - '0'; //carry down the next digit of the numerator
				
			} else {
				if (decimal == true) {
					Fraction current = new Fraction(dividend, divisor, dividend % divisor * 10, fractionNum);
					seen.put(current.toString(), current);
					fractionNum++;
				} else { //otherwise no more digits
					number.append(".");
					decimal = true;
					//seen.add(new Fraction());
				}
				
				dividend = (dividend % divisor) * 10; //so we carry down a 0
			}
			
			next = new Fraction(dividend, divisor, dividend%divisor * 10, 0);
				
		} // end of while loop
		
		
		
		long mid = System.currentTimeMillis();
		System.out.println(mid - st);
		if (dividend/divisor != 0 || dividend%divisor != 0) { //if the fraction is repeating and not just something like 1.(0)
			int start = 0;
			boolean seenDecimal = false;
			int decimalPlace = 0;
			char[] chars = number.toString().toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == '.')
					decimalPlace = i;
			}
			Fraction[] fractions = seen.values().toArray(new Fraction[seen.size()]);
			for (int i = 0; i < seen.size(); i++) {
				/*
				if (seen.get(i).nullFraction) {
					seenDecimal = true;
					decimalPlace = i;
				}*/
				if (fractions[i].equals(new Fraction(dividend, divisor, dividend%divisor * 10, 0))) { //make sure the number is not to the left of the decimal point!
					
					//if (seenDecimal == true) {
						//start = i + decimalPlace + 1;
					start = fractions[i].number + decimalPlace + 1;
					break;
					//}
					
				}
			}
			
			String repeatingPart = "(" + number.substring(start) + ")";
			//number = number.substring(0, start);
			//String numb = number.substring(0, start);
			//number = new StringBuilder(numb);
			number.delete(start, number.length());
			number.append(repeatingPart);
			
			
			//remove leading and trailing zeroes
			//number = number.substring(Integer.toString(num).length());
			//number = "0" + number;
			
			
			
		}
		char[] chars = number.toString().toCharArray();
		//for (int i = 0; i < number.length(); i++) {
			while (chars[0] == '0' && chars[1] != '.') {
				//number = new StringBuilder(number.substring(1));
				number.deleteCharAt(0);
				chars = number.toString().toCharArray();
				//i = 0;
			} //else break;
		//}
		//chars = number.toCharArray();
		for (int i = number.length() - 1; i >= 0; i--) {
			if (chars[i] == '0') {
				//number = number.substring(0, i);
				number.deleteCharAt(i);
			} else break;
		}
		
		chars = number.toString().toCharArray();
		if (chars[0] == '.')
			number = new StringBuilder("0" + number.toString());
		if (chars[chars.length - 1] == '.')
			number.append(0);
		
		chars = number.toString().toCharArray();
		//76 chars per line
		
		outer:
		for (int i = 0; i < chars.length; i += 76) {
			int previous = i + 76;
			char[] data = new char[76];
			
			int counter = 0;
			for (int j = i; j < previous; j++) {
				try {
					data[counter] = chars[j];
					counter++;
				} catch (ArrayIndexOutOfBoundsException e) {
					char[] temp = new char[counter];
					for (int k = 0; k < counter; k++) {
						temp[k] = data[k];
					}
					w.println(String.valueOf(temp));
 					break outer;
				}
				
			}
			w.println(String.valueOf(data));
		}
		
		System.out.println(System.currentTimeMillis() - mid);
		//w.println(number);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}

	public static class Fraction {
		int num, denom, result;
		boolean nullFraction = false;
		int number = 0;
		
		public Fraction(int n, int d, int r, int number) {
			int gcd = gcd(n, d);
			num = n/gcd;
			denom = d/gcd;
			result = r;
			this.number = number;
		}
		
		public Fraction() {
			nullFraction = true;
		}
		
		public boolean equals(Object o) {
			//System.out.println("equals checking");
			//System.out.println(o.toString() + "         " + toString());
			if (nullFraction == true) return false;
			if (o.toString().equals(toString())) return true;
			return false;
			/*
			Fraction temp = null;
			
			try {
				temp = (Fraction) o;
			} catch (ClassCastException e) {
				return false;
			}
			
			if (temp.num == num && temp.denom == denom) return true;
			return false;*/
			
		}
		
		public String toString() {
			return num + "/" + denom + " " + result;
		}
	}
	
	public static int gcd(int p, int q) {
		if (q == 0) return p;
		return gcd(q, p%q);
	}
}
