import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class digits {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("blink.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("blink.out")));

        String a = r.readLine();
        String b = r.readLine();
        
        char[] chars2 = a.toCharArray();
        char[] chars3 = b.toCharArray();
        
        for (int i = 0; i < chars2.length; i++) {
            //toggle thingy at i
            if (chars2[i] == '0') {
                chars2[i] = '1';
            } else {
                chars2[i] = '0';
            }
            
            int value1 = Integer.parseInt(new String(chars2), 2);
            
            for (int j = 0; j < chars3.length; j++) {
                if (chars3[j] == '0') {
                    chars3[j] = '1';
                    
                    int value2 = Integer.parseInt(new String(chars3), 3);
                    if (value1 == value2) {
                        w.println(value1);
                        w.flush();
                        System.exit(0);
                    }
                    
                    chars3[j] = '2';
                    
                    value2 = Integer.parseInt(new String(chars3), 3);
                    if (value1 == value2) {
                        w.println(value1);
                        w.flush();
                        System.exit(0);
                    }
                    chars3[j] = '0';
                } else if (chars3[j] == '1') {
                    chars3[j] = '0';
                    
                    int value2 = Integer.parseInt(new String(chars3), 3);
                    if (value1 == value2) {
                        w.println(value1);
                        w.flush();
                        System.exit(0);
                    }
                    
                    chars3[j] = '2';
                    
                    value2 = Integer.parseInt(new String(chars3), 3);
                    if (value1 == value2) {
                        w.println(value1);
                        w.flush();
                        System.exit(0);
                    }
                    chars3[j] = '1';
                } else {
                    chars3[j] = '1';
                    
                    int value2 = Integer.parseInt(new String(chars3), 3);
                    if (value1 == value2) {
                        w.println(value1);
                        w.flush();
                        System.exit(0);
                    }
                    
                    chars3[j] = '0';
                    
                    value2 = Integer.parseInt(new String(chars3), 3);
                    if (value1 == value2) {
                        w.println(value1);
                        w.flush();
                        System.exit(0);
                    }
                    chars3[j] = '2';
                }
            }
            
            if (chars2[i] == '0') {
                chars2[i] = '1';
            } else {
                chars2[i] = '0';
            }
        }
    }

}
