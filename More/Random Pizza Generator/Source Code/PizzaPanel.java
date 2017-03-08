
package randompizzagenerator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author TeamLS
 * @since 20/2/2017
 * @version 1.0
 */
public class PizzaPanel extends JPanel {

    private final int rows;
    private final int columns;
    private int width;
    private int height;
    private final String[][] pizza;
    private final Slice[] slices;

    public PizzaPanel(int rows, int columns, String[][] pizza, Slice[] slices) {
        this.rows = rows;
        this.columns = columns;
        this.pizza = pizza;
        this.slices = slices;

        initComponent();

    }

    private void initComponent() {

        this.setLayout(new GridLayout(rows, columns));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x, y, x0, x1, y0, y1;

        width = this.getWidth();
        height = this.getHeight();

        g.setColor(Color.black);

        double rectWidth = width / (columns * 1.0);
        double rectHeight = height / (rows * 1.0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                x0 = (int) ((j) * rectWidth);
                x1 = (int) (rectWidth);
                y0 = (int) ((i) * rectHeight);
                y1 = (int) (rectHeight);

                if (pizza[i][j].equals("T")) {
                    g.setColor(new Color(12, 186, 9));
                } else {
                    g.setColor(Color.red);
                }
                g.fillRect(x0, y0, x1, y1);
                g.setColor(Color.black);
                g.drawRect(x0, y0, x1, y1);

            }
        }

        Graphics2D g2 = (Graphics2D) g;
        
        if (rows <= 40 && columns <= 35){
            g2.setStroke(new BasicStroke(10));
        } else if (rows <= 80 && columns <= 70){
            g2.setStroke(new BasicStroke(5));            
        } else if (rows <= 100 && columns <= 90){
            g2.setStroke(new BasicStroke(2));     
            
        }

        for (Slice slice : slices) {
            x0 = (int) (slice.startJ * rectWidth);
            x1 = (int) ((slice.endJ - slice.startJ + 1) * rectWidth);
            y0 = (int) (slice.startI * rectHeight);
            y1 = (int) ((slice.endI - slice.startI + 1) * rectHeight);
            //System.out.println(slice);

            g2.setColor(new Color(255, 255, 0, 210));
            g2.fillRect(x0, y0, x1, y1);
            g2.setColor(Color.yellow);
            g2.drawRect(x0, y0, x1, y1);

        }

    }

    public void saveImage(String imageName) throws IOException {
        BufferedImage im = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.paint(im.getGraphics());
        ImageIO.write(im, "PNG", new File(imageName + ".png"));
    }

}
