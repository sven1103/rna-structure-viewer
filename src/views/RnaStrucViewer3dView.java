package views;

import javafx.scene.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;

/**
 * Created by sven on 12/12/15.
 */
public class RnaStrucViewer3dView {

    public final double INIT_SCENE_WIDTH = 200;

    public final double INIT_SCENE_HEIGHT = 200;

    public static volatile RnaStrucViewer3dView instance;

    public SubScene scene3d;

    public Rotate ry;

    public Rotate rx;

    public Rotate rz;

    public Group structures = new Group();

    public PerspectiveCamera camera;

    public FileChooser fileChooser;

    private RnaStrucViewer3dView(){}

    public static RnaStrucViewer3dView getInstance(){
        if(instance == null){
            synchronized (RnaStrucViewer3dView.class){
                if(instance == null) {
                    instance = new RnaStrucViewer3dView();
                    instance.initView();
                }
            }
        }
        return instance;
    }


    /**
     * Initializes the view
     */
    private void initView(){

        Group composition = new Group();

        AmbientLight backLight = new AmbientLight();
        PointLight frontLight = new PointLight();
        PointLight backLight2 = new PointLight();
        backLight2.setColor(Color.WHITE);
        frontLight.setColor(Color.web("#BBBBBB"));
        frontLight.setRotate(90);
        backLight.setColor(Color.web("#222222"));
        backLight2.setTranslateX(150);
        backLight2.setTranslateZ(50);
        frontLight.setTranslateZ(-50);

        composition.getChildren().addAll(backLight2, frontLight, backLight, structures);


        /*
        Make the scene
         */
        scene3d = new SubScene(composition, INIT_SCENE_WIDTH, INIT_SCENE_HEIGHT, true, SceneAntialiasing.BALANCED);
        scene3d.setFill(Color.web("#222222"));



        /*
        Set the camera for the scene
         */
        camera = new PerspectiveCamera(false);
        camera.setTranslateX(-scene3d.getWidth()/2);
        camera.setTranslateY(-scene3d.getHeight()/2);
        camera.setTranslateZ(50);
        camera.setFarClip(10000);
        camera.setNearClip(0.001);
        camera.setFieldOfView(45);

        scene3d.setCamera(camera);


        /*
        Set the rotation axis
         */
        ry = new Rotate(0, Rotate.Y_AXIS);
        rx = new Rotate(0, Rotate.X_AXIS);
        rz = new Rotate(0, Rotate.Z_AXIS);

        structures.getTransforms().addAll(ry, rx, rz);
        ry.setAngle(10);
        rx.setAngle(10);

    }

    public void update(){
        double deltaX;
        double zoomFactorX;

        double finalTranslateZ;

        double halfMoleculeWidth = this.structures.getLayoutBounds().getWidth() / 2.0;

        double halfFovAngle = this.camera.getFieldOfView() / 2.0;

        double computedCamerDistance = halfMoleculeWidth / (Math.tan(Math.toRadians(halfFovAngle)));

        double ratio = this.scene3d.getWidth() / this.scene3d.getHeight();

        deltaX = this.structures.getLayoutBounds().getWidth();

        zoomFactorX = this.scene3d.getWidth() / deltaX;

        finalTranslateZ = computedCamerDistance * zoomFactorX + (scene3d.getHeight() - zoomFactorX*deltaX) - ratio*100 - 10*ratio;

        this.camera.setTranslateZ(finalTranslateZ);
    }

    public void reset(){
        update();
        this.camera.setTranslateX(-scene3d.getWidth()/2);
        this.camera.setTranslateY(-scene3d.getHeight()/2);
    }




}
