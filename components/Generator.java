package components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class Generator {
    private int rows = -1, cols = -1, tileSize = -1, resolved = 0, weight = 1, maxEntropy = -1;
    private ArrayList<ArrayList<Entropy>> e = null;
    private ArrayList<Sprite> s = new ArrayList<Sprite>();
    private Sprite lastSprite = null;
    private String lastPath = "";
    private Random rand = new Random();

    public Generator(int rows, int cols, int tileSize, int weight, ArrayList<Tile> tiles) {
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;

        this.e = new ArrayList<ArrayList<Entropy>>();

        if (weight < 1)     weight = 1;
        this.weight = weight;

        for (int i = 0; i < rows; i++) {
            this.e.add(new ArrayList<Entropy>(cols));
        }

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                ArrayList<Tile> t = new ArrayList<Tile>();

                for (int k = 0; k < tiles.size(); k++) {
                    Tile _t = tiles.get(k);
                    if (!_t.isBasicTile())
                    {
                        t.add(new BorderTile(((BorderTile) _t).getPrimaryId(), ((BorderTile) _t).getBorderID(), ((BorderTile) _t).isRotationDisabled()));
                    }
                    else
                    {
                        for (int n = 0; n < weight; n++)
                        {
                            t.add(new Tile(_t.getId(), _t.getVariants()));
                        }
                    }
                }

                this.e.get(x).add(new Entropy(new ArrayList<Tile>(t)));
            }
        }

        this.maxEntropy = e.get(0).get(0).getEntropy();

        run(new int[]{rand.nextInt(rows), rand.nextInt(cols)});
    }

    private Boolean isValidCoord(int x, int y) {
        return !(x < 0 || x > rows - 1) && !(y < 0 || y > cols - 1);
    }

    private void run(int[] arr) {
        this.resolved++;

        Entropy temp = e.get(arr[0]).get(arr[1]);

        Tile temping = temp.resolve();

        int x = arr[0] - 1, y = arr[1] - 1;

        if (isValidCoord(x, y)) //topleft
        {
            this.e.get(x).get(y).mustMatch(3, temping.getType().get(0));
        }

        x++;

        if (isValidCoord(x, y)) //top
        {
            this.e.get(x).get(y).mustMatch(2, temping.getType().get(0));
            this.e.get(x).get(y).mustMatch(3, temping.getType().get(1));
        }

        x++;

        if (isValidCoord(x, y)) //topright
        {
            this.e.get(x).get(y).mustMatch(2, temping.getType().get(1));
        }

        x--;
        x--;
        y++;

        if (isValidCoord(x, y)) //left
        {
            this.e.get(x).get(y).mustMatch(1, temping.getType().get(0));
            this.e.get(x).get(y).mustMatch(3, temping.getType().get(2));
        }

        x++;
        x++;

        if (isValidCoord(x, y)) //right
        {
            this.e.get(x).get(y).mustMatch(0, temping.getType().get(1));
            this.e.get(x).get(y).mustMatch(2, temping.getType().get(3));
        }

        x--;
        x--;
        y++;

        if (isValidCoord(x, y)) //bottomleft
        {
            this.e.get(x).get(y).mustMatch(1, temping.getType().get(2));
        }

        x++;

        if (isValidCoord(x, y)) //bottom
        {
            this.e.get(x).get(y).mustMatch(0, temping.getType().get(2));
            this.e.get(x).get(y).mustMatch(1, temping.getType().get(3));
        }

        x++;

        if (isValidCoord(x, y)) //bottomright
        {
            this.e.get(x).get(y).mustMatch(0, temping.getType().get(3));
        }

        BufferedImage img = null;
        String pathString = "img/";

        if (temping.isBasicTile())
        {
            pathString = pathString + temping.getId() + rand.nextInt(temping.getVariants()) + ".png";
            try {
                img = ImageIO.read(new File(pathString));
            }
            catch (Exception e) {
                System.out.println("somthing wrong with the paths fam :/");
            }
        
            s.add(new Sprite(img, arr[0] * tileSize, arr[1] * tileSize));
            this.lastSprite = new Sprite(img, arr[0] * tileSize, arr[1] * tileSize);
            this.lastPath = pathString;

            return;
        }

        int itr = 0;
        while (temping.getId().charAt(itr) != '_')
        {
            pathString = pathString + temping.getId().charAt(itr);
            itr++;
        }

        char ch = '0';
        if (!((BorderTile) temping).isRotationDisabled()) 
        {
            ch = temping.getId().charAt(temping.getId().length() - 1);
        }
        if (temping.getId().contains(Disqualifier.D_SUBTYPE_CENTER))
        {
            pathString = pathString + "Center" + ch + ".png";
        }
        else if (temping.getId().contains(Disqualifier.D_SUBTYPE_LEFT))
        {
            pathString = pathString + "Left" + ch + ".png";
        }
        else if (temping.getId().contains(Disqualifier.D_SUBTYPE_IN))
        {
            pathString = pathString + "In" + ch + ".png";
        }
        else if (temping.getId().contains(Disqualifier.D_SUBTYPE_OUT))
        {
            pathString = pathString + "Out" + ch + ".png";
        }

        try {
            img = ImageIO.read(new File(pathString));
        }
        catch (Exception e) {
            System.out.println("somthing wrong with the paths fam :/ x2");
        }

        s.add(new Sprite(img, arr[0] * tileSize, arr[1] * tileSize));
        this.lastSprite = new Sprite(img, arr[0] * tileSize, arr[1] * tileSize);
        this.lastPath = pathString;
        return;
    }

    public int getWeight() {
        return weight;
    }

    public Sprite lastSprite() {
        return lastSprite;
    }

    public String lastPath() {
        return lastPath;
    }

    public boolean finished() {
        return (rows * cols) == resolved;
    }

    public void nextRun() {
        if (finished())     return;

        int low = 10000000;
        int[] arr = new int[]{-1, -1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((!e.get(i).get(j).isResolved()) && e.get(i).get(j).getEntropy() < low)
                {
                    low = e.get(i).get(j).getEntropy();
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }

        if (low == this.maxEntropy)
        {
            int remaining = (rows * cols) - resolved;
            int skip = rand.nextInt(remaining);
            int idx = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if ((!e.get(i).get(j).isResolved()) && skip != 0)
                    {
                        idx++;
                    }
                    if (idx == skip)
                    {
                        arr[0] = i;
                        arr[1] = j;
                        break;
                    }
                }
                if (idx == skip)
                {
                    break;
                }
            }
        }

        run(arr);
    }
}
