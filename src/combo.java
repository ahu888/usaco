import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: farmersrice
LANG: JAVA
TASK: combo
PROG: combo
*/
public class combo {

	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("combo.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new
		FileWriter("combo.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);
		
		int lockSize = Integer.parseInt(r.readLine());
		String john = r.readLine();
		String master = r.readLine();
		
		String[] johnS = john.split(" ");
		String[] masterS = master.split(" ");
		
		int john1 = Integer.parseInt(johnS[0]);
		int john2 = Integer.parseInt(johnS[1]);
		int john3 = Integer.parseInt(johnS[2]);
		
		int master1 = Integer.parseInt(masterS[0]);
		int master2 = Integer.parseInt(masterS[1]);
		int master3 = Integer.parseInt(masterS[2]);
		
		//int combo = 250; //250 is the number of combinations without overlap
		combo cb = new combo();
		ArrayList<Combination> combos = new ArrayList<Combination>();
		
		for (int i = john1 - 2; i <= john1 + 2; i++) {
			int i1 = i;
			if (i <= 0) i1 = lockSize + i;
			if (i1 > lockSize) i1 -= lockSize;
			if (i1 == 0) i1 = 1;
			for (int j = john2 - 2; j <= john2 + 2; j++) {
				int j1 = j;
				if (j <= 0) j1 = lockSize + j;
				if (j1 > lockSize) j1 -= lockSize;
				if (j1 == 0) j1 = 1;
				for (int k = john3 - 2; k <= john3 + 2; k++) {
					int k1 = k;
					if (k <= 0) k1 = lockSize + k;
					if (k1 > lockSize) k1 -= lockSize;
					if (k1 == 0) k1 = 1;
					Combination cbn = cb.new Combination(i1, j1, k1);
					if (combos.size() != 0) {
						int counter = 0;
						for (int a = 0; a < combos.size(); a++) {
							if (!cbn.equals(combos.get(a))) {
								counter++;
								
							}
							
						}
						if (counter == combos.size()) {
							combos.add(cb.new Combination(i1, j1, k1));
							
						}
					} else combos.add(cb.new Combination(i1, j1, k1));
				}
			}
		}
		
		for (int i = master1 - 2; i <= master1 + 2; i++) {
			int i1 = i;
			if (i <= 0) i1 = lockSize + i;
			if (i1 > lockSize) i1 -= lockSize;
			if (i1 == 0) i1 = 1;
			for (int j = master2 - 2; j <= master2 + 2; j++) {
				int j1 = j;
				if (j <= 0) j1 = lockSize + j;
				if (j1 > lockSize) j1 -= lockSize;
				if (j1 == 0) j1 = 1;
				for (int k = master3 - 2; k <= master3 + 2; k++) {
					int k1 = k;
					if (k <= 0) k1 = lockSize + k;
					if (k1 > lockSize) k1 -= lockSize;
					if (k1 == 0) k1 = 1;
					Combination cbn = cb.new Combination(i1, j1, k1);
					if (combos.size() != 0) {
						int counter = 0;
						for (int a = 0; a < combos.size(); a++) {
							
							if (!cbn.equals(combos.get(a))) {
								counter++;
								
							}
							
						}
						if (counter == combos.size()) {
							combos.add(cb.new Combination(i1, j1, k1));
							
						}
					} else combos.add(cb.new Combination(i1, j1, k1));
				}
			}
		}
		if (lockSize == 1) {
			w.println(1);
		} else {
			w.println(combos.size());
		}
		
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		

	}
	
	public class Combination {
		public int c1;
		public int c2;
		public int c3;
		public Combination(int a, int b, int c) {
			c1 = a;
			c2 = b;
			c3 = c;
		}
		
		
		public boolean equals(Combination c) {
			//Combination c = (Combination) a;
			if (this.c1 == c.c1 && this.c2 == c.c2 && this.c3 == c.c3) {
				return true;
			} else {
				return false;
			}
		}
	}

}
