package presenters;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import views.MainView;
import views.RnaStrucViewer3dView;

/**
 * Created by sven on 2/2/16.
 */
public class MainPresenter {

    static final KeyCode DESELECTION = KeyCode.D;

    static final KeyCode UPDATE_VIEW = KeyCode.R;

    public static RnaStrucViewer3dPresenter presenter3d;

    public static MainView mainView;

    public static RnaStrucViewer3dView view3d;

    public static PrimaryStructurePresenter primaryStructurePresenter;

    public static SecondaryStructurePresenter secondaryStructurePresenter;

    public static void setUpMainPresenter(RnaStrucViewer3dPresenter _presenter3d,
                         RnaStrucViewer3dView _view3d,
                         MainView _mainView, PrimaryStructurePresenter _primaryPresenter,
                                          SecondaryStructurePresenter _secondaryPresenter){
        presenter3d = _presenter3d;
        view3d = _view3d;
        mainView = _mainView;
        primaryStructurePresenter = _primaryPresenter;
        secondaryStructurePresenter = _secondaryPresenter;

        mainView.finalScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == DESELECTION){
                    if(SelectionModelPresenter.nucleotideSelectionModel != null){
                        SelectionModelPresenter.nucleotideSelectionModel.clearSelection();
                        MainPresenter.refreshAll();
                    }

                }
            }
        });

        mainView.finalScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == UPDATE_VIEW){
                    if(MainPresenter.presenter3d != null &&
                            MainPresenter.secondaryStructurePresenter != null){
                        MainView.instance.view3d.update();
                        secondaryStructurePresenter.drawStructure();
                        refreshAll();
                    }
                }
            }
        });
    }

    public static void refreshAll(){
        presenter3d.refreshSelectionStatus();
        primaryStructurePresenter.refreshSelectionStatus();
        secondaryStructurePresenter.refreshSelectionStatus();
    }



}
