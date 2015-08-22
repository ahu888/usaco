import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
ID: farmersrice
LANG: JAVA
TASK: cowtour
*/

/*
 * The solution is to loop through all non-connected vertices and generate path segments Find the largest distances on both ends of the segment.
 * Add those two distances plus the distance between the ends and set the answer to the minimum of the answer and this value.
 * Loop through all nodes to see if there is a path longer than the answer. If so, set the answer equal to that path.
 * 
 */
public class cowtour {

	static Vertex[] firstField;
	static boolean[] checked;
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		NumberFormat formatter = new DecimalFormat("#0.000000");
		
		int n = Integer.parseInt(r.readLine());
		Vertex[] vertices = new Vertex[n];
		double[][] distances = new double[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				distances[i][j] = Double.MAX_VALUE;
			}
		}

		
		//read in the vertices and their coordinates
		for (int i = 0; i < n; i++) {
			String input = r.readLine();
			String[] inputs = input.split(" ");
			vertices[i] = new Vertex(i, Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
		}
		
		//read in the adjacency matrix
		for (int i = 0; i < n; i++) {
			String input = r.readLine();
			char[] inputs = input.toCharArray();
			for (int j = 0; j < n; j++) {
				if (inputs[j] == '1') {
					double dist = distanceBetween(vertices[i], vertices[j]);
					vertices[i].addAdjacent(vertices[j], dist);
					//vertices[j].addAdjacent(vertices[i], dist);
					distances[i][j] = dist;
					
				}
			}
		}
		
		long initial = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			distances[i][i] = 0;
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (distances[i][j] > distances[i][k] + distances[k][j] && distances[i][k] != Double.MAX_VALUE && distances[k][j] != Double.MAX_VALUE) {
						distances[i][j] = distances[i][k] + distances[k][j];
						distances[j][i] = distances[i][k] + distances[k][j];
					}
				}
			}
		}
		
		long mid = System.currentTimeMillis();
		System.out.println(mid - initial);
		
		double answer = Double.MAX_VALUE;
		
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (distances[i][j] == Double.MAX_VALUE) { //then they are not connected
					double d1 = 0;
					double d2 = 0;
					
					//this loop finds the greatest diameters on either side of the connected line
					for (int k = 0; k < n; k++) { //loop thru all nodes
						if (distances[i][k] != Double.MAX_VALUE) //if the distance between i and k is not infinity
							d1 = Math.max(d1, distances[i][k]); //we set it equal to the max of d1 and the distance
						if (distances[j][k] != Double.MAX_VALUE)
							d2 = Math.max(d2, distances[j][k]);
					}
					
					answer = Math.min(answer, d1 + d2 + distanceBetween(vertices[i], vertices[j]));
				}
			}
		}
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (distances[i][j] != Double.MAX_VALUE && distances[i][j] > answer) {
					answer = distances[i][j];
				}
			}
		}
		
		if (answer != Double.MAX_VALUE)
			w.println(formatter.format(answer));
		else
			w.println(0);
		long end = System.currentTimeMillis();
		System.out.println(end - initial);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public static double distanceBetween(Vertex a, Vertex b) {
		return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
	}
	
	public static double calculateDiameter(Vertex[] vertices, double[][] distances) {
		double ans = 0;
		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				if (distances[i][j] > ans && distances[i][j] != Double.MAX_VALUE) {
					ans = distances[i][j];
				}
			}
		}
		return ans;
	}
	
	public static class Vertex implements Comparable<Vertex> {
		Vertex parent = null;
		//int distance = Integer.MAX_VALUE;
		boolean visited = false;
		
		Vertex[] adjacent;
		double[] dist; //distances to adjacents
		int index = 0;
		
		int number;
		
		int x, y;
		public Vertex(int n, int x, int y) {
			adjacent = new Vertex[0];
			dist = new double[0];
			number = n;
			this.x = x;
			this.y = y;
		}
		
		public Vertex() {
			
		}
		
		public void addAdjacent(Vertex v, double d) {
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
				
				double[] temp2 = new double[dist.length + 1];
				for (int i = 0; i < dist.length; i++) {
					temp2[i] = dist[i];
				}
				temp2[temp2.length - 1] = d;
				dist = temp2;
				
				index++;
			}
			
		}
		
		public boolean equals(Object o) {
			if (o.toString().equals(toString())) return true;
			else return false;
		}
		
		public String toString() {
			return number + "";
		}

		@Override
		public int compareTo(Vertex o) {
			return number - o.number;
		}
	}
	

}
