import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class milktemp {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("milktemp.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("milktemp.out")));
        
        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int low = Integer.parseInt(inputs[1]);
        int good = Integer.parseInt(inputs[2]);
        int high = Integer.parseInt(inputs[3]);
        
        int[] lowrange = new int[n];
        int[] highrange = new int[n];
        
        int lowest = Integer.MAX_VALUE;
        int highest = 0;
        
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            lowrange[i] = a;
            highrange[i] = b;
            
            if (lowest > a) {
                lowest = a;
            }
            if (highest < b) {
                highest = b;
            }
        }
        
        int mostmilk = 0;
        for (int i = 0; i < n; i++) {
            //check for lowrange items and highrange items
            int sum1 = 0;
            int sum2 = 0;
            for (int j = 0; j < n; j++) {
                if (lowrange[i] < lowrange[j]) {
                    sum1 += low;
                } else if (lowrange[i] >= lowrange[j] && lowrange[i] <= highrange[j]) {
                    sum1 += good;
                } else {
                    sum1 += high;
                }
                
                if (highrange[i] < lowrange[j]) {
                    sum2 += low;
                } else if (highrange[i] >= lowrange[j] && highrange[i] <= highrange[j]) {
                    sum2 += good;
                } else {
                    sum2 += high;
                }
            }
            
            if (sum1 > mostmilk)
                mostmilk = sum1;
            if (sum2 > mostmilk)
                sum2 = mostmilk;
        }
        /*
        int[] toolow = new int[highest + 1];
        int[] justright = new int[highest + 1];
        int[] toohigh = new int[highest + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = lowrange[i]; j <= highrange[i]; j++) {
                justright[j]++;
            }
            for (int j = 0; j < lowrange[i]; j++) {
                toolow[j]++;
            }
            for (int j = highrange[i] + 1; j < highest; j++) {
                toohigh[j]++;
            }
        }
        
        int most = 0;
        int index = 0;
        
        int msum = 0;
        for (int i = 0; i < highest; i++) {
            int sum = toolow[i] * low + justright[i] * good + toohigh[i] * high;
            if (sum > msum) {
                msum = sum;
            }
        }
        
        //binry search
        w.println(msum);
        //w.println(binarySearch(lowrange, highrange, lowest, highest));*/
        w.println(mostmilk);
        w.flush();
    }
}
