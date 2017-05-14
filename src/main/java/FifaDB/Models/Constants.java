package FifaDB.Models;

import javax.print.DocFlavor;

/**
 * Created by faraonul on 5/14/17.
 */
public class Constants {
    public static final String POSITION_LF = "LF";
    public static final String POSITION_CF = "CF";
    public static final String POSITION_RF = "RF";
    public static final String POSITION_ST = "ST";
    public static final String POSITION_LW = "LW";
    public static final String POSITION_LM = "LM";
    public static final String POSITION_CAM= "CAM";
    public static final String POSITION_CDM = "CDM";
    public static final String POSITION_CM = "CM";
    public static final String POSITION_RM = "RM";
    public static final String POSITION_RW = "RW";
    public static final String POSITION_LWB = "LWB";
    public static final String POSITION_LB = "LB";
    public static final String POSITION_CB = "CB";
    public static final String POSITION_RB = "RB";
    public static final String POSITION_RWB = "RWB";
    public static final String POSITION_GK = "GK";

    public static final String[] ALL_POSITIONS = {
            POSITION_LF,
            POSITION_CF,
            POSITION_RF,
            POSITION_ST,
            POSITION_LW,
            POSITION_LM,
            POSITION_CAM,
            POSITION_CDM,
            POSITION_CM,
            POSITION_RM,
            POSITION_RW,
            POSITION_LWB,
            POSITION_LB,
            POSITION_CB,
            POSITION_RB,
            POSITION_RWB,
            POSITION_GK
    };

    public static String getAllPositions() {
        return String.join(",", ALL_POSITIONS);
    }

}
