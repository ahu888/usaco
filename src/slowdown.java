import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class slowdown {

    public static void main(String[] args) throws Exception {
        //BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter w = new PrintWriter(System.out);
        
        BufferedReader r = new BufferedReader(new FileReader("slowdown.in"));
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));

        int n = Integer.parseInt(r.readLine());
        
        double currentTime = 0;
        double currentDist = 0; //in metres
        long speed = 1; //denominator of speed
        
        //HashSet<Integer> timeStops = new HashSet<Integer>();
        //HashSet<Integer> distStops = new HashSet<Integer>();
        
        ArrayList<Integer> timeStops = new ArrayList<Integer>();
        ArrayList<Integer> distStops = new ArrayList<Integer>();
        
        
        
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            if (inputs[0].equals("T")) {
                timeStops.add(Integer.parseInt(inputs[1]));
            } else {
                distStops.add(Integer.parseInt(inputs[1]));
            }
        }
        
        Collections.sort(timeStops);
        Collections.sort(distStops);
        
        int timeIndex = 0;
        int distIndex = 0;
        
        while (currentDist < 1000) {
            if (timeIndex >= timeStops.size() && !(distIndex >= distStops.size())) {
                //System.out.println("distance valid but no more time restriction");
                //go to dist index
                double distanceNeeded =  (distStops.get(distIndex) - currentDist);
                currentTime += distanceNeeded * speed;
                currentDist = distStops.get(distIndex);
                distIndex++;
                speed++;
                continue;
            }
            if (!(timeIndex >= timeStops.size()) && (distIndex >= distStops.size())) {
                //System.out.println("time valid but no more distance restrictions");
                double timeTraveled = timeStops.get(timeIndex) - currentTime;
                currentDist += timeTraveled/speed;
                currentTime = timeStops.get(timeIndex);
                timeIndex++;
                speed++;
                continue;
            }
            if (timeIndex >= timeStops.size() && (distIndex >= distStops.size())) {
                //System.out.println("no more left");
                //we just go to the end
                currentTime += (1000 - currentDist) * speed;
                currentDist = 1000;
                continue;
            }
            
            //check if you will reach time index first or dist index first
            //first check how much time it takes to reach distance
            double distanceNeeded = distStops.get(distIndex) - currentDist;
            
            double timeNeeded = distanceNeeded * speed + currentTime;
            if (timeStops.get(timeIndex) < timeNeeded) { //time index hppens first
                //System.out.println("timeindex happesn first");
                currentDist += (double) 1/ speed * (timeStops.get(timeIndex) - currentTime);
                currentTime = timeStops.get(timeIndex);
                timeIndex++;
                speed++;
            } else if (timeNeeded < timeStops.get(timeIndex)) { //distance index happens first
                //System.out.println("distance happens first");
                currentDist = distStops.get(distIndex);
                currentTime += distanceNeeded * speed;
                distIndex++;
                speed++;
            } else if (timeNeeded == timeStops.get(timeIndex)){ //they are equal
                //System.out.println("both tiemand index happens");
                currentDist = distStops.get(distIndex);
                currentTime = timeStops.get(timeIndex);
                distIndex++;
                timeIndex++;
                speed += 2;
            }
        }
        
        w.println((long) (currentTime + 0.5));
        
        
        w.flush();
    }

}
