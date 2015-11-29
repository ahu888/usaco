import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;


public class fpot {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int mintime = Integer.parseInt(inputs[1]);
        
        Drop[] drops = new Drop[n];
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            
            drops[i] = new Drop(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        Arrays.sort(drops);
        
        //LinkedList<Drop> queue = new LinkedList<Drop>();
        
        int width = Integer.MAX_VALUE;
        
        int lowestinrange = Math.min(drops[0].y, drops[1].y);
        int highestinrange = Math.max(drops[0].y, drops[1].y);
        
        //queue.add(drops[0]);
        int i = 0;
        for (int j = 1; j < n; j++) {
            //queue.add(drops[j]);
            if (drops[j].y > highestinrange) {
                highestinrange = drops[j].y;
            } else if (drops[j].y < lowestinrange) {
                lowestinrange = drops[j].y;
            }
            if (highestinrange - lowestinrange >= mintime) {
                if (Math.abs(drops[j].x - drops[i].x) < width)
                    width = Math.abs(drops[j].x - drops[i].x);
                while (highestinrange - lowestinrange >= mintime && i < j) {
                    i++;
                    //Drop popped = queue.pop();
                    if (drops[i - 1].y == lowestinrange) {
                        lowestinrange = Integer.MAX_VALUE;
                        for (int k = i; k <= j; k++) {
                            if (drops[k].y < lowestinrange) {
                                lowestinrange = drops[k].y;
                            }
                        }
                    } else if (drops[i - 1].y == highestinrange) {
                        highestinrange = 0;
                        for (int k = i; k <= j; k++) {
                            if (drops[k].y > highestinrange) {
                                highestinrange = drops[k].y;
                            }
                        }
                    }
                    if (Math.abs(drops[j].x - drops[i].x) < width && j != i && highestinrange - lowestinrange >= mintime)
                        width = Math.abs(drops[j].x - drops[i].x);
                }
                
            }
        }
        /*
        Combo[] combos = new Combo[(n * (n - 1)) / 2];
        
        int index = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                combos[index++] = new Combo(i, j, drops);
            }
        }
        
        int mindist = Integer.MAX_VALUE;
        
        for (int i = 0; i < combos.length; i++) {
            if (combos[i].height >= mintime && combos[i].width < mindist) {
                mindist = combos[i].width;
            }
        }
        */
        if (width == Integer.MAX_VALUE) {
            w.println(-1);
        } else {
            w.println(width);
        }
        w.flush();
        
    }
    /*
    public static class Combo {
        int d1, d2, width, height;
        
        public Combo(int d1, int d2, Drop[] drops) {
            this.d1 = d1;
            this.d2 = d2;
            width = Math.abs(drops[d1].x - drops[d2].x);
            height = Math.abs(drops[d1].y - drops[d2].y);
        }
    }*/
    
    public static class Drop implements Comparable<Drop> {
        int x, y;
        
        public Drop (int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Drop d) {
            if (d.x == x) {
                return y - d.y;
            } else 
            return x - d.x;
        }
        
        public String toString() {
            return x + " " + y;
        }
    }

}
