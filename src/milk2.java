import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Arrays;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: milk2
 PROG: milk2
 */
public class milk2 {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		
		
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		int farmers = Integer.parseInt(r.readLine());
		int[] startTimes = new int[farmers];
		int[] endTimes = new int[farmers];
		int atLeast = 0;
		int noCows = 0;

		for (int i = 0; i < farmers; i++) {
			String timesString = r.readLine();
			String[] times = timesString.split(" ");
			startTimes[i] = Integer.parseInt(times[0]);
			endTimes[i] = Integer.parseInt(times[1]);
		}
		Arrays.sort(startTimes);
		Arrays.sort(endTimes);

		if (farmers == 1) {
			atLeast = endTimes[0] - startTimes[0];
		} else {
			boolean previous = false;
			int streak = 0;
			for (int i = 0; i < farmers; i++) {

				int before = i - 1;
				int none = 0;
				if (before < 0) {
				} else {
					none = startTimes[i] - endTimes[before];
				}

				if (none <= 0 && previous == true) {
					streak += endTimes[i] - startTimes[i];
					streak += none;
				} else if (none > 0 && previous == true) {
					if (streak > atLeast) {
						atLeast = streak;
					}
					streak = endTimes[i] - startTimes[i];
					
					previous = false;
				} else if (none <= 0 && previous == false) {
					streak += endTimes[i] - startTimes[i];
					streak += none;
					previous = true;
				} 
				if (none > noCows) {
					noCows = none;
				}

			}

			if (streak > atLeast)
				atLeast = streak;

		}
		w.print(atLeast + " " + noCows + "\n");
		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}

}
