package models.nucleotide3d;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import models.misc.Atom;
import models.misc.AtomMapping;

/**
 * Created by svenfillinger on 13.12.15.
 */
public abstract class PurineModel extends BaseModel{

    private final int NUMBER_ATOMS = 27;

    private float[] texCoords = new float[]
            {
                    0, 1, // 1st triangle point
                    0.5f, 0.5f, // 2nd triangle point
                    0.5f, 1, // 3rd triangle point
            };

    private int[] faces = new int[]
            {
                    7,0,4,1,6,2,
                    7,0,6,2,4,1,
                    7,0,8,1,4,2,
                    7,0,4,2,8,1,
                    8,0,3,1,4,2,
                    8,0,4,2,3,1,
                    4,0,0,1,5,2,
                    4,0,5,2,0,1,
                    4,0,3,1,0,2,
                    4,0,0,2,3,1,
                    3,0,2,1,0,2,
                    3,0,0,2,2,1,
                    2,0,1,1,0,2,
                    2,0,0,2,1,1
            };


    public PurineModel(){
        this.atomCoords = new float[NUMBER_ATOMS];
    }


    public abstract PurineModel setAtomCoords(Atom atom);

    protected void makeAtomCoords(Atom atom){
        if(!AtomMapping.PURINE_MAPPING.containsKey(atom.getAtomName())){
            // DEbugging
        } else{
            int position = AtomMapping.PURINE_MAPPING.get(atom.getAtomName());
            System.arraycopy(atom.getCoords(), 0, this.atomCoords, position, 3);
        }
        if(AtomMapping.PURINE_HBONDS.contains(atom.getAtomName())){
           this.hBondMap.put(atom.getAtomName(), atom);
        }
        evaluateModel();
    }

    abstract void evaluateModel();

    /**
     * Make the MeshView of the ribose molecule
     * @return The MeshView representation of ribose
     */
    public PurineModel makeMesh(){

        TriangleMesh mesh = new TriangleMesh();

        mesh.getPoints().addAll(atomCoords);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);
        mesh.getFaceSmoothingGroups().addAll(0, 1, 0, 1,
                                             0, 1, 0, 1,
                                             0, 1, 0, 1,
                                             0, 1);

        MeshView meshView = new MeshView();
        meshView.setMesh(mesh);
        meshView.setMaterial(material);


        //sugarMeshView.setTranslateZ();
        this.meshView = meshView;
        return this;
    }


    public void resetCoords(){
        this.atomCoords = new float[NUMBER_ATOMS];
    }

}
