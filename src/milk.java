import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: milk
PROG: milk
*/

public class milk {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("milk.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("milk.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		int milkNeeded = Integer.parseInt(inputs[0]);
		int farmerCount = Integer.parseInt(inputs[1]);
		int[] prices = new int[farmerCount];
		int[] priceAmts = new int[1001];
		
		int price = 0;
		int milkCounter = 0;
		
		for (int i = 0; i < farmerCount; i++) {
			String farmerInput = r.readLine();
			String[] farmerInputs = farmerInput.split(" ");
			prices[i] = Integer.parseInt(farmerInputs[0]);
			priceAmts[prices[i]] += Integer.parseInt(farmerInputs[1]);
		}
		
		outerloop:
		for (int i = 0; i < 1001; i++) { //price loop
			if (milkCounter < milkNeeded) {
				for (int j = 0; j < priceAmts[i]; j++) {
					if (milkCounter == milkNeeded) {
						break outerloop;
					}
					else {
						price += i;
						milkCounter++;
					}
				}
			} else break;
			
		}
		w.println(price);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		

	}
	

}
