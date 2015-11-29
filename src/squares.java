import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class squares {
    static int k;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("squares.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("squares.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]); //n squares with side length k
        
        Pasture[] pastures = new Pasture[n];
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            pastures[i] = new Pasture(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        int intersections = 0;
        
        //sweep line to find number o intersections
        
        Arrays.sort(pastures);
        int POI1 = -1;
        int POI2 = -1;
        for (int i = 0; i < pastures.length; i++) {
            //check left and right for intersections, if x is out of bounds we continue, saves lot of calcs
            for (int j = i; j >= 0; j--) {
                if (i == j) continue;
                if (Math.abs(pastures[i].x - pastures[j].x) < k) {
                    if (Math.abs(pastures[i].y - pastures[j].y) < k && ((POI1 != i && POI2 != j) && (POI1 != j && POI2 != i))) { //intersection
                        intersections++;
                        POI1 = i;
                        POI2 = j;
                    }
                } else {
                    break;
                }
            }
            
            for (int j = i; j < n; j++) {
                if (i == j) continue;
                if (Math.abs(pastures[i].x - pastures[j].x) < k) {
                    if (Math.abs(pastures[i].y - pastures[j].y) < k && ((POI1 != i && POI2 != j) && (POI1 != j && POI2 != i))) { //intersection
                        intersections++;
                        POI1 = i;
                        POI2 = j;
                    }
                } else {
                    break;
                }
            }
            if (intersections > 1) break;
        }
        
        if (intersections > 1) {
            w.println(-1);
            w.flush();
            System.exit(0);
        }
        if (intersections == 0) {
            w.println(0);
            w.flush();
            System.exit(0);
        }
        
        w.println((long) (Math.abs(pastures[POI1].y - pastures[POI2].y) - k) * (Math.abs(pastures[POI1].x - pastures[POI2].x) - k));
        w.flush();
        System.exit(0);
    }

    public static class Pasture implements Comparable<Pasture> {
        int x,y;
        
        public Pasture(int a, int b) {
            x = a;
            y = b;
        }
        
        public int compareTo(Pasture p) {
            return x - p.x;
        }
        
        public String toString() {
            return x + " " + y;
        }
    }
}
