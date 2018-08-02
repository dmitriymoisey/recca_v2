package view;

import databases.DataBaseUtils;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ResultsViewer extends Stage {

    private Scene scene;
    private double screenWidth, screenHeight;

    private BorderPane root;

    /**
     * MenuBar Components
     */
    private MenuBar menuBar;
    private Menu fileMenu, viewMenu;
    private MenuItem plotViewer, saveMenu, closeMenu;
    private CheckMenuItem showAxisMenu;

    private ToolBar rightToolBar;

    private ListView<Integer> resultsListView;

    /**
     * Right ToolBar Component
     */
    private TitledPane cameraControlTitledPane, planeTitledPane, scaleTitlePane;
    private CameraControl cameraControl;
    private ScaleViewer scaleViewer;

    private ComboBox parametersComboBox;
    private ObservableList parametersList = FXCollections.observableArrayList(
            "Temperature",
            "Elastic Energy",
            "Dislocation Density",
            "Moments X",
            "Moments Y",
            "Moments Z"
    );

    private TabPane tabPane;

    private Button show;

    private String specimenName, taskName;

    /**
     * Camera parameters Properties
     */
    private SimpleDoubleProperty cameraRotateXProp, cameraRotateYProp, cameraRotateZProp,
            cameraTranslateXProp, cameraTranslateYProp, cameraTranslateZProp;

    public ResultsViewer(String specimenName, String taskName){
        this.specimenName = specimenName;
        this.taskName = taskName;

        root = new BorderPane();

        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        scene = new Scene(root, screenWidth, screenHeight);

        initAllComponents();
        initAllProperties();
        addAllComponents();
        handleEvents();

        this.setTitle("Results - Viewer : " + specimenName + " - " + taskName);
        this.setScene(scene);
        this.show();
    }

    private void initAllComponents(){
        /**
         * MenuBar Components
         */
        menuBar = new MenuBar();

        fileMenu = new Menu("File");

        plotViewer = new MenuItem("Open Plot Viewer...");

        saveMenu = new MenuItem("Save...");
        saveMenu.setAccelerator(KeyCombination.valueOf("Ctrl+S"));

        closeMenu = new MenuItem("Close.");
        closeMenu.setAccelerator(KeyCombination.valueOf("Ctrl+Q"));

        viewMenu = new Menu("View");
        showAxisMenu = new CheckMenuItem("Show Axis");
        showAxisMenu.setAccelerator(KeyCombination.valueOf("Ctrl+X"));

        /**
         **************************************************
         */

        /**
         * Left ToolBar Components
         */
        resultsListView = new ListView();
        resultsListView.setPadding(new Insets(10));
        resultsListView.setMaxWidth(80);
        /**
         **************************************************
         */

        /**
         * Right ToolBar Components
         */
        rightToolBar = new ToolBar();
        rightToolBar.setOrientation(Orientation.VERTICAL);

        cameraControlTitledPane = new TitledPane();
        cameraControlTitledPane.setText("Camera Parameters");

        cameraControl = new CameraControl();

        planeTitledPane = new TitledPane();
        planeTitledPane.setText("Plane Parameters");

        parametersComboBox = new ComboBox();
        parametersComboBox.getItems().addAll(parametersList);
        parametersComboBox.getSelectionModel().selectFirst();

        show = new Button(UICommon.SHOW);
        show.setPadding(new Insets(7, 15, 7, 15));
        /**
         **************************************************
         */

        /**
         * Scale Layout
         */
        scaleTitlePane = new TitledPane();
        scaleTitlePane.setText(UICommon.SCALE);

        scaleViewer = new ScaleViewer();

        scaleTitlePane.setContent(scaleViewer);
        /**
         **************************************************
         */


        tabPane = new TabPane();
        Tab tab = new Tab("Default Tab");
        tabPane.getTabs().add(tab);
    }

    private void addAllComponents(){

        /**
         * MenuBar
         */
        fileMenu.getItems().addAll(plotViewer, saveMenu, new SeparatorMenuItem(), closeMenu);
        viewMenu.getItems().add(showAxisMenu);
        menuBar.getMenus().addAll(fileMenu, viewMenu);
        /**
         *******************************************
         */

        resultsListView.getItems().addAll(DataBaseUtils.getTimeStepsList(specimenName, taskName));
        resultsListView.setContextMenu(this.getResultsListContextMenu());

        /**
         * Right ToolBar
         */
        cameraControlTitledPane.setContent(cameraControl);

        VBox rightToolBarLayout = new VBox();
        rightToolBarLayout.setPadding(new Insets(10));
        rightToolBarLayout.setAlignment(Pos.CENTER);
        rightToolBarLayout.setSpacing(10.0);
        rightToolBarLayout.getChildren().addAll(
                cameraControlTitledPane,
                planeTitledPane,
                parametersComboBox,
                show,
                scaleTitlePane
        );
        rightToolBar.getItems().add(rightToolBarLayout);

        root.setTop(menuBar);
        root.setLeft(resultsListView);
        root.setRight(rightToolBar);
        root.setCenter(tabPane);
    }

    private void initAllProperties(){
        cameraRotateXProp = new SimpleDoubleProperty(0.0);
        cameraRotateYProp = new SimpleDoubleProperty(0.0);
        cameraRotateZProp = new SimpleDoubleProperty(0.0);
        cameraTranslateXProp = new SimpleDoubleProperty(0.0);
        cameraTranslateYProp = new SimpleDoubleProperty(0.0);
        cameraTranslateZProp = new SimpleDoubleProperty(UICommon.CAMERA_INITIAL_DISTANCE);
    }

    private void bindCameraValues(Specimen3DView specimen3DView){
        specimen3DView.axisGroup.visibleProperty().bindBidirectional(showAxisMenu.selectedProperty());

        specimen3DView.cameraXform.rx.angleProperty().bindBidirectional(cameraRotateXProp);
        specimen3DView.cameraXform.ry.angleProperty().bindBidirectional(cameraRotateYProp);
        specimen3DView.cameraXform.rz.angleProperty().bindBidirectional(cameraRotateZProp);
        specimen3DView.camera.translateXProperty().bindBidirectional(cameraTranslateXProp);
        specimen3DView.camera.translateYProperty().bindBidirectional(cameraTranslateYProp);
        specimen3DView.camera.translateZProperty().bindBidirectional(cameraTranslateZProp);
    }

    private void handleEvents(){
        /**
         * MenuBar events
         */
        plotViewer.setOnAction(e -> {
            System.out.println("Action Event : open plot view menu is pushed");
            PlotViewer plotViewer = new PlotViewer();
        });
        closeMenu.setOnAction(e -> this.close());
        saveMenu.setOnAction(e -> {
            System.out.println("Action Event : save menu is pushed");

            if (!resultsListView.getSelectionModel().isEmpty()){
                ImageView imageView = new ImageView(this.tabPane.getSelectionModel().
                        getSelectedItem().getContent().snapshot(new SnapshotParameters(), null));
                SaveImage saveImage = new SaveImage(imageView);
            }
            else{
                System.out.println("Nothing is chosen");
                new Alert(Alert.AlertType.ERROR, "Nothing is chosen!").show();
            }

        });
        /**
         ********************************************
         */

        show.setOnAction(e -> {
            System.out.println("Action Event : show button is pushed");
        });
        resultsListView.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)->{
            int timeStep = newValue;
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            Specimen3DView specimen3DView = new Specimen3DView();
            specimen3DView.showParameterDistribution(specimenName,taskName, timeStep);
            scaleViewer.setMaxValueProp(1800.0);
            scaleViewer.setMinValueProp(300.0);
            bindCameraValues(specimen3DView);
            cameraControl.bindAllValues(specimen3DView);
            SubScene subScene =
                    new SubScene(specimen3DView.world, screenWidth*0.8, screenHeight*0.9, true, SceneAntialiasing.BALANCED);
            subScene.setCamera(specimen3DView.camera);
            handle3DArea(subScene, specimen3DView);
            selectedTab.setContent(subScene);

        });
    }

    double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
    private void handle3DArea(SubScene subScene, Specimen3DView specimen3DView){

        subScene.setOnMousePressed(
                event -> {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    mouseOldX = event.getSceneX();
                    mouseOldY = event.getSceneY();
                }
        );

        subScene.setOnMouseDragged(
                event -> {
                    mouseOldX = mousePosX;
                    mouseOldY = mousePosY;
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    mouseDeltaX = mousePosX - mouseOldX;
                    mouseDeltaY = mousePosY - mouseOldY;

                    if(event.isPrimaryButtonDown()){
                        specimen3DView.cameraXform.ry.setAngle(specimen3DView.cameraXform.ry.getAngle() - mouseDeltaY*UICommon.MOUSE_SPEED*UICommon.ROTATION_SPEED);
                        specimen3DView.cameraXform.rx.setAngle(specimen3DView.cameraXform.rx.getAngle() + mouseDeltaX*UICommon.MOUSE_SPEED*UICommon.ROTATION_SPEED);
                    }
                    else if(event.isMiddleButtonDown()){
                        specimen3DView.camera.setTranslateX(specimen3DView.camera.getTranslateX() - mouseDeltaX*UICommon.MOUSE_SPEED*UICommon.TRACK_SPEED);
                        specimen3DView.camera.setTranslateY(specimen3DView.camera.getTranslateY() - mouseDeltaY*UICommon.MOUSE_SPEED*UICommon.TRACK_SPEED);
                    }

                }
        );

        subScene.setOnScroll(event -> {
            double z = specimen3DView.camera.getTranslateZ();
            double newZ = z + event.getDeltaY()*0.5;
            specimen3DView.camera.setTranslateZ(newZ);
        });
    }

    private ContextMenu getResultsListContextMenu(){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem newTabItem = new MenuItem(UICommon.OPEN_IN_NEW_TAB);
        contextMenu.getItems().add(newTabItem);
        return contextMenu;
    }
}
