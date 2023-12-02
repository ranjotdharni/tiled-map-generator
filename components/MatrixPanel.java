package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MatrixPanel extends JPanel {

    private BufferedImage bg;
    private ArrayList<ArrayList<Entropy>> e = new ArrayList<ArrayList<Entropy>>();
    private int lastX = 0, lastY = 0;

    public MatrixPanel() {
        super();
        /*try {                
            bg = ImageIO.read(new File("img/baseBg.png"));
        } 
        catch (IOException ex) {
            System.out.println("No it happened here!");    // handle exception...
        }*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(bg, 0, 0, null); // see javadoc for more info on the parameters 
        g.setFont(new Font("Arial", Font.PLAIN, 8));   
        
        for (int x = 0; x < e.size(); x++) {
            for (int y = 0; y < e.get(x).size(); y++) {

                if (e.get(x).get(y).isResolved())
                {
                    g.setColor(Color.BLUE);
                }
                else
                {
                    g.setColor(Color.RED);
                }

                if (x == lastX && y == lastY)
                {
                    g.setColor(Color.YELLOW);
                }

                g.drawString("" + e.get(x).get(y).getEntropy(), x * 16, y * 16);
            }
        }
    }

    public void addMatrixToCanvas(ArrayList<ArrayList<Entropy>> _e, int _x, int _y) {
        this.e = _e;
        this.lastX = _x / 16;
        this.lastY = _y / 16; //inverted on display
        repaint();
    }

    public void clearPanel() {
        e.clear();
        repaint();
    }
}