import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;



/*
ID: farmersrice
LANG: JAVA
TASK: maze1
*/

/*
 * The solution is to use Dijkstra's algorithm.
 */
public class maze1 {

	static Vertex[] vertices;
	//static long[][] distances;
	//static int[][] weight;
	public static void main(String[] args) throws Exception {
		
		//BufferedReader r = new BufferedReader(new FileReader("maze1.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter w = new PrintWriter(System.out);
        
		String wh = r.readLine();
		String[] whArr = wh.split(" ");
		int width = Integer.parseInt(whArr[0]);
		int height = Integer.parseInt(whArr[1]);

		//initialize grid
		int nodes = height*width;
		vertices = new Vertex[height*width];
		int index = 0;
		Vertex[][] grid = new Vertex[height][width]; //rows by columns
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = new Vertex(index);
				vertices[index++] = grid[i][j];
			}
		}
		/*
		distances = new long[height*width][height*width];
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				distances[i][j] = Integer.MAX_VALUE;
			}
		}
		
		weight = new int[nodes][nodes];
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				weight[i][j] = Integer.MAX_VALUE;
			}
		}*/
		
		//assign variables for the two exits
		Vertex firstExit = null;
		Vertex secondExit = null;
		
		//read in maze
		String[] lines = new String[height*2 + 1];
		for (int i = 0; i < height*2 + 1; i++) {
			lines[i] = r.readLine();
		}
		
		long start = System.currentTimeMillis();
		//begin processing the maze
		for (int i = 0; i < height*2 + 1; i++) {
			String line = lines[i];
			char[] inputs = line.toCharArray();
			
			//something is wrong with the exit mapping, the rest is fine
			//if we are on the top row, we loop through, and if there is a space we know space below is an exit, so we assign it the exit
			if (i == 0 && inputs[0] == '+') {
				for (int j = 0; j < width*2 + 1; j++) {
					if (inputs[j] == ' ' && firstExit == null) {
						firstExit = grid[0][j/2];
					}
					else if (inputs[j] == ' ' && firstExit != null) {
						secondExit = grid[0][j/2];
					}
				}
			} else if (i == height*2 && inputs[0] == '+') { //else if we are on the bottom row, we know the one on top is the exit
				for (int j = 0; j < width*2 + 1; j++) {
					if (inputs[j] == ' ' && firstExit == null) {
						firstExit = grid[i/2 - 1][j/2];
					}
					else if (inputs[j] == ' ' && firstExit != null) {
						secondExit = grid[i/2 - 1][j/2];
					}
				}
			} else if (inputs[0] == '|' || inputs[0] == ' '){ //else we are in one of the middle few rows, so only the ones on the end count as exits
				if (inputs[inputs.length - 1] == ' ' && firstExit == null) {
					firstExit = grid[i/2][(inputs.length - 1)/2 - 1];
				}
				else if (inputs[inputs.length - 1] == ' ' && firstExit != null) {
					secondExit = grid[i/2][(inputs.length - 1)/2 - 1];
				}
				else if (inputs[0] == ' ' && firstExit == null) {
					firstExit = grid[i/2][0];
				}
				else if (inputs[0] == ' ' && firstExit != null) {
					secondExit = grid[i/2][0];
				}
			}
			
			
			if (inputs[0] == '+' && i != 0 && i != height*2) { //if it is one of the wall lines
				for (int j = 0; j < inputs.length; j++) {
					if (inputs[j] == ' ') {
						grid[i/2 - 1][j/2].addAdjacent(grid[i/2][j/2], 1);
						grid[i/2][j/2].addAdjacent(grid[i/2 - 1][j/2], 1);
					}
				}
			} else if (inputs[0] == '|' || inputs[0] == ' ') {
				for (int j = 1; j < inputs.length - 1; j++) {
					
					if (lines[i + 1].toCharArray()[j] == '+' && inputs[j] != '|') { //if the below character is a + then we know this space is not a vertex
						grid[i/2][j/2 - 1].addAdjacent(grid[i/2][j/2], 1);
						grid[i/2][j/2].addAdjacent(grid[i/2][j/2 - 1], 1);
						//(int) ((i/2 + 0.5) - 1)
						//if (grid[i/2][j/2 - 1].number == 8 || grid[i/2][j/2])
					} //else do nothing
				}
			}
			
		}
		
		
		firstExit.distance1 = 0;
		

		int nodesVisited = 1;
		
		//first run thru of the algorithm
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices[i].adjacent.length; j++) {
				if (vertices[i].adjacent[j].equals(firstExit) && vertices[i].dist[j] < vertices[i].distance1) {
					vertices[i].distance1 = vertices[i].dist[j];
				}
			}
		}
		
		while (nodesVisited < vertices.length) {
			Vertex u = new Vertex();
			if (nodesVisited == 4) {
				//System.out.println(1);
			}
			for (int i = 0; i < vertices.length; i++) {
				if (vertices[i].distance1 < u.distance1 && vertices[i].visited == false) 
					u = vertices[i];
			}
			//if (u.visited == false) {
			u.visited = true;
			nodesVisited++;
			//}
			
			for (int i = 0; i < u.adjacent.length; i++) {
				if (u.distance1 + u.dist[i] < u.adjacent[i].distance1 && u.adjacent[i].visited == false) {
					u.adjacent[i].distance1 = u.distance1 + u.dist[i];
					//u.adjacent[i].parent = u;
				}
			}
			
		}
		
		if (secondExit != null) {
			secondExit.distance2 = 0;
			nodesVisited = 1;
			for (int i = 0; i < vertices.length; i++) {
				for (int j = 0; j < vertices[i].adjacent.length; j++) {
					if (vertices[i].adjacent[j].equals(secondExit) && vertices[i].dist[j] < vertices[i].distance2) {
						vertices[i].distance2 = vertices[i].dist[j];
					}
				}
				vertices[i].visited = false;
			}
			
			while (nodesVisited < vertices.length) {
				Vertex u = new Vertex();
				
				for (int i = 0; i < vertices.length; i++) {
					if (vertices[i].distance2 < u.distance2 && vertices[i].visited == false) 
						u = vertices[i];
				}
				//if (u.visited == false) {
				u.visited = true;
				nodesVisited++;
				//}
				
				for (int i = 0; i < u.adjacent.length; i++) {
					if (u.distance2 + u.dist[i] < u.adjacent[i].distance2 && u.adjacent[i].visited == false) {
						u.adjacent[i].distance2 = u.distance2 + u.dist[i];
						//u.adjacent[i].parent = u;
					}
				}
				
			}
		}
		
		
		//int min1 = Integer.MIN_VALUE;
		//int min2 = Integer.MIN_VALUE;
		int min = 0;
		for (int i = 0; i < vertices.length; i++) {
			 if (Math.min(vertices[i].distance1, vertices[i].distance2) > min) {
				 min = Math.min(vertices[i].distance1, vertices[i].distance2);
			 }

		}
		long end = System.currentTimeMillis();
		//System.out.println(end - mid);
		
		//if (min1 == Integer.MIN_VALUE) w.println(min2 + 1);
		//else if (min2 == Integer.MIN_VALUE) w.println(min1 + 1);
		//else w.println(Math.min(min1, min2) + 1);
		w.println(min + 1);
		w.flush();
		r.close();
		w.close();

		/*
8 7
+ + +-+-+-+-+-+-+
|   | |   |     |
+ + + + + + +-+ +
| | |   | | | | |
+ + + +-+ + + + +
|   | |   | |   |
+-+ + + +-+ +-+-+
|   |   |     | |
+ + +-+ + +-+ + +
|       |       |
+-+-+-+ +-+ +-+-+
|   |     | |   |
+ + + +-+ + + +-+
|     |         |
+-+-+-+-+-+-+-+-+
		 */
		
		System.exit(0);
	}
	
	
	public static class Vertex {
		//Vertex parent = null;
		int distance1 = Integer.MAX_VALUE;
		int distance2 = Integer.MAX_VALUE;
		boolean visited = false;
		
		Vertex[] adjacent;
		int[] dist; //distances to adjacents, parallel arrays
		int index = 0;
		
		int number;
		public Vertex(int n) {
			adjacent = new Vertex[0];
			dist = new int[0];
			number = n;
		}
		
		public Vertex() {
			
		}
		
		public void addAdjacent(Vertex v, int d) {
			try {
				adjacent[index] = v;
				dist[index] = d;
				index++;
			} catch (ArrayIndexOutOfBoundsException e) {
				Vertex[] temp = new Vertex[adjacent.length + 1];
				for (int i = 0; i < adjacent.length; i++) {
					temp[i] = adjacent[i];
				}
				temp[temp.length - 1] = v;
				adjacent = temp;
				
				int[] temp2 = new int[dist.length + 1];
				for (int i = 0; i < dist.length; i++) {
					temp2[i] = dist[i];
				}
				temp2[temp2.length - 1] = d;
				dist = temp2;
				
				index++;
			}
			/*
			if (v == vertices.get('Z')) {
				distance = d;
			}*/
		}
		
		public boolean equals(Object o) {
			Vertex temp = null;
			try {
				temp = (Vertex) o;
			} catch (ClassCastException e) {
				return false;
			}
			
			if (temp != null && temp.number == number) return true;
			else return false;
		}
		
		public String toString() {
			return number + " " + distance1 + " " + distance2;
		}
	}

}
