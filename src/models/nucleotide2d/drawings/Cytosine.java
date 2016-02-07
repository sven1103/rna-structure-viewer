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
public class Cytosine extends AbstractNucleotideCircle {


    public Cytosine() {
        super();
        this.circle.setFill(GlobalSettings.cytosineColor);
        this.baseType = BaseType.C;
        setTooltip("C");
        setBaseType();
    }


    @Override
    protected void setBaseType() {
        this.base.setText("");
    }



}
