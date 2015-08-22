import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

/*
ID: farmersrice
LANG: JAVA
TASK: prefix
*/

public class prefix {

	static HashSet<String> primitives = new HashSet<String>();
	static String sequence = "";
	static int largest = 0;
	static int longest = 0;
	static boolean[] visited;// = new boolean[200010]; //200000 is longest length plus 10 more to eliminate out of bound exceptions
	public static void main(String[] args) throws Exception {
		//long start = System.currentTimeMillis();
		BufferedReader r = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		String prims = r.readLine();
		while (prims.equals(".") == false) {
			String[] primsArray = prims.split(" ");
			for (int i = 0; i < primsArray.length; i++) {
				primitives.add(primsArray[i]);
			}
			prims = r.readLine();
		}
		
		StringBuffer sb = new StringBuffer();
		String seq = r.readLine();
		while (seq != null) {
			sb.append(seq);
			seq = r.readLine();
		}
		sequence = sb.toString();
		visited = new boolean[sequence.length() + 10];
		//for (int i = 0; i < primitives.size(); i++) {
			//w.println(primitives.get(i));
		//}
		//w.println(sequence);
		//for (int i = 0; i < primitives.size(); i++) {
		//	if (primitives.get(i).length() > longest) longest = primitives.get(i).length();
		//}
		//recursiveTrials(0);
		markPossible();
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) largest = i;
		}
		w.println(largest);
		w.flush();
		r.close();
		w.close();
		//System.out.println(System.currentTimeMillis() - start);
		System.exit(0);
		
	}
	
	public static void markPossible() {
		visited[0] = true;
		String[] prims = primitives.toArray(new String[primitives.size()]);
		Arrays.sort(prims);
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				for (int j = 0; j < prims.length; j++) {
					int length = prims[j].length();
					
					if (i + length > sequence.length() || visited[i + length]) continue;
					if (sequence.substring(i, i + length).equals(prims[j])) {
						visited[i + length] = true;
					}
					/*
					boolean valid = true;
					for (int k = 0; k < prims[j].length(); k++) {
						if (prims[j].charAt(k) != sequence.charAt(i+k)) {
							valid = false;
							break;
						}
					}
					if (valid) {
						visited[i + length] = true;
					}*/
					
				}
				/*
				String str = null;
				int diff = 1;
				if (substringWorks(i, i + 1))
					str = sequence.substring(i, i + 1);
				else {
					largest = sequence.length();
					return;
				}
				while (str == null || (diff <= 10 && substringWorks(i, i + diff))) {
					str = sequence.substring(i, i + diff);
					if (primitives.contains(str)) {
						visited[i + diff] = true;
					}
					diff++;
				}*/
			}
		}
	}
	/*
	public static void recursiveTrials(int index) {
		String str = null;
		
		if (substringWorks(index, index + 1))
			str = sequence.substring(index, index + 1);
		else {
			largest = sequence.length();
			return;
		}
		int diff = 1;
		
		while (str == null || (diff <= 10 && substringWorks(index, index + diff))) {
			str = sequence.substring(index, index + diff);
			if (primitives.contains(str))
				recursiveTrials(index + diff);
			diff++;
			
		}
		if (index + 1 > largest) largest = index;
	}
	
	public static boolean substringWorks(int start, int end) {
		try {
			sequence.substring(start, end);
		} catch (Exception e) {
			return false;
		}
		return true;
	}*/

}
