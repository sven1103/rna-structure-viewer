package models.nucleotide1d;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.misc.GlobalSettings;
import models.nucleotide3d.IColorizable;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/3/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class SimpleNucleotide extends Text implements IColorizable{

    private Color defaultColor = GlobalSettings.TEXT_DEFAULT;

    private BooleanProperty isSelected = new SimpleBooleanProperty(false);

    public SimpleNucleotide(){
        super();
    }

    public SimpleNucleotide(String text){
        super(text);
        this.setOriginalColor();
    }

    public BooleanProperty isSelectedProperty(){
        return this.isSelected;
    }

    @Override
    public void setColor() {
        this.setFill(GlobalSettings.TEXT_SELECTED);
    }

    @Override
    public void resetColor() {
        this.setFill(GlobalSettings.TEXT_DEFAULT);
    }

    @Override
    public void setOriginalColor() {
        this.setFill(GlobalSettings.TEXT_DEFAULT);
    }
}
