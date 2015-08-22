import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: skidesign
 PROG: skidesign
 */
public class skidesign {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new
		FileReader("skidesign.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		int hillCount = Integer.parseInt(r.readLine());

		int[] hills = new int[hillCount];
		
		int[] values = new int[hillCount];
		for (int i = 0; i < hillCount; i++) {
			hills[i] = (Integer.parseInt(r.readLine()));
			values[i] = hills[i];
		}

		int cost = 0;

		Arrays.sort(hills);
		int hilldiff = hills[hillCount - 1] - hills[0];
		
		while (hilldiff > 17) {
			
			hills[0]++;
			hills[hillCount - 1]--;

			Arrays.sort(hills);
			hilldiff = hills[hillCount - 1] - hills[0];
			
		}
		
		int counter = 0;
		
		Arrays.sort(hills);
		Arrays.sort(values);
		
		if (counter == hillCount) System.out.println(counter);
		
		for (int i = 0; i < hillCount; i++) {
			while (hills[i] > values[i] && hills[i] >= hills[0] && hills[i] != hills[0]) {
				hills[i]--;
			}
			while (hills[i] < values[i] && hills[i] <= hills[hillCount - 1] && hills[i] != hills[hillCount - 1]) {
				hills[i]++;
			}
			cost += Math.pow(values[i] - hills[i], 2);
		}
		w.println(cost);
		w.flush();
		r.close();
		w.close();

	}

}
