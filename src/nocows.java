import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: nocows
*/

public class nocows {

	static int possible = 0;
	static long[][] cache; //first dimension nodes, second dimension height
	static Level[] levels;
	static int n;
	static int k;
	//static int nodes = 1;
	//static boolean condition = false;
	//static ArrayList<Node> checked = new ArrayList<Node>();
	public static void main(String[] args) throws Exception {
		//long start = System.currentTimeMillis();
		BufferedReader r = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		String input = r.readLine();
		String[] inputs = input.split(" ");
		n = Integer.parseInt(inputs[0]);
		k = Integer.parseInt(inputs[1]);
		
		cache = new long[n + 1][k + 1]; //because we are not using [0][0] so we need extra spaces
		cache[1][1] = 1; //base case
		
		for (int i = 2; i <= k; i++) {
			//loop through nodes
			for (int j = 2 * (i - 1) + 1; j <= n; j += 2) {
				//go through all possible combinations of left nodes first
				int leftHeight = i - 1;
				for (int leftNodes = j - 2; leftNodes > 0; leftNodes -= 2) {
					if (cache[leftNodes][leftHeight] == 0) continue; //if there are no possible trees for this combination we continue
					long leftPossible = cache[leftNodes][leftHeight]; //get the number of nodes possible for the left side
					
					int rightNodes = j - leftNodes - 1;
					
					for (int rightHeight = 1; rightHeight <= leftHeight; rightHeight ++) {
						if (cache[rightNodes][rightHeight] == 0) continue; //if there are no possible trees we continue
						long rightPossible = cache[rightNodes][rightHeight]; //get the number of nodes possibly generated on the right side
						
						if (leftHeight == rightHeight) { //if the heights are the same, loop will count twice for us, so we do not need *2
							cache[j][i] += leftPossible * rightPossible;
						} else { //otherwise there are 2 ways, the current combination and a flipped combination, so we multiply by 2
							cache[j][i] += leftPossible * rightPossible * 2;
						}
						cache[j][i] %= 9901;
					}
				}
			}
		}
		w.println(cache[n][k]);
		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}
	
	/*
	public static long recursiveSolve(int nodes, int height) {
		//check if the nodes is valid for the height
		if (nodes < levels[height].min || nodes > levels[height].max) return 0;
		//if (nodes == levels[height].max) return 1;
		
		if (nodes % 2 == 0) return 0; //we cannot have an even number of cows, so any case with an even number is impossible
		if (cache[nodes][height] != 0) return cache[nodes][height];
		long answer = 0;
		for (int i = nodes - 2; i >= nodes/2; i -= 2) {
			long multiplier = 0;
			int counter = 0;
			int multiheight = 0;
			int multinodes = 0;
			for (int j = 1; j < levels.length - 1; j++) {
				long possible = recursiveSolve(nodes - i - 1, j);
				if (possible != 0) {
					multiplier += possible;
					counter++;
					multiheight = j;
					multinodes = nodes - i - 1;
				}
				
			}
			if (counter == 1 && multinodes == i && multiheight == height - 1) {
				answer += (recursiveSolve(i, height - 1) * multiplier);
				System.out.println("nodes="+nodes+" height="+height+" answer="+answer+" i="+i+" multiplier="+multiplier);
			} else {
				answer += (recursiveSolve(i, height - 1) * multiplier * 2);
				System.out.println("nodes="+nodes+" height="+height+" answer="+answer+" i="+i+" 2xmultiplier="+multiplier);
			}
		}
		System.out.println("nodes="+nodes+" height="+height+" answer="+answer);
		cache[nodes][height] = answer;
		return answer;
	}*/
	/*
	public static void recursiveSearch(Node current, int depth) {
		if (depth == k && nodes == n) {
			boolean good = true;
			for (int i = 0; i < checked.size(); i++) {
				if (current.findRoot().equals(checked.get(i))) good = false;
			}
			if (good) {
				possible++;
				checked.add(current.findRoot().duplicate());
				return;
			}
			return;
		} else if (nodes == n) {
			return;
		} else if (depth == k && current.parent != null) {
			recursiveSearch(current.parent.right, depth - 1);
			return;
		}
		current.left = new Node(current);
		current.right = new Node(current);
		nodes += 2;
		recursiveSearch(current.left, depth + 1);
		recursiveSearch(current.right, depth + 1);
		//if (current.parent != null) recursiveSearch(current.parent, depth - 1);
		current.left = null;
		current.right = null;
		nodes -= 2;
		
	}
	*/
	
	public static class Level {
		long min;
		long max;
		long level;
		public Level(int level) {
			this.level = level;
			min = 2 * (level - 1) + 1;
			max = (long) Math.pow(2, level) - 1;
			System.out.println("level="+level+" min="+min+" max"+max);
		}
	}
	public static class Node {
		Node left;
		Node right;
		Node parent = null;
		int nodesLeft = 0;
		int nodesRight = 0;
		public Node() {
			
		}
		
		public Node(Node p) {
			parent = p;
		}
		
		public void changeNodes() {
			nodesLeft++;
			nodesRight++;
			if (parent != null) parent.changeNodes();
		}
		public Node duplicate() {
			Node dupe = new Node();
			dupe.left = this.left;
			dupe.right = this.right;
			dupe.nodesLeft = this.nodesLeft;
			dupe.nodesRight = this.nodesRight;
			return dupe;
		}
		
		public boolean equals(Object o) {
			Node n = null;
			try {
				n = (Node) o;
			} catch (Exception e) {
				return false;
			}
			if (n.left == null && n.right == null && left == null && right == null) return true;
			if (n.left.equals(this.left.duplicate()) && n.right.equals(this.right.duplicate())) return true;
			return false;
		}
		
		public Node findRoot() {
			if (this.parent == null) return this;
			else return parent.findRoot();
		}
		//public Node left() {return left;}
		
		//public Node right() {return right;}
		
		public String toString() {
			return nodesLeft + " " + nodesRight;
		}
	}

}
