import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class paren {
    static final long mod = 12345678910L;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        
        long n = Long.parseLong(r.readLine());

        ArrayList<Object> data = new ArrayList<Object>();
        
        for (int i = 0; i < n; i++) {
            if (r.readLine().equals("0"))
                data.add("(");
            else
                data.add(")");
        }
        boolean fhalf = true;
        for (int i = 0; i < data.size()/2; i++) {
            if (data.get(i).equals(")")) 
                fhalf = false;
        }
        boolean shalf = true;
        for (int i = data.size()/2; i < data.size(); i++) {
            if (data.get(i).equals("(")) 
                shalf = false;
        }
        if (fhalf && shalf) {
            long current = 1;
            for (int i = 0; i < n/2 - 1; i++) {
                current *= 2;
                current %= mod;
            }
            System.out.println(current);
            System.exit(0);
        }
        
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).equals("(") && data.get(i + 1).equals(")")) {
                data.remove(i);
                data.set(i, new Long(1));
            }
        }
        
        
        
        while (data.size() != 1) {
            for (int i = 0; i < data.size() - 1; i++) {
                //System.out.println(data.get(i).getClass().toString());
                if (data.get(i).equals("(") && data.get(i + 2).equals(")") && data.get(i + 1).getClass().toString().equals("class java.lang.Long")) {
                    long prev = (Long) data.get(i + 1);
                    data.remove(i);
                    data.set(i, new Long((prev*2) % mod));
                    data.remove(i + 1);
                } else if (data.get(i).getClass().toString().equals("class java.lang.Long") && data.get(i + 1).getClass().toString().equals("class java.lang.Long")) {
                    long prev1 = (Long) data.remove(i);
                    long prev2 = (Long) data.get(i);
                    data.set(i, (prev1 + prev2) % mod);
                }
            }
        }
        
        System.out.println(((Long) data.get(0)).longValue());
        
    }

}
