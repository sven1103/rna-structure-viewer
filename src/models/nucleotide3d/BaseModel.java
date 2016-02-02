package models.nucleotide3d;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import models.misc.Atom;
import models.misc.Constants;

import java.util.HashMap;

/**
 * Created by sven on 1/17/16.
 */
public class BaseModel implements Cloneable{

    protected MeshView meshView;

    BooleanProperty modelFilledComplete = new SimpleBooleanProperty(false);

    protected HashMap<String, Atom> hBondMap = new HashMap<>();

    protected PhongMaterial material = new PhongMaterial(Color.GRAY);

    protected float[] atomCoords;

    protected Group otherVisualElements = new Group();

    public BaseModel(){

    }

    public void setMaterial(PhongMaterial newMaterial){
        this.material = newMaterial;
    }

    public PhongMaterial getMaterial(){
        return this.material;
    }


    public int evaluateNumberHBonds(BaseModel otherBase){
        return 0;
    }

    public Group getBase(){
        Group finalGroup = new Group();
        finalGroup.getChildren().addAll(this.meshView, this.otherVisualElements);
        return finalGroup;
    }

    protected boolean isHbondAngle(double angle){
        boolean isHBondAngle = false;

        if(angle >= Constants.HBOND_MIN_ANGLE && angle <= Constants.HBOND_MAX_ANGLE){
            isHBondAngle = true;
        }
        return isHBondAngle;
    }

    public float[] getAtomCoords(){
        return this.atomCoords;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BaseModel clone = (BaseModel) super.clone();
        clone.hBondMap = (HashMap<String, Atom>) this.hBondMap.clone();
        this.hBondMap.clear();
        return clone;
    }

    public Atom[] getHBondAtoms(){
        return null;
    }


}
