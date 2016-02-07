package models.nucleotide2d.drawings;


import javafx.beans.binding.Binding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.misc.GlobalSettings;
import models.nucleotide3d.BaseType;
import models.nucleotide3d.IColorizable;


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
public class AbstractNucleotideCircle extends Group implements IColorizable {

    protected Circle circle;

    protected Circle shadow;

    protected Text base;

    protected Circle toolTipMask;

    protected BaseType baseType;

    //protected Group elementGroup;

    private final double DEFAULT_TEXT_SIZE = 10;

    private DoubleProperty radiusProperty = new SimpleDoubleProperty(4);

    private final double DEFAULT_X_OFFSET = -4;

    private final double DEFAULT_Y_OFFSET = 3;

    protected Tooltip tooltip;

    protected BooleanProperty isSelected = new SimpleBooleanProperty(false);

    public AbstractNucleotideCircle(){
        super();
        //this.elementGroup = new Group();
        this.circle = new Circle(radiusProperty.doubleValue());
        this.shadow = new Circle(radiusProperty.doubleValue()+1);
        this.toolTipMask = new Circle(radiusProperty.doubleValue()+1);
        this.base = new Text("");
        this.base.setStyle("-fx-font-size: 12");
        this.shadow.setFill(Color.WHITE);
        this.base.setFill(Color.WHITE);
        this.base.getStyleClass().addAll("nucleotide_text");
        this.toolTipMask.setFill(Color.TRANSPARENT);
        //this.elementGroup.getChildren().addAll(this.shadow,
        //        this.circle, this.base, this.toolTipMask);
        this.getChildren().addAll(this.shadow, this.circle, this.base, this.toolTipMask);
        this.base.layoutXProperty().setValue(this.circle.getCenterX()+DEFAULT_X_OFFSET);
        this.base.layoutYProperty().setValue(this.circle.getCenterY()+DEFAULT_Y_OFFSET);
        setBindings();
    }

    private void setBindings(){
        circle.radiusProperty().bind(radiusProperty);
        shadow.radiusProperty().bind(new DoubleBinding() {
            {
                bind(radiusProperty);
            }
            @Override
            protected double computeValue() {
                return radiusProperty.get()+1;
            }
        });
        toolTipMask.radiusProperty().bind(new DoubleBinding() {
            {
                bind(radiusProperty);
            }
            @Override
            protected double computeValue() {
                return radiusProperty.get()+1;
            }
        });
    }


    protected void setBaseType(){};

    public void setTooltip(String message) {
        this.tooltip = new Tooltip(message);
        tooltip.autoHideProperty().setValue(false);
        Tooltip.install(this.toolTipMask, this.tooltip);
    }

    public BooleanProperty getIsSelectedProperty(){
        return this.isSelected;
    }

    protected BaseType getBaseType(){
        return this.baseType;
    }

    public DoubleProperty getRadiusProperty(){
        return this.radiusProperty;
    }

    @Override
    public void setColor() {
        this.circle.setFill(GlobalSettings.TEXT_SELECTED);
    }

    @Override
    public void resetColor() {
        this.circle.setFill(GlobalSettings.DEFAULT_BASE_COLORS.get(BaseType.N));
    }

    @Override
    public void setOriginalColor() {
        this.circle.setFill(GlobalSettings.DEFAULT_BASE_COLORS.get(this.baseType));
    }
}
