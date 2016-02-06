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
public class Cytosine extends AbstractNucleotideCircle {


    public Cytosine() {
        super();
        this.setColor(Color.rgb(110,38,121));
        setTooltip("C");
        setBaseType();
    }


    @Override
    protected void setBaseType() {
        this.base.setText("C");
    }



}
