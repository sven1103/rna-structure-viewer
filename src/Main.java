import javafx.application.Application;
import javafx.stage.Stage;
import models.nucleotide3d.RnaStrucViewer3dModel;
import presenters.MainPresenter;
import presenters.RnaStrucViewer3dPresenter;
import views.MainView;
import views.PrimaryStructureView;
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

        RnaStrucViewer3dView view3d = RnaStrucViewer3dView.getInstance();
        PrimaryStructureView view1d = PrimaryStructureView.getInstance();
        MainView mainView = MainView.getInstance(view3d, view1d);
        RnaStrucViewer3dModel model = new RnaStrucViewer3dModel();
        RnaStrucViewer3dPresenter presenter = new RnaStrucViewer3dPresenter(view3d, primaryStage, model, mainView);

        MainPresenter.setUpMainPresenter(presenter, view3d, mainView);

        primaryStage.setScene(mainView.finalScene);

        primaryStage.show();
    }
}
