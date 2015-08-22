import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: farmersrice
LANG: JAVA
TASK: numtri
*/

public class numtri {

	public static int highest = 0;
	public static int rows;
	public static int[][] best;
	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("numtri.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new
		//FileWriter("numtri.out")));

		
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);
		
		rows = Integer.parseInt(r.readLine());
		
		int[][] array = new int[rows][rows + 1];
		best = new int[rows][rows];

		for (int i = 0; i < rows; i++) {
			StringTokenizer st = new StringTokenizer(r.readLine());
			for (int j = 0; j <= i; j++) {
				array[i][j] = Integer.parseInt(st.nextToken()); //initialize the array of inputs
				best[i][j] += Math.max(getBest(i - 1, j), getBest(i - 1, j - 1)) + array[i][j];
			}
		}
		for (int i = 0; i < rows; i++) {
			if (best[rows - 1][i] > highest) highest = best[rows - 1][i];
		}
		
		//for (int i = 0; i < rows; i++) {
			
		//}
		w.println(highest);
		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}
	
	public static int getBest(int rows, int columns) {
		if (rows != -1 && columns != -1) return best[rows][columns];
		else return 0;
	}
	
	/*public static void recursive(int[][] array, int sum, int row, int column) { //slow so we need to save values
		if (row == rows) { //no more rows left so we are at the bottom
			if (highest < sum) highest = sum;
			return;
		}
		sum += array[row][column]; //saving values is still too slow
		if (sum > best[row][column]) { //if the current sum is better than the record, then we extend that, otherwise we save recursion
			best[row][column] = sum;
			recursive(array, sum, row + 1, column);
			recursive(array, sum, row + 1, column + 1);
		}
		
	}*/

}
