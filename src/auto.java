import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;


public class auto {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("auto.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));

        String[] inputs = r.readLine().split(" ");
        int wordCount = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        
        StringIndex[] words = new StringIndex[wordCount];
        
        for (int i = 0; i < wordCount; i++) {
            words[i] = new StringIndex(i + 1, r.readLine());
        }
        
        Arrays.sort(words);
        
        
        char[][] chars = new char[wordCount][];
        for (int i = 0; i < wordCount; i++) {
            chars[i] = words[i].contents.toCharArray();
        }
        
        int[] positions = new int[26]; //starts with letter, such as 0 = a, 1 = b;
        for (int i = 0; i < 26; i++) {
            positions[i] = -1;
        }
        for (int i = 0; i < wordCount; i++) {
            if (positions[chars[i][0] - 'a'] == -1) {
                positions[chars[i][0] - 'a'] = i;
            }
        }
        
        long time = System.currentTimeMillis();
        mostouter:
        for (int i = 0; i < n; i++) {
            inputs = r.readLine().split(" ");
            int index = Integer.parseInt(inputs[0]);
            char[] wordChars = inputs[1].toCharArray();
            
            //find words with wordChars
            int start = -1;
            int end = -1;
            int current = 0;
            outer:
            for (int j = positions[wordChars[0] - 'a']; j < wordCount; j++) {
                boolean good = true;
                for (int k = 0; k < wordChars.length; k++) {
                    if (k >= chars[j].length || wordChars[k] != chars[j][k]) {
                        good = false;
                        if (end != -1) {
                            w.println(-1);
                            continue mostouter;
                        }
                        continue outer;
                    }
                }
                if (good && start == -1) {
                    start = j;
                }
                if (good) {
                    end = j;
                    current++;
                    if (current == index) {
                        w.println(words[j].originalIndex);
                        continue mostouter;
                    }
                }
            }
            w.println(-1);
            /*
            if (start + index - 1 <= end && start != -1 && end != -1) {
                w.println(words[start + index - 1].originalIndex);
            } else {
                w.println(-1);
            }*/
        }
        long mid = System.currentTimeMillis();
        
        
        w.flush();
        System.out.println(mid - time);
        //System.out.println(System.currentTimeMillis() - mid);
    }
    
    public static class StringIndex implements Comparable<StringIndex>{
        int originalIndex;
        String contents;
        
        public StringIndex(int o, String c) {
            originalIndex = o;
            contents = c;
        }
        
        public int compareTo(StringIndex s) {
            return (int) contents.compareTo(s.contents);
        }
    }

}
