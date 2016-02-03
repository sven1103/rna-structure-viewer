package presenters;

import javafx.scene.text.TextFlow;
import models.nucleotide1d.PrimaryStructureModel;
import views.PrimaryStructureView;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/3/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class PrimaryStructurePresenter {

    private PrimaryStructureView view1d;

    private PrimaryStructureModel primaryModel;

    public PrimaryStructurePresenter(PrimaryStructureView view1d, PrimaryStructureModel primaryModel){
        this.view1d = view1d;
        this.primaryModel = primaryModel;
        init();
    }

    public void makePrimaryView(String sequence, String notation){
        primaryModel.setNewStructure(sequence, notation);
    }

    private void init(){
        primaryModel.getPrimaryStructure().getStyleClass().addAll("nucleotide_text");
        primaryModel.getNotation().getStyleClass().addAll("nucleotide_text");
        view1d.primStructureContainer.getChildren().addAll(primaryModel.getPrimaryStructure(), primaryModel.getNotation());
    }

}
