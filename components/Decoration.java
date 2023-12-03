package components;

import java.util.ArrayList;

public class Decoration {
    private String id = "";
    private double possibility = 0.0;
    private int requiredRows = -1, requiredCols = -1, variance = 1; 
    private ArrayList<ArrayList<Tile>> requiredTileSpace = null; //starting from the top left corner of placement
                                                                 //Every item in the top array list represents one
                                                                 //column from top to bottom, this is the checking order
                                                                 //
                                                                 //so the items in the matrix are checked starting from the top
                                                                 //left corner in the matrix, it goes from top to bottom completely
                                                                 //checking the column that its on, and then it hops to the next 
                                                                 //column over and starts again at the top of said next column
                                                                 //until either every tile in the tile space is validated or a
                                                                 //single mismatch is detected against the grid space being validated

    public Decoration(String id, int variance, double possibility, int scalarTileSpace, ArrayList<ArrayList<Tile>> tileSpace) {
        this.requiredRows = (int) Math.sqrt(scalarTileSpace);
        this.requiredCols = (int) Math.sqrt(scalarTileSpace);
        this.requiredTileSpace = tileSpace;
        this.id = id;
        this.variance = variance;
        this.possibility = possibility;
    }

    public String getId() {
        return id;
    }

    public int requiresTiles() {
        return requiredRows * requiredCols;
    }

    public int requiredRows() {
        return requiredRows;
    }

    public int requiredColumns() {
        return requiredCols;
    }

    public int getVariance() {
        return variance;
    }

    public double getChance() {
        return possibility;
    }

    public ArrayList<ArrayList<Tile>> requiredTileSpace() {
        return requiredTileSpace;
    }
}
