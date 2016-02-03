package models.nucleotide1d;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/3/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class PrimaryStructureModel {

    private TextFlow primaryStructure = new TextFlow();

    private TextFlow notation = new TextFlow();

    private List<SimpleNucleotide> primaryStructureList = new ArrayList<>();
    {
        primaryStructureList.add(new SimpleNucleotide("A"));
        primaryStructureList.add(new SimpleNucleotide("C"));
        primaryStructureList.add(new SimpleNucleotide("G"));
        primaryStructureList.add(new SimpleNucleotide("U"));
    }

    private List<SimpleNucleotide> annotationList = new ArrayList<>();
    {
        annotationList.add(new SimpleNucleotide("("));
        annotationList.add(new SimpleNucleotide("("));
        annotationList.add(new SimpleNucleotide(")"));
        annotationList.add(new SimpleNucleotide(")"));
    }


    public PrimaryStructureModel(){
        init();
    }


    private void init(){
        primaryStructure.getChildren().addAll(primaryStructureList);
        notation.getChildren().addAll(annotationList);
    }


    public void setNewStructure(String sequence, String notation){
        clearAll();
        for(Character nucleotide : sequence.toCharArray()){
            primaryStructureList.add(new SimpleNucleotide(nucleotide.toString()));
        }
        for(Character notationChar : notation.toCharArray()){
            annotationList.add(new SimpleNucleotide(notationChar.toString()));
        }
        init();
    }

    private void clearAll(){
        primaryStructureList.clear();
        annotationList.clear();
        primaryStructure.getChildren().clear();
        notation.getChildren().clear();
    }

    public TextFlow getPrimaryStructure(){
        return this.primaryStructure;
    }

    public TextFlow getNotation(){
        return this.notation;
    }

    public List<SimpleNucleotide> getStructureList(){
        return this.primaryStructureList;
    }

    public List<SimpleNucleotide> getNotationList(){
        return this.annotationList;
    }

}
