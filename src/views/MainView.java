package views;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by sven on 2/2/16.
 */
public class MainView {

    public final double INIT_SCENE_WIDTH = 1400;

    public final double INIT_SCENE_HEIGHT = 600;

    public RnaStrucViewer3dView view3d;

    public PrimaryStructureView view1d;

    public SecondaryStructureView view2d;

    public static volatile MainView instance;

    public VBox topViewContainer;

    public HBox centerViewContainer;

    public Scene finalScene;

    public BorderPane controlsView;

    public MenuBar menuBar;

    public Menu menu;

    public MenuItem openFile;

    public static MainView getInstance(RnaStrucViewer3dView view3d, PrimaryStructureView view1d, SecondaryStructureView view2d){
        if(instance == null){
            synchronized (MainView.class){
                if(instance == null) {
                    instance = new MainView();
                    instance.view3d = view3d;
                    instance.view1d = view1d;
                    instance.view2d = view2d;
                    instance.initView();

                }
            }
        }
        return instance;
    }

    /**
     * Hide the constructor
     */
    private MainView(){}

    /**
     * Initialize the main view
     */
    private void initView(){
        menuBar = new MenuBar();

        menu = new Menu("File");

        openFile = new MenuItem("Open File");

        menu.getItems().addAll(openFile);

        menuBar.getMenus().addAll(menu);

        controlsView = new BorderPane();

        topViewContainer = new VBox();

        topViewContainer.getChildren().addAll(menuBar, view1d.pane1d);

        centerViewContainer = new HBox();

        centerViewContainer.getChildren().addAll(view2d.scene2d, view3d.scene3d);

        centerViewContainer.setSpacing(2);

        controlsView.setTop(topViewContainer);

        controlsView.setCenter(centerViewContainer);

        topViewContainer.setPickOnBounds(false);
        controlsView.setPickOnBounds(false);

        finalScene = new Scene(controlsView, INIT_SCENE_WIDTH, INIT_SCENE_HEIGHT);

        finalScene.getStylesheets().add("format.css");

        view1d.pane1d.setPrefWidth(finalScene.getWidth()-2);
        view1d.primStructureContainer.setMinWidth(finalScene.getWidth()-2);

        finalScene.widthProperty().addListener((observable, oldValue, newValue) -> {
            view1d.pane1d.setPrefWidth(finalScene.getWidth()-2);
            view1d.primStructureContainer.setMinWidth(finalScene.getWidth()-2);
        });

    }
}
