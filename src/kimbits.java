import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


/*
 ID: farmersrice
 LANG: JAVA
 TASK: kimbits
 */

public class kimbits {

    static ArrayList<String> combinations = new ArrayList<String>();
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("kimbits.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
              //System.out.println(System.getProperty("java.runtime.version"));
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        int N = Integer.parseInt(inputs[0]);
        int L = Integer.parseInt(inputs[1]);
        long K = Long.parseLong(inputs[2]);
        
        long start = System.currentTimeMillis();
        
        int[][] values = new int[32][32]; //[n][l] is the number of strings of length n with <= l bits. we find this by doing a simple choose. [n][l] == n choose l + n choose (l - 1) + ... + n choose 0;
        
        for (int i = 0; i < 32; i++) {
            values[0][i] = 1;
            values[i][i] = 1;
            values[i][0] = 1;
        }
        
        for (int i = 1; i < 32; i++) {
            for (int j = 1; j < 32; j++) {
                values[i][j] = values[i - 1][j] + values[i - 1][j - 1];
            }
        }
        
        String answer = "";
        
        for (int i = 0; i < N; i++) {
            if (values[N - i - 1][L] >= K) {
                answer += 0;
            } else {
                answer += 1;
                K -= values[N - i - 1][L];
                L--;
                
            }
        }
        w.println(answer);
        //int[] possible = new int[(int) Math.pow(2, bitsize)];
        //ArrayList<Integer> combinations = new ArrayList<Integer>();
        
        /*
        recursiveGenerate(bitsize, new StringBuilder("0"), 0, maxbits);
        recursiveGenerate(bitsize, new StringBuilder("1"), 1, maxbits);
        //Collections.sort(combinations);
        w.println(combinations.get(ithelement - 1));
        //boolean[] testing = new boolean[2147483647];
        byte[] truebits = new byte[(int) Math.pow(2, bitsize)];
        
        truebits[0] = 0;
        truebits[1] = 1;
        
        int counter = 0;
        if (maxbits >= 1)
            counter = 2;
        else
            counter = 1;
        
        if (ithelement == 1) {
            String binaryString = "0";
            char[] chars = binaryString.toCharArray();
            //w.println(Integer.toBinaryString(i));
            if (chars.length < bitsize) {
                int numzeroes = bitsize - chars.length;
                for (int k = 0; k < numzeroes; k++) {
                    binaryString = "0" + binaryString;
                }
            }
            w.println(binaryString);
            w.flush();
            r.close();
            w.close();
            System.exit(0);
        } else if (ithelement == 2) {
            String binaryString = "1";
            char[] chars = binaryString.toCharArray();
            //w.println(Integer.toBinaryString(i));
            if (chars.length < bitsize) {
                int numzeroes = bitsize - chars.length;
                for (int k = 0; k < numzeroes; k++) {
                    binaryString = "0" + binaryString;
                }
            }
            w.println(binaryString);
            w.flush();
            r.close();
            w.close();
            System.exit(0);
        }
        
        outer:
        for (int i = 1; i < bitsize; i++) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                
                if (truebits[j] + 1 <= maxbits) {
                    counter++;
                    if (counter == ithelement) {
                        String binaryString = Integer.toBinaryString(j + (int) Math.pow(2, i));
                        char[] chars = binaryString.toCharArray();
                        //w.println(Integer.toBinaryString(i));
                        if (chars.length < bitsize) {
                            int numzeroes = bitsize - chars.length;
                            for (int k = 0; k < numzeroes; k++) {
                                binaryString = "0" + binaryString;
                            }
                        }
                        w.println(binaryString);
                        break outer;
                    }
                }
                truebits[j + (int) Math.pow(2, i)] = (byte) (truebits[j] + 1);
                
            }
        }
        
        /*
        int counter = 0;
        for (int i = 0; i < truebits.length; i++) {
            if (truebits[i] <= maxbits) {
                counter++;
                if (counter == ithelement) {
                    String binaryString = Integer.toBinaryString(i);
                    char[] chars = binaryString.toCharArray();
                    //w.println(Integer.toBinaryString(i));
                    if (chars.length < bitsize) {
                        int numzeroes = bitsize - chars.length;
                        for (int j = 0; j < numzeroes; j++) {
                            binaryString = "0" + binaryString;
                        }
                    }
                    w.println(binaryString);
                    break;
                }
            }
        }*/
        System.out.println(System.currentTimeMillis() - start);
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }//21 10 1048576
    
    /*
    public static void recursiveGenerate(int bitsize, StringBuilder current, int onecount, int maxbits) {
        if (onecount <= maxbits && current.length() == bitsize) 
            combinations.add(current.toString());
        else if (current.length() > bitsize || onecount > maxbits) return;
        else if (onecount <= maxbits && current.length() < bitsize) {
            current.append("0");
            recursiveGenerate(bitsize, current, onecount, maxbits);
            current.delete(current.length() - 1, current.length());
            current.append("1");
            recursiveGenerate(bitsize, current, onecount + 1, maxbits);
            current.delete(current.length() - 1, current.length());
        }
        
        
    }*/

}
