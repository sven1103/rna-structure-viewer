package views;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * rnaStructureViewer
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 2/3/16
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class PrimaryStructureView {

    public ScrollPane pane1d;

    public VBox primStructureContainer;

    public static volatile PrimaryStructureView instance;

    public static PrimaryStructureView getInstance(){
        if(instance == null){
            synchronized (MainView.class){
                if(instance == null) {
                    instance = new PrimaryStructureView();
                    instance.initView();

                }
            }
        }
        return instance;
    }

    private PrimaryStructureView(){}

    private void initView(){

        primStructureContainer = new VBox();
        primStructureContainer.setStyle("-fx-padding: 10 0 0 0");
        primStructureContainer.setSpacing(0);

        pane1d = new ScrollPane();
        pane1d.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane1d.setContent(primStructureContainer);

        primStructureContainer.setPrefHeight(65);

    }


}
