package models.misc;

import javafx.geometry.Point3D;
import models.nucleotide3d.BaseType;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by sven on 12/12/15.
 */
public class Atom {

    private int residuePos = 0;

    private float[] coords = {0f, 0f, 0f};

    private BaseType baseType = BaseType.N;

    private String atomName = "";

    public Atom(){}

    public Atom setResiduePos(int pos){
        this.residuePos = pos;
        return this;
    }

    public Atom setCoords(float[] coords) throws IOException{
        if (coords.length != 3){
            throw new IOException("Wrong size of coordinate array. Must be of size three.");
        } else{
            this.coords = coords;
        }
        return this;
    }

    public Atom setAtomName(String name){
        this.atomName = name;
        return this;
    }

    public Atom setBaseType(BaseType basteType){
        this.baseType = basteType;
        return this;
    }

    public int getResiduePos() {
        return residuePos;
    }

    public float[] getCoords() {
        return coords;
    }

    public BaseType getBaseType() {
        return baseType;
    }

    public String getAtomName() {
        return atomName;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.residuePos);
        stringBuilder.append(":");
        stringBuilder.append(this.atomName);
        stringBuilder.append(":");
        stringBuilder.append(this.baseType);
        stringBuilder.append(":");
        stringBuilder.append(Arrays.toString(this.coords));
        return stringBuilder.toString();
    }

    /**
     * Calculates the euclidean distance to another atom in three dimensional space
     * @param atom
     * @return
     */
    public float getDistanceTo(Atom atom){
        float[] otherCoords = atom.getCoords();

        float distance = (float) Math.sqrt(Math.pow((coords[0]-otherCoords[0]), 2) +
        Math.pow((coords[1]-otherCoords[1]),2) + Math.pow((coords[2]-otherCoords[2]),2) );
        return distance;
    }

    public double getAngle(Atom a1, Atom a2){
        Point3D vertex = new Point3D(coords[0], coords[1], coords[2]);
        Point3D pA1 = new Point3D(a1.coords[0], a1.coords[1], a1.coords[2]);
        Point3D pA2 = new Point3D(a2.coords[0], a2.coords[1], a2.coords[2]);

        return vertex.angle(pA1, pA2);
    }
}
