package models.nucleotide2d.drawings;


import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


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
public abstract class AbstractNucleotideCircle extends Group {

    protected Circle circle;

    protected Circle shadow;

    protected Text base;

    protected Circle toolTipMask;

    //protected Group elementGroup;

    private final double DEFAULT_RADIUS = 10;

    private final double DEFAULT_X_OFFSET = -5;

    private final double DEFAULT_Y_OFFSET = 4;

    protected Tooltip tooltip;

    public AbstractNucleotideCircle(){
        super();
        //this.elementGroup = new Group();
        this.circle = new Circle(DEFAULT_RADIUS);
        this.shadow = new Circle(DEFAULT_RADIUS+1);
        this.toolTipMask = new Circle(DEFAULT_RADIUS+1);
        this.base = new Text("N");
        this.shadow.setFill(Color.WHITE);
        this.base.setFill(Color.WHITE);
        this.base.getStyleClass().addAll("nucleotide_text");
        this.toolTipMask.setFill(Color.TRANSPARENT);
        //this.elementGroup.getChildren().addAll(this.shadow,
        //        this.circle, this.base, this.toolTipMask);
        this.getChildren().addAll(this.shadow, this.circle, this.base, this.toolTipMask);
        this.base.layoutXProperty().setValue(this.circle.getCenterX()+DEFAULT_X_OFFSET);
        this.base.layoutYProperty().setValue(this.circle.getCenterY()+DEFAULT_Y_OFFSET);
    }

    public AbstractNucleotideCircle(double x, double y){
        this();
        this.layoutXProperty().setValue(x);
        this.layoutYProperty().setValue(y);
    }

    protected void setColor(Color color){
        this.circle.setFill(color);
    }

    abstract protected void setBaseType();

    public void setTooltip(String message) {
        this.tooltip = new Tooltip(message);
        tooltip.autoHideProperty().setValue(false);
        Tooltip.install(this.toolTipMask, this.tooltip);
    }


}
