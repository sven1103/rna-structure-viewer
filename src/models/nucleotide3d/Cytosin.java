package models.nucleotide3d;

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
public class Cytosin extends PyrimidineModel {


    public Cytosin(){
        super();
        this.baseType = BaseType.C;
        this.material = GlobalSettings.DEFAULT_BASE_MATERIALS.get(baseType);
    }


    @Override
    public PyrimidineModel setAtomCoords(Atom atom) {

        if(!atom.getBaseType().equals(BaseType.C)){
            return this;
        } else {
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
        if(!(hBondMap.containsKey("N4") && hBondMap.containsKey("H41") &&
                hBondMap.containsKey("N3") && hBondMap.containsKey("O2"))){
            isFilledCompletely = false;
        }
        modelFilledComplete.setValue(isFilledCompletely);
    }


    @Override
    public int evaluateNumberHBonds(BaseModel otherBase){
        if(!otherBase.getClass().getName().contains("Guanine")){
            return -1;
        } else{
            float prelDistance = hBondMap.get("H41").getDistanceTo(otherBase.hBondMap.get("O6"));
            if(prelDistance == 0 || prelDistance > 10 ){
                return -1;
            }
            return numberHBonds(otherBase.hBondMap);
        }
    }


    private int numberHBonds(HashMap<String, Atom> otherHBondMap){
        int numberHBonds = -1;
        float firstHBondDistance = hBondMap.get("H41").getDistanceTo(otherHBondMap.get("O6"));
        float secondHBondDistance = hBondMap.get("N3").getDistanceTo(otherHBondMap.get("H1"));
        float thirdHBondDistance = hBondMap.get("O2").getDistanceTo(otherHBondMap.get("H21"));

        boolean firstBondInrange = firstHBondDistance >= Constants.HBOND_MIN_DISTANCE && firstHBondDistance <= Constants.HBOND_MAX_DISTANCE;
        boolean secondBondInrange = secondHBondDistance >= Constants.HBOND_MIN_DISTANCE && secondHBondDistance <= Constants.HBOND_MAX_DISTANCE;
        boolean thirdBondInrange = thirdHBondDistance >= Constants.HBOND_MIN_DISTANCE && secondHBondDistance <= Constants.HBOND_MAX_DISTANCE;

        if(firstBondInrange || secondBondInrange || thirdBondInrange){
            numberHBonds = 3;
            double angle1 = hBondMap.get("H41").getAngle(otherHBondMap.get("O6"), hBondMap.get("N4"));
            double angle2 = otherHBondMap.get("H1").getAngle(otherHBondMap.get("N1"), hBondMap.get("N3"));
            double angle3 = otherHBondMap.get("H21").getAngle(otherHBondMap.get("N2"), hBondMap.get("O2"));

            boolean[] hBondEval = {isHbondAngle(angle1), isHbondAngle(angle2), isHbondAngle(angle3)};

            int countWrongAngles = 0;
            for(int i = 0; i < hBondEval.length; i++){
                if(!hBondEval[i]){
                    countWrongAngles++;
                }
            }

            if (countWrongAngles > 2) {
                numberHBonds = -1;
            }
        }
        return numberHBonds;
    }


    @Override
    public Atom[] getHBondAtoms(){
        Atom[] atomList = new Atom[3];
        atomList[0] = hBondMap.get("N4");
        atomList[1] = hBondMap.get("N3");
        atomList[2] = hBondMap.get("O2");
        return atomList;
    }

    public Cytosin makeAdditionalElements() {
        Sphere nitrogen = new Sphere();
        Sphere oxygen = new Sphere();
        CovalentBond bond = new CovalentBond();
        CovalentBond bond2 = new CovalentBond();


        oxygen.setMaterial(new PhongMaterial(Constants.OXYGEN_COLOR));
        oxygen.setRadius(Constants.EXTRA_ATOMS_RADIUS);
        oxygen.setTranslateX(hBondMap.get("O2").getCoords()[0]);
        oxygen.setTranslateY(hBondMap.get("O2").getCoords()[1]);
        oxygen.setTranslateZ(hBondMap.get("O2").getCoords()[2]);

        nitrogen.setMaterial(new PhongMaterial(Constants.NITROGEN_COLOR));
        nitrogen.setRadius(Constants.EXTRA_ATOMS_RADIUS);
        nitrogen.setTranslateX(hBondMap.get("N4").getCoords()[0]);
        nitrogen.setTranslateY(hBondMap.get("N4").getCoords()[1]);
        nitrogen.setTranslateZ(hBondMap.get("N4").getCoords()[2]);

        bond.setStartAtom(Arrays.copyOfRange(this.atomCoords, AtomMapping.PYRIMIDINE_MAPPING.get("C2"),
                AtomMapping.PYRIMIDINE_MAPPING.get("C2") + 3));
        bond.setEndAtom(this.hBondMap.get("O2").getCoords());

        bond2.setStartAtom(Arrays.copyOfRange(this.atomCoords, AtomMapping.PYRIMIDINE_MAPPING.get("C4"),
                AtomMapping.PYRIMIDINE_MAPPING.get("C4") + 3));
        bond2.setEndAtom(this.hBondMap.get("N4").getCoords());


        this.otherVisualElements.getChildren().addAll(oxygen, nitrogen,
                bond2.createConnection(0.02), bond.createConnection(0.02));
        return this;
    }


}
