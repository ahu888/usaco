import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

/*
ID: farmersrice
LANG: JAVA
TASK: spin
*/

public class spin {

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader("spin.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);

        Wheel[] wheels = new Wheel[5];
        
        for (int i = 0; i < 5; i++) {
            String input = r.readLine();
            String[] inputs = input.split(" ");
            
            int speed = Integer.parseInt(inputs[0]);
            int numintervals = Integer.parseInt(inputs[1]);
            
            int[] intstart = new int[numintervals];
            int[] intend = new int[numintervals];
            
            int counter = 0;
            for (int j = 0; j < numintervals * 2; j += 2) {
                
                intstart[counter] = Integer.parseInt(inputs[2 + j]);
                intend[counter] = Integer.parseInt(inputs[3 + j]);
                counter++;
            }
            
            wheels[i] = new Wheel(speed, intstart, intend);
        }
        
        int[] wheeldeg = new int[5];
        
        boolean[] available = new boolean[360];
        for (int i = 0; i < 360; i++) {
            available[i] = true;
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 360; j++) {
                if (wheels[i].available[j] == false) {
                    available[j] = false;
                }
            }
        }
        
        for (int i = 0; i < 360; i++) {
            if (available[i]) {
                w.println(0);
                w.flush();
                r.close();
                w.close();
                System.exit(0);
            }
        }
        
        HashMap<Combo, Combo> seen = new HashMap<Combo, Combo>();
        
        
        Combo current = new Combo(wheeldeg);
        
        int iterations = 0;
        while (seen.containsValue(current) == false) {
            seen.put(current, current);
            
            for (int i = 0; i < 5; i++) {
                int prevpos = wheeldeg[i];
                wheeldeg[i] += wheels[i].speed;
                if (wheeldeg[i] > 359) {
                    wheeldeg[i] = wheeldeg[i] % 360;
                }
                int diff = wheeldeg[i] - prevpos;
                
                //boolean[] excess = new boolean[Math.abs(diff)];
                
                if (diff < 0)
                    diff = 360 + diff;
                
                wheels[i].available = rotate(wheels[i].available, diff);
                /*
                if (diff < 0) {
                    diff = Math.abs(diff);
                    for (int j = 0; j < diff; j++) {
                        excess[j] = wheels[i].available[j];
                    }
                    for (int j = diff; j < 360; j++) {
                        wheels[i].available[j - diff] = wheels[i].available[j];
                    }
                    for (int j = 360 - diff; j < 360; j ++) {
                        int counter = 0;
                        wheels[i].available[j] = excess[counter++];
                    }
                } else if (diff > 0) {
                    for (int j = 360 - diff; j < 360; j ++) {
                        int counter = 0;
                        excess[counter++] = wheels[i].available[j];
                    }
                    for (int j = 0; j < 360 - diff; j++) {
                        wheels[i].available[j + diff] = wheels[i].available[j];
                    }
                    for (int j = 0; j < diff; j++) {
                        wheels[i].available[j] = excess[j];
                    }
                }*/
            }
            
            available = new boolean[360];
            for (int i = 0; i < 360; i++) {
                available[i] = true;
            }
            
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 360; j++) {
                    if (wheels[i].available[j] == false) {
                        available[j] = false;
                    }
                }
            }
            
            for (int i = 0; i < 360; i++) {
                if (available[i]) {
                    w.println(iterations + 1);
                    w.flush();
                    r.close();
                    w.close();
                    System.exit(0);
                }
            }
            iterations++;
            current = new Combo(wheeldeg);
        }
        w.println("none");
        w.flush();
        r.close();
        w.close();
        System.exit(0);
    }
    
    public static boolean[] rotate(boolean[] nums, int distance) {
        if (distance > nums.length) 
            distance %= nums.length;
     
        boolean[] result = new boolean[nums.length];
     
        for(int i = 0; i < distance; i++){
            result[i] = nums[nums.length-distance+i];
        }
     
        int j = 0;
        for (int i = distance; i < nums.length; i++){
            result[i] = nums[j];
            j++;
        }
     
        return result;
    }
    

    public static class Combo {
        int[] contents;
        
        public Combo(int[] c) {
            contents = new int[c.length];
            for (int i = 0; i < c.length; i++) {
                contents[i] = c[i];
            }
        }
        
        public boolean equals(Object o) {
            if (o.toString().equals(toString())) {
                return true;
            }
            return false;
        }
        
        public String toString() {
            return Arrays.toString(contents);
        }
    }
    public static class Wheel {
        int speed;
        //int[] intstart;
        //int[] intend;
        //int degrees = 0;
        boolean[] available = new boolean[360];
        
        public Wheel(int speed, int[] intstart, int[] intend) {
            this.speed = speed;
            //this.intstart = intstart;
            //this.intend = intend;
            Arrays.fill(available, false);
            for (int i = 0; i < intstart.length; i++) {
                int start = intstart[i];
                int length = intend[i];
                int end = start + length;
                
                if (end >= 360) {
                    end = end % 360;
                    
                    for (int j = start; j < 360; j++) {
                        available[j] = true;
                    }
                    for (int j = 0; j <= end; j++) {
                        available[j] = true;
                    }
                } else {
                    for (int j = start; j <= end; j++) {
                        available[j] = true;
                    }
                }
                
                /*
                if (intstart[i] > intend[i]) {
                    for (int j = intstart[i]; j < 360; j++) {
                        available[j] = true;
                    }
                    for (int j = 0; j <= intend[i]; j++) {
                        available[j] = true;
                    }
                /*} else if (intstart[i] == intend[i]) {
                    for (int j = 0; j < 360; j++) {
                        available[j] = true;
                    }
                } else {
                    for (int j = intstart[i]; j <= intend[i]; j++) {
                        available[j] = true;
                    }
                }
                */
            }
        }
        
        public String toString() {
            String temp = "";
            for (int i = 0; i < available.length; i++) {
                if (available[i])
                    temp += 1;
                else temp += 0;
            }
            return speed + " " + temp; //+ Arrays.toString(intstart) + Arrays.toString(intend);
        }
    }
}
