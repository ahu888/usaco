import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class lines {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);// TODO Auto-generated method stub

        int n = Integer.parseInt(r.readLine());
        
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            points[i] = new Point(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        
        ArrayList<Fraction> slopes = new ArrayList<Fraction>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Fraction s = points[i].slope(points[j]);
                if (!slopes.contains(s)) {
                    slopes.add(s);
                }
            }
        }
        
        System.out.println(slopes.size());
    }
    
    public static class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Fraction slope(Point p) {
            return new Fraction(p.y - y, p.x - x);
        }
    }

    public static class Fraction {
        int num,denom;
        boolean negative;
        boolean zero = false;
        boolean vertical = false;
        
        public Fraction(int n, int d) {
            if ((n < 0 ^ d < 0)) {
                negative = true;
            } else {
                negative = false;
            }
            
            num = Math.abs(n);
            denom = Math.abs(d);
            int gcd = gcd(num, denom);
            num /= gcd;
            denom /= gcd;
            
            if (num == 0) {
                zero = true;
            }
            if (denom == 0) {
                vertical = true;
            }
        }
        
        public String toString() {
            return num + "/" + denom + " " + negative;
        }
        
        public boolean equals(Object o) {
            Fraction f = null;
            try {
                f = (Fraction) o;
            } catch (Exception e) {
                return false;
            }
            if (f.zero && zero || f.vertical && vertical) {
                return true;
            }
            return f.negative == negative && f.num == num && f.denom == denom;
        }
    }
    
    public static int gcd(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
}
