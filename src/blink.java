import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;


public class blink {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("blink.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("blink.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        long b = Long.parseLong(inputs[1]);
        
        boolean[] values = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(r.readLine()) == 1;
        }
        
        State start = new State(values, 0);
        HashSet<State> states = new HashSet<State>();
        
        //we keep track of how many units of time before we cycle to state at time 0 again;
        State endState = null;
        long time = 0;
        while (time < b) {
        
            boolean[] next = new boolean[n];
            for (int i = 0; i < values.length; i++) {
                next[i] = values[i];
            }
            
            time++;
            //simulate the next state
            if (values[values.length - 1]) {
                if (next[0]) {
                    next[0] = false;
                } else {
                    next[0] = true;
                }
            }
            
            for (int i = 1; i < values.length; i++) {
                if (values[i - 1]) {
                    if (next[i]) {
                        next[i] = false;
                    } else {
                        next[i] = true;
                    }
                }
            }
            
            if (!states.contains(new State(next, 0))) {
                states.add(new State(next, time));
                endState = new State(next, time);
                values = next;
            } else {
                break;
            }
            
        }
        State repeated = new State(values, time);
        Iterator<State> iterator = states.iterator();
        
        if (time == b) {
            for (int i = 0; i < endState.values.length; i++) {
                if (endState.values[i]) {
                    w.println(1);
                } else {
                    w.println(0);
                }
            }
            w.flush();
            System.exit(0);
        } else {
            long cycleLength = 0;
            long startTime = 0;
            while (iterator.hasNext()) {
                State current = iterator.next();
                if (current.equals(repeated)) {
                    startTime = current.time;
                    cycleLength = time - startTime;
                    break;
                }
            }
            //long cycleLength = states.get(states.size() - 1).time; //number of time before we repeat
            
            int indexNumber = (int) ((b - startTime) % cycleLength);
            
            
            iterator = states.iterator();
            
            while (iterator.hasNext()) {
                State current = iterator.next();
                if (current.time == indexNumber + startTime) {
                    for (int i = 0; i < current.values.length; i++) {
                        if (current.values[i]) {
                            w.println(1);
                        } else {
                            w.println(0);
                        }
                    }
                    w.flush();
                    System.exit(0);
                }
            }
        }
        
    }

    public static class State {
        boolean[] values;
        long time;
        
        public State(boolean[] values, long time) {
            this.values = new boolean[values.length];
            
            for (int i = 0; i < values.length; i++) {
                this.values[i] = values[i];
            }
            
            this.time = time;
        }
        
        public boolean equals(Object o) {
            if (o instanceof State)  {
                if (Arrays.equals(values, ((State) o).values)) {
                    return true;
                }
            }
            return false;
        }
        
        public String toString() {
            return Arrays.toString(values);
        }
        
        public int hashCode() {
            String temp = "";
            for (int i = 0; i < values.length; i++) {
                if (values[i]) {
                    temp += "1";
                } else {
                    temp += "0";
                }
            }
            return Integer.parseInt(temp, 2);
        }
    }
}
