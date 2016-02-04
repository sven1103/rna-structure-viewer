package presenters;

import javafx.beans.binding.BooleanBinding;
import models.nucleotide1d.PrimaryStructureModel;
import models.nucleotide1d.SimpleNucleotide;
import views.PrimaryStructureView;

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
public class PrimaryStructurePresenter implements IRefresher {

    private PrimaryStructureView view1d;

    private PrimaryStructureModel primaryModel;

    public PrimaryStructurePresenter(PrimaryStructureView view1d, PrimaryStructureModel primaryModel){
        this.view1d = view1d;
        this.primaryModel = primaryModel;
        init();
    }

    public void makePrimaryView(String sequence, String notation){
        primaryModel.setNewStructure(sequence, notation);
        /*
        Connect to selection model
         */
        List<SimpleNucleotide> structure = primaryModel.getStructureList();
        for(SimpleNucleotide nucleotide : structure){
            final int index = structure.indexOf(nucleotide);

            setSelectionModelConnection(nucleotide, index);

            bindToSelectionModel(nucleotide, index);

        }

        List<SimpleNucleotide> annotation = primaryModel.getNotationList();
        for(SimpleNucleotide nucleotide : annotation){
            final int index = annotation.indexOf(nucleotide);

            setSelectionModelConnection(nucleotide, index);

            bindToSelectionModel(nucleotide, index);
        }

        //view1d.pane1d.setMinViewportWidth(primaryModel.getNotation().getWidth());
    }


    private void bindToSelectionModel(SimpleNucleotide nucleotide, int index){
        final int finalIndex = index;
        nucleotide.isSelectedProperty().bind(new BooleanBinding() {
            {
                bind(SelectionModelPresenter.nucleotideSelectionModel.getSelectedIndices());
            }
            @Override
            protected boolean computeValue() {
                return SelectionModelPresenter.nucleotideSelectionModel.getSelectedIndices().contains(finalIndex);
            }
        });
    }

    private void setSelectionModelConnection(SimpleNucleotide nucleotide, int index){
        nucleotide.setOnMouseClicked(e -> {
            if(!e.isShiftDown()){
                SelectionModelPresenter.nucleotideSelectionModel.clearSelection();
            }
            if(SelectionModelPresenter.nucleotideSelectionModel.isSelected(index)){
                SelectionModelPresenter.nucleotideSelectionModel.clearSelection(index);
            } else{
                SelectionModelPresenter.nucleotideSelectionModel.select(index);
            }
            MainPresenter.refreshAll();
        });
    }

    private void init(){
        primaryModel.getPrimaryStructure().getStyleClass().addAll("nucleotide_text");
        primaryModel.getNotation().getStyleClass().addAll("nucleotide_text");

        view1d.primStructureContainer.getChildren().addAll(primaryModel.getPrimaryStructure(), primaryModel.getNotation());
    }

    @Override
    public void refreshSelectionStatus() {
        for(SimpleNucleotide nucleotide : primaryModel.getStructureList()){
            if(nucleotide.isSelectedProperty().get()){
                nucleotide.setColor();
            } else{
                nucleotide.resetColor();
            }
        }
        for(SimpleNucleotide nucleotide : primaryModel.getNotationList()){
            if(nucleotide.isSelectedProperty().get()){
                nucleotide.setColor();
            } else{
                nucleotide.resetColor();
            }
        }

    }
}
