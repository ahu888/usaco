import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 ID: farmersrice
 LANG: JAVA
 TASK: wormhole
 */
public class wormhole {

	static int combinations = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("wormhole.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		wormhole worm = new wormhole();

		int wormholes = Integer.parseInt(r.readLine());
		Hole[] holes = new Hole[wormholes];
		for (int i = 0; i < wormholes; i++) {
			String str = r.readLine();
			String[] strVl = str.split(" ");
			int x = Integer.parseInt(strVl[0]);
			int y = Integer.parseInt(strVl[1]);
			holes[i] = worm.new Hole(x, y);
			holes[i].index = i;
		}

		recursivePair(holes, 0, 0, w);

		w.println(combinations);
		w.flush();
		r.close();
		w.close();
		System.exit(0);

	}

	public static void recursivePair(Hole[] holes, int index, int iteration, PrintWriter w) {

		for (int j = index + 1; j < holes.length; j++) {
			if (holes[index].pair == null && holes[j].pair == null) {
				holes[index].pair = holes[j];
				holes[j].pair = holes[index];

				int in = 0;
				while (in < holes.length && holes[in].pair != null) {
					in++;
				}

				if (in == holes.length) { // check if all pairs are

					for (int k = 0; k < holes.length; k++) {
						Hole current = holes[k];
						while (current != null && current.traveled == false) {
							current = current.travel(holes);
						}
						if (current != null && current.traveled == true) {
							combinations++;
							/*
							w.print("found combo ");
							for (int l = 0; l < holes.length; l++) {
								w.print(holes[l].toString() + ", ");
							}
							w.println();*/
							holes[index].pair = null;
							holes[j].pair = null;
							for (int l = 0; l < holes.length; l++) {
								holes[l].traveled = false;
								holes[l].repeat = 0;
							}
							break;

						}
						for (int l = 0; l < holes.length; l++) {
							holes[l].traveled = false;
						}

					}
					holes[index].pair = null;
					holes[j].pair = null;

				} else {
					recursivePair(holes, in, 1, w);
					holes[index].pair = null;
					holes[j].pair = null;
				}

			}

		}

	}

	public class Hole {
		public int x;
		public int y;
		public int index;
		int repeat = 0;
		boolean traveled = false;
		Hole pair;

		public Hole(int a, int b) {
			x = a;
			y = b;
		}

		public Hole(int a, int b, Hole p) {
			x = a;
			y = b;
			pair = p;
		}

		public Hole travel(Hole[] combos) {
			repeat++;
			if (repeat > 900) {
				traveled = true;
				return this;
			}

			
			Hole closest = null;
			for (int i = 0; i < combos.length; i++) {

				if ((combos[i].y == pair.y && combos[i].x > pair.x && closest == null) || (closest != null && combos[i].y == pair.y && combos[i].x > pair.x && combos[i].x < closest.x)) {
					closest = combos[i];
				}

			}
			if (closest != null) {
				if (closest.traveled) {
					return closest;
				} else {
					closest.repeat++;
					closest.pair.repeat++;
					if (closest.pair.repeat > 900) {
						closest.pair.traveled = true;
						return closest.pair;
					} else if (closest.repeat > 900) {
						closest.traveled = true;
						return closest;
					}
					return (closest.travel(combos)); //some error here
				}
			}

			return null;
		}

		/*
		 * 
		 * for (int i = 0; i < combos.length - 1; i++) {
		 * 
		 * if ((combos[i].y == y && combos[i].x > x && nextHole == null) ||
		 * (nextHole != null && combos[i].y == y && combos[i].x < nextHole.x)) {
		 * nextHole = combos[i]; } } if (nextHole != null) { nextHole.traveled =
		 * true; return nextHole; } else { return nextHole; }
		 * 
		 * }
		 */

		public boolean equals(Hole h) {
			if (x == h.x && y == h.y)
				return true;
			else
				return false;
		}

		public String toString() {
			if (pair != null) {
				return index + " " + pair.index;
			} else {
				return Integer.toString(index);
			}

		}
	}

}
