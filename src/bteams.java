import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class bteams {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("bteams.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("bteams.out")));

        int[] levels = new int[12];
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            levels[i] = Integer.parseInt(r.readLine());
            sum += levels[i];
        }
        
        //System.out.println(sum);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < 12; i++) {
            for (int j = i + 1; j < 12; j++) {
                for (int k = j + 1; k < 12; k++) {
                    for (int l = 0; l < 12; l++) {
                        if (l != i && l != k && l != j) {
                            for (int m = l + 1; m < 12; m++) {
                                if (m != i && m != k && m != j) {
                                    for (int n = m + 1; n < 12; n++) {
                                        if (n != i && n != k && n != j) {
                                            for (int o = 0; o < 12; o++) {
                                                if (o != i && o != k && o != j && o != l && o != m && o != n) {
                                                    for (int p = o + 1; p < 12; p++) {
                                                        if (p != i && p != j && p != k && p != l && p != m && p != n) {
                                                            for (int q = p + 1; q < 12; q++) {
                                                                if (q != i && q != k && q != j && q != l && q != m && q != n) {
                                                                    for (int a = 0; a < 12; a++) {
                                                                        if (a != i && a != j && a != k && a != l && a != m && a != n && a != o && a != p && a != q) {
                                                                            for (int b = 0; b < 12; b++) {
                                                                                if (b != i && b != j && b != k && b != l && b != m && b != n && b != o && b != p && b != q && b != a) {
                                                                                    for (int c = 0; c < 12; c++) {
                                                                                        if (c != i && c != j && c != k && c != l && c != m && c != n && c != o && c != p && c != q && c != a && c != b) {
                                                                                            int sum1 = levels[i] + levels[j] + levels[k];
                                                                                            int sum2 = levels[l] + levels[m] + levels[n];
                                                                                            int sum3 = levels[o] + levels[p] + levels[q];
                                                                                            int sum4 = levels[a] + levels[b] + levels[c];
                                                                                            //int total = sum1 + sum2 + sum3 + sum4;
                                                                                            int max = Math.max(sum1, Math.max(sum2, Math.max(sum3, sum4)));
                                                                                            int min = Math.min(sum1, Math.min(sum2, Math.min(sum3, sum4)));
                                                                                            int diff = max - min;
                                                                                            if (diff < minDiff) {
                                                                                                minDiff = diff;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        w.println(minDiff);
        w.flush();
    }

}
