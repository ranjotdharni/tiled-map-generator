package components;

public class Disqualifier {
    public static final String D_BY_ID = "_ID", D_BY_TYPE = "_TYPE", D_TYPE_BASIC = "_BASIC", D_TYPE_BORDER = "_BORDER",
                        D_SUBTYPE_CORNER = "_CORNER", D_SUBTYPE_LEFT = "_LEFT", D_SUBTYPE_TOP = "_TOP", D_SUBTYPE_RIGHT = "_RIGHT",
                        D_SUBTYPE_BOTTOM = "_BOTTOM", D_SUBTYPE_OUT = "_OUT", D_SUBTYPE_IN = "_IN", D_SUBTYPE_TOP_LEFT = "_TOP_LEFT",
                        D_SUBTYPE_TOP_RIGHT = "_TOP_RIGHT", D_SUBTYPE_BOTTOM_LEFT = "_BOTTOM_LEFT", D_SUBTYPE_BOTTOM_RIGHT = "_BOTTOM_RIGHT", D_COMPLEX_TYPE = "_COMPLEX_TYPE",
                        D_UNRESOLVED_TYPE = "_UNRESOLVED_TYPE", D_SUBTYPE_CENTER = "_CENTER", D_SUBTYPE_START = "_START", D_SUBTYPE_STOP = "_STOP",
                        D_SUBTYPE_BEGIN = "_BEGIN", D_SUBTYPE_END = "_END";
    
    private String d_by = "";
    private String disqualifier = "";

    public Disqualifier(String d_by, String disqualifier) throws Error {
        if (d_by != D_BY_ID && d_by != D_BY_TYPE)
        {
            throw new Error("Invalid disqualify_by: " + d_by + "\n       Check Disqualifier object for valid types/subtypes.");
        }

        this.d_by = d_by;
        this.disqualifier = disqualifier;
    }
    
    public String[] getDisqualification() {
        String[] kakarot = {d_by, disqualifier};
        return kakarot;
    }
}
