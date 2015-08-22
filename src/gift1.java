import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: gift1
PROG: gift1
*/
public class gift1 {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("gift1.in"));
		//new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter((new BufferedWriter(new FileWriter("gift1.out"))));
		
		int peopleCount = Integer.parseInt(r.readLine());
		String[] names = new String[peopleCount];
		for (int i = 0; i < peopleCount; i++) {
			names[i] = r.readLine();
		}
		int[] balances = new int[peopleCount];
		
		String line;
		String line2;
		//something in following code is broken
		while((line=r.readLine())!=null && (line2 = r.readLine())!=null) { 
			String giver = line;
			String[] twoValues = line2.split(" ");
			int given = Integer.parseInt(twoValues[0]);
			
			
			int numGivenTo = Integer.parseInt(twoValues[1]);
			int givenIndividual = 0;
			if (numGivenTo != 0) {
				givenIndividual = given/numGivenTo;
			} else {}
			outerloop:
			for (int i = 0; i < names.length; i++) {
				if (giver.equals(names[i])) {
					balances[i] -= given;
					balances[i] += (given - (givenIndividual*numGivenTo));
					break outerloop;
				}
			}
			
			String[] givenTo = new String[numGivenTo];
			for (int i = 0; i < givenTo.length; i++) {
				String name = r.readLine();
				for (int j = 0; j < names.length; j++) {
					if (name.equals(names[j])) {
						balances[j] += givenIndividual;
					}
				}
			}
		}
		
		for (int i = 0; i < names.length; i++) {
			w.print(names[i] + " ");
			w.println(balances[i]);
		}
		
		
		w.flush();
		w.close();
		r.close();
		System.exit(0);

	}

}
