import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class odometer {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("fairphoto.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));

        String[] inputs = r.readLine().split(" ");
        int start = Integer.parseInt(inputs[0]);
        int end = Integer.parseInt(inputs[1]);
        
        char[] current = inputs[0].toCharArray();
        int currentNum = start;
        while (currentNum < end) {
            //check if we have a special number
            byte[] count = new byte[10];
            
            for (int i = 0; i < current.length; i++) {
                count[current[i] - '0']++;
            }
            
            boolean interesting = false;
            for (int i = 0; i < 10; i++) {
                if (count[i] >= current.length / 2) {
                    //it's interesting
                    interesting = true;
                    break;
                }
            }
            
            if (!interesting) {
                currentNum++;
                int temp = currentNum;
                
            }
            //find the next number that may be non interesting
            //for example, if the number 110 is interesting, then the next number that may be non interesting is 120, because it doesn't have the 11
            //or if 3220 is interesting, next will be 3230 though this should never happen
            
            
            
        }
    }

}
