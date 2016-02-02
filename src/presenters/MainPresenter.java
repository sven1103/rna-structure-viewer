package presenters;

import views.MainView;
import views.RnaStrucViewer3dView;

/**
 * Created by sven on 2/2/16.
 */
public class MainPresenter {

    public static RnaStrucViewer3dPresenter presenter3d;

    public static MainView mainView;

    public static RnaStrucViewer3dView view3d;

    public static void setUpMainPresenter(RnaStrucViewer3dPresenter _presenter3d,
                         RnaStrucViewer3dView _view3d,
                         MainView _mainView){
        presenter3d = _presenter3d;
        view3d = _view3d;
        mainView = _mainView;
    }

}
