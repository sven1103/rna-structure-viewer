package views;

import javafx.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * Created by sven on 2/6/16.
 */
public class SecondaryStructureView {

    public HBox drawArea = new HBox();

    public SubScene scene2d;

    public final double INIT_SCENE_WIDTH = 800;

    public final double INIT_SCENE_HEIGHT = 600;

    public static volatile SecondaryStructureView instance;

    public static SecondaryStructureView getInstance(){
        if(instance == null){
            synchronized (SecondaryStructureView.class){
                if(instance == null) {
                    instance = new SecondaryStructureView();
                    instance.initView();

                }
            }
        }
        return instance;
    }

    private void initView(){

        Rectangle box = new Rectangle();
        drawArea.getChildren().addAll(box);
        scene2d = new SubScene(drawArea, INIT_SCENE_HEIGHT, INIT_SCENE_WIDTH);
    }

}
