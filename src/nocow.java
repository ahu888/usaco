import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/*
ID: farmersrice
LANG: JAVA
TASK: nocow
*/

public class nocow {
    static ArrayList[] possibles;
    static ArrayList<String[]> combinations = new ArrayList<String[]>();
    static int numAdj;
    static int[] changeCosts;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("nocow.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);
        
        inputs = r.readLine().split(" ");
        numAdj = inputs.length - 5;
        
        HashSet[] padj = new HashSet[numAdj]; //list of possible adjectives
        
        for (int i = 0; i < numAdj; i++) {
            padj[i] = new HashSet<String>();
        }
        
        String[][] nocows = new String[n][numAdj];
        
        for (int i = 4; i < inputs.length - 1; i++) {
            padj[i - 4].add(inputs[i]);
            nocows[0][i - 4] = inputs[i];
        }
        
        for (int i = 1; i < n; i++) {
            inputs = r.readLine().split(" ");
            for (int j = 4; j < inputs.length - 1; j++) {
                padj[j - 4].add(inputs[j]);
                nocows[i][j - 4] = inputs[j];
            }
        }
        
        //with the list of possible adjectives, generate a list of FJ's cows
        possibles = new ArrayList[numAdj];
        for (int i = 0; i < numAdj; i++) {
            possibles[i] = new ArrayList(padj[i]);
            Collections.sort(possibles[i]);
        }
        
        changeCosts = new int[numAdj];
        changeCosts[numAdj - 1] = 1; //find the number of thingies you increase by when you move up to the next thingy at ech lefvel
        for (int i = numAdj - 2; i > -1; i--) {
            changeCosts[i] = changeCosts[i + 1] * possibles[i + 1].size();
        }
        
        int newk = k;
        //int[] indices = new int[numAdj];
        for (int i = 0; i < nocows.length; i++) {
            if (findIndex(nocows[i]) < newk) {
                newk++;
            }
        }
        /*
1 large brown noisy
2 large brown silent
3 large spotted noisy
4 large spotted silent
5 large white noisy
6 large white silent
7 small brown noisy
8 small brown silent
9 small spotted noisy
10 small spotted silent
11 small white noisy
12 small white silent
        */
        StringBuilder sb = new StringBuilder();
        String[] answer = getIndex(newk - 1);
        
        for (int i = 0; i < numAdj; i++) {
            sb.append(answer[i]);
            if (i != numAdj - 1)
                sb.append(" ");
        }
        w.println(sb);
        
        w.flush();
    }
    
    public static String[] getIndex(int index) {
        String[] data = new String[numAdj];
        for (int i = 0; i < numAdj; i++) {
            data[i] = (String) possibles[i].get(index / changeCosts[i]);
            index %= changeCosts[i];
        }
        return data;
    }
    
    public static int findIndex(String[] data) {
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            int position = 0;
            for (int j = 0; j < possibles[i].size(); j++) {
                if (data[i].equals(possibles[i].get(j))) {
                    position = j;
                    break;
                }
            }
            index += position * changeCosts[i];
        }
        return index;
    }

}
