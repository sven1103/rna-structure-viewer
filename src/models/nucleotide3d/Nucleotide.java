package models.nucleotide3d;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import models.misc.GlobalSettings;


/**
 * Created by sven on 1/12/16.
 */
public class Nucleotide implements Cloneable, IColorizable{

    private BaseType baseType;

    private MeshView ribose;

    private BaseModel base;

    private Group nucleotide;

    public BooleanProperty modelFilled = new SimpleBooleanProperty(false);

    private BooleanProperty selected;

    private int residuePosition;

    public Nucleotide(){
        this.selected = new SimpleBooleanProperty(false);
        this.nucleotide = new Group();
        this.residuePosition = -1;
        this.baseType = BaseType.N;
    }

    public Nucleotide setBaseType(BaseType n){
        this.baseType = n;
        return this;
    }

    public Nucleotide setRibose(MeshView ribose){
        this.ribose = ribose;
        evaluateModel();
        return this;
    }

    public Nucleotide setBase(BaseModel base){
        this.base = base;
        evaluateModel();
        return this;
    }

    public BaseModel getBase(){
        return this.base;
    }

    public Nucleotide setResiduePosition(int pos){
        this.residuePosition = pos;
        return this;
    }

    private void evaluateModel(){
        boolean isFilledCompletely = true;
        if(ribose == null || base == null){
           isFilledCompletely = false;
        }
        modelFilled.setValue(isFilledCompletely);
    }

    public void reset(){
        this.nucleotide = new Group();
        this.residuePosition = -1;
        this.baseType = BaseType.N;
        this.ribose = null;
        this.base = null;
        this.selected = new SimpleBooleanProperty(false);
    }

    public BooleanProperty getSelectedProperty(){
        return this.selected;
    }

    public Group getNucleotide(){
        if(nucleotide.getChildren().isEmpty()){
            if(ribose != null && base != null){
                this.nucleotide.getChildren().addAll(ribose, base.getBase());
            }

            Tooltip tooltip = new Tooltip(this.baseType.toString() + this.residuePosition);
            Tooltip.install(this.nucleotide, tooltip);
        }
        return nucleotide;
    }

    public BaseType getBaseType() {
        return baseType;
    }

    public Node getRibose() {
        return ribose;
    }

    public void setNucleotide(Group nucleotide) {
        this.nucleotide = nucleotide;
    }

    public boolean getModelFilled() {
        return modelFilled.get();
    }

    public BooleanProperty modelFilledProperty() {
        return modelFilled;
    }

    public void setModelFilled(boolean modelFilled) {
        this.modelFilled.set(modelFilled);
    }

    public int getResiduePosition() {
        return residuePosition;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public void setColor() {
        if(this.ribose != null){
            this.ribose.setMaterial(GlobalSettings.SELECTED_MATERIAL);
        }

        this.base.setColor();
    }

    @Override
    public void resetColor() {
        if(this.ribose != null){
            this.ribose.setMaterial((GlobalSettings.RIBOSE_MATERIAL));
        }

        this.base.resetColor();
    }

    @Override
    public void setOriginalColor() {

    }
}
