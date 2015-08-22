import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

/*
ID: farmersrice
LANG: JAVA
TASK: comehome
*/

/*
 * The solution is to use Dijkstra's algorithm and then loop to find the shortest distance vertex.
 */
public class comehome {

	static HashMap<Character, Vertex> vertices;
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		int p = Integer.parseInt(r.readLine());
		
		vertices = new HashMap<Character, Vertex>();
		for (int i = 0; i < p; i++) {
			String input = r.readLine();
			String[] inputs = input.split(" ");
			
			Vertex first = new Vertex(inputs[0].toCharArray()[0]);
			Vertex second = new Vertex(inputs[1].toCharArray()[0]);
			if (vertices.containsKey(first.name) == false) //if the vertex does not already exit in the hashmap;
				vertices.put(first.name, first); //we put it in
			else
				first = vertices.get(first.name); //we replace first with the existing vertex
			
			
			if (vertices.containsKey(second.name) == false) 
				vertices.put(second.name, second); //add the new vertices to the hashset
			else
				second = vertices.get(second.name);
			
			first.addAdjacent(second, Integer.parseInt(inputs[2]));
			second.addAdjacent(first, Integer.parseInt(inputs[2]));
		}
		
		Vertex barn = vertices.get('Z'); //get the barn vertex so we find the shortest occupied vertex to it
		barn.distance = 0;
		
		int nodesVisited = 1;
		
		Vertex[] vertexArr = vertices.values().toArray(new Vertex[0]);
		for (int i = 0; i < vertexArr.length; i++) {
			for (int j = 0; j < vertexArr[i].adjacent.length; j++) {
				if (vertexArr[i].adjacent[j].equals(barn) && vertexArr[i].dist[j] < vertexArr[i].distance) {
					vertexArr[i].distance = vertexArr[i].dist[j];
				}
			}
		}
		
		while (nodesVisited < vertices.size()) {
			Vertex u = new Vertex();
			
			for (int i = 0; i < vertexArr.length; i++) {
				if (vertexArr[i].distance < u.distance && vertexArr[i].name != 'Z' && vertexArr[i].visited == false) 
					u = vertexArr[i];
			}
			//if (u.visited == false) {
			u.visited = true;
			nodesVisited++;
			//}
			
			for (int i = 0; i < u.adjacent.length; i++) {
				if (u.distance + u.dist[i] < u.adjacent[i].distance && u.adjacent[i].visited == false) {
					u.adjacent[i].distance = u.distance + u.dist[i];
					u.adjacent[i].parent = u;
				}
			}
			
		}
		
		
		
		Vertex first = null;
		for (int i = 0; i < vertexArr.length; i++) {
			if (first == null && vertexArr[i].name >= 'A' && vertexArr[i].name <= 'Y') {
				first = vertexArr[i];
				//i = 0;
			}
			if (first != null && vertexArr[i].distance < first.distance && vertexArr[i].name >= 'A' && vertexArr[i].name <= 'Y') {
				first = vertexArr[i];
				//System.out.println(vertexArr[i].name);
			}
		}
		
		w.println(first.name + " " + first.distance);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
	}
	
	public static class Vertex {
		Vertex parent = null;
		int distance = Integer.MAX_VALUE;
		boolean visited = false;
		
		Vertex[] adjacent;
		int[] dist; //distances to adjacents, parallel arrays
		int index = 0;
		
		char name;
		public Vertex(char name) {
			adjacent = new Vertex[1];
			dist = new int[1];
			this.name = name;
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
			
			if (v == vertices.get('Z')) {
				distance = d;
			}
		}
		
		public boolean equals(Object o) {
			Vertex temp = null;
			try {
				temp = (Vertex) o;
			} catch (ClassCastException e) {
				return false;
			}
			
			if (temp != null && temp.name == name) return true;
			else return false;
		}
		
		public String toString() {
			return name + " " + distance;
		}
	}

}
