import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: ttwo
*/

/*
 * The solution is to create a simulation. When they are on the same spot, you have the answer.
 */
public class ttwo {

	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		map = new int[10][10]; //each int represents the object in that space, 0 means empty, 1 means obstacle, 2 means cows, 3 means farmer
		
		int farmerDir = 0;
		int cowDir = 0; //0 means north, 1 means east, 2 means south, 3 means west
		int farmerRow = 0;
		int farmerCol = 0;
		int cowRow = 0;
		int cowCol = 0;
		
		for (int i = 0; i < 10; i++) {
			String line = r.readLine();
			char[] items = line.toCharArray();
			for (int j = 0; j < 10; j++) {
				if (items[j] == '.') {
					map[i][j] = 0;
				} else if (items[j] == '*') {
					map[i][j] = 1;
				} else if (items[j] == 'C') {
					map[i][j] = 2;
					cowRow = i;
					cowCol = j;
				} else if (items[j] == 'F') {
					map[i][j] = 3;
					farmerRow = i;
					farmerCol = j;
				} else {
					System.out.println("wrong input");
				}
			}
		}
		
		
		int turns = 0;
		
		//while turns != 160,000, because there are 400 different position and direction combinations for the cow and farmer, and when you multiply it you get 160k possibilities
		while (turns != 2000000 && (farmerRow != cowRow || farmerCol != cowCol)) {
			//move the farmer
			map[farmerRow][farmerCol] = 0; //farmer leaves empty space behind
			if (farmerDir == 0 && farmerRow - 1 >= 0 && farmerCol >= 0 && farmerRow - 1 < 10 && farmerCol < 10 && map[farmerRow - 1][farmerCol] != 1) { //moving north
				map[farmerRow - 1][farmerCol] = 3;
				farmerRow--;
			}
			else if (farmerDir == 2 && farmerRow + 1 >= 0 && farmerCol >= 0 && farmerRow + 1 < 10 && farmerCol < 10 && map[farmerRow + 1][farmerCol] != 1) { //moving south
				map[farmerRow + 1][farmerCol] = 3;
				farmerRow++;
			}
			else if (farmerDir == 1 && farmerRow >= 0 && farmerCol + 1 >= 0 && farmerRow < 10 && farmerCol + 1 < 10 && map[farmerRow][farmerCol + 1] != 1) { //moving east
				map[farmerRow][farmerCol + 1] = 3;
				farmerCol++;
			}
			else if (farmerDir == 3 && farmerRow >= 0 && farmerCol - 1 >= 0 && farmerRow < 10 && farmerCol - 1 < 10 && map[farmerRow][farmerCol - 1] != 1) { //moving west
				map[farmerRow][farmerCol - 1] = 3;
				farmerCol--;
			}
			
			//otherwise we can't move, so we rotate

			else {
				if (farmerDir != 3) farmerDir++;
				else farmerDir = 0;
				// farmerDir++;
				// farmerDir = farmerDir % 4;
			}
			
			
			
			
			//move the cows
			map[cowRow][cowCol] = 0; //cow leaves empty space behind
			if (cowDir == 0 && cowRow - 1 >= 0 && cowCol >= 0 && map[cowRow - 1][cowCol] != 1) { //moving north
				map[cowRow - 1][cowCol] = 2;
				cowRow--;
			}
			else if (cowDir == 2 && cowRow + 1 >= 0 && cowCol >= 0 && cowRow + 1 < 10 && map[cowRow + 1][cowCol] != 1) { //moving south
				map[cowRow + 1][cowCol] = 2;
				cowRow++;
			}
			else if (cowDir == 1 && cowRow >= 0 && cowCol + 1 >= 0 && cowCol + 1 < 10 && map[cowRow][cowCol + 1] != 1) { //moving east
				map[cowRow][cowCol + 1] = 2;
				cowCol++;
			}
			else if (cowDir == 3 && cowRow >= 0 && cowCol - 1 >= 0 && map[cowRow][cowCol - 1] != 1) { //moving west
				map[cowRow][cowCol - 1] = 2;
				cowCol--;
			}
			
			else {
				if (cowDir != 3) cowDir++;
				else cowDir = 0;
			}
			
			//otherwise we can't move, so we rotate
			turns++;
		}
		
		if (turns == 2000000) w.println(0);
		else w.println(turns);
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	/*public static boolean canMove(int dir, int row, int column) {
		if (dir == 0) { //moving north
			if (map[row - 1][column] != 1 && row - 1 > 0 && column > 0) return true;
		}
		if (dir == 1) { //moving south
			if (map[row + 1][column] != 1 && row + 1 > 0 && column > 0) return true;
		}
		if (dir == 2) { //moving east
			if (map[row][column + 1] != 1 && row > 0 && column + 1 > 0) return true;
		}
		if (dir == 3) { //moving west
			if (map[row][column - 1] != 1 && row > 0 && column - 1 > 0) return true;
		}
		return false;
	}*/

}
