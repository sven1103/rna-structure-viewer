package views;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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

    public TextFlow primarySequence = new TextFlow();

    public TextFlow structureAnnotation = new TextFlow();

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
        primStructureContainer.setStyle("-fx-padding: 0 0 0 0");
        pane1d = new ScrollPane();
        pane1d.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        primarySequence.getStyleClass().addAll("nucleotide_text");
        primarySequence.setPrefWidth(Region.USE_COMPUTED_SIZE);
        structureAnnotation.getStyleClass().addAll("nucleotide_text");

        primarySequence.getChildren().addAll(new Text("ACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGAACGUCGA"));
        structureAnnotation.getChildren().addAll(new Text("...(.)."));


        primStructureContainer.getChildren().addAll(primarySequence, structureAnnotation);

        pane1d.setContent(primStructureContainer);
        primStructureContainer.setPrefHeight(75);

    }


}
