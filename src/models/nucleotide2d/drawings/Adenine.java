package models.nucleotide2d.drawings;

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
public class Adenine extends AbstractNucleotideCircle {


    public Adenine(){
        super();
        this.circle.setFill(GlobalSettings.adenineColor);
        this.baseType = BaseType.A;
        setTooltip("A");
        setBaseType();
    }


    @Override
    protected void setBaseType() {
        this.base.setText("");
    }



}
