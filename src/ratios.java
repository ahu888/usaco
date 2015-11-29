import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: farmersrice
LANG: JAVA
TASK: ratios
*/

public class ratios {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("ratios.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int x = Integer.parseInt(inputs[0]);
        int y = Integer.parseInt(inputs[1]);
        int z = Integer.parseInt(inputs[2]);
        
        Feed[] feed = new Feed[3];
        
        for (int i = 0; i < 3; i++) {
            input = r.readLine();
            inputs = input.split(" ");
            
            feed[i] = new Feed(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
        }
        
        int firstgcd = gcd(x, gcd(y, z));
        int modx = x/firstgcd;
        int mody = y/firstgcd;
        int modz = z/firstgcd;
        
        int totalints = Integer.MAX_VALUE;
        int amta = 0;
        int amtb = 0;
        int amtc = 0;
        int times = 0;
        
        for (int i = 0; i < 100; i++) {
            int a = feed[0].x*i;
            int b = feed[0].y*i;
            int c = feed[0].z*i;
            
            for (int j = 0; j < 100; j++) {
                a += feed[1].x*j;
                b += feed[1].y*j;
                c += feed[1].z*j;
                for (int k = 0; k < 100; k++) {
                    a += feed[2].x*k;
                    b += feed[2].y*k;
                    c += feed[2].z*k;
                    
                    //check that the ratios are the same, if so, update the min feed values
                    if (!(i == 0 && j == 0 && k == 0)) {
                        int gcd = gcd(a, gcd(b, c));
                        int ratioa = a/gcd;
                        int ratiob = b/gcd;
                        int ratioc = c/gcd;
                        
                        if (ratioa == modx && ratiob == mody && ratioc == modz && totalints > i + j + k && a >= x && b >= y && c >= z) {
                            totalints = i + j + k;
                            amta = i;
                            amtb = j;
                            amtc = k;
                            times = gcd / firstgcd;
                        }
                    }
                    
                    a -= feed[2].x*k;
                    b -= feed[2].y*k;
                    c -= feed[2].z*k;
                }
                a -= feed[1].x*j;
                b -= feed[1].y*j;
                c -= feed[1].z*j;
            }
        }
        if (amta == 0 && amtb == 0 && amtc == 0)
            w.println("NONE");
        else 
            w.println(amta + " " + amtb + " " + amtc + " " + times);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static int gcd(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
    
    public static class Feed {
        int x, y, z;
        
        public Feed(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public String toString() {
            return x + " " + y + " " + z;
        }
    }

}
