import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class delivery {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);

        int n = Integer.parseInt(r.readLine());
        
        Vertex[] vertices = new Vertex[n * 5];
        
        
        //HashMap<Integer, HashMap<Integer, Farm>> matrix = new HashMap<Integer, HashMap<Integer, Farm>>();
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            vertices[count] = new Vertex(x, y, true);
            count++;
            
            vertices[i].number = i;
        }
        
        for (int i = 0; i < n; i++) {
            if (vertices[i] == null) {
                break;
            }
            
            int x = vertices[i].x;
            int y = vertices[i].y;
            boolean valid1 = true;
            boolean valid2 = true;
            boolean valid3 = true;
            boolean valid4 = true;
            for (int j = 0; j < vertices.length; j++) {
                if (vertices[j] == null) continue;
                if (vertices[j].x == x - 1 && vertices[j].y == y) {
                    valid1 = false;
                }
                if (vertices[j].x == x + 1 && vertices[j].y == y) {
                    valid2 = false;
                }
                if (vertices[j].x == x && vertices[j].y == y - 1) {
                    valid3 = false;
                }
                if (vertices[j].x == x && vertices[j].y == y + 1) {
                    valid4 = false;
                }
            }
            
            if (valid1) {
                vertices[count] = new Vertex(x - 1, y, false);
                count++;
            }
            if (valid2) {
                vertices[count++] = new Vertex(x + 1, y, false);
            }
            if (valid3) {
                vertices[count++] = new Vertex(x, y - 1, false);
            }
            if (valid4) {
                vertices[count++] = new Vertex(x, y + 1, false);
            }
        }
        
        int[][] distances = new int[n * 5][n * 5];
        
        for (int i = 0; i < n * 5; i++) {
            for (int j = 0; j < n * 5; j++) {
                distances[i][j] = 1000000000;
                if (i == j) {
                    distances[i][j] = 0;
                }
            }
        }
        
        for (int i = 0; i < distances.length; i++) {
            if (vertices[i] == null) continue;
            for (int j = 0; j < distances.length; j++) {
                if (vertices[j] == null || i == j) continue;
                if (vertices[i].x > vertices[j].x) continue;
                /*
                if (vertices[i].x > vertices[j].x || vertices[i].y > vertices[j].y) {
                    continue;
                }*/
                //check if we can go from i to j
                boolean valid1 = true; //going up/down then right
                boolean valid2 = true; //going right then up/down
                for (int k = 0; k < distances.length; k++) {
                    if (vertices[k] == null) continue;
                    if (i != k && k != j && i != j) {
                        
                        if (!valid1 && !valid2) {
                            break;
                        }
                        //vertices[i].x <= vertices[j].x will always evaluate to true
                        if (vertices[i].x == vertices[j].x && vertices[k].x == vertices[i].x) {
                            if (vertices[i].y < vertices[j].y) {
                                if (vertices[k].y > vertices[i].y && vertices[k].y < vertices[j].y) {
                                    valid1 = false;
                                    valid2 = false;
                                    break;
                                }
                            } else if (vertices[i].y > vertices[j].y) {
                                if (vertices[k].y > vertices[j].y && vertices[k].y < vertices[i].y) {
                                    valid1 = false;
                                    valid2 = false;
                                    break;
                                }
                            }
                        } else if (vertices[i].y < vertices[j].y) {
                            if (vertices[k].x == vertices[i].x && vertices[k].y > vertices[i].y && vertices[k].y <= vertices[j].y || vertices[k].y == vertices[j].y && vertices[k].x >= vertices[i].x && vertices[k].x < vertices[j].x) {
                                valid1 = false;
                            }
                            if (vertices[k].y == vertices[i].y && vertices[k].x > vertices[i].x && vertices[k].x <= vertices[j].x || vertices[k].x == vertices[j].x && vertices[k].y >= vertices[i].y && vertices[k].y < vertices[j].y) {
                                valid2 = false;
                            }
                        } else if (vertices[i].y > vertices[j].y) {
                            if (vertices[k].x == vertices[i].x && vertices[k].y < vertices[i].y && vertices[k].y >= vertices[j].y || vertices[k].y == vertices[j].y && vertices[k].x >= vertices[i].x && vertices[k].x < vertices[j].x) {
                                valid1 = false;
                            }
                            if (vertices[k].y == vertices[i].y && vertices[k].x > vertices[i].x && vertices[k].x <= vertices[j].x || vertices[k].x == vertices[j].x && vertices[k].y <= vertices[i].y && vertices[k].y > vertices[j].y) {
                                valid2 = false;
                            }
                        } else {
                            if (vertices[k].y == vertices[i].y && vertices[k].x > vertices[i].x && vertices[k].x < vertices[j].x) {
                                valid1 = false;
                                valid2 = false;
                                break;
                            }
                            
                        }
                        /*
                        if (vertices[k].y == vertices[i].y && vertices[k].x > vertices[i].x && vertices[k].x <= vertices[j].x || vertices[k].x == vertices[j].x && (vertices[j].y > vertices[i].y && vertices[k].y >= vertices[i].y && vertices[k].y < vertices[j].y || vertices[j].y < vertices[i].y && vertices[k].y <= vertices[i].y && vertices[k].y > vertices[j].y)) {
                            valid2 = false;
                        }
                        if (vertices[k].x == vertices[i].x && vertices[k].y > vertices[i].y && vertices[k].y <= vertices[j].y || vertices[k].y == vertices[j].y && vertices[k].x >= vertices[i].x && vertices[k].x < vertices[j].x && (vertices[j].y > vertices[i].y && vertices[k].y >= vertices[i].y && vertices[k].y < vertices[j].y || vertices[j].y < vertices[i].y && vertices[k].y <= vertices[i].y && vertices[k].y > vertices[j].y)) {
                            valid1 = false;
                        }*/
                    }
                }
                
                if (valid1 || valid2) {
                    distances[i][j] = Math.abs(vertices[i].x - vertices[j].x) + Math.abs(vertices[i].y - vertices[j].y);
                    distances[j][i] = distances[i][j];
                } else {
                    distances[i][j] = 1000000000;
                }
            }
        }
        
        for (int k = 0; k < distances.length; k++) {
            if (vertices[k] != null && !vertices[k].farm) {
                for (int i = 0; i < distances.length; i++) {
                    if (vertices[i] != null)
                    for (int j = 0; j < distances.length; j++) {
                        if (vertices[j] != null )
                        distances[i][j] = Math.min(distances[i][k] + distances[k][j], distances[i][j]);
                    }
                }
            }
        }
        
        long answer = 0;
        for (int i = 0; i < n - 1; i++) {
            if (distances[i][i + 1] == 1000000000) {
                answer = -1;
                break;
            }
            answer += distances[i][i + 1];
        }
        if (distances[0][n - 1] != 1000000000 && answer != -1)
            answer += distances[0][n - 1];
        else
            answer = -1;
        
        w.println(answer);
        /*
        //check if the farm is accessible by checking all four sides
        
        boolean valid = true;
        for (int i = 0; i < n; i++) {
            int x = farms[i].x;
            int y = farms[i].y;
            int sum = 0;
            if (matrix.get(x + 1) != null && matrix.get(x + 1).get(y) != null && matrix.get(x + 1).get(y).number > farms[i].number) {
                sum++;
            }
            if (matrix.get(x - 1) != null && matrix.get(x - 1).get(y) != null && matrix.get(x - 1).get(y).number > farms[i].number) {
                sum++;
            }
            if (matrix.get(x).get(y + 1) != null && matrix.get(x).get(y + 1).number > farms[i].number) {
                sum++;
            }
            if (matrix.get(x).get(y - 1) != null && matrix.get(x).get(y - 1).number > farms[i].number) {
                sum++;
            }
            if (sum == 4) {
                valid = false;
            }
        }
        
        if (!valid) {
            w.println(-1);
            w.flush();
            System.exit(0);
        }
        
        /*
        long answer = 0;
        
        for (int i = 0; i < n - 1; i++) {
            answer += search(farms[i], farms[i + 1]);
        }
        answer += search(farms[0], farms[n - 1]);
        */
        //w.println(answer);
        w.flush();
    }
    
    /*
     * how to check distance:
     * let 0 mean nothing and 1 mean a farm
     * 2 signifies start/end barn
     *           
     * 100000002
     * 010000000
     * 001000000
     * 000100000
     * 000010000
     * 000001000
     * 000000100
     * 000000010
     * 200000001
     *          
     * in this case, we see that we can't cross rectngle from the 2 barns
     * 
     * we check this by seeing if either scanning x or scanning y, if there is no break then we must expand rectangle
     * if both are scanned and we find no breaks then we must expand in two directions
     */
    
    
    public static class Vertex {
        int x, y;
        int number = -1;
        boolean farm;
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        ArrayList<Integer> distances = new ArrayList<Integer>();
        
        
        public Vertex(int a, int b, boolean farm) {
            x = a;
            y = b;
            this.farm = farm;
        }
        
        public String toString() {
            return x + " " + y + " " + number;
        }
    }

}
