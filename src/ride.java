import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: ride
PROG: ride
*/
public class ride {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("ride.in"));
		//r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		//StringTokenizer st = new StringTokenizer(r.readLine());
		char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z'};
		
		String comet = r.readLine();
		String group = r.readLine();
		char[] cometChars = comet.toCharArray();
		char[] groupChars = group.toCharArray();
		long cometValue = 1;
		long groupValue = 1;
		
		for (int i = 0; i < cometChars.length; ++i) {
			for (int j = 1; j <= chars.length; ++j) {
				if (cometChars[i] == chars[j - 1]) {
					cometValue *= j;
				}
			}
		}
		for (int i = 0; i < groupChars.length; ++i) {
			for (int j = 1; j <= chars.length; ++j) {
				if (groupChars[i] == chars[j - 1]) {
					groupValue *= j;
				}
			}
		}
		if (cometValue % 47 == groupValue % 47) {
			w.println("GO");
		} else {
			w.println("STAY");
		}
		
		w.flush();
		w.close();
		r.close();
		System.exit(0);

	}

}
