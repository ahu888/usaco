import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: farmersrice
LANG: JAVA
TASK: inflate
*/

public class inflate {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		String input = r.readLine();
		String[] inputs = input.split(" ");
		
		int minutes = Integer.parseInt(inputs[0]);
		int classCount = 0;
		int counter = 1;
		while (classCount == 0) {
			try {
				classCount = Integer.parseInt(inputs[counter]);
			} catch (Exception e) {
				counter++;
			}
			
		}
		
		Class[] classes = new Class[classCount];
		
		for (int i = 0; i < classCount; i++) {
			input = r.readLine();
			inputs = input.split(" ");
			classes[i] = new Class(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
		}
		
		Arrays.sort(classes);
		
		int answer = 0;
		int index = 0;
		while (minutes > 0) {
			if (minutes - classes[index].minutes >= 0) {
				answer += classes[index].points;
				minutes -= classes[index].minutes;
			} else if (index + 1 < classes.length) {
				index++;
			} else break;
		}
		
		w.println(answer);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public static class Class implements Comparable<Class> {
		int points;
		int minutes;
		double ratio; //points per minute
		
		public Class(int p, int m) {
			points = p;
			minutes = m;
			ratio = ((double) p)/((double) m);
		}

		@Override
		public int compareTo(Class o) {
			if (ratio - o.ratio < 0) return 1;
			if (ratio - o.ratio > 0) return -1;
			
			if (minutes - o.minutes < 0) return 1;
			if (minutes - o.minutes > 0) return -1;
			return 0;
		}
		
		public String toString() {
			return points + " " + minutes;
		}
	}

}
