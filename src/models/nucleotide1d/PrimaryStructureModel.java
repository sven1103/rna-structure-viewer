package models.nucleotide1d;

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

    private TextFlow ruler = new TextFlow();

    private String sequence = "";

    private String dotBracketString = "";


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
        int index = 1;
        int whiteSpace = 9;
        ruler.getChildren().addAll(new SimpleNucleotide(Integer.toString(index)));
        this.sequence = sequence;
        this.dotBracketString = notation;
        for(Character nucleotide : sequence.toCharArray()){
            primaryStructureList.add(new SimpleNucleotide(nucleotide.toString()));
            if(index % 10 == 0){
                ruler.getChildren().addAll(new SimpleNucleotide(Integer.toString(index)));
                whiteSpace = 10 - String.valueOf(index).length();
            } else{
                if(index != 1) {
                    if(whiteSpace > 0){
                        ruler.getChildren().addAll(new SimpleNucleotide(" "));
                        whiteSpace--;
                    }

                }
            }
            index++;
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
        ruler.getChildren().clear();
    }

    public TextFlow getPrimaryStructure(){
        return this.primaryStructure;
    }

    public TextFlow getNotation(){
        return this.notation;
    }

    public TextFlow getRuler(){ return this.ruler; }
    public List<SimpleNucleotide> getStructureList(){
        return this.primaryStructureList;
    }

    public List<SimpleNucleotide> getNotationList(){
        return this.annotationList;
    }

    public String getSequence(){
       return sequence;
    }

    public String getDotBracketString(){
        return dotBracketString;
    }

}
