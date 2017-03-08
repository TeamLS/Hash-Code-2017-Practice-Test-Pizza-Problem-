package randompizzagenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author TeamLS
 * @since 20/2/2017
 * @version 1.0
 */
public class PizzaFrame extends JFrame {

    public static int L;
    public static int H;
    public static int R;
    public static int C;
    public static String[][] pizza;

    public static String fileName = "filename";
    public static int score = 0;

    public static int numSlices;
    public static Slice[] slices;

    private PizzaPanel pizzaPanel;
    private JLabel infoLabel;

    public PizzaFrame() {
        initComponents();
    }

    private void initComponents() {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                (new InputForm()).setVisible(true);
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setTitle(fileName);

        try {
            readInput();
            readOutput();
        } catch (IOException ex) {
            Logger.getLogger(PizzaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        pizzaPanel = new PizzaPanel(R, C, pizza, slices);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        pizzaPanel.setBounds(50, 50, width - 100, height - 240);
        pizzaPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(pizzaPanel);
        pizzaPanel.setVisible(true);
        
        infoLabel = new JLabel("<html><font color='red'>Score: " + score + "</font>, <font color='blue'>Slices: " + numSlices + "</font>, <font color='green'>Rows: " + R + "</font>, <font color='green'>Columns: " + C + "</font>, <font color='gray'>L: " + L + "</font>, <font color='gray'>H: " + H + "</font></html>", JLabel.CENTER);
        infoLabel.setFont(new Font(infoLabel.getFont().getFontName(), Font.PLAIN, 26));
        infoLabel.setBounds(50, height - 180, width - 100, 80);
        this.add(infoLabel);
        infoLabel.setVisible(true);
        
        try {
            pizzaPanel.saveImage(fileName);
        } catch (IOException ex) {
            Logger.getLogger(PizzaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }

    private void readInput() throws FileNotFoundException, IOException {
        BufferedReader fr = new BufferedReader(new FileReader(fileName + ".in"));

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

    }

    private void readOutput() throws FileNotFoundException, IOException {
        BufferedReader fr = new BufferedReader(new FileReader(fileName + ".out"));

        String line, firstLine;
        int[] pos;

        pos = new int[4];

        firstLine = fr.readLine();
        numSlices = Integer.valueOf(firstLine);

        slices = new Slice[numSlices];

        int i = 0;
        int j;
        while ((line = fr.readLine()) != null) {
            String letters[] = line.split(" ");
            j = 0;
            for (String letter : letters) {
                pos[j] = Integer.valueOf(letter);
                j++;
            }

            slices[i] = new Slice(pos[0], pos[1], pos[2], pos[3]);

            i++;
        }

    }

    public static void main(String args[]) {

        if (args.length == 2) {
            fileName = args[0];
            score = Integer.parseInt(args[1]);
        }
        
        

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PizzaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PizzaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PizzaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PizzaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PizzaFrame myFrame = new PizzaFrame();
                myFrame.setVisible(true);                
            }
        });
    }
}
