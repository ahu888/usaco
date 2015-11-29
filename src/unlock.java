import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class unlock {
    //algorithm is simple, just slide around the pieces and try to check if overlap, you have at most 1000000 possibilities
    //implement impossible,,,,,,,,,,,,,,,hellothis is is the greatest is is siissisiisisii for the histoy of werld
    
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        String[] inputs = r.readLine().split(" ");
        
        int n1 = Integer.parseInt(inputs[0]);
        int n2 = Integer.parseInt(inputs[1]);
        int n3 = Integer.parseInt(inputs[2]);
        
        boolean[][] object1 = new boolean[10][10];
        boolean[][] object2 = new boolean[10][10];
        boolean[][] object3 = new boolean[10][10];

        for (int i = 0; i < n1; i++) {
            inputs = r.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            object1[x][y] = true;
        }
        for (int i = 0; i < n2; i++) {
            inputs = r.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            object2[x][y] = true;
        }
        for (int i = 0; i < n3; i++) {
            inputs = r.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            object3[x][y] = true;
        }
        
        //find width and height of objects
        
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    int x1 = i / 10; //represent top left hand coordinates
                    int y1 = i % 10;
                    int x2 = j / 10;
                    int y2 = j % 10;
                    int x3 = k / 10;
                    int y3 = k % 10;
                    
                    
                }
            }
        }
    }

}
