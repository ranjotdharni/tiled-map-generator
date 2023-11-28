package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BorderTile extends Tile {
    private ArrayList<Tile> tileArray = new ArrayList<Tile>();
    private Boolean isResolved = false;
    private Boolean rotationsDisabled = false;
    private String primaryId = "", borderId = "";
    
    public BorderTile(String primaryId, String borderId, Boolean rotationsDisabled) {
        super(primaryId, -1);
        this.type = null;
        this.isBasicTile = false;
        this.rotationsDisabled = rotationsDisabled;
        this.primaryId = primaryId;
        this.borderId = borderId;

        Tile temp1 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_LEFT + "_" + "0", -1); //left
        temp1.setBasicTile(false);
        temp1.setType(new ArrayList<String>(Arrays.asList(borderId, primaryId, borderId, primaryId)));
        tileArray.add(temp1);
        
        Tile temp2 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_LEFT + "_" + "1", -1);
        temp2.setBasicTile(false);
        temp2.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, primaryId, primaryId)));
        tileArray.add(temp2);

        Tile temp3 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_LEFT + "_" + "2", -1);
        temp3.setBasicTile(false);
        temp3.setType(new ArrayList<String>(Arrays.asList(primaryId, borderId, primaryId, borderId)));
        tileArray.add(temp3);

        Tile temp4 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_LEFT + "_" + "3", -1);
        temp4.setBasicTile(false);
        temp4.setType(new ArrayList<String>(Arrays.asList(primaryId, primaryId, borderId, borderId)));
        tileArray.add(temp4);

        Tile temp5 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_CENTER + "_" + "0", -1); //center
        temp5.setBasicTile(false);
        temp5.setType(new ArrayList<String>(Arrays.asList(primaryId, primaryId, primaryId, primaryId)));
        tileArray.add(temp5);

        Tile temp6 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_IN + "_" + "0", -1); //in
        temp6.setBasicTile(false);
        temp6.setType(new ArrayList<String>(Arrays.asList(borderId, primaryId, primaryId, primaryId)));
        tileArray.add(temp6);

        Tile temp7 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_IN + "_" + "1", -1); 
        temp7.setBasicTile(false);
        temp7.setType(new ArrayList<String>(Arrays.asList(primaryId, borderId, primaryId, primaryId)));
        tileArray.add(temp7);

        Tile temp8 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_IN + "_" + "2", -1); 
        temp8.setBasicTile(false);
        temp8.setType(new ArrayList<String>(Arrays.asList(primaryId, primaryId, primaryId, borderId)));
        tileArray.add(temp8);

        Tile temp9 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_IN + "_" + "3", -1); 
        temp9.setBasicTile(false);
        temp9.setType(new ArrayList<String>(Arrays.asList(primaryId, primaryId, borderId, primaryId)));
        tileArray.add(temp9);

        Tile temp10 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "0", -1); //out
        temp10.setBasicTile(false);
        temp10.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, borderId, primaryId)));
        tileArray.add(temp10);

        Tile temp11 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "1", -1); 
        temp11.setBasicTile(false);
        temp11.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, primaryId, borderId)));
        tileArray.add(temp11);

        Tile temp12 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "2", -1); 
        temp12.setBasicTile(false);
        temp12.setType(new ArrayList<String>(Arrays.asList(primaryId, borderId, borderId, borderId)));
        tileArray.add(temp12);

        Tile temp13 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "3", -1); 
        temp13.setBasicTile(false);
        temp13.setType(new ArrayList<String>(Arrays.asList(borderId, primaryId, borderId, borderId)));
        tileArray.add(temp13);

        Tile temp100 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "0", -1); //out
        temp100.setBasicTile(false);
        temp100.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, borderId, primaryId)));
        tileArray.add(temp100);

        Tile temp110 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "1", -1); 
        temp110.setBasicTile(false);
        temp110.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, primaryId, borderId)));
        tileArray.add(temp110);

        Tile temp120 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "2", -1); 
        temp120.setBasicTile(false);
        temp120.setType(new ArrayList<String>(Arrays.asList(primaryId, borderId, borderId, borderId)));
        tileArray.add(temp120);

        Tile temp130 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "3", -1); 
        temp130.setBasicTile(false);
        temp130.setType(new ArrayList<String>(Arrays.asList(borderId, primaryId, borderId, borderId)));
        tileArray.add(temp130);

        Tile temp1000 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "0", -1); //out
        temp1000.setBasicTile(false);
        temp1000.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, borderId, primaryId)));
        tileArray.add(temp1000);

        Tile temp1100 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "1", -1); 
        temp1100.setBasicTile(false);
        temp1100.setType(new ArrayList<String>(Arrays.asList(borderId, borderId, primaryId, borderId)));
        tileArray.add(temp1100);

        Tile temp1200 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "2", -1); 
        temp1200.setBasicTile(false);
        temp1200.setType(new ArrayList<String>(Arrays.asList(primaryId, borderId, borderId, borderId)));
        tileArray.add(temp1200);

        Tile temp1300 = new Tile(primaryId + "_" + borderId + Disqualifier.D_SUBTYPE_OUT + "_" + "3", -1); 
        temp1300.setBasicTile(false);
        temp1300.setType(new ArrayList<String>(Arrays.asList(borderId, primaryId, borderId, borderId)));
        tileArray.add(temp1300);
    }

    @Override
    public int possibleTiles() {
        return tileArray.size();
    }

    public void resolve(int index) {
        Tile t = tileArray.get(index);
        this.tileArray = new ArrayList<Tile>(Arrays.asList(t));
        this.id = t.getId();
        this.type = t.getType();
        this.isResolved = true;
    }

    public Boolean isResolved() {
        return isResolved;
    }

    public Boolean isRotationDisabled() {
        return rotationsDisabled;
    }

    public String getPrimaryId() {
        return primaryId;
    }

    public String getBorderID() {
        return borderId;
    }

    @Override
    public void muteTilesNotMatching(int index, String sublet) {
        Iterator<Tile> itr = this.tileArray.iterator();

        while (itr.hasNext()) {
            Tile t = itr.next();

            if (!t.getType().get(index).equals(sublet)) {
                itr.remove();
            }
        }
    }
}
