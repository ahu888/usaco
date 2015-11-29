import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class planting {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        int n = Integer.parseInt(r.readLine());
        
        Rectangle[] rectangles = new Rectangle[n];
        
        double answer = 0;
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            rectangles[i] = new Rectangle(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]));
            answer += rectangles[i].area;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j)
                    answer -= rectangles[i].overlap(rectangles[j]);
            }
        }
        
        w.println((long) answer);
        w.flush();
    }
    
    public static class Rectangle {
        long x1, y1, x2, y2; //1 is upper left 2 is lower right corner
        long area;
        
        public Rectangle(long a, long b, long c, long d) {
            x1 = a;
            y1 = b;
            x2 = c;
            y2 = d;
            area = Math.abs((x1 - x2) * (y1 - y2));
        }
        
        public double overlap(Rectangle r) {
            
            if (r.x1 >= x1 && r.x1 <= x2 && r.y1 >= y2 && r.y1 <= y1 && r.x2 >= x1 && r.x2 <= x2 && r.y2 >= y2 && r.y2 <= y1) {
                return r.area;
            }
            if (r.x1 >= x1 && r.x1 <= x2 && r.y1 >= y2 && r.y1 <= y1 && r.x2 >= x2 && r.y2 <= y2) { //vertex 1 is contained but not vertex 2
                return Math.abs((r.x1 - x2) * (r.y1 - y2)) / 2;
            }
            if (r.x1 >= x1 && r.x1 <= x2 && r.y1 >= y2 && r.y1 <= y1 && r.x2 >= x2 && r.y2 >= y2) {
                return Math.abs((r.x1 - x2) * (r.y1 - r.y2));
            }
            if (r.x1 >= x1 && r.x1 <= x2 && r.y1 >= y2 && r.y1 <= y1 && r.x2 <= x2 && r.y2 <= y2) {
                return Math.abs((r.x1 - r.x2) * (r.y1 - y2));
            }
            if (r.x2 >= x1 && r.x2 <= x2 && r.y2 >= y2 && r.y2 <= y1 && r.x1 <= x1 && r.y1 >= y1) {
                return Math.abs((r.x2 - x1) * (r.y2 - y1)) / 2; //divide by 2 because it will be counted twice
            }
            if (r.x2 >= x1 && r.x2 <= x2 && r.y2 >= y2 && r.y2 <= y1 && r.x1 >= x1 && r.y1 >= y1) {
                return Math.abs((r.x2 - r.x1) * (r.y2 - y1));
            }
            if (r.x2 >= x1 && r.x2 <= x2 && r.y2 >= y2 && r.y2 <= y1 && r.x1 <= x1 && r.y1 <= y1) {
                return Math.abs((r.x2 - x1) * (r.y2 - r.y1));
            }
            if (r.x1 >= x1 && r.y1 >= y1 && r.x2 <= x2 && r.y2 <= y2) {
                return Math.abs((r.x1 - r.x2) * (y1 - y2));
            }
            
            
            return 0;
        }
    }

}
