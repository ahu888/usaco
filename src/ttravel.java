import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Stack;


public class ttravel {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        int n = Integer.parseInt(r.readLine());

        Stack<Integer>[] past = new Stack[n]; //stacks from the past
        Stack<Integer> present = new Stack<Integer>();
        
        for (int i = 0; i < n; i++) {
            past[i] = (Stack<Integer>) present.clone();
            String[] inputs = r.readLine().split(" ");
            if (inputs[0].equals("a")) {
                present.add(Integer.parseInt(inputs[1]));
                try {
                    w.println(present.peek());
                } catch (EmptyStackException e) {
                    w.println(-1);
                }
                continue;
            }
            if (inputs[0].equals("s")) {
                present.pop();
                try {
                    w.println(present.peek());
                } catch (EmptyStackException e) {
                    w.println(-1);
                }
                continue;
            }
            if (inputs[0].equals("t")) {
                present = (Stack<Integer>) past[Integer.parseInt(inputs[1]) - 1].clone();
                try {
                    w.println(present.peek());
                } catch (EmptyStackException e) {
                    w.println(-1);
                }
            }
        }
        w.flush();
    }

}
