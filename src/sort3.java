import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: farmersrice
LANG: JAVA
TASK: sort3
*/
public class sort3 {

	static int exchanges = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		int n = Integer.parseInt(r.readLine());
		
		int[] values = new int[n];
		int[] answer = new int[n];
		int ones = 0;
		int twos = 0;
		for (int i = 0; i < n; i++) {
			values[i] = Integer.parseInt(r.readLine());
			answer[i] = values[i];
			if (values[i] == 1) ones++;
			if (values[i] == 2) twos++;
		}
		Arrays.sort(answer);
		int twosinone = 0;
		int threesinone = 0;
		int onesintwo = 0;
		int threesintwo = 0;
		int onesinthree = 0;
		int twosinthree = 0;
		
		boolean looped = false;
		while ((onesintwo != 0 || onesinthree != 0 || twosinone != 0 || twosinthree != 0 || threesinone != 0 || threesintwo != 0) || looped == false) {
			twosinone = 0;
			threesinone = 0;
			onesintwo = 0;
			threesintwo = 0;
			onesinthree = 0;
			twosinthree = 0;
			for (int i = 0; i < ones; i++) {
				if (values[i] != 1) {
					if (values[i] == 2) twosinone++;
					else if (values[i] == 3) threesinone++;
				}
			}
			for (int i = ones; i < twos + ones; i++) {
				if (values[i] != 2) {
					if (values[i] == 1) onesintwo++;
					else if (values[i] == 3) threesintwo++;
				}
			}
			for (int i = twos + ones; i < values.length; i++) {
				if (values[i] != 3) {
					if (values[i] == 1) onesinthree++;
					else if (values[i] == 2) twosinthree++;
				}
			}
			
			ones = 0;
			twos = 0;
			int min1 = Math.min(onesintwo, twosinone);
			exchanges += min1;
			onesintwo -= min1;
			twosinone -= min1;
			
			int min2 = Math.min(onesinthree, threesinone);
			exchanges += min2;
			onesinthree -= min2;
			threesinone -= min2;
			
			int min3 = Math.min(twosinthree, threesintwo);
			exchanges += min3;
			twosinthree -= min3;
			threesintwo -= min3;
			
			int sum = onesintwo + onesinthree + twosinone + twosinthree + threesinone + threesintwo;
			if (min1 == 0 && min2 == 0 && min3 == 0) {
				int i;
				for (i = 0; i < values.length; i++) {
					if (values[i] != answer[i]) break;
				}
				if (i == values.length) break;
				
				exchanges += sum * 2/3;
				break;
			}
			//ones = twosinone + threesinone;
			//twos = onesintwo + threesintwo;
			int threes = onesinthree + twosinthree;
			values = new int[onesintwo + onesinthree + twosinone + twosinthree + threesinone + threesintwo];
			int previous = 0;
			for (int i = 0; i < twosinone; i++) {
				values[previous] = 2;
				previous++;
				twos++;
			}
			for (int i = 0; i < threesinone; i++) {
				values[previous] = 3;
				previous++;
				threes++;
			}
			for (int i = 0; i < onesintwo; i++) {
				values[previous] = 1;
				previous++;
				ones++;
			}
			for (int i = 0; i < threesintwo; i++) {
				values[previous] = 3;
				previous++;
				threes++;
			}
			for (int i = 0; i < twosinthree; i++) {
				values[previous] = 2;
				previous++;
				twos++;
			}
			for (int i = 0; i < onesinthree; i++) {
				values[previous] = 1;
				previous++;
				ones++;
			}
			looped = true;
		}
		
		w.println(exchanges);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	

}
