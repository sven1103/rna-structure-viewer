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
public class Adenine extends AbstractNucleotideCircle {


    public Adenine(){
        super();
        this.setColor(Color.rgb(184,133,56));
        setTooltip("A");
        setBaseType();
    }

    public Adenine(double x, double y){
        super(x, y);
        this.setColor(Color.rgb(184,133,56));
        setTooltip("A");
        setBaseType();
    }


    @Override
    protected void setBaseType() {
        this.base.setText("A");
    }



}
