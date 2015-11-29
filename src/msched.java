import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Note that there are two usaco problems named msched. The one commented out is "Goldilocks and the N Cows" and the non-commented version is "Milk Scheduling".
 *
 */
public class msched {
    
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("msched.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
        
        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        Cow[] cows = new Cow[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            cows[i] = new Cow(Integer.parseInt(r.readLine()), true, false, i);
            cows[i].finishTime = cows[i].milkTime;
        }
        
        Constraint[] constraints = new Constraint[m];
        
        //HashMap<Integer, Integer> pairs = new HashMap<Integer, Integer>(); //key must be done milked to milk the set of values
        
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            constraints[i] = new Constraint(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            cows[constraints[i].then].canMilk = false; //we can't milk yet
            cows[constraints[i].then].milkFirst.add(cows[constraints[i].first]);
            //pairs.put(constraints[i].first, constraints[i].then);
        }
        
        //ArrayList<Cow> milking = new ArrayList<Cow>();
        
        ArrayList<Cow> waiting = new ArrayList<Cow>();
        
        for (int i = 1; i < n + 1; i++) {
            if (!cows[i].canMilk) {
                waiting.add(cows[i]);
            }
        }
        
        long answer = 0;
        
        while (waiting.size() != 0) {
            for (int i = 0; i < waiting.size(); i++) {
                Cow current = waiting.get(i);
                for (int j = 0; j < current.milkFirst.size(); j++) {
                    if (current.milkFirst.get(j).canMilk) {
                        if (current.milkFirst.get(j).finishTime > current.highest) {
                            current.highest = current.milkFirst.get(j).finishTime;
                        }
                        current.milkFirst.remove(j);
                        j--;
                    }
                }
                
                if (current.milkFirst.size() == 0) {
                    current.finishTime += current.highest;
                    answer = Math.max(current.finishTime, answer);
                    current.canMilk = true;
                    waiting.remove(i);
                    i--;
                }
            }
        }
        
        
        
        /*
        long totalTime = 0;
        while (milking.size() > 0) {
            Cow milked = milking.remove();
            int decreaseBy = milked.milkTime;
            Iterator<Cow> iterator = milking.iterator();
            
            while (iterator.hasNext()) {
                Cow current = iterator.next();
                current.milkTime -= decreaseBy;
            }
            totalTime += decreaseBy;
            
            if (pairs.containsKey(milked.number)) {
                cows[pairs.get(milked.number)].canMilk = true;
                milking.add(cows[pairs.get(milked.number)]);
            }
        }*/
        
        w.println(answer);
        w.flush();
    }
    
    public static class Cow implements Comparable<Cow> {
        int milkTime;
        boolean canMilk;
        boolean milked;
        int number;
        int finishTime;
        ArrayList<Cow> milkFirst = new ArrayList<Cow>(); //must milk these cows first
        int highest = 0;
        
        public Cow(int mt, boolean mkg, boolean mkd, int n) {
            milkTime = mt;
            canMilk = mkg;
            milked = mkd;
            
            number = n;
        }
        
        public int compareTo(Cow c) {
            if (!c.canMilk && !canMilk) {
                return 0;
            } else if (c.canMilk && !canMilk) {
                return 1000000;
            } else if (!c.canMilk && canMilk) {
                return -1000000;
            } else {
                return milkTime - c.milkTime;
            }
        }
        
        public String toString() {
            return number + " " + canMilk + " " + milkTime + " ";
        }
        
    }
    
    public static class Constraint {
        int first, then;
        //first we milk first, then we can milk then
        
        public Constraint(int f, int t) {
            first = f;
            then = t;
        }
    }
    /*
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("msched.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new
        FileWriter("msched.out")));
        
        int n = Integer.parseInt(r.readLine());

        ArrayList<Cow> cows = new ArrayList<Cow>(n);
        
        int time = 0;
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            int m = Integer.parseInt(inputs[0]);
            int d = Integer.parseInt(inputs[1]);
            if (d > time) {
                time = d;
            }
            cows.add(new Cow(m, d));
        }
        
        int sum = 0;
        
        for (int i = time; i >= 0; i--) {
            int biggest = 0;
            int index = -1;
            for (int j = 0; j < cows.size(); j++) {
                if (cows.get(j).milk > biggest && cows.get(j).deadline > i) {
                    index = j;
                    biggest = cows.get(j).milk;
                }
            }
            sum += biggest;
            if (index != -1)
            cows.remove(index);
        }
        
        
        w.println(sum);
        w.flush();
    }

    public static class Cow implements Comparable<Cow> {
        int milk, deadline;
        
        public Cow(int m, int d) {
            milk = m;
            deadline = d;
        }
        
        public String toString() {
            return milk + " " + deadline;
        }
        
        public int compareTo(Cow c) {
            if (c.deadline != deadline) {
                return deadline - c.deadline;
            } else {
                return c.milk - milk;
            }
        }
    }*/
    
    
}
