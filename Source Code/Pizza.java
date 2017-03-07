package pizza;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author TeamLS
 * @since 20/2/2017
 * @version 1.0
 */
public class Pizza {

    public static int L;
    public static int H;
    public static int R;
    public static int C;
    public static int score = 0;
    public static int slices = 0;
    public static String[][] pizza;
    public static ArrayList<String> outputList;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        outputList = new ArrayList<>();
        
        // Read input file.
        
        String name = "big"; // The name of the imput file (without .in extension)        
        BufferedReader fr = new BufferedReader(new FileReader(name + ".in"));
        
        String line, firstLine;
        firstLine = fr.readLine();
        
        String[] vars;
        vars = firstLine.split(" ");

        R = Integer.valueOf(vars[0]);
        C = Integer.valueOf(vars[1]);
        L = Integer.valueOf(vars[2]);
        H = Integer.valueOf(vars[3]);

        pizza = new String[R][C];

        int i = 0;
        int j;
        while ((line = fr.readLine()) != null) {
            String letters[] = line.split("");
            j = 0;
            for (String letter : letters) {
                pizza[i][j] = letter;
                j++;
            }
            i++;
        }
        
        
        DVD(0, 0, R - 1, C - 1); // Solves the problem recursively.
        
        System.out.println("Score: " + score);
        System.out.println("Slices: " + slices);
        
        // Create output file.
        try (PrintWriter output = new PrintWriter(name + ".out", "UTF-8")) {
            output.println(slices);
            for (String outputLine : outputList){
                output.println(outputLine);
            }
        }
        

    }

    public static void DVD(int startI, int startJ, int endI, int endJ) {

        Data maxProb = partition(startI, startJ, endI, endJ); // Get the best position to cut.
        
        if (maxProb == null){
            return;
        }
        
        if (maxProb.horizontal) {
            DVD(startI, startJ, startI + maxProb.i, endJ); // Call DVD for the upper slice
            DVD(startI + maxProb.i + 1, startJ, endI, endJ); // Call DVD for the lower slice
        } else {
            DVD(startI, startJ, endI, startJ + maxProb.i); // Call DVD for the left slice.
            DVD(startI, startJ + maxProb.i + 1, endI, endJ); // Call DVD for the right slice.
        }

    }

    public static Data partition(int startI, int startJ, int endI, int endJ) {

        if (startI==endI && startJ==endJ){
            return null;
        }
        
        // Check if this slice satisfies the conditions.
        
        double[] pt = check(startI, startJ, endI, endJ);
        
        if (pt[1] == 1) { // If all conditions are satisfied take the whole slice as a slice.
            
            System.out.println("SLICE: " + startI + " " + startJ + " " + endI + " " + endJ);            
            outputList.add(startI + " " + startJ + " " + endI + " " + endJ);
            
            score += pt[2];
            slices++;
            
            return null;
            
        } else if (pt[1] == 0) {
            return null;
        }
        
        
        ArrayList<Data> list = new ArrayList<>(); // List that contains all possible cuts, vertical and horizontal.

        double prob;

        // Cut vertical.
        
        int over = endJ - startJ;

        for (int i = 0; i < over; i++) {

            double p1 = check(startI, startJ, endI, startJ+i)[0]; // Probability for the left slice.
            double p2 = check(startI, startJ + i + 1, endI, endJ)[0]; // Probability for the right slice.

            prob = p1 * p2;

            list.add(new Data(i, false, prob)); // false indicates that this cut is vertical.
        }

        // Cut horizontal.
        
        over = endI - startI;

        for (int i = 0; i < over; i++) {
            
            double p1 = check(startI, startJ, startI + i, endJ)[0]; // Probability for the upper slice.
            double p2 = check(startI + i + 1, startJ, endI, endJ)[0]; // Probability for the lower slice.

            prob = p1 * p2;

            list.add(new Data(i, true, prob)); // false indicates that this cut is horizontal.
        }

        // Search for the best probability and cut the pizza there.
        
        Data maxProb = list.get(0);
        
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).probability > maxProb.probability) {
                maxProb = list.get(i);
            }
        }

        return new Data(maxProb.i, maxProb.horizontal, maxProb.probability);

    }

    public static double[] check(int startI, int startJ, int endI, int endJ) {
        
        int T = 0; // Number of tomatoes taken in this slice.
        int M = 0; // Number of mushrooms taken in this slice.
        
        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
                if (pizza[i][j].equals("T")) {
                    T++;
                } else {
                    M++;
                }
            }
        }

        int total = T + M; // Number of pieces taken in this slice.
        
        double t = 0; // Tomatoes left to add to reach L.
        if (T < L) {
            t = L - T;
        }

        double m = 0; // Mushrooms left to add to reach L.
        if (M < L) {
            m = L - M;
        }

        double s = 0; // Pieces left to add to reach M. 
        if (H > total) {
            s = H - total;
        }
        
        double s2 = 0; // How more pieces than M.
        if (H < total) {
            s2 = total - H;
        }
            
        double q=0; // 0 if L condition is satisfied, 1 otherwise.
        if (t==0 && m==0){
            q=1;
        }

        double tick = 0; // 2 if both conditions are satisfied, 1 if only M condition is satisfied, 0 otherwise.
        if (T>=L && M>=L){
            if (total>H){
                tick = 2;
            } else {
                tick = 1;
            }
        }
        
        double tipos = (1/3.0)*( (1 - ((t+m)/(2.0*L))) + q*(1-s/H) + (s2/(T+M))*((Math.min(T,M)*1.0)/Math.max(T,M)));
        
        double res[] = {tipos, tick, total};
        
        return res;
        
    }
}
