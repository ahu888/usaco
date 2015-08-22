import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: agrinet
*/


public class agrinet {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		int n = Integer.parseInt(r.readLine());
		int[][] distances = new int[n][n];
		
		Vertex[] vertices = new Vertex[n];
		
		for (int i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}
		for (int i = 0; i < n; i++) {
			int counter = 0;
			while (counter < n) {
				String input = r.readLine();
				String[] inputs = input.split(" ");
				for (int j = 0; j < inputs.length; j++) {
					vertices[i].addAdjacent(vertices[counter], Integer.parseInt(inputs[j]));
					distances[i][counter] = Integer.parseInt(inputs[j]);
					counter++;
				}
			}
			
		}
		
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (distances[i][j] > distances[i][k] + distances[k][j] && distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
						distances[i][j] = distances[i][k] + distances[k][j];
						distances[j][i] = distances[i][k] + distances[k][j];
					}
				}
			}
		}
		
		int treesize = 1;
		int length = 0;
		vertices[0].intree = true;
		vertices[0].distance = 0;
		
		for (int i = 1; i < n; i++) {
			vertices[i].distance = vertices[0].dist[i];
			vertices[i].parent = vertices[0];
		}
		
		while (treesize < n) {
			//find node with MINIMUM!!! distance to tree
			int leastDistance = Integer.MAX_VALUE;
			int index = 0;
			
			for (int i = 0; i < n; i++) {
				if (vertices[i].intree) {
					for (int j = 0; j < n; j++) {
						if (vertices[j].intree == false && distances[i][j] < leastDistance) {
							leastDistance = distances[i][j];
							index = j;
						}
					}
				}
			}
			
			
			if (vertices[index].intree == false) {
				if (vertices[index].distance >= leastDistance) {
					vertices[index].distance = leastDistance;
					vertices[index].intree = true;
					treesize++;
					length += vertices[index].distance;
					
					for (int k = index + 1; k < n; k++) {
						if (vertices[k].distance > distances[index][k]) {
							vertices[k].distance = distances[index][k];
							vertices[k].parent = vertices[index];
						}
					}
				}
				
			}
		}
		
		w.println(length);
		w.flush();
		w.close();
		r.close();
		System.exit(0);
		
	}

	
	public static class Vertex implements Comparable<Vertex> {
		Vertex parent = null;
		int distance = Integer.MAX_VALUE;
		boolean intree = false;
		
		Vertex[] adjacent;
		int[] dist; //distances to adjacents
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
			
		}
		
		public boolean equals(Object o) {
			if (o.toString().equals(toString())) return true;
			else return false;
		}
		
		public String toString() {
			return number + " " + distance;
		}

		@Override
		public int compareTo(Vertex o) {
			return number - o.number;
		}
	}
	
}
