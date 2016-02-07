package presenters;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import models.nucleotide2d.Graph;
import models.nucleotide2d.SpringEmbedder;
import models.nucleotide2d.drawings.AbstractNucleotideCircle;
import models.nucleotide2d.drawings.NucleotideFactory;
import models.selection.MySelectionModel;
import sun.applet.Main;
import utils.NucleotideLabeler;
import views.MainView;
import views.SecondaryStructureView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

/**
 * advanced-java-bioinformatics
 *
 * Description:
 *  <- content ->
 *
 * @author fillinger
 * @version
 * Date: 11/24/15
 * EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class SecondaryStructurePresenter implements IRefresher {

    private final SecondaryStructureView view;

    private final MainView mainView;

    private double lastX;
    private double lastY;

    private AbstractNucleotideCircle[] nucleotides;


    /**
     * Presenter constructor
     * @param view the view
     */
    public SecondaryStructurePresenter(SecondaryStructureView view, MainView mainView){
        this.view = view;
        this.mainView = mainView;
        this.view.scene2d.widthProperty().bind(new DoubleBinding() {
            {
                bind(mainView.finalScene.widthProperty());
            }

            @Override
            protected double computeValue() {
                return mainView.finalScene.widthProperty().get()/2;
            }
        });
        this.view.scene2d.heightProperty().bind(new DoubleBinding() {
            {
                bind(mainView.finalScene.heightProperty());
            }

            @Override
            protected double computeValue() {
                return mainView.finalScene.getHeight() - mainView.topViewContainer.getHeight();
            }
        });

    }


    /**
     * Draws the secondary structure in the drawArea
     */
    public void drawStructure(){

        // clear the draw area :)
        view.drawArea.getChildren().clear();

        // Init a Graph object
        Graph structure = new Graph();

        // Init a NucleotideFactory for easy nucleotide graphic object generation
        NucleotideFactory nucleotideFactory = new NucleotideFactory();

        // List for collection of nucleotide objects
        List<AbstractNucleotideCircle> nucleotideList = new ArrayList<>();
        // List of lines representing the sequence connection
        List<Line> sequenceConnection = new ArrayList<>();
        // List of lines representing the predicted base-pairing
        List<Line> basePairing = new ArrayList<>();
        // Gather the animation keyframes
        List<Timeline> timeLineList = new ArrayList<>();

        /*
        1. Parse the secondary structure notation from dot-bracket
        notation to a Graph object.

        2. Then assign coordinates to each node.

        3. Iterate over the coordinates and make NucleotideCircles,
        which will be added then to the draw area.
         */
        try {
            double[][] initCoordinates;
            // The Parsing
            structure.parseNotation(MainPresenter.primaryStructurePresenter.getDotBracket());

            // Assign the coordinates
            double[][] newCoordinates = SpringEmbedder.computeSpringEmbedding(50,
                    structure.getNumberOfNodes(), structure.getEdges(), null);

            initCoordinates = newCoordinates;

            // Center the coordinates with respect to the draw area size
            SpringEmbedder.centerCoordinates(newCoordinates, 20, view.scene2d.widthProperty().intValue(),
                    20, view.scene2d.heightProperty().intValue() -20);

            // Now generate the lines and nucleotide circles
            for (int i=0; i < newCoordinates.length; i++){

                // Create NucleotideCircles dependent on the
                // sequence character at position 'i'
                Character base = MainPresenter.primaryStructurePresenter.getSequence().charAt(i);
                AbstractNucleotideCircle nucleotideCircle = nucleotideFactory.getNucleotide(NucleotideLabeler.getType(base));
                nucleotideCircle.setLayoutX(initCoordinates[i][0]);
                nucleotideCircle.setLayoutY(initCoordinates[i][1]);
                nucleotideCircle.setTooltip(nucleotideCircle.getClass().
                        getSimpleName()+"\nPosition: " + (i+1));
                nucleotideList.add(nucleotideCircle);

                // Make the animation and add it to the list
                timeLineList.add(makeTimeline(nucleotideCircle, newCoordinates[i][0],
                        newCoordinates[i][1], Duration.millis(1000)));
            }

            // Add the connections
            for (int i=0; i < newCoordinates.length; i++){
                Line connection;
                // Draw connection lines between sequential nucleotides
                if (i < newCoordinates.length-1){
                    connection = new Line(initCoordinates[i][0], initCoordinates[i][1],
                            initCoordinates[i+1][0], initCoordinates[i+1][1]);
                    connection.setStroke(Color.WHITE);
                    sequenceConnection.add(connection);
                    connection.startXProperty().bind(nucleotideList.get(i).layoutXProperty());
                    connection.startYProperty().bind(nucleotideList.get(i).layoutYProperty());
                    connection.endXProperty().bind(nucleotideList.get(i+1).layoutXProperty());
                    connection.endYProperty().bind(nucleotideList.get(i+1).layoutYProperty());
                }

                // Check if index is part of a computed base pair
                // and add base-pair lines if positive
                int basePair = isPairing(i, structure.getEdges());
                if (basePair != -1){
                    Line basePairLine = new Line(initCoordinates[i][0], initCoordinates[i][1],
                            initCoordinates[basePair][0], initCoordinates[basePair][1]);
                    basePairLine.setStroke(Color.MAGENTA);
                    basePairing.add(basePairLine);
                    basePairLine.startXProperty().bind(nucleotideList.get(i).layoutXProperty());
                    basePairLine.startYProperty().bind(nucleotideList.get(i).layoutYProperty());
                    basePairLine.endXProperty().bind(nucleotideList.get(basePair).layoutXProperty());
                    basePairLine.endYProperty().bind(nucleotideList.get(basePair).layoutYProperty());
                }

            }


            // Add the lines to the SceneGraph
            view.drawArea.getChildren().addAll(sequenceConnection);
            view.drawArea.getChildren().addAll(basePairing);

            // Add the nucleotides to the SceneGraph
            nucleotides = new AbstractNucleotideCircle[nucleotideList.size()];
            int counter = 0;
            for (AbstractNucleotideCircle nucleotide : nucleotideList){
                view.drawArea.getChildren().addAll(nucleotide);
                nucleotides[counter] = nucleotide;
                counter++;
            }

        } catch (IOException e){
            System.err.println(e);
        }

        // Last but not least, add interactivity
        // to the graphic elements :)
        addInteractivity();
        playAnimation(timeLineList);
        configureSelectionModel(nucleotides);
        for(AbstractNucleotideCircle nucleotideCircle : nucleotides){
            nucleotideCircle.getRadiusProperty().bind(mainView.slider.valueProperty());
        }
    }


    /**
     * Configures the selection model correctly
     * @param nucleotides An array of Nucleotide() objects
     */
    public void configureSelectionModel(AbstractNucleotideCircle[] nucleotides){

        for(int i = 0; i < nucleotides.length; i++){
            final int index=i;
            nucleotides[i].setOnMouseEntered(e -> {
                nucleotides[index].setCursor(Cursor.CROSSHAIR);
                MySelectionModel.multiSelectionHoverActivate(e, index);
            });

            nucleotides[i].setOnMouseClicked((e) ->{
                if(!e.isControlDown()){
                    SelectionModelPresenter.nucleotideSelectionModel.clearSelection();
                }
                if(SelectionModelPresenter.nucleotideSelectionModel.isSelected(index)){
                    SelectionModelPresenter.nucleotideSelectionModel.clearSelection(index);
                } else{
                    SelectionModelPresenter.nucleotideSelectionModel.select(index);
                }
                MainPresenter.refreshAll();
            });
        }

        // bind visibility of bounding boxes to selection state
        for(int i=0; i<nucleotides.length; i++) {
            final int index=i;
            nucleotides[i].getIsSelectedProperty().bind(new BooleanBinding() {
                {
                    bind(SelectionModelPresenter.nucleotideSelectionModel.getSelectedIndices());
                }
                @Override
                protected boolean computeValue() {
                    return SelectionModelPresenter.nucleotideSelectionModel.getSelectedIndices().contains(index);
                }
            });
        }

    }


    /**
     * Plays all Timelines in the list
     * @param timelineList A list with timeline objects
     */
    private void playAnimation(List<Timeline> timelineList){
        for (Timeline timeline : timelineList){
            timeline.play();
        }
    }


    /**
     * Adds interactivity to the Nucleotide objects
     */
    private void addInteractivity(){

        for (Node nucleotide : nucleotides){

            nucleotide.setOnMousePressed(event -> {
                lastX = nucleotide.getLayoutX();
                lastY = nucleotide.getLayoutY();
            });
            nucleotide.setOnMouseDragged(event -> {
                nucleotide.setLayoutX(event.getX() + nucleotide.getLayoutX());
                nucleotide.setLayoutY(event.getY() + nucleotide.getLayoutY());
            });
            nucleotide.setOnMouseReleased(event -> makeTimeline(nucleotide, lastX, lastY, Duration.millis(100)).play());


        }
    }


    /**
     * Checks if a index i is in a 2d array of indices with the computed
     * values for base pairing
     * @param i the index to evaluate
     * @param pairList 2D integer array with computed indices
     * @return the index of the corresponding base pair, -1 if not found
     */
    private int isPairing(int i, int[][] pairList){
        boolean firstOccurence = false;
        for (int[] row : pairList){
            if(row[0] == i){
                if(!firstOccurence){
                    firstOccurence = true;
                } else {
                    return row[1];
                }
            }
        }
        return -1;
    }


    /**
     * Function that constructs a Timeline() object on a nucleotide.
     * @param nucleotide An AbstractNucelotideCircle object
     * @param newX The final x-value
     * @param newY The final y-value
     * @param duration The duration of the animation
     * @return
     */
    private Timeline makeTimeline(AbstractNucleotideCircle nucleotide, double newX,
                                  double newY, Duration duration){
        final KeyValue keyValueX0 =
                new KeyValue(nucleotide
                        .layoutXProperty(), nucleotide.getLayoutX(), Interpolator.EASE_BOTH);
        final KeyValue keyValueY0 =
                new KeyValue(nucleotide
                        .layoutYProperty(), nucleotide.getLayoutY(), Interpolator.EASE_BOTH);
        final KeyFrame keyFrame0 = new KeyFrame(Duration.millis(0),
                keyValueX0, keyValueY0);
        final KeyValue keyValueX1 = new KeyValue(nucleotide
                .layoutXProperty(), newX, Interpolator.EASE_BOTH);
        final KeyValue keyValueY1 = new KeyValue(nucleotide
                .layoutYProperty(), newY, Interpolator.EASE_BOTH);
        final KeyFrame keyFrame1 = new KeyFrame(duration,
                keyValueX1, keyValueY1);

        return new Timeline(keyFrame0, keyFrame1);
    }


    /**
     * Function that constructs a Timeline() object on a Node.
     * @param nucleotide An Node object
     * @param newX The final x-value
     * @param newY The final y-value
     * @param duration The duration of the animation
     * @return
     */
    private Timeline makeTimeline(Node nucleotide, double newX,
                                  double newY, Duration duration){
        final KeyValue keyValueX0 =
                new KeyValue(nucleotide.layoutXProperty(), nucleotide.getLayoutX(), Interpolator.EASE_BOTH);
        final KeyValue keyValueY0 =
                new KeyValue(nucleotide.layoutYProperty(), nucleotide.getLayoutY(), Interpolator.EASE_BOTH);
        final KeyFrame keyFrame0 = new KeyFrame(Duration.millis(0),
                keyValueX0, keyValueY0);
        final KeyValue keyValueX1 = new KeyValue(nucleotide.layoutXProperty(), newX, Interpolator.EASE_BOTH);
        final KeyValue keyValueY1 = new KeyValue(nucleotide.layoutYProperty(), newY, Interpolator.EASE_BOTH);
        final KeyFrame keyFrame1 = new KeyFrame(duration,
                keyValueX1, keyValueY1);

        return new Timeline(keyFrame0, keyFrame1);
    }


    /**
     * Randomizes a 2D coordinate array
     * @param finalCoordinates The fixed and calculated final coordinates
     * @return A randomized 2D-Array of coordinates
     */
    private double[][] randomizeCoordinates(double[][] finalCoordinates){
        double[][] copyCoordinates = new double[finalCoordinates.length][finalCoordinates[0].length];
        for(double[] row : copyCoordinates){
            // randomize init x-coordinate
            row[0] = random() * view.drawArea.widthProperty().get();
            // randomize init y-coordinate
            row[1] = random() * view.drawArea.heightProperty().get();
        }
        return copyCoordinates;
    }


    @Override
    public void refreshSelectionStatus() {
        for (AbstractNucleotideCircle nucleotide : nucleotides){
            if(SelectionModelPresenter.nucleotideSelectionModel.getSelectedIndices().isEmpty()){
                nucleotide.setOriginalColor();
            } else if(nucleotide.getIsSelectedProperty().getValue()){
                nucleotide.setColor();
            } else{
                nucleotide.resetColor();
            }
        }
    }

    public AbstractNucleotideCircle[] getNucleotides(){
        return this.nucleotides;
    }
}
