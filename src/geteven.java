import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 * we just brute force if variable is even/odd and if it works we add to sum
 * 
 * 
 */

public class geteven {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("geteven.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("geteven.out")));

        int n = Integer.parseInt(r.readLine());
        
        int[] evens = new int[7]; //b, e, s, i, g, o, m, in that order, each array item gives number of evens for that letter
        int[] odds = new int[7];
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            int value = Integer.parseInt(inputs[1]);
            int number = -1;
            if (inputs[0].equals("B")) {
                number = 0;
            }
            if (inputs[0].equals("E")) {
                number = 1;
            }
            if (inputs[0].equals("S")) {
                number = 2;
            }
            if (inputs[0].equals("I")) {
                number = 3;
            }
            if (inputs[0].equals("G")) {
                number = 4;
            }
            if (inputs[0].equals("O")) {
                number = 5;
            }
            if (inputs[0].equals("M")) {
                number = 6;
            }
            
            if (value % 2 == 0) {
                evens[number]++;
            } else {
                odds[number]++;
            }
        }
        
        long total = 0;
        for (int b = 0; b < 2; b++) {
            for (int e = 0; e < 2; e++) {
                for (int s = 0; s < 2; s++) {
                    for (int i = 0; i < 2; i++) {
                        for (int g = 0; g < 2; g++) {
                            for (int o = 0; o < 2; o++) {
                                for (int m = 0; m < 2; m++) {
                                    if (((b + e + s + s + i + e)*(g + o + e + s)*(m + o + o)) % 2 == 0) {
                                        long sum = 1;
                                        if (b == 0) {
                                            sum *= evens[0];
                                        } else {
                                            sum *= odds[0];
                                        }
                                        if (e == 0) {
                                            sum *= evens[1];
                                        } else {
                                            sum *= odds[1];
                                        }
                                        if (s == 0) {
                                            sum *= evens[2];
                                        } else {
                                            sum *= odds[2];
                                        }
                                        if (i == 0) {
                                            sum *= evens[3];
                                        } else {
                                            sum *= odds[3];
                                        }
                                        if (g == 0) {
                                            sum *= evens[4];
                                        } else {
                                            sum *= odds[4];
                                        }
                                        if (o == 0) {
                                            sum *= evens[5];
                                        } else {
                                            sum *= odds[5];
                                        }
                                        if (m == 0) {
                                            sum *= evens[6];
                                        } else {
                                            sum *= odds[6];
                                        }
                                        total += sum;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        w.println(total);
        w.flush();
    }

}
