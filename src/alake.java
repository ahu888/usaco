import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class alake {
    static ArrayList<Level> levels;
    static long time;
    static int levelsfilled;
    
    static int lowestheight = Integer.MAX_VALUE;
    //static Node lowest;
    static Level[] original;
    
    static int index = 0;
    static int[] fillorder;
    
    static boolean lr;
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter w = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        
        original = new Level[n];
        levels = new ArrayList<Level>(n);
        int maxheight = 0;
        int maxindex = 0;
        int minheight = Integer.MAX_VALUE;
        int minindex = 0;
        for (int i = 0; i < n; i++) {
            String[] inputs = r.readLine().split(" ");
            //levels[i] = new Level(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), i);
            levels.add(new Level(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), i));
            original[i] = levels.get(i);
            if (i > 0) {
                levels.get(i - 1).right = levels.get(i);
                levels.get(i).left = levels.get(i - 1);
            }
            
            if (levels.get(i).height + 1 > maxheight) {
                maxheight = levels.get(i).height + 1;
                maxindex = i;
            }
            if (levels.get(i).height < minheight) {
                minheight = levels.get(i).height;
                minindex = i;
            }
        }
        
        if (n == 0) {
            System.out.println(levels.get(0).width);
            System.exit(0);
        }
        
        search(levels.get(minindex));
        
        
        
        
        
        /*
        //fill in the tree
        Node root = new Node(maxindex, 0, n);
        
        root.findChildren();
        
        fillorder = new int[n];
        for (int i = 0; i < n; i++) {
            fillorder[i] = -1;
        }
        
        fillorder[0] = lowest.number;
        
        lowest.traversed = true;
        
        Node current = lowest;
        
        if (current.parent.left == current) {
            lr = true; //true for left false for right
        } else {
            lr = false;
        }
        
        searchTree(current, true);*/
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        int current = minindex;
        long totalvolume = 0;
        levelsfilled = 1;
        time = levels[minindex].width;
        
        levels[minindex].filltime = levels[minindex].width;
        levels[minindex].filled = true;
        
        int levelsleft = 0;
        if (minindex - 1 < 0) {
            levelsleft = levels[minindex + 1].height - levels[minindex].height;
        } else if (minindex + 1 >= n) {
            levelsleft = levels[minindex - 1].height - levels[minindex].height;
        } else {
            levelsleft = Math.max(levels[minindex - 1].height, levels[minindex + 1].height) - levels[minindex].height;
        }
        levelsleft--;
        time += levelsleft * levels[minindex].width;
        levels[minindex].waterheight += levelsleft + 1;

        levels[current].fillLeft();
        levels[current].fillRight();

        for (int i = 0; i < n; i++) {
            w.println(levels[i].filltime);
        }
        w.flush();
        
9
1 2
1 6
1 4
1 8
1 1
1 9
1 7
1 3
1 5
*/
        
        for (int i = 0; i < n; i++) {
            w.println(original[i].filltime);
        }
        w.flush();
    }
    
    public static void search(Level current) {
        int lheight = 10000000, rheight = 10000000;
        /*
        if (current.left != null && !current.left.filled && current.left.height < current.height) {
            search(current.left);
            return;
        }
        if (current.right != null && !current.right.filled && current.right.height < current.height) {
            search(current.right);
            return;
        }*/
        if (current.left != null) {
            lheight = current.left.height;
        }
        if (current.right != null) {
            rheight = current.right.height;
        }
        /*
        if (lheight == -1 && current.right != null && !current.right.filled) { //fill to right
            time += current.width * (current.right.height - current.height);
            current.filltime = time - current.width * (current.right.height - current.height - 1);
            current.filled = true;
            //current.waterheight = current.right.height;
            levels.remove(current);
            current.right.width += current.width;
            current.right.left = current.left;
            search(current.right);
        } else if (rheight == -1 && current.right != null && !current.left.filled) { //fill to left
            time += current.width * (current.left.height - current.height);
            current.filltime = time - current.width * (current.left.height - current.height - 1);
            current.filled = true;
            //current.waterheight = current.right.height;
            levels.remove(current);
            current.left.width += current.width;
            current.left.right = current.right;
            search(current.left);
        } else { *///fill to lowest
        Level lowest = null;
        if (lheight > rheight && current.right != null && !current.right.filled) {
            lowest = current.right;
        } else if (current.left != null && !current.left.filled) {
            lowest = current.left;
        } else {//if (!current.filled) { //fill to one higher than this level
            time += current.width;
            current.filltime = time;
            current.filled = true;
            current.height++;
            return;
        } //else {
            //return;
        //}
        
        fill(current, lowest);
        
        lheight = 10000000;
        rheight = 10000000;
        
        if (lowest.left != null) {
            lheight = lowest.left.height;
        }
        if (lowest.right != null) {
            rheight = lowest.right.height;
        }
        
        if (lheight < rheight) {
            while (lowest.left != null && lowest.left.height < lowest.height) {
                lowest = lowest.left;
            }
        } else if (rheight < lheight) {
            while (lowest.right != null && lowest.right.height < lowest.height) {
                lowest = lowest.right;
            }
        }
        search(lowest);
            
        //}
    }
    
    public static void fill(Level current, Level lowest) {
        time += current.width * (lowest.height - current.height);
        current.filltime = time - current.width * (lowest.height - current.height - 1);
        current.filled = true;
        //current.waterheight = current.right.height;
        levels.remove(current);
        lowest.width += current.width;
        if (lowest == current.left) {
            lowest.right = current.right;
        } else if (lowest == current.right) {
            lowest.left = current.left;
        }
    }
    
    
    public static class Level {
        int width, height;
        long filltime = 0;
        boolean filled = false;
        int waterheight;
        int index;
        Level left, right;
        
        public Level(int w, int h, int i) {
            width = w;
            height = h;
            index = i;
            waterheight = height;
        }
        
        long calculateVolume(long h) {
            return width * (h - height);
        }
        
        public String toString() {
            return width + " " + height + " " + filltime;
        }
        
        /*
        public boolean checkLeft() {
            if (index - 1 < 0) {
                return false;
            }
            if (levels[index - 1].height < waterheight && !levels[index - 1].filled || index + 1 < levels.length && levels[index + 1].filled && filled || filled && index + 1 >= levels.length) {
                return true;
            }
            return false;
        }
        
        public boolean checkRight() {
            if (index + 1 >= levels.length) {
                return false;
            }
            if (levels[index + 1].height < waterheight && !levels[index + 1].filled || index - 1 >= 0 && levels[index - 1].filled && filled || filled && index - 1 < 0) {
                return true;
            }
            return false;
        }
        
        public void fillLeftTo(int h) {
            if (index - 1 < 0) return;
            if (h < levels[index - 1].waterheight) return;
            
            time += levels[index - 1].width * (h - levels[index - 1].waterheight);
            levels[index - 1].waterheight = h;
            if (index - 1 >= 0) {
                levels[index - 1].fillLeftTo(h);
            }
        }
        
        public void fillRightTo(int h) {
            if (index + 1 >= levels.length) return;
            if (h < levels[index + 1].waterheight) return;
            
            time += levels[index + 1].width * (h - levels[index + 1].waterheight);
            levels[index + 1].waterheight = h;
            if (index + 1 < levels.length) {
                levels[index + 1].fillRightTo(h);
            }
        }
        
        public void fillLeft() {
            if (checkLeft()) { //if we are good to go
                if (!levels[index - 1].checkLeft() && !levels[index - 1].filled) { //fill the level
                    if (levelsfilled == levels.length) {
                        return;
                    }
                    int fillheight = 0;
                    if (index - 2 < 0) {
                        fillheight = waterheight;
                    } else {
                        fillheight = Math.max(levels[index - 2].waterheight, waterheight);
                    }
                    if (fillheight < levels[index - 1].height + 1) {
                        fillheight = levels[index - 1].height + 1;
                    }
                    
                    int diff = fillheight - levels[index - 1].waterheight;
                    time += diff * levels[index - 1].width;
                    levels[index - 1].filltime = time - (diff - 1) * levels[index - 1].width;
                    levels[index - 1].waterheight = fillheight;
                    levelsfilled++;
                    if (levelsfilled == levels.length) {
                        return;
                    }
                } else if (levels[index - 1].checkLeft()) {
                    levels[index - 1].fillLeft();
                }
                if (!checkRight()) { //fill this level
                    fillCurrent();
                }
            } else if (!checkRight()) { //fill this level
                fillCurrent();
            }
        }
        
        public void fillCurrent() {
            if (levelsfilled == levels.length) {
                return;
            }
            int fillheight = 0;
            if (index - 1 < 0) {
                fillheight = levels[index + 1].waterheight;
            } else if (index + 1 < levels.length){
                fillheight = levels[index - 1].waterheight;
            } else {
                fillheight = Math.min(levels[index - 1].waterheight, levels[index + 1].waterheight);
            }
            if (fillheight < levels[index].height + 1) {
                fillheight = levels[index].height + 1;
            }
            fillLeftTo(fillheight);
            fillRightTo(fillheight);
            int diff = fillheight - levels[index].waterheight;
            time += diff * levels[index].width;
            levels[index].filltime = time - (diff - 1) * levels[index].width;
            waterheight = fillheight;
            levelsfilled++;
            if (levelsfilled == levels.length) {
                return;
            }
        }
        
        public void fillRight() {
            if (checkRight()) { //if we are good to go
                if (!levels[index + 1].checkRight() && !levels[index + 1].filled) { //fill the next level
                    if (levelsfilled == levels.length) {
                        return;
                    }
                    int fillheight = 0;
                    if (index + 2 >= levels.length) {
                        fillheight = waterheight;
                    } else {
                        fillheight = Math.max(levels[index + 2].waterheight, waterheight);
                    }
                    if (fillheight < levels[index + 1].height + 1) {
                        fillheight = levels[index + 1].height + 1;
                    }
                    
                    int diff = fillheight - levels[index + 1].waterheight;
                    time += diff * levels[index + 1].width;
                    levels[index + 1].filltime = time - (diff - 1) * levels[index + 1].width;
                    levels[index + 1].waterheight = fillheight;
                    levelsfilled++;
                    if (levelsfilled == levels.length) {
                        return;
                    }
                } else if (levels[index + 1].checkRight()) {
                    levels[index + 1].fillRight();
                } 
                if (!checkLeft()) { //fill this level
                    fillCurrent();
                }
            } else if (!checkLeft()) { //fill this level
                fillCurrent();
            }
        }*/
    }

}
