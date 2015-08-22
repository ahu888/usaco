import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

/*
ID: farmersrice
LANG: JAVA
TASK: milk3
*/

public class milk3 {

	static int aCapacity;
	static int bCapacity;
	static int cCapacity;
	static boolean visited[][][];
	public static HashSet<Integer> answers = new HashSet<Integer>();

	public static void main(String[] args) throws Exception {
		//BufferedReader r = new BufferedReader(new FileReader("milk3.in"));
		//PrintWriter w = new PrintWriter(new BufferedWriter(new
		//FileWriter("milk3.out")));

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter w = new PrintWriter(System.out);

		milk3 milk = new milk3();

		String input = r.readLine();
		String[] inputs = input.split(" ");
		int a = Integer.parseInt(inputs[0]);
		int b = Integer.parseInt(inputs[1]);
		int c = Integer.parseInt(inputs[2]);

		aCapacity = a;
		bCapacity = b;
		cCapacity = c;

		visited = new boolean[a + 1][b + 1][c + 1];
		// Bucket a = milk.new Bucket(A, 0);
		// Bucket b = milk.new Bucket(B, 0);
		// Bucket c = milk.new Bucket(C, C);

		milk.recursive(0, 0, c);

		Object[] print = answers.toArray();
		int[] ints = new int[print.length];
		for (int i = 0; i < print.length; i++) {
			ints[i] = (Integer) print[i];
		}

		Arrays.sort(ints);
		for (int i = 0; i < ints.length; i++) {
			if (i != ints.length - 1) {
				w.print(ints[i] + " ");
			} else {
				w.println(ints[i]);
			}
			
		}

		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}

	public void recursive(int a, int b, int c) {

		if (visited[a][b][c] == true)
			return;
		visited[a][b][c] = true;

		if (a == 0) {
			answers.add(c);
		}

		// Bucket tempa = new Bucket(a);
		// Bucket tempb = new Bucket(b);
		// Bucket tempc = new Bucket(c);
		// c to a
		recursive(a + Math.min(c, aCapacity - a), b, c - Math.min(c, aCapacity - a));
		// a to c
		recursive(a - Math.min(cCapacity - c, a), b, c + Math.min(cCapacity - c, a));

		// b to a
		recursive(a + Math.min(b, aCapacity - a), b - Math.min(b, aCapacity - a), c);
		// a to b
		recursive(a - Math.min(bCapacity - b, a), b + Math.min(bCapacity - b, a), c);

		// c to b
		recursive(a, b + Math.min(c, bCapacity - b), c - Math.min(c, bCapacity - b));
		// b to c
		recursive(a, b - Math.min(cCapacity - c, b), c + Math.min(cCapacity - c, b));
	}

	public class Bucket {
		int capacity;
		int milk;

		public Bucket(int c, int m) {
			capacity = c;
			milk = m;
		}

		public Bucket(Bucket b) {
			capacity = b.capacity;
			milk = b.milk;
		}

		public Bucket(int m) {
			milk = m;
		}

		public Bucket[] pour(Bucket target) {
			int thismilk = milk;
			int targetmilk = target.milk; // avoid changing target milk values
			while (thismilk != 0 && targetmilk < target.capacity) {
				thismilk--;
				targetmilk++;
			}
			Bucket[] values = { new Bucket(thismilk), new Bucket(targetmilk) };
			return values;

		}

		public String toString() {
			return capacity + " " + milk;
		}

		public boolean equals(Object o) {
			Bucket b = (Bucket) o;

			if (capacity == b.capacity && milk == b.milk) {
				return true;
			}
			return false;
		}
	}

}
