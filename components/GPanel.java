package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GPanel extends JPanel {

    private BufferedImage bg;
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public GPanel() {
        super();
        try {                
            bg = ImageIO.read(new File("img/baseBg.png"));
        } 
        catch (IOException ex) {
            System.out.println("No it happened here!");    // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null); // see javadoc for more info on the parameters    
        
        for (int i = 0; i < sprites.size(); i++) {
            Sprite s = sprites.get(i);
            g.drawImage(s.getImage(), s.getX(), s.getY(), null);
        }
    }

    public void addSpriteToCanvas(Sprite s) {
        sprites.add(s);
        repaint();
    }

    public void clearPanel() {
        sprites.clear();
        repaint();
    }
}