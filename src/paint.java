import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


public class paint {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("paint.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        
        Event[] events = new Event[n];
        
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            events[i] = new Event(Integer.parseInt(inputs[0]), inputs[1].equals("R"));
        }
        
        //loop thru events to find critical points
        ArrayList<Integer> points = new ArrayList<Integer>();
        HashSet<Integer> seen = new HashSet<Integer>();
        points.add(0);
        
        int current = 0;
        for (int i = 0; i < n; i++) {
            if (events[i].direction) {
                current += events[i].time;
            } else {
                current -= events[i].time;
            }
            if (!seen.contains(current)) {
                points.add(current);
                seen.add(current);
            }
        }
        
        Collections.sort(points);
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); //keeps track of value (key) and index at which the value is at (value);
        
        for (int i = 0; i < points.size(); i++) {
            map.put(points.get(i), i);
        }
        
        int[] coats = new int[points.size() - 1]; //coats on each section, section i is between points[i] and points[i + 1];
        
        current = 0;
        for (int i = 0; i < n; i++) {
            int previous = current;
            if (events[i].direction) {
                current += events[i].time;
            } else {
                current -= events[i].time;
            }
            
            int index = map.get(previous);
            int currentIndex = map.get(current);
            
            int start = Math.min(index, currentIndex);
            int max = Math.max(index, currentIndex);
            for (int j = start; j < max; j++) {
                coats[j]++;
            }
        }
        
        long sum = 0;
        for (int i = 0; i < coats.length; i++) {
            if (coats[i] >= k) {
                sum += Math.abs(points.get(i + 1) - points.get(i));
            }
        }
        
        w.println(sum);
        w.flush();
    }
    
    public static class Event {
        int time = 0;
        boolean direction; //false == left, true == right;
        
        public Event(int t, boolean d) {
            time = t;
            direction = d;
        }
    }

}
