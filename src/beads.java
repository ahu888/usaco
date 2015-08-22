import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: beads
 PROG: beads
 */

public class beads {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("beads.in"));
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("beads.out")));
		//PrintWriter w = new PrintWriter(System.out);

		r.readLine();
		String a = r.readLine();
		char[] composition = a.toCharArray();

		int maximum = 0;
		// char previous = 'w';
		char first = composition[0];
		int counter = 0;
		for (int i = 0; i < composition.length; i++) {
			if (composition[i] == first)
				counter++;
		}

		if (counter == composition.length) {
			w.println(composition.length);
			w.flush();
		} else {
			for (int i = 0; i < composition.length; i++) {
				int leftCount = 0;
				int rightCount = 0;

				int j = i + 1;
				if (j < 0)
					j += composition.length;
				if (j > composition.length - 1)
					j -= composition.length;
				char color = composition[i];
				if (color == 'w') {
					while (composition[j] == color) {
						rightCount++;
						j++;
						if (j < 0)
							j += composition.length;
						if (j > composition.length - 1)
							j -= composition.length;
					}
					color = composition[j];
				}
				while (composition[j] == color || composition[j] == 'w') {
					rightCount++;
					j++;
					if (j < 0)
						j += composition.length;
					if (j > composition.length - 1)
						j -= composition.length;
				}
				int k = i - 1;
				if (k < 0)
					k += composition.length;
				if (k > composition.length - 1)
					k -= composition.length;

				int previous = i - 1;
				if (previous < 0)
					previous += composition.length;
				color = composition[previous];
				if (color == 'w') {
					while (composition[j] == color) {
						rightCount++;
						j++;
						if (j < 0)
							j += composition.length;
						if (j > composition.length - 1)
							j -= composition.length;
					}
					color = composition[j];
				}
				// if (previous > composition.length) previous -=
				// composition.length;
				while (composition[previous] == composition[k] || composition[k] == 'w') {
					leftCount++;
					k--;
					if (k < 0)
						k += composition.length;
					if (k > composition.length - 1)
						k -= composition.length;
				}
				int total = leftCount + rightCount + 1;
				if (total > composition.length) total = composition.length;
				if (total > maximum)
					maximum = total;

			}
			w.println(maximum);
			w.flush();
		}

		r.close();
		w.close();
		System.exit(0);

	}

}
