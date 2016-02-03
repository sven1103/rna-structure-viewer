package views;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by sven on 2/2/16.
 */
public class MainView {

    public final double INIT_SCENE_WIDTH = 800;

    public final double INIT_SCENE_HEIGHT = 600;

    public RnaStrucViewer3dView view3d;

    public PrimaryStructureView view1d;

    public static volatile MainView instance;

    public VBox topViewContainer;

    public StackPane finalView;

    public Scene finalScene;

    public BorderPane controlsView;

    public MenuBar menuBar;

    public Menu menu;

    public MenuItem openFile;

    public static MainView getInstance(RnaStrucViewer3dView view3d, PrimaryStructureView view1d){
        if(instance == null){
            synchronized (MainView.class){
                if(instance == null) {
                    instance = new MainView();
                    instance.view3d = view3d;
                    instance.view1d = view1d;
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


        topViewContainer.setStyle("-fx-background-color: aqua");

        controlsView.setTop(topViewContainer);

        finalView = new StackPane();

        finalView.getChildren().addAll(view3d.scene3d, controlsView);

        topViewContainer.setPickOnBounds(false);
        controlsView.setPickOnBounds(false);

        finalScene = new Scene(finalView, INIT_SCENE_WIDTH, INIT_SCENE_HEIGHT);

        finalScene.getStylesheets().add("format.css");

        controlsView.getTop().getStyleClass().addAll("textfield");
    }
}
