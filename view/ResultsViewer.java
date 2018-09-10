package view;

import databases.DataBaseUtils;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.Element;
import utils.Specimen;
import utils.SpecimenData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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

    private ToolBar rightToolBar, leftToolBar;

    private Button refreshButton, deleteButton;
    private ListView<Integer> resultsListView;

    /**
     * Right ToolBar Component
     */
    private TitledPane cameraControlTitledPane, planeTitledPane, scaleTitlePane;
    private CameraControl cameraControl;
    private PlaneConfigurations planeConfigurations;
    private ScaleViewer scaleViewer;

    private ComboBox<String> parametersComboBox;
    private ObservableList parametersList = FXCollections.observableArrayList(
            UICommon.TEMPERATURE,
            UICommon.ELASTIC_ENERGY,
            UICommon.DISLOCATION_DENSITY,
            UICommon.MOMENTS_X,
            UICommon.MOMENTS_Y,
            UICommon.MOMENTS_Z,
            UICommon.STRUCTURE
    );

    private TabPane tabPane;

    private String specimenName, taskName;
    private int axisLength;

    /**
     * Camera parameters Properties
     */
    private SimpleDoubleProperty cameraRotateXProp, cameraRotateYProp, cameraRotateZProp,
            cameraTranslateXProp, cameraTranslateYProp, cameraTranslateZProp;

    /**
     * Context Menu for Left ListView
     */
    private ContextMenu contextMenu;
    private MenuItem newTabItem, setRecommendedScaleMenuItem, showInfoMenuItem, deleteMenuItem;

    public ResultsViewer(SpecimenData specimenData){
        this.specimenName = specimenData.getSpecimenName();
        this.taskName = specimenData.getTask();

        DataBaseUtils.setResultsData(specimenName, taskName);

        this.axisLength = UIUtils.getAxisLength(specimenData);

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
        leftToolBar = new ToolBar();
        leftToolBar.setOrientation(Orientation.VERTICAL);

        resultsListView = new ListView();
        resultsListView.setPadding(new Insets(10));
        resultsListView.setMaxWidth(80);

        refreshButton = new Button(UICommon.REFRESH);
        deleteButton = new Button(UICommon.DELETE);

        /**
         **************************************************
         */

        /**
         * Right ToolBar Components
         */
        rightToolBar = new ToolBar();
        rightToolBar.setOrientation(Orientation.VERTICAL);
        rightToolBar.setPrefWidth(this.screenWidth*0.25);
        cameraControlTitledPane = new TitledPane();
        cameraControlTitledPane.setText("Camera Parameters");
        cameraControlTitledPane.setExpanded(false);

        cameraControl = new CameraControl();

        planeTitledPane = new TitledPane();
        planeTitledPane.setText("Plane Parameters");
        planeTitledPane.setExpanded(false);

        planeConfigurations = new PlaneConfigurations(specimenName);

        parametersComboBox = new ComboBox();
        parametersComboBox.getItems().addAll(parametersList);
        parametersComboBox.getSelectionModel().selectFirst();
        parametersComboBox.setCellFactory(param -> {
            final ListCell<String> cell = new ListCell<String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null){
                        if (item.equals(UICommon.STRUCTURE)){
                            setText(item);
                            setFont(this.getFont().font(this.getFont().getName(), FontWeight.BOLD, 12.0));
                        }
                        else {
                            setText(item);
                            setFont(this.getFont().font(this.getFont().getName(), FontWeight.MEDIUM, 12.0));
                        }
                    }
                    else {
                        setText(null);
                    }
                }
            };
            return cell;
        });

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

        contextMenu = new ContextMenu();
        newTabItem = new MenuItem(UICommon.OPEN_IN_NEW_TAB);
        setRecommendedScaleMenuItem = new MenuItem(UICommon.RECOMMENDED_SCALE);
        deleteMenuItem = new MenuItem(UICommon.DELETE);
        showInfoMenuItem = new MenuItem(UICommon.SHOW_INFO);
        contextMenu.getItems().addAll(newTabItem,
                setRecommendedScaleMenuItem,
                showInfoMenuItem,
                deleteMenuItem);
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

        resultsListView.getItems().addAll(DataBaseUtils.resultDataBase.getTimeSteps());
        resultsListView.setContextMenu(contextMenu);

        VBox leftToolBarLayout = new VBox();
        leftToolBarLayout.setPadding(new Insets(5));
        leftToolBarLayout.setSpacing(10.0);
        leftToolBarLayout.setAlignment(Pos.CENTER_LEFT);
        leftToolBarLayout.getChildren().addAll(resultsListView, refreshButton, deleteButton);
        leftToolBar.getItems().add(leftToolBarLayout);

        /**
         * Right ToolBar
         */
        cameraControlTitledPane.setContent(cameraControl);
        planeTitledPane.setContent(planeConfigurations);

        VBox rightToolBarLayout = new VBox();
        rightToolBarLayout.setPadding(new Insets(10));
        rightToolBarLayout.setAlignment(Pos.CENTER);
        rightToolBarLayout.setSpacing(10.0);
        rightToolBarLayout.getChildren().addAll(
                cameraControlTitledPane,
                planeTitledPane,
                parametersComboBox,
                scaleTitlePane
        );
        rightToolBar.getItems().add(rightToolBarLayout);

        root.setTop(menuBar);
        root.setLeft(leftToolBar);
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

    private boolean firstTime = true;
    private List<Color> listOfColors;

    private void handleEvents(){
        /**
         * MenuBar events
         */
        handleScaleViewer(scaleViewer);

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

        parametersComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)->{
            if (newValue.equals(UICommon.STRUCTURE)){
                scaleTitlePane.setExpanded(false);
            }
            else{
                scaleTitlePane.setExpanded(true);
            }
        });

        resultsListView.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)->{
            int timeStep = newValue;
            String parameter = parametersComboBox.getSelectionModel().getSelectedItem();
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            List<Element> elementList = DataBaseUtils.resultDataBase.getResult(timeStep);
            Specimen3DView specimen3DView = new Specimen3DView(axisLength);
            if (parameter.equals(UICommon.STRUCTURE)){
                if (listOfColors == null){
                    listOfColors = UICommon.generateRandomColors(elementList);
                    specimen3DView.showStructure(elementList, listOfColors);
                }
                else {
                    specimen3DView.showStructure(elementList, listOfColors);
                }
            }
            else {
                double minValue = 0.0;
                double maxValue = 0.0;

                if (firstTime){
                    firstTime = false;
                    HashMap<String, Double> minAndMax = UIUtils.getMaxAndMinValues(elementList, parameter);
                    minValue = minAndMax.get("Min");
                    maxValue = minAndMax.get("Max");
                    scaleViewer.setMaxValueProp(maxValue);
                    scaleViewer.setMinValueProp(minValue);
                }
                else{
                    minValue = scaleViewer.getMinValue();
                    maxValue = scaleViewer.getMaxValue();
                }

                String scaleType = scaleViewer.getScaleType();
                boolean isReverse = scaleViewer.getIsReverse();

                ColorScale colorScale = new ColorScale(scaleType, minValue, maxValue, isReverse);
                scaleViewer.setScaleImage(colorScale.createScaleImage());
                specimen3DView.showParameterDistribution(elementList, colorScale);
            }

            bindCameraValues(specimen3DView);
            cameraControl.bindAllValues(specimen3DView);
            SubScene subScene =
                    new SubScene(specimen3DView.world, screenWidth*0.8, screenHeight*0.9, true, SceneAntialiasing.BALANCED);
            subScene.setCamera(specimen3DView.camera);
            handle3DArea(subScene, specimen3DView);
            selectedTab.setContent(subScene);
        });

        setRecommendedScaleMenuItem.setOnAction(e -> {
            System.out.println("Action Event : Set Recommended Scale is Pushed");
            if (!resultsListView.getSelectionModel().isEmpty()){
                int timeStep = resultsListView.getSelectionModel().getSelectedItem();
                String parameter = parametersComboBox.getSelectionModel().getSelectedItem();
                Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
                List<Element> elementList = DataBaseUtils.resultDataBase.getResult(timeStep);

                HashMap<String, Double> minAndMax = UIUtils.getMaxAndMinValues(elementList, parameter);
                double minValue = minAndMax.get("Min");
                double maxValue = minAndMax.get("Max");
                scaleViewer.setMaxValueProp(maxValue);
                scaleViewer.setMinValueProp(minValue);

                String scaleType = scaleViewer.getScaleType();
                boolean isReverse = scaleViewer.getIsReverse();
                ColorScale colorScale = new ColorScale(scaleType, minValue, maxValue, isReverse);
                scaleViewer.setScaleImage(colorScale.createScaleImage());
                Specimen3DView specimen3DView = new Specimen3DView(axisLength);
                specimen3DView.showParameterDistribution(elementList, colorScale);
                bindCameraValues(specimen3DView);
                cameraControl.bindAllValues(specimen3DView);
                SubScene subScene =
                        new SubScene(specimen3DView.world, screenWidth*0.8, screenHeight*0.9, true, SceneAntialiasing.BALANCED);
                subScene.setCamera(specimen3DView.camera);
                handle3DArea(subScene, specimen3DView);
                selectedTab.setContent(subScene);
            }
        });

        deleteMenuItem.setOnAction(e -> {
            int timeStep = resultsListView.getSelectionModel().getSelectedItem();
            deleteElement(timeStep);
        });

        deleteButton.setOnAction(e -> {
            int timeStep = resultsListView.getSelectionModel().getSelectedItem();
            deleteElement(timeStep);
        });

        newTabItem.setOnAction(e -> {
            showParameter(0.0, 0.0, true);
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

    private void handleScaleViewer(ScaleViewer scaleViewer){
        scaleViewer.maxValueTextField.setOnKeyPressed(keyEvent -> {
            double maxValue = 0.0;
            double minValue = 0.0;
            if (KeyCode.ENTER.equals(keyEvent.getCode())){
                try{
                    maxValue = scaleViewer.getMaxValue();
                    minValue = scaleViewer.getMinValue();
                }
                catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }
                showParameter(maxValue, minValue, false);
            }
        });

        scaleViewer.minValueTextField.setOnKeyPressed(keyEvent -> {
            double maxValue = 0.0;
            double minValue = 0.0;
            if (KeyCode.ENTER.equals(keyEvent.getCode())){
                try{
                    maxValue = scaleViewer.getMaxValue();
                    minValue = scaleViewer.getMinValue();
                }
                catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                }
                showParameter(maxValue, minValue, false);
            }
        });

        scaleViewer.scaleComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            System.out.println("Action Event : scale type has been changed");
            showParameter(scaleViewer.getMaxValue(), scaleViewer.getMinValue(), false);
        });

        scaleViewer.reverseScaleCheckBox.selectedProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue){
                System.out.println("Action Event : scale is reversed");
                showParameter(scaleViewer.getMaxValue(), scaleViewer.getMinValue(), false);
            }
            else {
                System.out.println("Action Event : scale is not reversed");
                showParameter(scaleViewer.getMaxValue(), scaleViewer.getMinValue(), false);
            }
        });

    }

    private void showParameter(double maxValue, double minValue, boolean newTab){
        int timeStep = resultsListView.getSelectionModel().getSelectedItem();
        String parameter = parametersComboBox.getSelectionModel().getSelectedItem();
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        List<Element> elementList = DataBaseUtils.resultDataBase.getResult(timeStep);

        if (newTab){
            tab = new Tab("New Tab");
            tabPane.getTabs().add(tab);
            HashMap<String, Double> minAndMax = UIUtils.getMaxAndMinValues(elementList, parameter);
            minValue = minAndMax.get("Min");
            maxValue = minAndMax.get("Max");
            scaleViewer.setMaxValueProp(maxValue);
            scaleViewer.setMinValueProp(minValue);
        }

        String scaleType = scaleViewer.getScaleType();
        boolean isReverse = scaleViewer.getIsReverse();
        ColorScale colorScale = new ColorScale(scaleType, minValue, maxValue, isReverse);
        scaleViewer.setScaleImage(colorScale.createScaleImage());
        Specimen3DView specimen3DView = new Specimen3DView(axisLength);
        specimen3DView.showParameterDistribution(elementList, colorScale);

        bindCameraValues(specimen3DView);
        cameraControl.bindAllValues(specimen3DView);
        SubScene subScene = createSubScene(specimen3DView);
        tab.setContent(subScene);
    }

    private SubScene createSubScene(Specimen3DView specimen3DView){
        SubScene subScene = new SubScene(specimen3DView.world, screenWidth*0.8, screenHeight*0.9, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(specimen3DView.camera);
        handle3DArea(subScene, specimen3DView);
        return subScene;
    }

    private void deleteElement(int timeStep){
        DataBaseUtils.resultDataBase.deleteResult(timeStep);
        resultsListView.getItems().remove(new Integer(timeStep));
    }

}
