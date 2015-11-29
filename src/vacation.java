import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class vacation {
    static final long infinity = 1000000000000000000L;
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("vacation.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("vacation.out")));

        String[] inputs = r.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]); //number farms
        int m = Integer.parseInt(inputs[1]); //number flights
        int hubs = Integer.parseInt(inputs[2]); //number hub
        int q = Integer.parseInt(inputs[3]); //number of requests
        
        long[][] costs = new long[n + 1][n + 1];
        long[][] costsHubIncluded = new long[n + 1][n + 1];
        //boolean[][] hubIncluded = new boolean[n + 1][n + 1];
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs.length; j++) {
                costs[i][j] = infinity;
                costsHubIncluded[i][j] = infinity;
            }
        }
        
        
        for (int i = 0; i < m; i++) {
            inputs = r.readLine().split(" ");
            int u = Integer.parseInt(inputs[0]);
            int v = Integer.parseInt(inputs[1]);
            int d = Integer.parseInt(inputs[2]);
            
            costs[u][v] = Math.min(costs[u][v], d);
            if (u <= hubs || v <= hubs) {
                costsHubIncluded[u][v] = Math.min(costsHubIncluded[u][v], costs[u][v]);
            }
        }
        
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    
                    if (costs[i][j] > costs[i][k] + costs[k][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        //System.out.println("non hub set " + costs[i][j]);
                    }
                    
                    /*
                    if (costsHubIncluded[i][j] > costsHubIncluded[i][k] + costsHubIncluded[k][j]) {
                        costsHubIncluded[i][j] = costsHubIncluded[i][k] + costsHubIncluded[k][j];
                        System.out.println("3nd cost includded " + costsHubIncluded[i][j]);
                    }*/
                    
                }
            }
        }
        
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if ((i <= hubs || j <= hubs) && costs[i][j] != infinity && costsHubIncluded[i][j] > costs[i][j]) {
                        costsHubIncluded[i][j] = costs[i][j];
                        //System.out.println("hub set 0 " + costs[i][j]);
                    }/*
                    if ((i <= hubs || k <= hubs) && costs[i][k] != infinity && costsHubIncluded[i][k] > costs[i][k]) {
                        costsHubIncluded[i][k] = costs[i][k];
                        System.out.println("hub set 1 " + costs[i][k]);
                    }
                    if ((j <= hubs || k <= hubs) && costs[k][j] != infinity && costsHubIncluded[k][j] > costs[k][j]) {
                        costsHubIncluded[k][j] = costs[k][j];
                        System.out.println("hub set 2 " + costs[k][j]);
                    }*/
                    
                    //if (costsHubIncluded[i][j] > costs[i][k] + costs[k][j] && (i <= hubs || j <= hubs || k <= hubs)) {
                        //costsHubIncluded[i][j] = costs[i][k] + costs[k][j];
                    //}
                    
                    if (costsHubIncluded[i][j] > costsHubIncluded[i][k] + costs[k][j]) {
                        costsHubIncluded[i][j] = costsHubIncluded[i][k] + costs[k][j];
                        //System.out.println("1nd cost includded " + costsHubIncluded[i][j]);
                    }
                    
                    if (costsHubIncluded[i][j] > costs[i][k] + costsHubIncluded[k][j]) {
                        costsHubIncluded[i][j] = costs[i][k] + costsHubIncluded[k][j];
                        //System.out.println("2nd cost includded " + costsHubIncluded[i][j]);
                    } 
                    /*
                    if (costsHubIncluded[i][j] > costsHubIncluded[i][k] + costsHubIncluded[k][j]) {
                        costsHubIncluded[i][j] = costsHubIncluded[i][k] + costsHubIncluded[k][j];
                        System.out.println("3nd cost includded " + costsHubIncluded[i][j]);
                    }*/
                    
                }
            }
        }
        
        int possible = 0;
        long sum = 0;
        for (int i = 0; i < q; i++) {
            inputs = r.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            
            if (costsHubIncluded[a][b] != infinity) {
                possible++;
                sum += costsHubIncluded[a][b];
            }
        }
        
        w.println(possible);
        w.println(sum);
        w.flush();
    }

}
