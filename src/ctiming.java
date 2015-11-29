import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ctiming {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("ctiming.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("ctiming.out")));
        
        String[] inputs = r.readLine().split(" ");
        
        int day = Integer.parseInt(inputs[0]);
        int hour = Integer.parseInt(inputs[1]);
        int minute = Integer.parseInt(inputs[2]);
        
        int totaltime = 0;
        
        int initialStart = 11 * 1440 + 11 * 60 + 11; //day 11 hour 11 minute 11
        
        int end = day * 1440 + hour * 60 + minute;
        
        if (end - initialStart >= 0)
        w.println(end - initialStart);
        else
            w.println(-1);
        w.flush();
    }

}
