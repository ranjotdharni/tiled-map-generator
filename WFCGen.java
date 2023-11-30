import javax.imageio.ImageIO;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.font.GraphicAttribute;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import components.BorderTile;
import components.Decoration;
import components.Decorator;
import components.GPanel;
import components.Generator;
import components.Sprite;
import components.Tile;

public class WFCGen {
    private static final int FRAME_WIDTH = 1920;
    private static final int FRAME_HEIGHT = 1080;
    static File f = new File("img/waterOut.png");

    public static void main(String[] args) {
        GPanel canvas = new GPanel();
        JFrame window = new JFrame("Test");
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        window.add(canvas);
        window.pack();
        window.setVisible(true);

        int delaySeconds = 5;
        long begin = System.currentTimeMillis();
        long lastPrint = System.currentTimeMillis();

        while (System.currentTimeMillis() - begin < (delaySeconds + 2) * 1000)
        {
            if (System.currentTimeMillis() - lastPrint > 1000)
            {
                System.out.println("Starting in " + (int) Math.ceil((((delaySeconds + 2) * 1000) - (System.currentTimeMillis() - begin)) / 1000) + "s...");
                lastPrint = System.currentTimeMillis();
            }
        }

        BigInteger itr = BigInteger.ZERO;

        while(true) {
            Generator gen = new Generator(
            (int) Math.ceil(FRAME_WIDTH / 16), 
            (int) Math.ceil(FRAME_HEIGHT / 16), 
            16, 
            new ArrayList<Tile>(Arrays.asList(
                new Tile("grass", 1, 100), 
                new BorderTile("dirt", "grass", false, 1), 
                new BorderTile("rock", "grass", false, 1)
            )));

            canvas.addSpriteToCanvas(gen.lastSprite());

            while (!gen.finished()) {
                gen.nextRun();
                canvas.addSpriteToCanvas(gen.lastSprite());
            }

            itr = itr.add(BigInteger.ONE);

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }

            Decoration _d = new Decoration(
                "house", 
                1, 
                0.15, 
                1,
                new ArrayList<ArrayList<Tile>>(Arrays.asList(
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "grass", "grass", "grass")))
                        ))
                ))
            );

            Decorator d = new Decorator(16, gen.getEntropyMatrix(), new ArrayList<Decoration>(Arrays.asList(_d)));

            while (!d.isFinished()) {
                d.nextRun();
                canvas.addSpriteToCanvas(d.lastSprite());
            }

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }

            canvas.clearPanel();
        }
    }
}