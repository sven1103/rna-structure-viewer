package models.pdb;

import models.misc.Atom;
import models.nucleotide3d.BaseType;
import system.PDBparseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sven on 12/12/15.
 */
public class PDBparser {

    /**
     * The current parser instance
     */
    public static volatile PDBparser instance;

    /**
     * Will store the parsed atoms
     */
    private List<Atom> atomList;

    /**
     * Will store phosphate atoms only
     * from which the geometrical center of
     * the molecule will be computed
     */
    private List<Atom> atomPhosphateList;

    /**
     * Make the constructor private
     */
    private PDBparser(){}

    /**
     * Singleton getInstance()
     * @return PDPparser
     */
    public static PDBparser getInstance() {
        if(instance == null){
            synchronized (PDBparser.class){
                if(instance == null) {
                    instance = new PDBparser();
                }
            }
        }
        return instance;
    }

    /**
     * Parses a pdb-file containing RNA structures
     * @param pdbFile The path to the pdb-file
     * @return this
     */
    public PDBparser parsePDB(String pdbFile) throws PDBparseException{

        atomList = new ArrayList<>();   // Will store the atoms

        atomPhosphateList = new ArrayList<>();  // Will store the phosphate atoms only
        /*
        Read in the pdb file
         */
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pdbFile))){
            String line;

            while((line = bufferedReader.readLine()) != null){

                // Check if line is of record type 'ATOM'
                if( !line.startsWith("ATOM")){
                    if (line.startsWith("TER")){
                        break;
                    }
                    continue;
                }

                Atom atom = new Atom();

                /*
                Extract the important information, such as residue name, position and coordinates
                 */
                String atomName = line.substring(PDBcolumns.ATOM_NAME_START, PDBcolumns.ATOM_NAME_END+1);
                String residueName = line.substring(PDBcolumns.RESIDUE_NAME_START, PDBcolumns.RESIDUE_NAME_END+1);
                String residuePos = line.substring(PDBcolumns.RESIDUE_SEQ_NUMBER_START, PDBcolumns.RESIDUE_SEQ_NUMBER_END+1);
                String xCoord = line.substring(PDBcolumns.ATOM_X_START, PDBcolumns.ATOM_X_END+1);
                String yCoord = line.substring(PDBcolumns.ATOM_Y_START, PDBcolumns.ATOM_Y_END+1);
                String zCoord = line.substring(PDBcolumns.ATOM_Z_START, PDBcolumns.ATOM_Z_END+1);

                /*
                Fill the atom with the parsed information
                 */
                atom.setAtomName(removeWhiteSpace(atomName));
                atom.setBaseType(evaluateBaseType(residueName));
                atom.setResiduePos(Integer.parseInt(removeWhiteSpace(residuePos)));
                atom.setCoords(new float[]{makeFloat(xCoord),
                                           makeFloat(yCoord),
                                           makeFloat(zCoord)});
                /*
                Adds the atom to the phosphate list, for
                calculation of the geometric center
                 */
                if(atom.getAtomName().equals("P")){
                    atomPhosphateList.add(atom);
                }
                // Add the atom to the list
                atomList.add(atom);
            }

        } catch (IOException e){
            throw new PDBparseException(e);
        }

        if(atomList.isEmpty()){
            throw new PDBparseException();
        }

        centerAtoms();

        return this;
    }


    public void centerAtoms(){

        List<Float> x = new ArrayList<>();
        List<Float> y = new ArrayList<>();
        List<Float> z = new ArrayList<>();
        float xAverage = 0f;
        float yAverage = 0f;
        float zAverage = 0f;

        for(Atom atom : atomPhosphateList){
            float[] coords = atom.getCoords();
            x.add(coords[0]);
            y.add(coords[1]);
            z.add(coords[2]);
        }

        xAverage = x.stream().reduce(0.f, (a, b) -> (a + b)) / x.size();
        yAverage = y.stream().reduce(0.f, (a, b) -> (a + b)) / y.size();
        zAverage = z.stream().reduce(0.f, (a, b) -> (a + b)) / z.size();


        for(Atom atom : atomList){
            float[] coordsToAdjust = atom.getCoords();
            coordsToAdjust[0] -= xAverage;
            coordsToAdjust[1] -= yAverage;
            coordsToAdjust[2] -= zAverage;

            try{
                atom.setCoords(coordsToAdjust);
            } catch (Exception e){
                System.err.println("Could not center the atom");
            }

        }

    }


    /**
     * Aquire the atom list
     * @return
     */
    public List<Atom> getAtomList(){
        return this.atomList;
    }

    /**
     * Helper function for removing whitespaces in strings
     * @param string The target string
     * @return Formatted string
     */
    private String removeWhiteSpace(String string){
        return string.replaceAll("\\s", "");
    }

    /**
     * Helper function for converting a string to float
     * @param string The target string
     * @return The parsed float
     */
    private Float makeFloat(String string){
        string = removeWhiteSpace(string);
        return Float.parseFloat(string);
    }

    /**
     * Determines the base type and sets an
     * enum type for robustness.
     * @param string The string to be checked
     * @return The parsed BaseType enum
     */
    private BaseType evaluateBaseType(String string){
        BaseType baseType = BaseType.N;
        string = removeWhiteSpace(string);
        switch(string){
            case "A":
                baseType = BaseType.A;
                break;
            case "ADE":
                baseType = BaseType.A;
                break;
            case "C":
                baseType = BaseType.C;
                break;
            case "CYT":
                baseType = BaseType.C;
                break;
            case "G":
                baseType = BaseType.G;
                break;
            case "GUA":
                baseType = BaseType.G;
                break;
            case "U":
                baseType = BaseType.U;
                break;
            case "URA":
                baseType = BaseType.U;
                break;
            default:
                baseType = BaseType.N;
        }
        return baseType;
    }

    interface FloatOperation {
        float operation(float a, float b);
    }
}
