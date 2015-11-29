import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class mooomoo {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("mooomoo.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        
        int[] breedValues = new int[b];
        
        for (int i = 0; i < b; i++) {
            breedValues[i] = Integer.parseInt(r.readLine());
        }
        Arrays.sort(breedValues);
        int[] volumes = new int[n];
        
        for (int i = 0; i < n; i++) {
            volumes[i] = Integer.parseInt(r.readLine());
        }
        
        int totalCows = 0;
        
        for (int i = 0; i < n; i++) {
            //dp on volumes[i];
            //find lowest amount of cows possible in the field
            int volume = 0;
            if (i != 0 && volumes[i - 1] != 0) {
                volume = volumes[i] - volumes[i - 1] + 1;
            } else {
                volume = volumes[i];
            }
            int[] lowest = new int[volume + 1];
            
            for (int j = 0; j < lowest.length; j++) {
                lowest[j] = Integer.MAX_VALUE;
            }
            lowest[0] = 0;
            for (int j = 0; j < lowest.length; j++) {
                if (lowest[j] != Integer.MAX_VALUE) {
                    for (int k = 0; k < breedValues.length; k++) {
                        if (j + breedValues[k] >= lowest.length) {
                            break;
                        }
                        lowest[j + breedValues[k]] = Math.min(lowest[j] + 1, lowest[j + breedValues[k]]);
                    }
                }
            }
            
            if (lowest[volume] != Integer.MAX_VALUE) {
                totalCows += lowest[volume];
            } else {
                w.println(-1);
                w.flush();
                System.exit(0);
            }
        }
        
        w.println(totalCows);
        w.flush();
    }
    

}
