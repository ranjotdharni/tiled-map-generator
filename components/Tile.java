package components;

import java.util.ArrayList;
import java.util.Arrays;

public class Tile {
    private int possibleTiles = 1, variants = 1, weight = 1;
    protected String id = "";
    protected ArrayList<String> type = new ArrayList<String>(4); //[[0, 0], [0, 1], [1, 0], [1, 1]]
    protected Boolean isBasicTile = true;

    public Tile(String id, int variants) {
        this.id = id;
        this.variants = variants;
        this.type = new ArrayList<String>(Arrays.asList(id, id, id, id));
    }

    public Tile(String id, int variants, int weight) {
        this.id = id;
        this.variants = variants;
        this.weight = weight;
        this.type = new ArrayList<String>(Arrays.asList(id, id, id, id));
    }

    protected void setWeight(int weight) {
        this.weight = weight;
    }

    protected void setType(ArrayList<String> a) {
        this.type = a;
    }

    protected void setBasicTile(Boolean a) {
        this.isBasicTile = a;
    }

    public Boolean isBasicTile() {
        return isBasicTile;
    }

    public int getVariants() {
        return variants;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int possibleTiles() {
        return possibleTiles;
    }

    public void muteTilesNotMatching(int index, String sublet) {
        if (!type.get(index).equals(sublet)) {
            this.possibleTiles = 0;
        }
    } 
}
