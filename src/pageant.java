import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;


public class pageant {
    static int shortest;
    public static void main(String[] args) throws Exception {
      //BufferedReader r = new BufferedReader(new FileReader("butter.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);// TODO Auto-generated method stub

        String[] inputs = r.readLine().split(" ");
        
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);
        
        char[][] hide = new char[n][];
        
        for (int i = 0; i < n; i++) {
            hide[i] = r.readLine().toCharArray();
        }
        
        int[][] tags = new int[n][m];
        
        //foolldl fill spots
        boolean[][] visited = new boolean[n][m];
        
        //long start = System.currentTimeMillis();
        int tag = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (hide[i][j] == 'X' && !visited[i][j]) {
                    fill(hide, tags, i, j, visited, tag);
                    tag++;
                }
            }
        }
        //long mid = System.currentTimeMillis();
        boolean one = false;
        boolean two = false;
        boolean three = false;
        
        shortest = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //bfs from this point
                if (tags[i][j] == 1 && !one) {
                    shortest = Math.min(shortest, search(tags, i, j));
                    one = true;
                } else if (tags[i][j] == 2 && !two) {
                    shortest = Math.min(shortest, search(tags, i, j));
                    two = true;
                } else if (tags[i][j] == 3 && !three) {
                    shortest = Math.min(shortest, search(tags, i, j));
                    three = true;
                } else if (tags[i][j] == 0) {
                    shortest = Math.min(shortest, search(tags, i, j));
                }
            }
        }
        //System.out.println(mid - start);
        //System.out.println(System.currentTimeMillis() - mid);
        
        
        w.println(shortest);
        w.flush();
    }
    
    public static int search(int[][] tags, int r, int c) {
        if (tags[r][c] == 0) {
            int dist1 = Integer.MAX_VALUE;
            int dist2 = Integer.MAX_VALUE;
            int dist3 = Integer.MAX_VALUE;
            
            for (int i = 0; i < tags.length; i++) {
                for (int j = 0; j < tags[0].length; j++) {
                    int distTo = Math.abs(i - r) + Math.abs(j - c);
                    
                    if (tags[i][j] == 1) {
                        dist1 = Math.min(dist1, distTo);
                    } else if (tags[i][j] == 2) {
                        dist2 = Math.min(dist2, distTo);
                    } else if (tags[i][j] == 3) {
                        dist3 = Math.min(dist3, distTo);
                    }
                }
            }
            
            return dist1 + dist2 + dist3;
        } else {
            //int currenttag = tags[r][c];
            int[][] costTo = new int[tags.length][tags[0].length];
            
            for (int i = 0; i < costTo.length; i++) {
                for (int j = 0; j < costTo[0].length; j++) {
                    costTo[i][j] = Integer.MAX_VALUE;
                }
            }
            
            LinkedList<Entry> queue = new LinkedList<Entry>();
            
            queue.add(new Entry(r, c, 0));
            
            int dist1 = Integer.MAX_VALUE;
            int dist2 = Integer.MAX_VALUE;
            int dist3 = Integer.MAX_VALUE;
            
            //int cycle = 0;
            while (!queue.isEmpty()) {
                Entry current = queue.remove();
                r = current.r;
                c = current.c;
                
                
                costTo[r][c] = current.distance;
                
                if (tags[r][c] == 1) {
                    dist1 = Math.min(dist1, current.distance);
                } else if (tags[r][c] == 2) {
                    dist2 = Math.min(dist2, current.distance);
                } else if (tags[r][c] == 3) {
                    dist3 = Math.min(dist3, current.distance);
                }
                
                
                if (r - 1 > -1) {
                    if (tags[r - 1][c] == 0 && costTo[r - 1][c] > current.distance + 1) {
                        queue.add(new Entry(r - 1, c, current.distance + 1));
                        costTo[r - 1][c] = current.distance + 1;
                    } else if (tags[r - 1][c] != 0 && costTo[r - 1][c] > current.distance) {
                        queue.add(new Entry(r - 1, c, current.distance));
                        costTo[r - 1][c] = current.distance;
                    }
                }
                if (r + 1 < tags.length) {
                    if (tags[r + 1][c] == 0 && costTo[r + 1][c] > current.distance + 1) {
                        queue.add(new Entry(r + 1, c, current.distance + 1));
                        costTo[r + 1][c] = current.distance + 1;
                    } else if (tags[r + 1][c] != 0 && costTo[r + 1][c] > current.distance) {
                        queue.add(new Entry(r + 1, c, current.distance));
                        costTo[r + 1][c] = current.distance;
                    }
                }
                if (c - 1 > -1) {
                    if (tags[r][c - 1] == 0 && costTo[r][c - 1] > current.distance + 1) {
                        queue.add(new Entry(r, c - 1, current.distance + 1));
                        costTo[r][c - 1] = current.distance + 1;
                    } else if (tags[r][c - 1] != 0 && costTo[r][c - 1] > current.distance) {
                        queue.add(new Entry(r, c - 1, current.distance));
                        costTo[r][c - 1] = current.distance;
                    }
                }
                if (c + 1 < tags[0].length) {
                    if (tags[r][c + 1] == 0 && costTo[r][c + 1] > current.distance + 1) {
                        queue.add(new Entry(r, c + 1, current.distance + 1));
                        costTo[r][c + 1] = current.distance + 1;
                    } else if (tags[r][c + 1] != 0 && costTo[r][c + 1] > current.distance) {
                        queue.add(new Entry(r, c + 1, current.distance));
                        costTo[r][c + 1] = current.distance;
                    }
                }
            }
            
            
            return dist1 + dist2 + dist3;
        }
        /*
        //boolean[][] visited = new boolean[tags.length][tags[0].length];
        
        */
    }
    
    public static class Entry {
        int r, c, distance;
        
        public Entry(int r, int c, int d) {
            this.r = r;
            this.c = c;
            distance = d;
        }
        
        public String toString() {
            return r + " " + c + " " + distance;
        }
    }
    
    public static void fill(char[][] hide, int[][] tags, int r, int c, boolean[][] visited, int tag) {
        visited[r][c] = true;
        tags[r][c] = tag;
        if (r - 1 > -1 && hide[r - 1][c] == 'X' && !visited[r - 1][c]) {
            fill(hide, tags, r - 1, c, visited, tag);
        }
        if (r + 1 < hide.length && hide[r + 1][c] == 'X' && !visited[r + 1][c]) {
            fill(hide, tags, r + 1, c, visited, tag);
        }
        if (c - 1 > -1 && hide[r][c - 1] == 'X' && !visited[r][c - 1]) {
            fill(hide, tags, r, c - 1, visited,tag);
        }
        if (c + 1 < hide[0].length && hide[r][c + 1] == 'X' && !visited[r][c + 1]) {
            fill(hide, tags, r, c + 1, visited, tag);
        }
    }

}
