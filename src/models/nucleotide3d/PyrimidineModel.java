package models.nucleotide3d;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import models.misc.Atom;
import models.misc.AtomMapping;
import models.misc.GlobalSettings;

/**
 * Created by svenfillinger on 13.12.15.
 */
abstract class PyrimidineModel extends BaseModel{

    private final int NUMBER_ATOMS = 18;

    private float[] texCoords = new float[]
            {
                    0, 1, // 1st triangle point
                    0.5f, 0.5f, // 2nd triangle point
                    0.5f, 1, // 3rd triangle point
            };

    private int[] faces = new int[]
            {
                    5, 0, 3, 1, 4, 2,  // font face triangle 1
                    5, 0, 4, 2, 3, 1,  // back face triangle 1
                    5, 0, 0, 1, 3, 2,  // font face triangle 2
                    5, 0, 3, 2, 0, 1,  // back face triangle 2
                    0, 0, 2, 1, 3, 2,  // font face triangle 3
                    0, 0, 3, 2, 2, 1,   // back face triangle 3
                    0, 0, 1, 1, 2, 2,  // font face triangle 4
                    0, 0, 2, 2, 1, 1   // back face triangle 4
            };


    public PyrimidineModel(){
        this.atomCoords = new float[NUMBER_ATOMS];
    }

    public abstract PyrimidineModel setAtomCoords(Atom atom);


    protected void makeAtomCoords(Atom atom){
        if(!AtomMapping.PYRIMIDINE_MAPPING.containsKey(atom.getAtomName())){

        } else{
            int position = AtomMapping.PYRIMIDINE_MAPPING.get(atom.getAtomName());
            System.arraycopy(atom.getCoords(), 0, this.atomCoords, position, 3);
        }
        if(AtomMapping.PYRIMIDINE_HBONDS.contains(atom.getAtomName())){
            this.hBondMap.put(atom.getAtomName(), atom);
        }
        evaluateModel();
    }

    abstract void evaluateModel();


    /**
     * Make the MeshView of the ribose molecule
     * @return The MeshView representation of ribose
     */
    public PyrimidineModel makeMesh(){

        TriangleMesh mesh = new TriangleMesh();

        mesh.getPoints().addAll(atomCoords);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);
        mesh.getFaceSmoothingGroups().addAll(0, 1, 0, 1, 0, 1, 0, 1);

        MeshView meshView = new MeshView();
        meshView.setMesh(mesh);
        meshView.setMaterial(this.material);


        //sugarMeshView.setTranslateZ();
        this.meshView = meshView;
        return this;
    }


    public void resetCoords(){
        this.atomCoords = new float[NUMBER_ATOMS];
    }

}
