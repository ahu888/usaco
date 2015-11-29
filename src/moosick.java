import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class moosick {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("irrigation.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));

        int n = Integer.parseInt(r.readLine());
        
        int[] notes = new int[n];
        
        for (int i = 0; i < n; i++) {
            notes[i] = Integer.parseInt(r.readLine());
        }
        
        int c = Integer.parseInt(r.readLine());
        
        int[] chord = new int[c];
        for (int i = 0; i < c; i++) {
            chord[i] = Integer.parseInt(r.readLine());
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chord.length; i++) {
            if (chord[i] < min) {
                min = chord[i];
            }
        }
        
        for (int i = 0; i < chord.length; i++) {
            chord[i] -= min;
        }
        
        Arrays.sort(chord);
        
        int count = 0;
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        int[] temp = new int[c];
        for (int i = 0; i < notes.length - c + 1; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[j] = notes[i + j];
            }
            
            min = Integer.MAX_VALUE;
            
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] < min) {
                    min = temp[j];
                }
            }
            
            for (int j = 0; j < temp.length; j++) {
                temp[j] -= min;
            }
            
            Arrays.sort(temp);
            
            if (Arrays.equals(temp, chord)) {
                count++;
                indices.add(i + 1);
            }
        }
        
        w.println(count);
        
        for (int i = 0; i < indices.size(); i++) {
            w.println(indices.get(i));
        }
        w.flush();
    }

}
