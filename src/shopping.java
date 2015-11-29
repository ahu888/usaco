import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/*
ID: farmersrice
LANG: JAVA
TASK: shopping
*/

//i believe the solution is 5 dimensional dp which complexity is n^5 or 5^5, easily doable

public class shopping {
    static final int infinity = 1000000000;
    static int lowestPrice = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("shopping.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));

        int s = Integer.parseInt(r.readLine());
        
        ArrayList<Offer> offers = new ArrayList<Offer>();
        for (int i = 0; i < s; i++) {
            String[] inputs = r.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            
            int[] items = new int[5];
            int[] number = new int[5];
            for (int j = 0; j < n; j++) {
                items[j] = Integer.parseInt(inputs[j * 2 + 1]);
                number[j] = Integer.parseInt(inputs[j * 2 + 2]);
            }
            
            int cost = Integer.parseInt(inputs[inputs.length - 1]);
            offers.add(new Offer(items, number, cost));
        }
        
        int b = Integer.parseInt(r.readLine());
        
        int[] items = new int[5]; //ID of items to be bought
        int[] count = new int[5]; //number of that item to be bought
        int[] itemPrice = new int[5]; //price of that item
        
        for (int i = 0; i < b; i++) {
            String[] inputs = r.readLine().split(" ");
            items[i] = Integer.parseInt(inputs[0]);
            count[i] = Integer.parseInt(inputs[1]);
            itemPrice[i] = Integer.parseInt(inputs[2]);
        }
        
        //remove invalid offers that contain unwanted items
        for (int i = 0; i < offers.size(); i++) {
            for (int j = 0; j < offers.get(i).items.length; j++) {
                Pair currentItem = offers.get(i).items[j];
                if (currentItem.itemID == 0 && currentItem.amount == 0) continue;
                boolean seen = false;
                for (int k = 0; k < items.length; k++) {
                    if (currentItem.itemID == items[k]) {
                        seen = true;
                        break;
                    }
                }
                if (!seen) {
                    //invalid offer
                    offers.remove(i);
                    i--;
                    break;
                }
            }
        }
        
        int[][][][][] dp = new int[count[0] + 1][count[1] + 1][count[2] + 1][count[3] + 1][count[4] + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    for (int l = 0; l < dp[0][0][0].length; l++) {
                        for (int m = 0; m < dp[0][0][0][0].length; m++) {
                            dp[i][j][k][l][m] = itemPrice[0] * i + itemPrice[1] * j + itemPrice[2] * k + itemPrice[3] * l + itemPrice[4] * m;
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    for (int l = 0; l < dp[0][0][0].length; l++) {
                        for (int m = 0; m < dp[0][0][0][0].length; m++) {
                            for (int n = 0; n < offers.size(); n++) {
                                Pair[] objects = offers.get(n).items;
                                int[] sums = new int[5];
                                for (int o = 0; o < objects.length; o++) {
                                    for (int p = 0; p < 5; p++) {
                                        if (objects[o].itemID == items[p]) {
                                            sums[p] += objects[o].amount;
                                        }
                                    }
                                }
                                
                                if (i + sums[0] < dp.length 
                                        && j + sums[1] < dp[0].length 
                                        && k + sums[2] < dp[0][0].length 
                                        && l + sums[3] < dp[0][0][0].length 
                                        && m + sums[4] < dp[0][0][0][0].length 
                                        && dp[i + sums[0]][j + sums[1]][k + sums[2]][l + sums[3]][m + sums[4]] > dp[i][j][k][l][m] + offers.get(n).cost) {
                                    dp[i + sums[0]][j + sums[1]][k + sums[2]][l + sums[3]][m + sums[4]] = dp[i][j][k][l][m] + offers.get(n).cost;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        w.println(dp[count[0]][count[1]][count[2]][count[3]][count[4]]);
        w.flush();
        
        r.close();
        w.close();
    }
    
    //whoa
    /*
    public static void search(ArrayList<Offer> offers, int cost, int[] items, int[] count, int[] itemPrice) {
        for (int i = 0; i < offers.size(); i++) {
            int numValid = 0;
            
            Offer current = offers.get(i);
            for (int j = 0; j < current.items.length; j++) {
                for (int k = 0; k < items.length; k++) {
                    if (current.items[j] == items[k] && current.number[j] <= count[k]) {
                        numValid++;
                    }
                }
            }
            if (numValid == current.items.length) {
                //it's valid so we check it recursively
                
                for (int j = 0; j < current.items.length; j++) {
                    for (int k = 0; k < items.length; k++) {
                        if (current.items[j] == items[k]) {
                            count[k] -= current.number[j];
                        }
                    }
                }
                
                cost += current.cost;
                
                offers.remove(i);
                
                search(offers, cost, items, count, itemPrice);
                
                offers.add(i, current);
                
                for (int j = 0; j < current.items.length; j++) {
                    for (int k = 0; k < items.length; k++) {
                        if (current.items[j] == items[k]) {
                            count[k] += current.number[j];
                        }
                    }
                }
                
                cost -= current.cost;
            }
            
        }
        
        for (int i = 0; i < items.length; i++) {
            cost += count[i] * itemPrice[i];
        }
        if (cost < lowestPrice) {
            lowestPrice = cost;
        }
    }*/
    
    public static class Pair implements Comparable<Pair> {
        int itemID, amount;
        
        public Pair(int i, int a) {
            itemID = i;
            amount = a;
        }
        
        public int compareTo(Pair p) {
            return itemID - p.itemID;
        }
        
        public String toString() {
            return itemID + " " + amount;
        }
    }
    
    public static class Offer {
        Pair[] items;
        int cost;
        
        public Offer(int[] i, int[] n, int c) {
            items = new Pair[5];
            
            for (int a = 0; a < i.length; a++) {
                items[a] = new Pair(i[a], n[a]);
            }
            Arrays.sort(items);
            cost = c;
        }
        
        public String toString() {
            return Arrays.toString(items) + " " + cost;
        }
    }

}
