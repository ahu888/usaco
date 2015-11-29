import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class chocmilk {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        int n = Integer.parseInt(r.readLine());
        
        Joint[] joints = new Joint[n + 1];

        for (int i = 1; i < n + 1; i++) {
            joints[i] = new Joint();
        }
        
        for (int i = 0; i < n - 1; i++) {
            String[] inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            joints[a].addTo(b);
            joints[b].addFrom(a);
        }
        
        int start = 0, end = 0;
        //boolean inPipe = false;
        
        for (int i = 1; i < n + 1; i++) {
            //if ((joints[start] == null || i != joints[i - 1].to.get(0)) && joints[i].from.size() >= 1 && joints[i].to.size() == 1 && joints[joints[i].to.get(0)].from.size() == 1) {
            //    start = i;
            //}
            if (joints[i].from.size() >= 1 && (joints[i].to.size() > 1 || joints[i].to.size() == 0)) {
                end = i;
                break;
            }
        }
        
        for (int i = n; i > 0; i--) {
            //if ((joints[start] == null || i != joints[i - 1].to.get(0)) && joints[i].from.size() >= 1 && joints[i].to.size() == 1 && joints[joints[i].to.get(0)].from.size() == 1) {
            //    start = i;
            //}
            if (joints[i].to.size() == 1 && (joints[i].from.size() >= 1)) {
                start = i;
            }
            if (start != 0 && joints[i].from.size() > 1) {
                break;
            }
        }
/*
9
1 3
2 3
3 4
4 5
6 7
5 8
7 8
8 9
 */
        if (start != 0)
        for (int i = start; i <= end; i++) {
            w.println(i);
        }
        else if (start == 0) {
            w.println(end);
        }
        if (end < start) {
            w.println(end);
        }
        w.flush();
    }
    
    public static class Joint {
        ArrayList<Integer> to, from;
        
        public Joint() {
            to = new ArrayList<Integer>();
            from = new ArrayList<Integer>();
        }
        
        public void addTo(int n) {
            to.add(n);
        }
        
        public void addFrom(int n) {
            from.add(n);
        }
    }

}
