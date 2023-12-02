import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import components.BorderTile;
import components.Decoration;
import components.Decorator;
import components.GPanel;
import components.Generator;
import components.MatrixPanel;
import components.Sprite;
import components.Tile;

public class WFCGen {
    private static final int FRAME_WIDTH = 1920;
    private static final int FRAME_HEIGHT = 1080;
    private static final int tileSize = 16;
    private static final int rows = (int) Math.ceil(FRAME_WIDTH / tileSize);
    private static final int cols = (int) Math.ceil(FRAME_HEIGHT / tileSize);

    public static void main(String[] args) {
        GPanel canvas = new GPanel();
        JFrame window = new JFrame("Test");
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        window.add(canvas);
        window.pack();
        window.setVisible(true);

        MatrixPanel debug = new MatrixPanel();
        JFrame debugWindow = new JFrame("Debug");
        debugWindow.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        debugWindow.add(debug);
        debugWindow.pack();
        debugWindow.setVisible(true);

        int delaySeconds = 10;
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
        //Scanner in  = new Scanner(System.in);

        while(true) {
            Generator gen = new Generator(
            rows, 
            cols, 
            tileSize, 
            new ArrayList<Tile>(Arrays.asList(
                new Tile("grass", 1, 30), //The basic tile type defined for this Generator instance                                          
                new Tile("grass", 1, 1), //This is a necessary padding tile, explanation below!!!!!!!!!
                new BorderTile("dirt", "grass", false, 1), 
                new BorderTile("rock", "grass", false, 1)
            )));

            /*
             * !!!!!!!!!!!!!!!!!!!!!IMPORTANT!!!!!!!!!!!!!!!!!!!!!
             * Notice how the above instance of the Generator class has one extra tile that is a
             * copy of the basic tile type (however with a weight of 1) for the to be generated tile map. This is a necessary padding tile! 
             * When instantiating the Generator class, always add one extra of whatever your basic tile type
             * is (remember, whenever you use this Generator, you must always add at least one tile to the Generator that is specifically a Tile object
             * which will serve as what we will refer to as the 'basic tile type' in the context of this program. The basic tile type serves as a default
             * contrast to all of your border tiles and its inclusion in the Generator is mandatory for proper functionality. THE BASIC TILE TYPE MAY NOT
             * BE A 'BorderTile' OBJECT, IT MUST BE THE 'Tile' OBJECT). 
             * 
             * Why add the extra basic padding tile?
             * Say a basic tile type is resolved and layed down by our Generator, then the immediately adjacent entropies to this freshly resolved tile
             * (which was just resolved to a basic tile) will usually be greatly reduced because basic tiles are composed of all 4 sublets in the tile
             * being the same sublet. So, any adjacent/diagonal tiles must be chosen where all touching edges of said adjacent tile must match the edges
             * that it touches on the freshly resolved tile (which remember, we are assuming was resolved to a basic tile type). Since basic tile types
             * are made up of one repeated, same sublet 4 times, then this actually ends up reducing the entropy for adjacent tiles
             * by a large amount since all touching edges must strictly match the one, specified basic tile type sublet. The takeaway here
             * is that placing a basic tile type will reduce the entropy of adjacent tiles by a lot to a small number.
             * 
             * Now, BorderTile objects also have very strict requirements for adjacent tiles once resolved and placed. This makes it possible
             * for the entropy of tiles adjacent to a freshly placed border tile to match the entropy of unresolved tiles which may potentially become 
             * basic tiles types. As a result, our Generator may attempt to resolve more basic tile type entropies before resolving the entropies of all unclosed
             * border tiles first. However, we want our generator to prioritize the closure of any unclosed borders before laying down 
             * more basic tile types if a border tile happens to be placed. The extra padding tile is included so that no matter how low entropies get, 
             * anything that can be resolved to a basic tile type will always have one extra value added to its entropy, so its entropy will inherently
             * be one greater than the lowest entropy possible for all non-basic tile types (all border tiles). Since the generator resolves the next lowest
             * entropy, our padding tile effectively ensures that all border tiles that are placed are closed before the generator attempts to place another
             * basic tile type.
             * 
             * This is a necessary nuance of this program, if any single border tile is placed down at any point, the closure of that newly open
             * border must now be a priority as opposed to allowing the generator to place basic tiles types or opening new borders before the
             * closure of the previously opened border. 
             * 
             * !!!!!!!!!THIS BEHAVIOR IS REQUIRED TO AVOID ENTROPY COLLISIONS!!!!!!!!!
             * !!!!!!!!!!!!!!!!!!!!!!DEVELOPERS BEWARE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             */

            canvas.addSpriteToCanvas(gen.lastSprite());
            debug.addMatrixToCanvas(gen.getEntropyMatrix(), gen.lastSprite().getX(), gen.lastSprite().getX());
            //String nel = in.nextLine();

            while (!gen.finished()) {
                gen.nextRun();
                canvas.addSpriteToCanvas(gen.lastSprite());
                debug.addMatrixToCanvas(gen.getEntropyMatrix(), gen.lastSprite().getX(), gen.lastSprite().getX());
                //String nelly = in.nextLine();
            }

            itr = itr.add(BigInteger.ONE);

            Decoration _d = new Decoration(
                "bigRock", 
                1, 
                0.05, 
                4,
                new ArrayList<ArrayList<Tile>>(Arrays.asList(
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("rock", "rock", "rock", "rock"))),
                            new Tile(new ArrayList<String>(Arrays.asList("rock", "rock", "rock", "rock")))
                        )),
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("rock", "rock", "rock", "rock"))),
                            new Tile(new ArrayList<String>(Arrays.asList("rock", "rock", "rock", "rock")))
                        ))
                ))
            );

            Decoration _cover1 = new Decoration(
                "cover", 
                1, 
                0.99, 
                4,
                new ArrayList<ArrayList<Tile>>(Arrays.asList(
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "grass", "grass", "rock"))),
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "grass", "rock", "grass")))
                        )),
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "rock", "grass", "grass"))),
                            new Tile(new ArrayList<String>(Arrays.asList("rock", "grass", "grass", "grass")))
                        ))
                ))
            );

            Decoration _cover2 = new Decoration(
                "cover", 
                1, 
                0.99, 
                4,
                new ArrayList<ArrayList<Tile>>(Arrays.asList(
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("dirt", "grass", "grass", "grass"))),
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "dirt", "grass", "grass")))
                        )),
                    new ArrayList<Tile>(Arrays.asList(
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "grass", "dirt", "grass"))),
                            new Tile(new ArrayList<String>(Arrays.asList("grass", "grass", "grass", "dirt")))
                        ))
                ))
            );

            Decorator d = new Decorator(tileSize, gen.getEntropyMatrix(), new ArrayList<Decoration>(Arrays.asList(_d, _cover1, _cover2)));
            Sprite ref = null;

            while (!d.isFinished()) {
                d.nextRun();

                if (d.lastSprite() != null && d.lastSprite() != ref)
                {
                    canvas.addSpriteToCanvas(d.lastSprite());
                    ref = d.lastSprite();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }

            canvas.clearPanel();
        }
    }
}