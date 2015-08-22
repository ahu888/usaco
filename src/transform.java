import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Arrays;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: transform
 PROG: transform
 */
class transform {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("transform.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		
		
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		int size = Integer.parseInt(r.readLine());
		char[][] square = new char[size][size];
		for (int i = 0; i < size; i++) {
			String list = r.readLine();
			char[] terms = list.toCharArray();
			for (int j = 0; j < size; j++) {
				square[i][j] = terms[j];
			}
		}
		char[][] transformed = new char[size][size];
		for (int i = 0; i < size; i++) {
			String list = r.readLine();
			char[] terms = list.toCharArray();
			for (int j = 0; j < size; j++) {
				transformed[i][j] = terms[j];
			}
		}
		char[][] temp = new char[size][size];
		
		
		//rotate 90 degrees clockwise and check if it equals transformed
		for (int i = 0; i < size; i++) {
			char[] row = new char[size];
			for (int j = 0; j < size; j++) {
				row[j] = square[i][j];
				temp[j][size - i - 1] = row[j];
			}
			
		}
		if (Arrays.deepEquals(temp, transformed)) {
			w.println("1");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		
		//rotate 180 clockwise and check if it equals transformed
		//rotate 180 again just by rotating 90degrees clockwise one more time
		temp = rotate90(temp);

		if (Arrays.deepEquals(temp, transformed)) {
			w.println("2");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		//rotate 270
		temp = rotate90(temp);
		
		if (Arrays.deepEquals(temp, transformed)) {
			w.println("3");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		
		temp = rotate90(temp); //rotate another 90 degrees to return to the original array
		
		char[][] result = new char[size][size];
		//now reflect horizontally
		for (int i = 0; i < temp.length; i++) { //temp.length == size
			char[] row = new char[temp.length];
			for (int j = 0; j < temp.length; j++) {
				row[j] = temp[i][size - 1 - j];
				result[i][j] = row[j];
			}
		}
		temp = result;
		
		if (Arrays.deepEquals(temp, transformed)) {
			w.println("4");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		
		temp = rotate90(temp);
		if (Arrays.deepEquals(temp, transformed)) {
			w.println("5");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		temp = rotate90(temp);
		if (Arrays.deepEquals(temp, transformed)) {
			w.println("5");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		temp = rotate90(temp);
		if (Arrays.deepEquals(temp, transformed)) {
			w.println("5");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		if (Arrays.deepEquals(square, transformed)) { //if it hasn't been transformed
			w.println("6");
			w.flush();
			r.close();
			w.close();
			System.exit(0);
		}
		w.println("7");
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		
		
	}
	
	public static char[][] rotate90(char[][] temp) {
		char[][] result = new char[temp.length][temp.length];
		for (int i = 0; i < temp.length; i++) {
			char[] row = new char[temp.length];
			for (int j = 0; j < temp.length; j++) {
				row[j] = temp[i][j];
				result[j][temp.length - i - 1] = row[j];
			}
			
		}
		return result;
	}

}
