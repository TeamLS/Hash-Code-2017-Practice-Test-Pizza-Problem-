package randompizzagenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author TeamLS
 * @since 20/2/2017
 * @version 1.0
 */
public class RandomPizzaGenerator {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        String name = args[0];
        int rows = Integer.parseInt(args[1]);
        int columns = Integer.parseInt(args[2]);
        int L = Integer.parseInt(args[3]);
        int H = Integer.parseInt(args[4]);

        try (PrintWriter output = new PrintWriter(name + ".in", "UTF-8")) {
            output.println(rows + " " + columns + " " + L + " " + H);

            Random rand = new Random();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (rand.nextBoolean()) {
                        output.print("T");
                    } else {
                        output.print("M");
                    }
                }
                output.println("");
            }
        }

        try {
            String[] argss = {name};
            int score = Pizza.main(argss);
            String[] argss2 = {name, String.valueOf(score)};
            PizzaFrame.main(argss2);
        } catch (IOException ex) {
            Logger.getLogger(RandomPizzaGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
