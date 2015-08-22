import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: namenum
 PROG: namenum
 */

public class namenum {
	static int writtenTimes = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("namenum.out")));
		BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		String input = r.readLine();
		//long number = Long.parseLong(input);
		int length = input.length();
		
		int[] digits = new int[length];
		for (int i = 0; i < length; i++) {
			digits[i] = input.charAt(i) - '0';
		}

		String name = "";
		// Set up dictionary
		HashSet<String> names = new HashSet<String>();
		for (int j = 0; j < 5000; j++) {
			names.add(dict.readLine());
		}
		
		// Set up name to letter
		//HashMap<String, char[]> nameToLetter = new HashMap<String, char[]>();
		//nameToLetter.put("2", new char[] {'A', 'B', 'C'});
		//char[] letters = nameToLetter.get(name);
		
		makeString(name, length, 0, digits, w, names);
		
		if (writtenTimes == 0) {
			w.println("NONE");
		}
		w.flush();
		r.close();
		dict.close();
		//emptyCheck.close();
		w.close();
		System.exit(0);
	}

	public static void makeString(String name, int times, int index, int[] digits, PrintWriter w, HashSet names) {
		if (times != 0) {
			for (int i = 0; i < 3; i++) {
				switch (digits[index]) {
				case 2:
					switch (i) {
					case 0:
						name += "A";
						break;
					case 1:
						name += "B";
						break;
					case 2:
						name += "C";
						break;
					}
					break;
				case 3:
					switch (i) {
					case 0:
						name += "D";
						break;
					case 1:
						name += "E";
						break;
					case 2:
						name += "F";
						break;
					}
					break;
				case 4:
					switch (i) {
					case 0:
						name += "G";
						break;
					case 1:
						name += "H";
						break;
					case 2:
						name += "I";
						break;
					}
					break;
				case 5:
					switch (i) {
					case 0:
						name += "J";
						break;
					case 1:
						name += "K";
						break;
					case 2:
						name += "L";
						break;
					}
					break;
				case 6:
					switch (i) {
					case 0:
						name += "M";
						break;
					case 1:
						name += "N";
						break;
					case 2:
						name += "O";
						break;
					}
					break;
				case 7:
					switch (i) {
					case 0:
						name += "P";
						break;
					case 1:
						name += "R";
						break;
					case 2:
						name += "S";
						break;
					}
					break;
				case 8:
					switch (i) {
					case 0:
						name += "T";
						break;
					case 1:
						name += "U";
						break;
					case 2:
						name += "V";
						break;
					}
					break;
				case 9:
					switch (i) {
					case 0:
						name += "W";
						break;
					case 1:
						name += "X";
						break;
					case 2:
						name += "Y";
						break;
					}
					break;
				}
				if (times == 1 && names.contains(name))  { 
					w.println(name); 
					writtenTimes++; 
				}
				makeString(name, times - 1, index + 1, digits, w, names);
				name = name.substring(0, name.length() - 1);
			}
		}
	}

}
