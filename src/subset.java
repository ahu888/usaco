import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
/*
ID: farmersrice
LANG: JAVA
TASK: subset
*/

public class subset {

	static long subsets = 0;
	static int sum;
	static long[][] checked = new long[400][40];
	//static HashSet<Integer[]> checked = new HashSet<Integer[]>();
	public static void main(String[] args) throws Exception {
		BufferedReader r = new BufferedReader(new FileReader("subset.in"));
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter w = new PrintWriter(System.out);

		int n = Integer.parseInt(r.readLine());
		
		int[] set = new int[n];
		for (int i = 1; i <= n; i++) {
			set[i - 1] = i;
		}
		
		
		for (int i = 0; i < checked.length; i++) {
			Arrays.fill(checked[i], -1);
		}
		sum = (n*(n + 1))/2;
		if (sum % 2 == 0) {
			/*for (int i = 1; i <= n/2; i++) { //i represents the number of terms in the subset, divided by 2 because one half is reflection
				checkSets(i, set, 1, null, true, n/2);
			}*/
			subsets = findSets(sum/2, n);
		}
		
		
		w.println(subsets/2);
		w.flush();
		r.close();
		w.close();
		System.exit(0);
		
	}
	
	public static long findSets(int sum, int upto) {
		if (sum < 0 || upto < 0) return 0;
		if (checked[sum][upto] != -1) return checked[sum][upto];
		if (sum == 0 && upto == 0) {
			checked[sum][upto] = 1;
			return 1;
		}
		checked[sum][upto] = findSets(sum, upto - 1) + findSets(sum - upto, upto - 1);
		return checked[sum][upto];
	}

	public static void checkSets(int iteration, int[] set, int index, ArrayList<Integer> subset, boolean first, int middle) {
		if (iteration == 0) {
			Integer[] sub = subset.toArray(new Integer[subset.size()]);
			Integer[] intSet = new Integer[set.length];
			for (int i = 0; i < intSet.length; i++) {
				intSet[i] = set[i];
			}
			//Integer[] otherSet = removeSet(intSet, sub);
			
			int subSum = 0;
			int otherSum = 0;
			
			for (int i = 0; i < sub.length; i++) {
				subSum += sub[i];
			}
			if (subSum == sum/2) subsets++;
			return;
		}
		/*
		if (iteration == 0 && subset.size() != middle && checked.contains(subset.toArray(new Integer[subset.size()]))) {
			Integer[] sub = subset.toArray(new Integer[subset.size()]);
			Integer[] intSet = new Integer[set.length];
			for (int i = 0; i < intSet.length; i++) {
				intSet[i] = set[i];
			}
			Integer[] otherSet = removeSet(intSet, sub);
			
			int subSum = 0;
			int otherSum = 0;
			
			for (int i = 0; i < sub.length; i++) {
				subSum += sub[i];
			}
			for (int i = 0; i < otherSet.length; i++) {
				otherSum += otherSet[i];
			}
			if (subSum == otherSum) subsets++;
			checked.add(sub);
			checked.add(otherSet);
			return;
		}*/
		if (first) {
			subset = new ArrayList<Integer>();
		}
		if (subset != null) {
			for (int i = index; i < set.length + 1; i++) {
				subset.add(i);
				
				checkSets(iteration - 1, set, i + 1, subset, false, middle);
				subset.remove((Integer) i);
			}
		}
		
	}
	
	protected static Integer[] removeSet(Object[] items, Object[] selected) { //removes all duplicates in the List and return an array of non-duplicate values
		Object[] temp = new Object[items.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = items[i];
		}
		int counter = 0; //number of null objects
		for (int i = 0; i < selected.length; i++) { //these two loops go through all possible pairs of objects in the List
			for (int j = 0; j < items.length; j++) {
				if (selected[i] == temp[j]) {
					temp[j] = null;
					counter++;
				}
			}
			
		}
		Integer[] answer = new Integer[items.length - counter];
		int position = 0;
		for (int i = 0; i < temp.length; i++) { //loop through the List we modified earlier
			if (temp[i] != null) { //if an object is not null, we preserve it by adding it to the ArrayList, otherwise, we discard it
				answer[position] = (Integer) temp[i];
				position++;
			}
		}
		return answer;
	} //end scope of removeDuplicates
}
