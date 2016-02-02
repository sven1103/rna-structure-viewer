package models.nucleotide3d;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import models.misc.Atom;
import models.misc.AtomMapping;
import models.misc.Constants;
import models.misc.GlobalSettings;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by svenfillinger on 13.12.15.
 */
public class Adenine extends PurineModel {


    public Adenine(){
        super();
        this.baseType = BaseType.A;
        this.material = GlobalSettings.DEFAULT_BASE_MATERIALS.get(baseType);
    }

    @Override
    public PurineModel setAtomCoords(Atom atom) {
        if(!atom.getBaseType().equals(BaseType.A)){
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
        if(!(hBondMap.containsKey("H62") && hBondMap.containsKey("N6") && hBondMap.containsKey("N1"))){
            isFilledCompletely = false;
        }
        modelFilledComplete.setValue(isFilledCompletely);
    }



    @Override
    public int evaluateNumberHBonds(BaseModel otherBase){
        if(!otherBase.getClass().getName().contains("Uracil")){
            return -1;
        } else{
            float prelDistance = hBondMap.get("H62").getDistanceTo(otherBase.hBondMap.get("O4"));
            if(prelDistance == 0 || prelDistance > 10 ){
                return -1;
            }
            return numberHBonds(otherBase.hBondMap);
        }
    }

    private int numberHBonds(HashMap<String, Atom> otherHBondMap){
        int numberHBonds = -1;
        float firstHBondDistance = hBondMap.get("H62").getDistanceTo(otherHBondMap.get("O4"));
        float secondHBondDistance = hBondMap.get("N1").getDistanceTo(otherHBondMap.get("H3"));

        boolean firstBondInrange = firstHBondDistance >= Constants.HBOND_MIN_DISTANCE && firstHBondDistance <= Constants.HBOND_MAX_DISTANCE;
        boolean secondBondInrange = secondHBondDistance >= Constants.HBOND_MIN_DISTANCE && secondHBondDistance <= Constants.HBOND_MAX_DISTANCE;

       if(firstBondInrange || secondBondInrange){
           numberHBonds = 2;
           double angle1 = hBondMap.get("H62").getAngle(otherHBondMap.get("O4"), hBondMap.get("N6"));
           double angle2 = otherHBondMap.get("H3").getAngle(otherHBondMap.get("N3"), hBondMap.get("N1"));

           if(!isHbondAngle(angle1) && !isHbondAngle(angle2)){
               numberHBonds = -1;
           }
       }

        return numberHBonds;
    }

    @Override
    public Atom[] getHBondAtoms(){
        Atom[] atomList = new Atom[2];
        atomList[0] = hBondMap.get("N6");
        atomList[1] = hBondMap.get("N1");
        return atomList;
    }

    public Adenine makeAdditionalElements(){
        Sphere nitrogen = new Sphere();
        CovalentBond bond = new CovalentBond();

        nitrogen.setMaterial(new PhongMaterial(Constants.NITROGEN_COLOR));
        nitrogen.setRadius(Constants.EXTRA_ATOMS_RADIUS);
        nitrogen.setTranslateX(hBondMap.get("N6").getCoords()[0]);
        nitrogen.setTranslateY(hBondMap.get("N6").getCoords()[1]);
        nitrogen.setTranslateZ(hBondMap.get("N6").getCoords()[2]);

        bond.setStartAtom(Arrays.copyOfRange(this.atomCoords, AtomMapping.PURINE_MAPPING.get("C6"),
                AtomMapping.PURINE_MAPPING.get("C6") + 3));
        bond.setEndAtom(this.hBondMap.get("N6").getCoords());

        this.otherVisualElements.getChildren().addAll(nitrogen, bond.createConnection(0.02));
        return this;
    }



}
