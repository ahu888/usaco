import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class lineup {
    static HashSet<Integer> breeds = new HashSet<Integer>();
    //solution is to use 2 sweep line. when you have found an interval containing all breeds you move the left bound to the right until you ahve the smallest point. then if it doesn't work you keep expanding right bound line
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        
        //BufferedReader r = new BufferedReader(new FileReader("blink.in"));
        //PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("blink.out")));

        int n = Integer.parseInt(r.readLine());
        
        Cow[] cows = new Cow[n];
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            cows[i] = new Cow(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            breeds.add(cows[i].breed);
        }
        
        Arrays.sort(cows);
        
        int minDist = Integer.MAX_VALUE;
        
        
        //take a picture between cow i and cow j, inclusive
        ArrayList<Integer> contained = new ArrayList<Integer>();
        Integer[] breedArray = lineup.breeds.toArray(new Integer[lineup.breeds.size()]);
        contained.add(cows[0].breed);
        
        boolean valid = true; //if valid photo
        
        if (contained.size() >= breeds.size()) {
            HashSet<Integer> unique = new HashSet<Integer>();
            for (int a = 0; a < contained.size(); a++) {
                unique.add(contained.get(a));
            }
            
            if (unique.size() != breeds.size()) {
                valid = false;
            }
        } else {
            valid = false;
        }
        /*
        for (int a = 0; a < breeds.length; a++) {
            boolean thisValid = false;
            for (int b = 0; b < contained.size(); b++) {
                if (breeds[a] == contained.get(b)) {
                    thisValid = true;
                }
            }
            if (!thisValid) {
                valid = false;
                break;
            }
        }*/
        
        HashMap<Integer, Integer> unique = new HashMap<Integer, Integer>();
        HashSet<Integer> temp = new HashSet<Integer>();
        
        for (int i = 0; i < breedArray.length; i++) {
            unique.put(breedArray[i], 0);
        }
        unique.put(cows[0].breed, 1);
        
        int i = 0;
        int j = 0;
        
        boolean entered = true;
        outer:
        while (entered) {
            entered = false;
            if (!valid && j < n - 1) {
                entered = true;
                j++;
                contained.add(cows[j].breed);
                unique.put(cows[j].breed, unique.get(cows[j].breed) + 1);
                
                //boolean[] valids = new boolean[breeds.length];
                
                //determine if new group is valid
                //all we have to do is check for the number of unique numbers, if that number == breeds.size() then we're valid
                
                
                Iterator<Integer> counts = unique.values().iterator();
                
                while (counts.hasNext()) {
                    if (counts.next().equals(new Integer(0))) {
                        valid = false;
                        continue outer;
                    }
                }
                valid = true;
                /*
                if (contained.size() >= breeds.size()) {
                    for (int a = 0; a < contained.size(); a++) {
                        temp.add(contained.get(a));
                    }
                    
                    if (temp.size() == breeds.size()) {
                        valid = true;
                    }
                    temp.clear();
                }*/
                
                /*
                for (int a = 0; a < breeds.length; a++) {
                    boolean thisValid = false;
                    for (int b = 0; b < contained.size(); b++) {
                        if (breeds[a].equals(contained.get(b))) {
                            thisValid = true;
                            break;
                        }
                    }
                    if (!thisValid) {
                        valid = false;
                        continue outer;
                    }
                }*/
                //valid = true;
            } else if (valid && i < n - 1) {
                entered = true;
                if (minDist > cows[j].x - cows[i].x) {
                    minDist = cows[j].x - cows[i].x;
                }
                
                i++;
                unique.put(contained.get(0), unique.get(contained.get(0)) - 1);
                if (unique.get(contained.get(0)) == 0) {
                    valid = false;
                }
                contained.remove(0);
                
                
              //determine if new group is valid
                //all we have to do is check for the number of unique numbers, if that number == breeds.size() then we're valid
                /*
                if (contained.size() >= breeds.size()) {
                    for (int a = 0; a < contained.size(); a++) {
                        unique.add(contained.get(a));
                    }
                    
                    if (unique.size() != breeds.size()) {
                        valid = false;
                    }
                    unique.clear();
                } else {
                    valid = false;
                }*/
                /*
                for (int a = 0; a < breeds.length; a++) {
                    boolean thisValid = false;
                    for (int b = 0; b < contained.size(); b++) {
                        if (breeds[a].equals(contained.get(b))) {
                            thisValid = true;
                            break;
                        }
                    }
                    if (!thisValid) {
                        valid = false;
                        continue outer;
                    }
                }
                valid = true;*/
            }
        }
        
        if (minDist == Integer.MAX_VALUE) {
            w.println(0);
        } else 
        w.println(minDist);
        w.flush();
    }
    
    public static class Cow implements Comparable<Cow> {
        int x, breed;
        
        public Cow(int a, int b) {
            x = a;
            breed = b;
        }

        @Override
        public int compareTo(Cow o) {
            return x - o.x;
        }
        
        public String toString() {
            return x +" " + breed;
        }
    }

}
