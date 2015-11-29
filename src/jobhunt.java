import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;


public class jobhunt {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int salary = Integer.parseInt(inputs[0]); //number of dollars that bessie can make per city
        int p = Integer.parseInt(inputs[1]); //number of directed paths
        int c = Integer.parseInt(inputs[2]); //number of cities
        int f = Integer.parseInt(inputs[3]); //number of flights
        int s = Integer.parseInt(inputs[4]); //citi bessie is currently in
        
        boolean[][] paths = new boolean[c + 1][c + 1];
        
        for (int i = 0; i < p; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            paths[a][b] = true;
        }
        
        int[][] flights = new int[c + 1][c + 1];
        
        for (int i = 0; i < f; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int cost = Integer.parseInt(inputs[2]);
            if (flights[a][b] == 0) {
                flights[a][b] = cost;
            } else {
                flights[a][b] = Math.min(flights[a][b], cost);
            }
        }
        
        /*
         * bfs, if you visit a previously visited node you stop there, with 2 cases
         * 1. you had a higher money amount at the previous node than currently, do nothing
         * 2. you have more money than the time you previously arrived, break out and print -1 because
         * you can cycle in a loop to make infinite moneys
         */
        int[] prevMoney = new int[c + 1];
        for (int i = 0; i < c + 1; i++) {
            prevMoney[i] = -1;
        }
        LinkedList<State> queue = new LinkedList<State>();
        queue.add(new State(s, salary));
        
        State current = null;
        boolean infinite = false;
        
        outer:
        while (queue.peek() != null) {
            current = queue.remove();
            prevMoney[current.city] = Math.max(prevMoney[current.city], current.money);
            
            for (int i = 0; i < c + 1; i++) {
                if (paths[current.city][i] && prevMoney[i] < current.money + salary) {
                    //prevMoney[i] = current.money + salary;
                    queue.add(new State(i, current.money + salary));
                    prevMoney[i] = current.money + salary;
                }/* else if (paths[current.city][i] && prevMoney[i] != -1) {
                    if (prevMoney[i] < current.money + salary) {
                        infinite = true;
                        break outer;
                    }
                }*/
                
                
                if (flights[current.city][i] > 0 && prevMoney[i] < current.money + salary - flights[current.city][i]) {
                    //prevMoney[i] = current.money + salary;
                    queue.add(new State(i, current.money + salary - flights[current.city][i]));
                    prevMoney[i] = current.money + salary - flights[current.city][i];
                } /*else if (flights[current.city][i] > 0 && prevMoney[i] != -1) { //if it is visited and we can go theree
                    if (prevMoney[i] < current.money + salary - flights[current.city][i]) { //if the prev money is less than the money we would have
                        infinite = true;
                        break outer;
                    }
                }*/
            }
        }
        
        int max = 0;
        for (int i = 0; i < prevMoney.length; i++) {
            if (prevMoney[i] > max) {
                max = prevMoney[i];
            }
        }
        if (infinite) {
            w.println(-1);
        } else {
            w.println(max);
        }
        w.flush();
    }

    public static class State {
        int city, money;
        
        public State(int c, int m) {
            city = c;
            money = m;
        }
        
        public String toString() {
            return city + " " + money;
        }
    }
}
