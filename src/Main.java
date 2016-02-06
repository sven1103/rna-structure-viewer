import javafx.application.Application;
import javafx.stage.Stage;
import models.nucleotide1d.PrimaryStructureModel;
import models.nucleotide3d.RnaStrucViewer3dModel;
import presenters.MainPresenter;
import presenters.PrimaryStructurePresenter;
import presenters.RnaStrucViewer3dPresenter;
import presenters.SecondaryStructurePresenter;
import views.MainView;
import views.PrimaryStructureView;
import views.RnaStrucViewer3dView;
import views.SecondaryStructureView;

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
        SecondaryStructureView view2d = SecondaryStructureView.getInstance();
        MainView mainView = MainView.getInstance(view3d, view1d, view2d);

        PrimaryStructureModel model1d = new PrimaryStructureModel();
        RnaStrucViewer3dModel model = new RnaStrucViewer3dModel();

        PrimaryStructurePresenter presenter1d = new PrimaryStructurePresenter(view1d, model1d);
        RnaStrucViewer3dPresenter presenter = new RnaStrucViewer3dPresenter(view3d, primaryStage, model, mainView);
        SecondaryStructurePresenter presenter2d = new SecondaryStructurePresenter(view2d, mainView);
        MainPresenter.setUpMainPresenter(presenter, view3d, mainView, presenter1d, presenter2d);

        primaryStage.setScene(mainView.finalScene);

        primaryStage.show();
    }
}
