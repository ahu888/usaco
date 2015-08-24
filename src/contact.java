import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
ID: farmersrice
LANG: JAVA
TASK: contact
*/

/*
 * Solution: Brute force. Generate all possible combinations, and then loop through the main data string and check the substrings.
 */

public class contact {

    static HashMap<String, Combo> combos;
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        BufferedReader r = new BufferedReader(new FileReader("contact.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        int n = Integer.parseInt(inputs[2]);
        
        combos = new HashMap<String, Combo>();
        
        //generate combos
        for (int i = a; i <= b; i++) {
            recursiveGenerate(i, new StringBuilder(), 0);
        }
        
        StringBuilder sb = new StringBuilder(r.readLine());
        String line = r.readLine();
        
        while (line != null) {
            sb.append(line);
            line = r.readLine();
        }
        
        String data = sb.toString();
        
        //String[] combos = contact.combos.toArray(new String[contact.combos.size()]);
        
        for (int i = a; i <= b; i++) { //loop through the string lengths
            for (int j = 0; j + i <= data.length(); j++) { //loop through the data
                String sub = data.substring(j, j + i);
                if (combos.containsKey(sub))
                    combos.get(sub).count++;
            }
        }
        
        Combo[] combos = contact.combos.values().toArray(new Combo[contact.combos.size()]);
        
        Arrays.sort(combos);
        
        int counter = 0;
        
        for (int i = combos[0].count; counter < n && i > 0; i--) {
            ArrayList<Combo> list = new ArrayList<Combo>();
            for (int j = 0; j < combos.length; j++) {
                if (combos[j].count == i)
                    list.add(combos[j]);
            }
            if (list.size() != 0) {
                w.println(i);
                Combo[] list2 = list.toArray(new Combo[list.size()]);
                for (int j = 0; j < list2.length; j++) {
                    if (j != list2.length - 1 && j % 6 != 5)
                        w.print(list2[j].combo + " ");
                    else
                        w.println(list2[j].combo);
                    
                }
                counter++;
            }
        }
        
        w.flush();
        r.close();
        w.close();
        System.out.println(System.currentTimeMillis() - start);
        System.exit(0);
    }

    public static void recursiveGenerate(int length, StringBuilder current, int depth) {
        if (depth < length) {
            //StringBuilder temp = new StringBuilder(current);
            current.append("0");
            recursiveGenerate(length, current, depth + 1);
            current.deleteCharAt(current.length() - 1);
            current.append("1");
            recursiveGenerate(length, current, depth + 1);
            current.deleteCharAt(current.length() - 1);
        } else if (depth == length) {
            Combo combo = new Combo(current.toString());
            combos.put(combo.combo, combo);
        }
    }
    
    public static class Combo implements Comparable<Combo> {
        String combo; //12 digits max
        int count = 0;
        
        public Combo(String combo) {
            this.combo = combo;
        }
        
        public int compareTo(Combo o) {
            if (o.count - count != 0)
                return (int) (o.count - count);
            if (combo.length() != o.combo.length()) {
                return combo.length() - o.combo.length();
            }
            
            long a = Long.parseLong(combo);
            long b = Long.parseLong(o.combo);
            if (a - b < 0)
                return -1;
            if (a - b > 0)
                return 1;
            return 0;
        }
        
        public String toString() {
            return combo + " " + count;
        }
        
        public boolean equals(Object o) {
            return toString().equals(o.toString());
        }
    }
    
}
