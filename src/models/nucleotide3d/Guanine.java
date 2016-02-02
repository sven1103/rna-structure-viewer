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
public class Guanine extends PurineModel {

    public Guanine(){
        super();
        this.material = new PhongMaterial(Color.ORANGERED);
    }

    @Override
    public PurineModel setAtomCoords(Atom atom) {
        if(!atom.getBaseType().equals(BaseType.G)){
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
        if(!(hBondMap.containsKey("O6") && hBondMap.containsKey("N1") &&
                hBondMap.containsKey("H1") && hBondMap.containsKey("N2") &&
                hBondMap.containsKey("H21"))){
            isFilledCompletely = false;
        }
        modelFilledComplete.setValue(isFilledCompletely);
    }

    @Override
    public Atom[] getHBondAtoms(){
        Atom[] atomList = new Atom[3];
        atomList[0] = hBondMap.get("O6");
        atomList[1] = hBondMap.get("N1");
        atomList[2] = hBondMap.get("N2");
        return atomList;
    }

    public Guanine makeAdditionalElements(){
        Sphere nitrogen = new Sphere();
        Sphere oxygen = new Sphere();
        CovalentBond bond = new CovalentBond();
        CovalentBond bond2 = new CovalentBond();


        oxygen.setMaterial(new PhongMaterial(Constants.OXYGEN_COLOR));
        oxygen.setRadius(Constants.EXTRA_ATOMS_RADIUS);
        oxygen.setTranslateX(hBondMap.get("O6").getCoords()[0]);
        oxygen.setTranslateY(hBondMap.get("O6").getCoords()[1]);
        oxygen.setTranslateZ(hBondMap.get("O6").getCoords()[2]);

        nitrogen.setMaterial(new PhongMaterial(Constants.NITROGEN_COLOR));
        nitrogen.setRadius(Constants.EXTRA_ATOMS_RADIUS);
        nitrogen.setTranslateX(hBondMap.get("N2").getCoords()[0]);
        nitrogen.setTranslateY(hBondMap.get("N2").getCoords()[1]);
        nitrogen.setTranslateZ(hBondMap.get("N2").getCoords()[2]);

        bond.setStartAtom(Arrays.copyOfRange(this.atomCoords, AtomMapping.PURINE_MAPPING.get("C6"),
                AtomMapping.PURINE_MAPPING.get("C6") + 3));
        bond.setEndAtom(this.hBondMap.get("O6").getCoords());

        bond2.setStartAtom(Arrays.copyOfRange(this.atomCoords, AtomMapping.PURINE_MAPPING.get("C2"),
                AtomMapping.PURINE_MAPPING.get("C2") + 3));
        bond2.setEndAtom(this.hBondMap.get("N2").getCoords());


        this.otherVisualElements.getChildren().addAll(oxygen, nitrogen,
                bond2.createConnection(0.02), bond.createConnection(0.02));
        return this;
    }

}
