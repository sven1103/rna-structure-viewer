import javafx.application.Application;
import javafx.stage.Stage;
import models.nucleotide3d.RnaStrucViewer3dModel;
import presenters.RnaStrucViewer3dPresenter;
import views.RnaStrucViewer3dView;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/2/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class Main extends Application{
    public static void main(String[] args){

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        RnaStrucViewer3dView view = RnaStrucViewer3dView.getInstance();
        RnaStrucViewer3dModel model = new RnaStrucViewer3dModel();
        RnaStrucViewer3dPresenter presenter = new RnaStrucViewer3dPresenter(view, primaryStage, model);

        primaryStage.setScene(view.totalScene);

        primaryStage.show();
    }
}
