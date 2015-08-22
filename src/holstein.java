import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: farmersrice
LANG: JAVA
TASK: holstein
*/


public class holstein {

	static int v;
	static int g;
	static int shovels = 0;
	static ArrayList<Integer> eaten = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		holstein h = new holstein();
		
		v = Integer.parseInt(r.readLine());
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		int[] required = new int[v];
		for (int i = 0; i < v; i++) {
			required[i] = Integer.parseInt(inputs[i]);
		}
		
		g = Integer.parseInt(r.readLine());
		Feed[] food = new Feed[g];
		
		bests = new int[g];
		
		for (int i = 0; i < g; i++) {
			String in = r.readLine();
			String[] inp = in.split(" ");
			int[] vit = new int[v];
			for (int j = 0; j < v; j++) {
				vit[j] = Integer.parseInt(inp[j]);
			}
			food[i] = h.new Feed(vit, i);
		}
		
		int[] sums = new int[v];
		
		int a;
		for (a = 1; a <= g; a++) { //loop for eating 1 shovel, 2 shovels, etc.
			recursive(required, sums, food, a, 0);
			
		}
		
		
		w.print(shovels + " ");
		for (int i = 0; i < eaten.size() - 1; i++) {
			w.print(eaten.get(i) + " ");
		}
		w.println(eaten.get(eaten.size() - 1));
		r.close();
		w.close();
		System.exit(0);
	}
	
	static boolean solution = false;
	static int[] bests;
	public static void recursive(int[] required, int[] sums, Feed[] food, int repeated, int next) {
		if (check(required, sums) == true) { //we have a solution
			solution = true;
			return;
		}
		if (repeated == 0) return;
		
		for (int i = next; i < g; i++) {
			if (food[i].eaten == false) {
				for (int j = 0; j < v; j++) {
					sums[j] += food[i].vitamins[j];
				}
				eaten.add(i + 1);
				food[i].eaten = true;
				shovels++;
				/*if (check(required, sums) == true) { //we have a solution
					solution = true;
					return;
				}*/
				if (solution == false) {
					recursive(required, sums, food, repeated - 1, i + 1);
					
					if (solution == false) {
						for (int j = 0; j < v; j++) {
							sums[j] -= food[i].vitamins[j];
						}
						eaten.remove((Integer) (i + 1));
						shovels--;
						food[i].eaten = false;
					} else return;
					
				}
			}
			
			
			
		}
		
		
	}
	
	public class Feed {
		int[] vitamins;
		int num;
		boolean eaten = false;
		
		public Feed(int[] vit, int n) {
			vitamins = vit;
			num = n;
		}
		
		public String toString() {
			return Integer.toString(num);
		}
	}
	
	public static boolean check(int[] required, int[] sums) {
		int i;
		for (i = 0; i < sums.length; i++) {
			if (required[i] > sums[i]) return false;
		}
		return true;
	}

}
