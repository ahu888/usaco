import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

/*
ID: farmersrice
LANG: JAVA
TASK: lamps
*/

public class lamps {

	public static int n;
	static int[] on;
	static int[] off;
	static HashSet<String> answers = new HashSet<String>();
	//static boolean printed = false;
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		n = Integer.parseInt(r.readLine());
		int c = Integer.parseInt(r.readLine());
		
		String onString = r.readLine();
		String[] onStrings = onString.split(" ");
		on = new int[onStrings.length - 1];
		for (int i = 0; i < on.length; i++) {
			if (onStrings[i].equals("-1") == false) {
				on[i] = Integer.parseInt(onStrings[i]);
			} else break;
		}
		
		String offString = r.readLine();
		String[] offStrings = offString.split(" ");
		off = new int[offStrings.length - 1];
		for (int i = 0; i < off.length; i++) {
			if (offStrings[i].equals("-1") == false) {
				off[i] = Integer.parseInt(offStrings[i]);
			} else break;
		}
		
		generateCombos(c - 1, 1, 0, w);
		
		
		String[] ans = answers.toArray(new String[answers.size()]);
		Arrays.sort(ans);
		for (int i = 0; i < ans.length; i++) {
			w.println(ans[i]);
		}
		if (ans.length == 0) w.println("IMPOSSIBLE");
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public static void generateCombos(int c, int index, int number, PrintWriter w) {
		if (c < 0) {
			int[] lamps = new int[n];
			Arrays.fill(lamps, 1);
			int ons = 0;
			for (int j = 0; j < on.length; j++) {
				if (lamps[on[j] - 1] == 1) ons++;
			}
			int offs = 0;
			for (int j = 0; j < off.length; j++) {
				if (lamps[off[j] - 1] == 0) offs++;
			}
			if (ons == on.length && offs == off.length) {
				/*for (int j = 0; j < lamps.length; j++) {
					if (j != lamps.length - 1) w.print(lamps[j]);
					else w.println(lamps[j]);
				}*/
				//printed = true;
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < lamps.length; j++) {
					sb.append(lamps[j]);
				}
				answers.add(sb.toString());
			}
			return;
		}
		//if (possibleCombos == null) possibleCombos = new int[16];
		if (c == 0) {
			for (int i = index; i <= 4; i++) {
				number *= 10;
				number += i;
				char[] buttons = Integer.toString(number).toCharArray();
				int[] lamps = new int[n];
				Arrays.fill(lamps, 1);
				for (int j = 0; j < buttons.length; j++) {
					pressButton(buttons[j] - '0', lamps);
				}
				//check if the combination fits the requirements
				int ons = 0;
				for (int j = 0; j < on.length; j++) {
					if (lamps[on[j] - 1] == 1) ons++;
				}
				int offs = 0;
				for (int j = 0; j < off.length; j++) {
					if (lamps[off[j] - 1] == 0) offs++;
				}
				if (ons == on.length && offs == off.length) {
					//printed = true;
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < lamps.length; j++) {
						sb.append(lamps[j]);
					}
					answers.add(sb.toString());
				}
				
				for (int j = 0; j < buttons.length; j++) { //pressing twice is the same as unpressing
					pressButton(buttons[j] - '0', lamps);
				}
				number -= i;
				number /= 10;
			}
			return;
		}
		if (c > 4) c = 4;
		for (int i = index; i <= 4; i++) {
			number *= 10;
			number += i;
			generateCombos(c - 1, i, number, w);
			number -= i;
			number /= 10;
		}
	}
	
	//Note that pressing a button twice will revert to the state prior to pressing the button.
	public static void pressButton(int button, int[] lamps) {
		switch(button) {
		case 1:
			for (int i = 0; i < lamps.length; i++) {
				if (lamps[i] == 0) lamps[i] = 1;
				else if (lamps[i] == 1) lamps[i] = 0;
			}
			break;
		case 2:
			for (int i = 0; i < lamps.length; i += 2) {
				if (lamps[i] == 0) lamps[i] = 1;
				else if (lamps[i] == 1) lamps[i] = 0;
			}
			break;
		case 3:
			for (int i = 1; i < lamps.length; i += 2) {
				if (lamps[i] == 0) lamps[i] = 1;
				else if (lamps[i] == 1) lamps[i] = 0;
			}
			break;
		case 4:
			for (int i = 0; i < lamps.length; i += 3) {
				if (lamps[i] == 0) lamps[i] = 1;
				else if (lamps[i] == 1) lamps[i] = 0;
			}
			break;
		}
	}
	/*
	public static boolean contains(ArrayList<String> container, int[] array) {
		for (int i = 0; i < container.size(); i++) {
			if (Arrays.equals(array, container.get(i))) {
				return true;
			}
		}
		return false;
	}*/

}
