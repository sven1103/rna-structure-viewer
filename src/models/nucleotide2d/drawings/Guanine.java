package models.nucleotide2d.drawings;

import javafx.scene.paint.Color;

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
public class Guanine extends AbstractNucleotideCircle {

    public Guanine(){
        super();
        this.setColor(Color.rgb(43,79,120));
        setTooltip("G");
        setBaseType();
    }


    @Override
    protected void setBaseType() {
        this.base.setText("G");
    }



}
