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


    public static final HashMap<BaseType, PhongMaterial> DEFAULT_BASE_MATERIALS= new HashMap<>();
    static {
        DEFAULT_BASE_MATERIALS.put(BaseType.A, new PhongMaterial(Color.web("0772A1")));
        DEFAULT_BASE_MATERIALS.put(BaseType.C, new PhongMaterial(Color.web("00B74A")));
        DEFAULT_BASE_MATERIALS.put(BaseType.G, new PhongMaterial(Color.web("00B74A")));
        DEFAULT_BASE_MATERIALS.put(BaseType.U, new PhongMaterial(Color.web("0772A1")));
    }

    public static Color TEXT_SELECTED = Color.rgb(255,102,255);

    public static Color TEXT_DEFAULT = Color.WHITE;

    public static int DEFAULT_TEXT_SIZE = 12;

}
