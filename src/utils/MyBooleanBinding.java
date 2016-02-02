package utils;

import javafx.beans.binding.BooleanBinding;
import models.selection.MySelectionModel;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/2/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class MyBooleanBinding extends BooleanBinding {

    private int index;

    private MySelectionModel model;

    public void setIndex(int i){
        this.index = i;
    }

    public MyBooleanBinding(MySelectionModel model){
        super();
        this.model = model;
    }

    @Override
    protected boolean computeValue() {
        return model.getSelectedIndices().contains(index);
    }
}
