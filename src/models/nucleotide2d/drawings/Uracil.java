package models.nucleotide2d.drawings;

import javafx.scene.paint.Color;
import models.misc.GlobalSettings;
import models.nucleotide3d.BaseType;

/**
 * advanced-java-bioinformatics
 *
 * Description:
 *  <- content ->
 *
 * @author fillinger
 * @version
 * Date: 11/26/15
 * EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class Uracil extends AbstractNucleotideCircle {

    public Uracil(){
        super();
        this.circle.setFill(GlobalSettings.uracilColor);
        setTooltip("U");
        setBaseType();
    }

    @Override
    protected void setBaseType() {
        this.baseType = BaseType.U;
        setOriginalColor();
    }



}
