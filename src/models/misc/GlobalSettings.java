package models.misc;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import models.nucleotide3d.BaseType;

import java.util.HashMap;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/2/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class GlobalSettings {

    public static PhongMaterial SELECTED_MATERIAL = new PhongMaterial(Color.rgb(255,102,255));

    public static PhongMaterial RIBOSE_MATERIAL = new PhongMaterial(Color.GRAY);

    public static PhongMaterial UNSELECTED_MATERIAL = new PhongMaterial(Color.GRAY);

    public static Color adenineColor = Color.web("0772A1");
    public static Color cytosineColor = Color.web("00B74A");
    public static Color guanineColor = Color.web("00B74A");
    public static Color uracilColor = Color.web("0772A1");
    public static Color neutralColor = Color.web("999999");


    public static HashMap<BaseType, Color> DEFAULT_BASE_COLORS = new HashMap<>();

    public static HashMap<BaseType, PhongMaterial> DEFAULT_BASE_MATERIALS= new HashMap<>();

    static {
        makeMaps();
    }

    public static void refreshColors(){
        makeMaps();
    }

    private static void makeMaps(){
        DEFAULT_BASE_COLORS.put(BaseType.A, adenineColor);
        DEFAULT_BASE_COLORS.put(BaseType.C, cytosineColor);
        DEFAULT_BASE_COLORS.put(BaseType.G, guanineColor);
        DEFAULT_BASE_COLORS.put(BaseType.U, uracilColor);
        DEFAULT_BASE_COLORS.put(BaseType.N, neutralColor);

        DEFAULT_BASE_MATERIALS.put(BaseType.A, new PhongMaterial(adenineColor));
        DEFAULT_BASE_MATERIALS.put(BaseType.C, new PhongMaterial(cytosineColor));
        DEFAULT_BASE_MATERIALS.put(BaseType.G, new PhongMaterial(guanineColor));
        DEFAULT_BASE_MATERIALS.put(BaseType.U, new PhongMaterial(uracilColor));
    }

    public static Color TEXT_SELECTED = Color.rgb(255,102,255);

    public static Color TEXT_DEFAULT = Color.WHITE;

    public static int DEFAULT_TEXT_SIZE = 12;

}
