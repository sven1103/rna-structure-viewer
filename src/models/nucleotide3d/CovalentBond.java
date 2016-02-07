package models.nucleotide3d;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * advanced-java-bioinformatics
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 12/15/15
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class CovalentBond {

    private Point3D startAtom;

    private Point3D endAtom;

    BooleanProperty fullBondSet = new SimpleBooleanProperty(false);

    private Color color = Color.WHITE;

    public CovalentBond(){
    }

    public Point3D getStartAtom() {
        return startAtom;
    }

    public void setStartAtom(float[] startAtom) {
        this.startAtom = new Point3D(startAtom[0], startAtom[1], startAtom[2]);
        checkAllPointsSet();
    }

    public Point3D getEndAtom() {
        return endAtom;
    }

    public void setEndAtom(float[] endAtom) {
        this.endAtom = new Point3D(endAtom[0], endAtom[1], endAtom[2]);
        checkAllPointsSet();
    }

    public void setColor(Color color){
        this.color = color;
    }

    private void checkAllPointsSet(){
        if(startAtom != null && endAtom != null){
            this.fullBondSet.setValue(true);
        } else {
            this.fullBondSet.set(false);
        }
    }

    public void resetBond(){
        this.startAtom = null;
        this.endAtom = null;
        this.fullBondSet.setValue(false);
    }


    /**
     * Code ref: http://netzwerg.ch/blog/2015/03/22/javafx-3d-line/
     * @param radius the radius of the cylinder
     * @return the correct scaled and rotated cylinder
     */
    public Cylinder createConnection(double radius) {
        if(startAtom != null && endAtom != null){
            Point3D yAxis = new Point3D(0, 1, 0);
            Point3D diff = this.endAtom.subtract(this.startAtom);
            double height = diff.magnitude();

            Point3D mid = this.endAtom.midpoint(this.startAtom);
            Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

            Point3D axisOfRotation = diff.crossProduct(yAxis);
            double angle = Math.acos(diff.normalize().dotProduct(yAxis));
            Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

            Cylinder line = new Cylinder(radius, height);

            line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
            line.setMaterial(new PhongMaterial(color));

            this.endAtom = null;
            this.startAtom = null;

            return line;
        } else{
            return new Cylinder();
        }
    }

}
