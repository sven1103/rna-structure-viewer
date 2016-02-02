package models.nucleotide3d;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import models.misc.Atom;
import models.misc.AtomMapping;
import models.misc.Constants;

import java.util.Arrays;


/**
 * Created by svenfillinger on 13.12.15.
 */
public class Uracil extends PyrimidineModel{

    public Uracil(){
        super();
        this.material = new PhongMaterial(Color.LIGHTSKYBLUE);
    }


    @Override
    public PyrimidineModel setAtomCoords(Atom atom) {
        if(!atom.getBaseType().equals(BaseType.U)){
            return this;
        } else{
            makeAtomCoords(atom);
        }
        return this;
    }

    @Override
    void evaluateModel() {
        boolean isFilledCompletely = true;
        for(float coordinate : atomCoords){
            if(coordinate == 0){
                isFilledCompletely = false;
                break;
            }
        }
        if(!(hBondMap.containsKey("O4") && hBondMap.containsKey("N3") && hBondMap.containsKey("H3"))){
            isFilledCompletely = false;
        }
        modelFilledComplete.setValue(isFilledCompletely);
    }

    @Override
    public Atom[] getHBondAtoms(){
        Atom[] atomList = new Atom[2];
        atomList[0] = hBondMap.get("O4");
        atomList[1] = hBondMap.get("N3");
        return atomList;
    }

    public Uracil makeAdditionalElements(){
        Sphere nitrogen = new Sphere();
        CovalentBond bond = new CovalentBond();

        nitrogen.setMaterial(new PhongMaterial(Constants.OXYGEN_COLOR));
        nitrogen.setRadius(Constants.EXTRA_ATOMS_RADIUS);
        nitrogen.setTranslateX(hBondMap.get("O4").getCoords()[0]);
        nitrogen.setTranslateY(hBondMap.get("O4").getCoords()[1]);
        nitrogen.setTranslateZ(hBondMap.get("O4").getCoords()[2]);

        bond.setStartAtom(Arrays.copyOfRange(this.atomCoords, AtomMapping.PYRIMIDINE_MAPPING.get("C4"),
                AtomMapping.PYRIMIDINE_MAPPING.get("C4") + 3));
        bond.setEndAtom(this.hBondMap.get("O4").getCoords());

        this.otherVisualElements.getChildren().addAll(nitrogen, bond.createConnection(0.02));
        return this;
    }

}
