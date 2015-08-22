import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: farmersrice
LANG: JAVA
TASK: preface
*/

public class preface {

	static int is = 0;
	static int vs = 0;
	static int xs = 0;
	static int ls = 0;
	static int cs = 0;
	static int ds = 0;
	static int ms = 0;
	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("preface.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);
		
		int pages = Integer.parseInt(r.readLine());
		for (int i = 1; i <= pages; i++) {
			char[] roman = recursive(i);
			for (int j = 0; j < roman.length; j++) {
				if (roman[j] == 'I') is++;
				if (roman[j] == 'V') vs++;
				if (roman[j] == 'X') xs++;
				if (roman[j] == 'L') ls++;
				if (roman[j] == 'C') cs++;
				if (roman[j] == 'D') ds++;
				if (roman[j] == 'M') ms++;
			}
			//String temp = new String(roman);
			//System.out.println(temp + " " + i);
		}
		
		if (is != 0) {
			w.println("I " + is);
		}
		if (vs != 0) {
			w.println("V " + vs);
		}
		if (xs != 0) {
			w.println("X " + xs);
		}
		if (ls != 0) {
			w.println("L " + ls);
		}
		if (cs != 0) {
			w.println("C " + cs);
		}
		if (ds != 0) {
			w.println("D " + ds);
		}
		if (ms != 0) {
			w.println("M " + ms);
		}
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public static char[] recursive(int page) {
		
		ArrayList<Character> answer = new ArrayList<Character>();
		String pageString = Integer.toString(page);
		char[] pageChars = pageString.toCharArray();
		int[] digits = new int[pageChars.length];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = pageChars[i] - '0';
			char[] chars = null;
			switch((int) (digits[i] * Math.pow(10, digits.length - i - 1))) { //create all cases that the computer cannot compute. Anything between these cases can be computed by the computer through recursion.
			case 0:
				chars = null;
				break;
			case 1:
				chars = new char[] {'I'};
				break;
			case 2:
				chars = new char[] {'I', 'I'};
				break;
			case 3:
				chars = new char[] {'I', 'I', 'I'};
				break;
			case 4:
				chars = new char[] {'I', 'V'};
				break;
			case 5:
				chars = new char[] {'V'};
				break;
			case 6:
				chars = new char[] {'V', 'I'};
				break;
			case 7:
				chars = new char[] {'V', 'I', 'I'};
				break;
			case 8:
				chars = new char[] {'V', 'I', 'I', 'I'};
				break;
			case 9:
				chars = new char[] {'I', 'X'};
				break;
			case 10:
				chars = new char[] {'X'};
				break;
			case 20:
				chars = new char[] {'X', 'X'};
				break;
			case 30:
				chars = new char[] {'X', 'X', 'X'};
				break;
			case 40:
				chars = new char[] {'X', 'L'};
				break;
			case 50:
				chars = new char[] {'L'};
				break;
			case 60:
				chars = new char[] {'L', 'X'};
				break;
			case 70:
				chars = new char[] {'L', 'X', 'X'};
				break;
			case 80:
				chars = new char[] {'L', 'X', 'X', 'X'};
				break;
			case 90:
				chars = new char[] {'X', 'C'};
				break;
			case 100:
				chars = new char[] {'C'};
				break;
			case 200:
				chars = new char[] {'C', 'C'};
				break;
			case 300:
				chars = new char[] {'C', 'C', 'C'};
				break;
			case 400:
				chars = new char[] {'C', 'D'};
				break;
			case 500:
				chars = new char[] {'D'};
				break;
			case 600:
				chars = new char[] {'D', 'C'};
				break;
			case 700:
				chars = new char[] {'D', 'C', 'C'};
				break;
			case 800:
				chars = new char[] {'D', 'C', 'C', 'C'};
				break;
			case 900:
				chars = new char[] {'C', 'M'};
				break;
			case 1000:
				chars = new char[] {'M'};
				break;
			case 2000:
				chars = new char[] {'M', 'M'};
				break;
			case 3000:
				chars = new char[] {'M', 'M', 'M'};
				break;
			}
			if (chars != null) {
				for (int j = 0; j < chars.length; j++) {
					answer.add(chars[j]);
				}
			}
			
		}
		char[] returnValue = new char[answer.size()];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i] = answer.get(i);
		}
		return returnValue;
	}
	

}
