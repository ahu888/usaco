import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: farmersrice
LANG: JAVA
TASK: castle
*/

public class castle {

	public static int counter = 0;
	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("castle.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);
		castle c = new castle();
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		
		int columns = Integer.parseInt(inputs[0]);
		int rows = Integer.parseInt(inputs[1]);
		Space[][] spaces = new Space[rows][columns];
		for (int i = 0; i < rows; i++) {
			String row = r.readLine();
			String[] rowContents = row.split(" ");
			for (int j = 0; j < columns; j++) {
				spaces[i][j] = c.new Space(Integer.parseInt(rowContents[j]), i, j);
			}
		}
		
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (spaces[i][j].traversed == false) {
					counter = 0;
					int traverse = spaces[i][j].traverse(spaces);
					if (traverse == 0) traverse = 1;
					sizes.add(traverse);
				}
			}
		}
		reset(spaces);
		int modified = 0;
		int highestx = 0;
		int highesty = columns;
		char ne = 'a';
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				for (int k = 0; k < 2; k++) {
					switch(k) {
					case 1:
						if (i != 0 && spaces[i][j].north == true) {
							counter = 0;
							spaces[i][j].north = false;
							spaces[i - 1][j].south = false;
							int after = spaces[i][j].traverse(spaces);
							if (after > modified) {
								modified = after;
								highestx = i;
								highesty = j;
								ne = 'N';
								
							}
							if (after == modified) {
								if (j <= highesty) {
									highestx = i;
									highesty = j;
									ne = 'N';
								}
							}
							reset(spaces);
							spaces[i][j].north = true;
							spaces[i - 1][j].south = true;
							break;
						}
						break;
					case 0:
						if (j != columns - 1 && spaces[i][j].east == true) {
							counter = 0;
							spaces[i][j].east = false;
							spaces[i][j + 1].west = false;
							int after = spaces[i][j].traverse(spaces);
							if (after > modified) {
								modified = after;
								highestx = i;
								highesty = j;
								ne = 'E';
								
							}
							if (after == modified) {
								if (j <= highesty) {
									highestx = i;
									highesty = j;
									ne = 'E';
								}
							}
							reset(spaces);
							spaces[i][j].east = true;
							spaces[i][j + 1].west = true;
							break;
						}
						break;
					}
				}
			}
		}
		
		int highest = 0;
		for (int i = 0; i < sizes.size(); i++) {
			if (highest < sizes.get(i)) highest = sizes.get(i);
		}
		
		w.println(sizes.size());
		w.println(highest);
		w.println(modified);
		w.println((highestx + 1) + " " + (highesty + 1) + " " + ne);
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		

	}
	
	public static void reset(Space[][] spaces) {
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[0].length; j++) { //number of columns
				spaces[i][j].traversed = false;
			}
		}
	}
	
	public class Space {
		public boolean north = false; //whether or not it has a wall north of it, and so on
		public boolean south = false;
		public boolean east = false;
		public boolean west = false;
		
		public boolean traversed = false;
		
		int row;
		int column;
		
		public String toString() {
			return north + " " + south + " " + east + " " + west;
		}
		public int traverse(Space[][] spaces) throws Exception {
			//int sum = counter;
			if (this.north == false && spaces[row - 1][column].traversed == false) {
				spaces[row - 1][column].traversed = true;
				counter++;
				spaces[row - 1][column].traverse(spaces);
				
			}
			if (this.south == false && spaces[row + 1][column].traversed == false) {
				spaces[row + 1][column].traversed = true;
				counter++;
				spaces[row + 1][column].traverse(spaces);
				
			}
			if (this.east == false && spaces[row][column + 1].traversed == false) {
				spaces[row][column + 1].traversed = true;
				counter++;
				spaces[row][column + 1].traverse(spaces);
				
			}
			if (this.west == false && spaces[row][column - 1].traversed == false) {
				spaces[row][column - 1].traversed = true;
				counter++;
				spaces[row][column - 1].traverse(spaces);
				
			}
			this.traversed = true;
			return counter;
		}
		
		public Space(int sum, int row, int column) {
			this.row = row;
			this.column = column;
			switch(sum) {
			case 1:
				west = true;
				break;
			case 2:
				north = true;
				break;
			case 4:
				east = true;
				break;
			case 8:
				south = true;
				break;
			case 3:
				west = true;
				north = true;
				break;
			case 5:
				west = true;
				east = true;
				break;
			case 9:
				west = true;
				south = true;
				break;
			case 6:
				north = true;
				east = true;
				break;
			case 10:
				north = true;
				south = true;
				break;
			case 12:
				east = true;
				south = true;
				break;
			case 7:
				west = true;
				north = true;
				east = true;
				break;
			case 11:
				west = true;
				north = true;
				south = true;
				break;
			case 13:
				west = true;
				east = true;
				south = true;
				break;
			case 14:
				north = true;
				east = true;
				south = true;
				break;
			case 15:
				north = true;
				south = true;
				east = true;
				west = true;
				break;
			}
		}
	}

}
