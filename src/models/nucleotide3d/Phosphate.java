package models.nucleotide3d;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import models.misc.Atom;

/**
 * Created by sven on 1/6/16.
 */
public class Phosphate extends Sphere {

    private final Color color = Color.CHOCOLATE;

    private static final double DEFAULT_RADIUS = 1.0;

    public Phosphate(Atom atom){
        super(DEFAULT_RADIUS);
        make(atom);
    }

    public Phosphate(Atom atom, double radius){
        super(radius);
        make(atom);
    }

    private void make(Atom atom){
        this.setMaterial(new PhongMaterial(this.color));
        this.setTranslateX(atom.getCoords()[0]);
        this.setTranslateY(atom.getCoords()[1]);
        this.setTranslateZ(atom.getCoords()[2]);
    }

}
