import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class cowids {
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new FileReader("kimbits.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
              //System.out.println(System.getProperty("java.runtime.version"));
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        String input = r.readLine();
        String[] inputs = input.split(" ");
        
        //int bitsize = Integer.parseInt(inputs[0]);
        int maxbits = Integer.parseInt(inputs[1]);
        int ithelement = Integer.parseInt(inputs[0]);
        
        int[] dp = new int[ithelement]; //x represents the xth element and value represents 
        
        long start = System.currentTimeMillis();
        //int[] possible = new int[(int) Math.pow(2, bitsize)];
        //ArrayList<Integer> combinations = new ArrayList<Integer>();
        
        /*
        recursiveGenerate(bitsize, new StringBuilder("0"), 0, maxbits);
        recursiveGenerate(bitsize, new StringBuilder("1"), 1, maxbits);
        //Collections.sort(combinations);
        w.println(combinations.get(ithelement - 1));
        //boolean[] testing = new boolean[2147483647];
        byte[] truebits = new byte[(int) Math.pow(2, 24)];
        
        truebits[0] = 0;
        truebits[1] = 1;
        
        int counter = 0;
        /*
        if (maxbits >= 1)
            counter = 2;
        else
            counter = 1;
        */
        /*
        if (ithelement == 1) {
            String binaryString = "0";
            char[] chars = binaryString.toCharArray();
            //w.println(Integer.toBinaryString(i));
            w.println(binaryString);
            w.flush();
            r.close();
            w.close();
            System.exit(0);
        } else if (ithelement == 2) {
            String binaryString = "1";
            char[] chars = binaryString.toCharArray();
            //w.println(Integer.toBinaryString(i));
            w.println(binaryString);
            w.flush();
            r.close();
            w.close();
            System.exit(0);
        }
        
        outer:
        for (int i = 1; i < 24; i++) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                /*
                if (truebits[j] + 1 == maxbits) {
                    counter++;
                    if (counter == ithelement) {
                        String binaryString = Integer.toBinaryString(j + (int) Math.pow(2, i));
                        char[] chars = binaryString.toCharArray();
                        //w.println(Integer.toBinaryString(i));
                        w.println(binaryString);
                        break outer;
                    }
                }
                truebits[j + (int) Math.pow(2, i)] = (byte) (truebits[j] + 1);
                
            }
        }
        
        int count = 0;
        for (int i = 0; i < truebits.length; i++) {
            if (truebits[i] == maxbits) {
                count++;
                w.println(Integer.toBinaryString(i));
                if (count == ithelement) {
                    w.println(Integer.toBinaryString(i));
                    break;
                }
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
    }
}
