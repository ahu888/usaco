import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;


public class fuel {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("fuel.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]); //number of gas stations
        int g = Integer.parseInt(inputs[1]); //gas tank capacity
        int b = Integer.parseInt(inputs[2]); //initial fuel count
        int d = Integer.parseInt(inputs[3]); //distance needed to travel
        
        long moneySpent = 0;
        
        Station[] stations = new Station[n];
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            stations[i] = new Station(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        long current = 0;
        Arrays.sort(stations);
        
        
    }
    
    public static class Station implements Comparable<Station> {
        int x, fuelCost;
        
        public Station(int a, int f) {
            x = a;
            fuelCost = f;
        }
        
        public int compareTo(Station s) {
            return s.x - x;
        }
    }

}
