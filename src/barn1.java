import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class barn1 {


/*
 ID: farmersrice
 LANG: JAVA
 TASK: barn1
 PROG: barn1
 */
	
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("barn1.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		int m = Integer.parseInt(inputs[0]);
		int s = Integer.parseInt(inputs[1]);
		int c = Integer.parseInt(inputs[2]);

		boolean[] stalls = new boolean[s]; //true if filled false if empty
		for (int i = 0; i < c; i++) {
			int stall = Integer.parseInt(r.readLine());
			stalls[stall - 1] = true;
		}
		
		boolean[] covered = new boolean[s]; //covered stalls array
		
		for (int i = 0; i < s; i++) {
			covered[i] = true;
		}
		m--;
		
		int index = 0;

		while (stalls[index] == false) {
			covered[index] = false;
			stalls[index] = true;
			index++;
		} //check for empty stalls in front and uncover them
		
		index = s - 1;
		while (stalls[index] == false) {
			covered[index] = false;
			stalls[index] = true;
			index--;
		} //check for empty stalls in back and uncover them
		
		for (int i = 0; i < m; i++) {
			int longest = 0;
			int streak = 0;
			int streakStart = 0;
			int streakEnd = 0;
			boolean previous = true;
			boolean current;
			for (int j = 0; j < s; j++) {
				if (j == 0) {
				} else {
					previous = stalls[j - 1];
				}
				current = stalls[j];
				
				if (current == false && previous == false) {
					streak++;
				} else if (current == false && previous == true) {
					
					streak = 1;
					//streakStart = j;
				} else if (current == true && previous == false) {
					if (streak > longest) {
						longest = streak;
						streakEnd = j - 1;
						streakStart = streakEnd - streak + 1;
					}
					streak = 0;
				}
			}
			for (int j = streakStart; j <= streakEnd; j++) {
				covered[j] = false;
				stalls[j] = true;
			}
		}
		int stallsCovered = 0;
		for (int i = 0; i < s; i++) {
			if (covered[i] == true) stallsCovered++;
		}
		
		w.println(stallsCovered);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		
		
	}

}
