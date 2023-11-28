package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Entropy {
    private Boolean isResolved = false;
    private Tile resolution = null;
    private ArrayList<Tile> possibilities = null;
    private Random random = new Random();

    public Entropy(ArrayList<Tile> possibilities) {
        this.possibilities = possibilities;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public Tile getResolution() {
        return resolution;
    }

    public Tile resolve() {
        if (isResolved)     return resolution;

        this.isResolved = true;

        int index = random.nextInt(possibilities.size());
        Tile temp = possibilities.get(index);

        if (temp.isBasicTile())
        {
            this.resolution = temp;
            this.possibilities = new ArrayList<Tile>(Arrays.asList(temp));
            return temp;
        }
        
        BorderTile temping = (BorderTile) temp;

        if (!temping.isResolved()) 
        {
            temping.resolve(random.nextInt(temping.possibleTiles()));
        }

        this.resolution = temping;
        this.possibilities = new ArrayList<Tile>(Arrays.asList(temping));
        return temping;
    }

    public int getEntropy() {
        int temp = 0;

        for (int i = 0; i < possibilities.size(); i++) {
            if (possibilities.get(i).isBasicTile())
            {
                temp++;
            }
            else
            {
                temp += possibilities.get(i).possibleTiles();
            }
        }

        return temp;
    }

    public void mustMatch(int index, String sublet) {
        Iterator<Tile> itr = possibilities.iterator();

        while (itr.hasNext()) {
            Tile temp = itr.next();

            if (temp.isBasicTile())
            {
                temp.muteTilesNotMatching(index, sublet);
                if (temp.possibleTiles() < 1)
                {
                    itr.remove();
                }
            }
            else
            {
                temp.muteTilesNotMatching(index, sublet);
                if (temp.possibleTiles() < 1)
                {
                    itr.remove();
                }
            }
        }
    }
}
