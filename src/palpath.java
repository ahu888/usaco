import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;


public class palpath {
    static char[][] grid;
    static HashSet<String> palindromes = new HashSet<String>();
    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("palpath.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));

        int n = Integer.parseInt(r.readLine());
        
        grid = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            grid[i] = r.readLine().toCharArray();
        }
        
        search(0, 0, new StringBuilder().append(grid[0][0]));
        w.println(palindromes.size());
        w.flush();
    }
    
    public static void search(int r, int c, StringBuilder chars) {
        if (r + 1 < grid.length) {
            chars.append(grid[r + 1][c]);
            search(r + 1, c, chars);
            chars.deleteCharAt(chars.length() - 1);
        }
        if (c + 1 < grid.length) {
            chars.append(grid[r][c + 1]);
            search(r, c + 1, chars);
            chars.deleteCharAt(chars.length() - 1);
        }
        if (r == grid.length - 1 && c == grid.length - 1) {
            //check palindrome
            if (isPalindrome(chars)) {
                palindromes.add(chars.toString());
            }
        }
    }
    
    public static boolean isPalindrome(StringBuilder s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

}
