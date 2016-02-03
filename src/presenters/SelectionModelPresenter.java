package presenters;

import models.nucleotide3d.Nucleotide;
import models.selection.MySelectionModel;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/3/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class SelectionModelPresenter {

    /**
     * This static variable represents the global selection model
     * and should be accessable from all other presenters.
     */
    public static MySelectionModel<Nucleotide> nucleotideSelectionModel;

}
