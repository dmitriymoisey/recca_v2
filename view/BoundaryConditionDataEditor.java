package view;

import databases.DataBaseUtils;
import javafx.beans.binding.Binding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import utils.BoundaryCondition;

import javax.script.Bindings;
import java.util.List;

public class BoundaryConditionDataEditor extends Stage {

    private Scene scene;
    private BorderPane root;

    private CheckBox topFacetCheckBox, bottomFacetCheckBox, leftFacetCheckBox,
            rightFacetCheckBox, frontFacetCheckBox, backFacetCheckBox;

    private ComboBox facetsComboBox;

    private TextField nameTextField,
            averageTemperatureTextField, temperatureDeviationTextField, temperatureLoadTimeTextField,
            averageElasticEnergyTextField, elasticEnergyDeviationTextField, elasticEnergyLoadTimeTextField,
            averageDislocationDensTextField, dislocationDensDeviationTextField, dislocationDensLoadTimeTextField,
            averageXMomentTextField, xMomentDeviationTextField, xMomentLoadTimeTextField,
            averageYMomentTextField, yMomentDeviationTextField, yMomentLoadTimeTextField,
            averageZMomentTextField, zMomentDeviationTextField, zMomentLoadTimeTextField;

    private ComboBox temperatureComboBox, elasticEnergyComboBox, dislocationDensityComboBox,
            xMomentComboBox, yMomentComboBox, zMomentComboBox;

    private ObservableList<String> conditionType = FXCollections.observableArrayList("Homogeneus");

    private SimpleDoubleProperty topAverageTemperatureValue, topTemperatureDeviationValue, topTemperatureLoadTimeValue,
            topAverageElasticEnergyValue, topElasticEnergyDeviationValue, topElasticEnergyLoadTimeValue,
            topAverageDislocationDensityValue, topDislocationDensityDeviationValue, topDislocationDensityLoadTimeValue,
            topAverageXMomentValue, topXMomentDeviationValue, topXMomentLoadTimeValue,
            topAverageYMomentValue, topYMomentDeviationValue, topYMomentLoadTimeValue,
            topAverageZMomentValue, topZMomentDeviationValue, topZMomentLoadTimeValue;

    private SimpleDoubleProperty bottomAverageTemperatureValue, bottomTemperatureDeviationValue, bottomTemperatureLoadTimeValue,
            bottomAverageElasticEnergyValue, bottomElasticEnergyDeviationValue, bottomElasticEnergyLoadTimeValue,
            bottomAverageDislocationDensityValue, bottomDislocationDensityDeviationValue, bottomDislocationDensityLoadTimeValue,
            bottomAverageXMomentValue, bottomXMomentDeviationValue, bottomXMomentLoadTimeValue,
            bottomAverageYMomentValue, bottomYMomentDeviationValue, bottomYMomentLoadTimeValue,
            bottomAverageZMomentValue, bottomZMomentDeviationValue, bottomZMomentLoadTimeValue;

    private SimpleDoubleProperty leftAverageTemperatureValue, leftTemperatureDeviationValue, leftTemperatureLoadTimeValue,
            leftAverageElasticEnergyValue, leftElasticEnergyDeviationValue, leftElasticEnergyLoadTimeValue,
            leftAverageDislocationDensityValue, leftDislocationDensityDeviationValue, leftDislocationDensityLoadTimeValue,
            leftAverageXMomentValue, leftXMomentDeviationValue, leftXMomentLoadTimeValue,
            leftAverageYMomentValue, leftYMomentDeviationValue, leftYMomentLoadTimeValue,
            leftAverageZMomentValue, leftZMomentDeviationValue, leftZMomentLoadTimeValue;

    private SimpleDoubleProperty rightAverageTemperatureValue, rightTemperatureDeviationValue, rightTemperatureLoadTimeValue,
            rightAverageElasticEnergyValue, rightElasticEnergyDeviationValue, rightElasticEnergyLoadTimeValue,
            rightAverageDislocationDensityValue, rightDislocationDensityDeviationValue, rightDislocationDensityLoadTimeValue,
            rightAverageXMomentValue, rightXMomentDeviationValue, rightXMomentLoadTimeValue,
            rightAverageYMomentValue, rightYMomentDeviationValue, rightYMomentLoadTimeValue,
            rightAverageZMomentValue, rightZMomentDeviationValue, rightZMomentLoadTimeValue;

    private SimpleDoubleProperty frontAverageTemperatureValue, frontTemperatureDeviationValue, frontTemperatureLoadTimeValue,
            frontAverageElasticEnergyValue, frontElasticEnergyDeviationValue, frontElasticEnergyLoadTimeValue,
            frontAverageDislocationDensityValue, frontDislocationDensityDeviationValue, frontDislocationDensityLoadTimeValue,
            frontAverageXMomentValue, frontXMomentDeviationValue, frontXMomentLoadTimeValue,
            frontAverageYMomentValue, frontYMomentDeviationValue, frontYMomentLoadTimeValue,
            frontAverageZMomentValue, frontZMomentDeviationValue, frontZMomentLoadTimeValue;

    private SimpleDoubleProperty backAverageTemperatureValue, backTemperatureDeviationValue, backTemperatureLoadTimeValue,
            backAverageElasticEnergyValue, backElasticEnergyDeviationValue, backElasticEnergyLoadTimeValue,
            backAverageDislocationDensityValue, backDislocationDensityDeviationValue, backDislocationDensityLoadTimeValue,
            backAverageXMomentValue, backXMomentDeviationValue, backXMomentLoadTimeValue,
            backAverageYMomentValue, backYMomentDeviationValue, backYMomentLoadTimeValue,
            backAverageZMomentValue, backZMomentDeviationValue, backZMomentLoadTimeValue;

    private Button okButton, cancelButton;

    public BoundaryConditionDataEditor(){
        initAllValues();
        initAllComponents();
        addAllComponents();
        handleEvents();
        this.setTitle("Boundary Condition - Editor");
        this.show();
    }

    public BoundaryConditionDataEditor(List<BoundaryCondition> boundaryConditions){
        initAllValues();
        initAllComponents();
        addAllComponents();
        handleEvents();

        if (!boundaryConditions.isEmpty()){
            String boundCondName = boundaryConditions.get(0).getName();
            this.nameTextField.setText(boundCondName);
            this.facetsComboBox.setDisable(false);
            for (BoundaryCondition condition : boundaryConditions){
                this.setBoundaryConditionData(condition);
            }
        }



        this.setTitle("Boundary Condition - Editor");
        this.show();
    }

    private void initAllValues(){
        topAverageTemperatureValue = new SimpleDoubleProperty(0.0);
        topTemperatureDeviationValue = new SimpleDoubleProperty(0.0);
        topTemperatureLoadTimeValue = new SimpleDoubleProperty(0.0);

        topAverageElasticEnergyValue = new SimpleDoubleProperty(0.0);
        topElasticEnergyDeviationValue = new SimpleDoubleProperty(0.0);
        topElasticEnergyLoadTimeValue = new SimpleDoubleProperty(0.0);

        topAverageDislocationDensityValue = new SimpleDoubleProperty(0.0);
        topDislocationDensityDeviationValue = new SimpleDoubleProperty(0.0);
        topDislocationDensityLoadTimeValue = new SimpleDoubleProperty(0.0);

        topAverageXMomentValue = new SimpleDoubleProperty(0.0);
        topXMomentDeviationValue = new SimpleDoubleProperty(0.0);
        topXMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        topAverageYMomentValue = new SimpleDoubleProperty(0.0);
        topYMomentDeviationValue = new SimpleDoubleProperty(0.0);
        topYMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        topAverageZMomentValue = new SimpleDoubleProperty(0.0);
        topZMomentDeviationValue = new SimpleDoubleProperty(0.0);
        topZMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        bottomAverageTemperatureValue = new SimpleDoubleProperty(0.0);
        bottomTemperatureDeviationValue = new SimpleDoubleProperty(0.0);
        bottomTemperatureLoadTimeValue = new SimpleDoubleProperty(0.0);

        bottomAverageElasticEnergyValue = new SimpleDoubleProperty(0.0);
        bottomElasticEnergyDeviationValue = new SimpleDoubleProperty(0.0);
        bottomElasticEnergyLoadTimeValue = new SimpleDoubleProperty(0.0);

        bottomAverageDislocationDensityValue = new SimpleDoubleProperty(0.0);
        bottomDislocationDensityDeviationValue = new SimpleDoubleProperty(0.0);
        bottomDislocationDensityLoadTimeValue = new SimpleDoubleProperty(0.0);

        bottomAverageXMomentValue = new SimpleDoubleProperty(0.0);
        bottomXMomentDeviationValue = new SimpleDoubleProperty(0.0);
        bottomXMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        bottomAverageYMomentValue = new SimpleDoubleProperty(0.0);
        bottomYMomentDeviationValue = new SimpleDoubleProperty(0.0);
        bottomYMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        bottomAverageZMomentValue = new SimpleDoubleProperty(0.0);
        bottomZMomentDeviationValue = new SimpleDoubleProperty(0.0);
        bottomZMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        leftAverageTemperatureValue = new SimpleDoubleProperty(0.0);
        leftTemperatureDeviationValue = new SimpleDoubleProperty(0.0);
        leftTemperatureLoadTimeValue = new SimpleDoubleProperty(0.0);

        leftAverageElasticEnergyValue = new SimpleDoubleProperty(0.0);
        leftElasticEnergyDeviationValue = new SimpleDoubleProperty(0.0);
        leftElasticEnergyLoadTimeValue = new SimpleDoubleProperty(0.0);

        leftAverageDislocationDensityValue = new SimpleDoubleProperty(0.0);
        leftDislocationDensityDeviationValue = new SimpleDoubleProperty(0.0);
        leftDislocationDensityLoadTimeValue = new SimpleDoubleProperty(0.0);

        leftAverageXMomentValue = new SimpleDoubleProperty(0.0);
        leftXMomentDeviationValue = new SimpleDoubleProperty(0.0);
        leftXMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        leftAverageYMomentValue = new SimpleDoubleProperty(0.0);
        leftYMomentDeviationValue = new SimpleDoubleProperty(0.0);
        leftYMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        leftAverageZMomentValue = new SimpleDoubleProperty(0.0);
        leftZMomentDeviationValue = new SimpleDoubleProperty(0.0);
        leftZMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        rightAverageTemperatureValue = new SimpleDoubleProperty(0.0);
        rightTemperatureDeviationValue = new SimpleDoubleProperty(0.0);
        rightTemperatureLoadTimeValue = new SimpleDoubleProperty(0.0);

        rightAverageElasticEnergyValue = new SimpleDoubleProperty(0.0);
        rightElasticEnergyDeviationValue = new SimpleDoubleProperty(0.0);
        rightElasticEnergyLoadTimeValue = new SimpleDoubleProperty(0.0);

        rightAverageDislocationDensityValue = new SimpleDoubleProperty(0.0);
        rightDislocationDensityDeviationValue = new SimpleDoubleProperty(0.0);
        rightDislocationDensityLoadTimeValue = new SimpleDoubleProperty(0.0);

        rightAverageXMomentValue = new SimpleDoubleProperty(0.0);
        rightXMomentDeviationValue = new SimpleDoubleProperty(0.0);
        rightXMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        rightAverageYMomentValue = new SimpleDoubleProperty(0.0);
        rightYMomentDeviationValue = new SimpleDoubleProperty(0.0);
        rightYMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        rightAverageZMomentValue = new SimpleDoubleProperty(0.0);
        rightZMomentDeviationValue = new SimpleDoubleProperty(0.0);
        rightZMomentLoadTimeValue = new SimpleDoubleProperty(0.0);


        frontAverageTemperatureValue = new SimpleDoubleProperty(0.0);
        frontTemperatureDeviationValue = new SimpleDoubleProperty(0.0);
        frontTemperatureLoadTimeValue = new SimpleDoubleProperty(0.0);

        frontAverageElasticEnergyValue = new SimpleDoubleProperty(0.0);
        frontElasticEnergyDeviationValue = new SimpleDoubleProperty(0.0);
        frontElasticEnergyLoadTimeValue = new SimpleDoubleProperty(0.0);

        frontAverageDislocationDensityValue = new SimpleDoubleProperty(0.0);
        frontDislocationDensityDeviationValue = new SimpleDoubleProperty(0.0);
        frontDislocationDensityLoadTimeValue = new SimpleDoubleProperty(0.0);

        frontAverageXMomentValue = new SimpleDoubleProperty(0.0);
        frontXMomentDeviationValue = new SimpleDoubleProperty(0.0);
        frontXMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        frontAverageYMomentValue = new SimpleDoubleProperty(0.0);
        frontYMomentDeviationValue = new SimpleDoubleProperty(0.0);
        frontYMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        frontAverageZMomentValue = new SimpleDoubleProperty(0.0);
        frontZMomentDeviationValue = new SimpleDoubleProperty(0.0);
        frontZMomentLoadTimeValue = new SimpleDoubleProperty(0.0);


        backAverageTemperatureValue = new SimpleDoubleProperty(0.0);
        backTemperatureDeviationValue = new SimpleDoubleProperty(0.0);
        backTemperatureLoadTimeValue = new SimpleDoubleProperty(0.0);

        backAverageElasticEnergyValue = new SimpleDoubleProperty(0.0);
        backElasticEnergyDeviationValue = new SimpleDoubleProperty(0.0);
        backElasticEnergyLoadTimeValue = new SimpleDoubleProperty(0.0);

        backAverageDislocationDensityValue = new SimpleDoubleProperty(0.0);
        backDislocationDensityDeviationValue = new SimpleDoubleProperty(0.0);
        backDislocationDensityLoadTimeValue = new SimpleDoubleProperty(0.0);

        backAverageXMomentValue = new SimpleDoubleProperty(0.0);
        backXMomentDeviationValue = new SimpleDoubleProperty(0.0);
        backXMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        backAverageYMomentValue = new SimpleDoubleProperty(0.0);
        backYMomentDeviationValue = new SimpleDoubleProperty(0.0);
        backYMomentLoadTimeValue = new SimpleDoubleProperty(0.0);

        backAverageZMomentValue = new SimpleDoubleProperty(0.0);
        backZMomentDeviationValue = new SimpleDoubleProperty(0.0);
        backZMomentLoadTimeValue = new SimpleDoubleProperty(0.0);
    }

    private void initAllComponents(){
        root = new BorderPane();
        scene = new Scene(root);

        nameTextField = new TextField();

        topFacetCheckBox = new CheckBox("Top");
        bottomFacetCheckBox = new CheckBox("Bottom");
        leftFacetCheckBox = new CheckBox("Left");
        rightFacetCheckBox = new CheckBox("Right");
        frontFacetCheckBox = new CheckBox("Front");
        backFacetCheckBox = new CheckBox("Back");

        facetsComboBox = new ComboBox();
        facetsComboBox.setDisable(true);

        temperatureComboBox = new ComboBox();
        temperatureComboBox.getItems().addAll(conditionType);
        temperatureComboBox.getSelectionModel().selectFirst();

        elasticEnergyComboBox = new ComboBox();
        elasticEnergyComboBox.getItems().addAll(conditionType);
        elasticEnergyComboBox.getSelectionModel().selectFirst();

        dislocationDensityComboBox = new ComboBox();
        dislocationDensityComboBox.getItems().addAll(conditionType);
        dislocationDensityComboBox.getSelectionModel().selectFirst();

        xMomentComboBox = new ComboBox();
        xMomentComboBox.getItems().addAll(conditionType);
        xMomentComboBox.getSelectionModel().selectFirst();

        yMomentComboBox = new ComboBox();
        yMomentComboBox.getItems().addAll(conditionType);
        yMomentComboBox.getSelectionModel().selectFirst();

        zMomentComboBox = new ComboBox();
        zMomentComboBox.getItems().addAll(conditionType);
        zMomentComboBox.getSelectionModel().selectFirst();

        averageTemperatureTextField = new TextField();
        temperatureDeviationTextField = new TextField();
        temperatureLoadTimeTextField = new TextField();

        averageElasticEnergyTextField = new TextField();
        elasticEnergyDeviationTextField = new TextField();
        elasticEnergyLoadTimeTextField = new TextField();

        averageDislocationDensTextField = new TextField();
        dislocationDensDeviationTextField = new TextField();
        dislocationDensLoadTimeTextField = new TextField();

        averageXMomentTextField = new TextField();
        xMomentDeviationTextField = new TextField();
        xMomentLoadTimeTextField = new TextField();

        averageYMomentTextField = new TextField();
        yMomentDeviationTextField = new TextField();
        yMomentLoadTimeTextField = new TextField();

        averageZMomentTextField = new TextField();
        zMomentDeviationTextField = new TextField();
        zMomentLoadTimeTextField = new TextField();

        okButton = new Button("OK");
        okButton.setPadding(new Insets(10, 20, 10, 20));
        cancelButton = new Button("Cancel");
        cancelButton.setPadding(new Insets(10, 20, 10, 20));
    }

    private void addAllComponents(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);

        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();

        TabPane tabPane = initTabPane();
        Label l1 = new Label("Name:");
        Label l2 = new Label("Change Facet");

        GridPane.setConstraints(l1, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(nameTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator1, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(topFacetCheckBox, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(bottomFacetCheckBox, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(leftFacetCheckBox, 0, 3, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(rightFacetCheckBox, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(frontFacetCheckBox, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(backFacetCheckBox, 1, 4, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(separator2, 0, 5, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l2, 0, 6,  1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(facetsComboBox, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator3, 0, 7, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(tabPane, 0, 8, 3, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l1, l2, separator1, separator2, separator3,
                nameTextField, facetsComboBox,
                tabPane,
                topFacetCheckBox, bottomFacetCheckBox, leftFacetCheckBox,
                rightFacetCheckBox, frontFacetCheckBox, backFacetCheckBox
        );
        root.setCenter(gridPane);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setAlignment(Pos.CENTER);

        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPadding(new Insets(2, 10, 2, 10));

        hBox.getChildren().addAll(okButton, separator, cancelButton);

        root.setBottom(hBox);

        this.setScene(scene);
    }

    private TabPane initTabPane(){
        TabPane tabPane = new TabPane();

        Tab temperatureTab = new Tab(UICommon.TEMPERATURE);
        temperatureTab.setClosable(false);
        temperatureTab.setContent(getTemperatureTabContent());

        Tab elasticEnergyTab = new Tab(UICommon.ELASTIC_ENERGY);
        elasticEnergyTab.setClosable(false);
        elasticEnergyTab.setContent(getElasticEnergyTabContent());

        Tab dislocationDensityTab = new Tab(UICommon.DISLOCATION_DENSITY);
        dislocationDensityTab.setClosable(false);
        dislocationDensityTab.setContent(getDislocationDensityTabContent());

        Tab momentXTab = new Tab(UICommon.MOMENTS_X);
        momentXTab.setClosable(false);
        momentXTab.setContent(getMomentXTabContent());

        Tab momentYTab = new Tab(UICommon.MOMENTS_Y);
        momentYTab.setClosable(false);
        momentYTab.setContent(getMomentYTabContent());

        Tab momentZTab = new Tab(UICommon.MOMENTS_Z);
        momentZTab.setClosable(false);
        momentZTab.setContent(getMomentZTabContent());

        tabPane.getTabs().addAll(
                temperatureTab, elasticEnergyTab, dislocationDensityTab,
                momentXTab, momentYTab, momentZTab
        );
        return tabPane;
    }

    private void handleEvents(){
        topFacetCheckBox.setOnAction(e -> {
            if(topFacetCheckBox.isSelected()){
                facetsComboBox.setDisable(false);
                facetsComboBox.getItems().add(UICommon.TOP);
            }
            else{
                facetsComboBox.getItems().remove(UICommon.TOP);
                if(facetsComboBox.getItems().isEmpty()){
                    facetsComboBox.setDisable(true);
                }
            }
        });

        bottomFacetCheckBox.setOnAction(e -> {
            if(bottomFacetCheckBox.isSelected()){
                facetsComboBox.setDisable(false);
                facetsComboBox.getItems().add(UICommon.BOTTOM);
            }
            else{
                facetsComboBox.getItems().remove(UICommon.BOTTOM);
                if(facetsComboBox.getItems().isEmpty()){
                    facetsComboBox.setDisable(true);
                }
            }
        });

        leftFacetCheckBox.setOnAction(e -> {
            if(leftFacetCheckBox.isSelected()){
                facetsComboBox.setDisable(false);
                facetsComboBox.getItems().add(UICommon.LEFT);
            }
            else{
                facetsComboBox.getItems().remove(UICommon.LEFT);
                if(facetsComboBox.getItems().isEmpty()){
                    facetsComboBox.setDisable(true);
                }
            }
        });

        rightFacetCheckBox.setOnAction(e -> {
            if(rightFacetCheckBox.isSelected()){
                facetsComboBox.setDisable(false);
                facetsComboBox.getItems().add(UICommon.RIGHT);
            }
            else{
                facetsComboBox.getItems().remove(UICommon.RIGHT);
                if(facetsComboBox.getItems().isEmpty()){
                    facetsComboBox.setDisable(true);
                }
            }
        });

        frontFacetCheckBox.setOnAction(e -> {
            if(frontFacetCheckBox.isSelected()){
                facetsComboBox.setDisable(false);
                facetsComboBox.getItems().add(UICommon.FRONT);
            }
            else{
                facetsComboBox.getItems().remove(UICommon.FRONT);
                if(facetsComboBox.getItems().isEmpty()){
                    facetsComboBox.setDisable(true);
                }
            }
        });

        backFacetCheckBox.setOnAction(e -> {
            if(backFacetCheckBox.isSelected()){
                facetsComboBox.setDisable(false);
                facetsComboBox.getItems().add(UICommon.BACK);
            }
            else{
                facetsComboBox.getItems().remove(UICommon.BACK);
                if(facetsComboBox.getItems().isEmpty()){
                    facetsComboBox.setDisable(true);
                }
            }
        });

        facetsComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)->{
            String selectedFacet = String.valueOf(newValue);
            switch (selectedFacet){
                case UICommon.TOP:
                    unbindAllValues();
                    setTopFacetValues();
                    break;
                case UICommon.BOTTOM:
                    unbindAllValues();
                    setBottomFacetValues();
                    break;
                case UICommon.LEFT:
                    unbindAllValues();
                    setLeftFacetValues();
                    break;
                case UICommon.RIGHT:
                    unbindAllValues();
                    setRightFacetValues();
                    break;
                case UICommon.FRONT:
                    unbindAllValues();
                    setFrontFacetValues();
                    break;
                case UICommon.BACK:
                    unbindAllValues();
                    setBackFacetValues();
                    break;
            }
        });

        cancelButton.setOnAction(e -> {
            this.close();
        });
    }

    private GridPane getTemperatureTabContent(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5.0);
        gridPane.setHgap(5.0);

        Label l0 = new Label("Select Type of Loading:");
        Label l1 = new Label("Average");
        Label l2 = new Label("Deviation");
        Label l3 = new Label("Loading Time");

        Separator separator = new Separator();

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(temperatureComboBox, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(averageTemperatureTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(temperatureDeviationTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(temperatureLoadTimeTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l0, l1, l2, l3, separator,
                temperatureComboBox,
                averageTemperatureTextField,
                temperatureDeviationTextField,
                temperatureLoadTimeTextField
        );
        return gridPane;
    }

    private GridPane getElasticEnergyTabContent(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5.0);
        gridPane.setHgap(5.0);

        Separator separator = new Separator();

        Label l0 = new Label("Select Type of Loading:");
        Label l1 = new Label("Average");
        Label l2 = new Label("Deviation");
        Label l3 = new Label("Loading Time");

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(elasticEnergyComboBox, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(averageElasticEnergyTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(elasticEnergyDeviationTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(elasticEnergyLoadTimeTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l0, l1, l2, l3, separator,
                elasticEnergyComboBox,
                averageElasticEnergyTextField,
                elasticEnergyDeviationTextField,
                elasticEnergyLoadTimeTextField
        );
        return gridPane;
    }

    private GridPane getDislocationDensityTabContent(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5.0);
        gridPane.setHgap(5.0);

        Separator separator = new Separator();

        Label l0 = new Label("Select Type of Loading:");
        Label l1 = new Label("Average");
        Label l2 = new Label("Deviation");
        Label l3 = new Label("Loading Time");

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(dislocationDensityComboBox, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(averageDislocationDensTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(dislocationDensDeviationTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(dislocationDensLoadTimeTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l0, l1, l2, l3, separator,
                dislocationDensityComboBox,
                averageDislocationDensTextField,
                dislocationDensDeviationTextField,
                dislocationDensLoadTimeTextField
        );
        return gridPane;
    }

    private GridPane getMomentXTabContent(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5.0);
        gridPane.setHgap(5.0);

        Separator separator = new Separator();

        Label l0 = new Label("Select Type of Loading:");
        Label l1 = new Label("Average");
        Label l2 = new Label("Deviation");
        Label l3 = new Label("Loading Time");

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(xMomentComboBox, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(averageXMomentTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(xMomentDeviationTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(xMomentLoadTimeTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l0, l1, l2, l3, separator,
                xMomentComboBox,
                averageXMomentTextField,
                xMomentDeviationTextField,
                xMomentLoadTimeTextField
        );
        return gridPane;
    }

    private GridPane getMomentYTabContent(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5.0);
        gridPane.setHgap(5.0);

        Separator separator = new Separator();

        Label l0 = new Label("Select Type of Loading:");
        Label l1 = new Label("Average");
        Label l2 = new Label("Deviation");
        Label l3 = new Label("Loading Time");

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(yMomentComboBox, 1, 0,1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(averageYMomentTextField, 1, 2,1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(yMomentDeviationTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(yMomentLoadTimeTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator, 0, 1,2, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l0, l1, l2, l3, separator,
                yMomentComboBox,
                averageYMomentTextField,
                yMomentDeviationTextField,
                yMomentLoadTimeTextField
        );
        return gridPane;
    }

    private GridPane getMomentZTabContent(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5.0);
        gridPane.setHgap(5.0);

        Separator separator = new Separator();

        Label l0 = new Label("Select Type of Loading:");
        Label l1 = new Label("Average");
        Label l2 = new Label("Deviation");
        Label l3 = new Label("Loading Time");

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(zMomentComboBox, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(averageZMomentTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(zMomentDeviationTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(zMomentLoadTimeTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(separator, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l1, l2, l3,l0, separator,
                zMomentComboBox,
                averageZMomentTextField,
                zMomentDeviationTextField,
                zMomentLoadTimeTextField
        );
        return gridPane;
    }

    public void handleOKButton(String specimenName, ComboBox comboBox, boolean editing, String boundCondName){
        okButton.setOnAction(e -> {
            System.out.println("Action Event: ok button is pushed");

            String conditionName = nameTextField.getText();

            if (topFacetCheckBox.isSelected()){

                String facet = UICommon.TOP;

                double averageTemperature = topAverageTemperatureValue.get();
                double temperatureDeviation = topTemperatureDeviationValue.get();
                double temperatureLoadTime = topTemperatureLoadTimeValue.get();
                double averageElasticEnergy = topAverageElasticEnergyValue.get();
                double elasticEnergryDeviation = topElasticEnergyDeviationValue.get();
                double elasticEnergyLoadTime = topElasticEnergyLoadTimeValue.get();
                double averageDislocationDensity = topAverageDislocationDensityValue.get();
                double dislocationDensityDeviation = topDislocationDensityDeviationValue.get();
                double dislocationDensityLoadTime = topDislocationDensityLoadTimeValue.get();
                double averageMomentX = topAverageXMomentValue.get();
                double momentXDeviation = topXMomentDeviationValue.get();
                double momentXLoadTime = topXMomentLoadTimeValue.get();
                double averageMomentY = topAverageYMomentValue.get();
                double momentYDeviation = topYMomentDeviationValue.get();
                double momentYLoadTime = topYMomentLoadTimeValue.get();
                double averageMomentZ = topAverageZMomentValue.get();
                double momentZDeviation = topZMomentDeviationValue.get();
                double momentZLoadTime = topZMomentLoadTimeValue.get();

                BoundaryCondition topBoundaryCondition = new BoundaryCondition(
                        conditionName, facet,
                        averageTemperature, temperatureDeviation, temperatureLoadTime,
                        averageElasticEnergy, elasticEnergryDeviation, elasticEnergyLoadTime,
                        averageDislocationDensity, dislocationDensityDeviation, dislocationDensityLoadTime,
                        averageMomentX, momentXDeviation, momentXLoadTime,
                        averageMomentY, momentYDeviation, momentYLoadTime,
                        averageMomentZ, momentZDeviation, momentZLoadTime);

                if (editing){
                    DataBaseUtils.boundaryConditionDataBase.updateBoundaryCondition(topBoundaryCondition, boundCondName);
                }
                else {
                    DataBaseUtils.boundaryConditionDataBase.addBoundaryCondition(topBoundaryCondition);
                }
            }

            if (bottomFacetCheckBox.isSelected()){
                String facet = UICommon.BOTTOM;

                double averageTemperature = bottomAverageTemperatureValue.get();
                double temperatureDeviation = bottomTemperatureDeviationValue.get();
                double temperatureLoadTime = bottomTemperatureLoadTimeValue.get();
                double averageElasticEnergy = bottomAverageElasticEnergyValue.get();
                double elasticEnergryDeviation = bottomElasticEnergyDeviationValue.get();
                double elasticEnergyLoadTime = bottomElasticEnergyLoadTimeValue.get();
                double averageDislocationDensity = bottomAverageDislocationDensityValue.get();
                double dislocationDensityDeviation = bottomDislocationDensityDeviationValue.get();
                double dislocationDensityLoadTime = bottomDislocationDensityLoadTimeValue.get();
                double averageMomentX = bottomAverageXMomentValue.get();
                double momentXDeviation = bottomXMomentDeviationValue.get();
                double momentXLoadTime = bottomXMomentLoadTimeValue.get();
                double averageMomentY = bottomAverageYMomentValue.get();
                double momentYDeviation = bottomYMomentDeviationValue.get();
                double momentYLoadTime = bottomYMomentLoadTimeValue.get();
                double averageMomentZ = bottomAverageZMomentValue.get();
                double momentZDeviation = bottomZMomentDeviationValue.get();
                double momentZLoadTime = bottomZMomentLoadTimeValue.get();

                BoundaryCondition bottomBoundaryCondition = new BoundaryCondition(
                        conditionName, facet,
                        averageTemperature, temperatureDeviation, temperatureLoadTime,
                        averageElasticEnergy, elasticEnergryDeviation, elasticEnergyLoadTime,
                        averageDislocationDensity, dislocationDensityDeviation, dislocationDensityLoadTime,
                        averageMomentX, momentXDeviation, momentXLoadTime,
                        averageMomentY, momentYDeviation, momentYLoadTime,
                        averageMomentZ, momentZDeviation, momentZLoadTime);

                if (editing){
                    DataBaseUtils.boundaryConditionDataBase.updateBoundaryCondition(bottomBoundaryCondition, boundCondName);
                }
                else {
                    DataBaseUtils.boundaryConditionDataBase.addBoundaryCondition(bottomBoundaryCondition);
                }
            }

            if (leftFacetCheckBox.isSelected()){
                String facet = UICommon.LEFT;

                double averageTemperature = leftAverageTemperatureValue.get();
                double temperatureDeviation = leftTemperatureDeviationValue.get();
                double temperatureLoadTime = leftTemperatureLoadTimeValue.get();
                double averageElasticEnergy = leftAverageElasticEnergyValue.get();
                double elasticEnergryDeviation = leftElasticEnergyDeviationValue.get();
                double elasticEnergyLoadTime = leftElasticEnergyLoadTimeValue.get();
                double averageDislocationDensity = leftAverageDislocationDensityValue.get();
                double dislocationDensityDeviation = leftDislocationDensityDeviationValue.get();
                double dislocationDensityLoadTime = leftDislocationDensityLoadTimeValue.get();
                double averageMomentX = leftAverageXMomentValue.get();
                double momentXDeviation = leftXMomentDeviationValue.get();
                double momentXLoadTime = leftXMomentLoadTimeValue.get();
                double averageMomentY = leftAverageYMomentValue.get();
                double momentYDeviation = leftYMomentDeviationValue.get();
                double momentYLoadTime = leftYMomentLoadTimeValue.get();
                double averageMomentZ = leftAverageZMomentValue.get();
                double momentZDeviation = leftZMomentDeviationValue.get();
                double momentZLoadTime = leftZMomentLoadTimeValue.get();

                BoundaryCondition leftBoundaryCondition = new BoundaryCondition(
                        conditionName, facet,
                        averageTemperature, temperatureDeviation, temperatureLoadTime,
                        averageElasticEnergy, elasticEnergryDeviation, elasticEnergyLoadTime,
                        averageDislocationDensity, dislocationDensityDeviation, dislocationDensityLoadTime,
                        averageMomentX, momentXDeviation, momentXLoadTime,
                        averageMomentY, momentYDeviation, momentYLoadTime,
                        averageMomentZ, momentZDeviation, momentZLoadTime);

                if (editing)
                    DataBaseUtils.boundaryConditionDataBase.updateBoundaryCondition(leftBoundaryCondition, boundCondName);
                else
                    DataBaseUtils.boundaryConditionDataBase.addBoundaryCondition(leftBoundaryCondition);
            }

            if (rightFacetCheckBox.isSelected()){
                String facet = UICommon.RIGHT;

                double averageTemperature = rightAverageTemperatureValue.get();
                double temperatureDeviation = rightTemperatureDeviationValue.get();
                double temperatureLoadTime = rightTemperatureLoadTimeValue.get();
                double averageElasticEnergy = rightAverageElasticEnergyValue.get();
                double elasticEnergryDeviation = rightElasticEnergyDeviationValue.get();
                double elasticEnergyLoadTime = rightElasticEnergyLoadTimeValue.get();
                double averageDislocationDensity = rightAverageDislocationDensityValue.get();
                double dislocationDensityDeviation = rightDislocationDensityDeviationValue.get();
                double dislocationDensityLoadTime = rightDislocationDensityLoadTimeValue.get();
                double averageMomentX = rightAverageXMomentValue.get();
                double momentXDeviation = rightXMomentDeviationValue.get();
                double momentXLoadTime = rightXMomentLoadTimeValue.get();
                double averageMomentY = rightAverageYMomentValue.get();
                double momentYDeviation = rightYMomentDeviationValue.get();
                double momentYLoadTime = rightYMomentLoadTimeValue.get();
                double averageMomentZ = rightAverageZMomentValue.get();
                double momentZDeviation = rightZMomentDeviationValue.get();
                double momentZLoadTime = rightZMomentLoadTimeValue.get();

                BoundaryCondition rightBoundaryCondition = new BoundaryCondition(
                        conditionName, facet,
                        averageTemperature, temperatureDeviation, temperatureLoadTime,
                        averageElasticEnergy, elasticEnergryDeviation, elasticEnergyLoadTime,
                        averageDislocationDensity, dislocationDensityDeviation, dislocationDensityLoadTime,
                        averageMomentX, momentXDeviation, momentXLoadTime,
                        averageMomentY, momentYDeviation, momentYLoadTime,
                        averageMomentZ, momentZDeviation, momentZLoadTime);

                if (editing)
                    DataBaseUtils.boundaryConditionDataBase.updateBoundaryCondition(rightBoundaryCondition, boundCondName);
                else
                    DataBaseUtils.boundaryConditionDataBase.addBoundaryCondition(rightBoundaryCondition);
            }

            if (frontFacetCheckBox.isSelected()){
                String facet = UICommon.FRONT;

                double averageTemperature = frontAverageTemperatureValue.get();
                double temperatureDeviation = frontTemperatureDeviationValue.get();
                double temperatureLoadTime = frontTemperatureLoadTimeValue.get();
                double averageElasticEnergy = frontAverageElasticEnergyValue.get();
                double elasticEnergryDeviation = frontElasticEnergyDeviationValue.get();
                double elasticEnergyLoadTime = frontElasticEnergyLoadTimeValue.get();
                double averageDislocationDensity = frontAverageDislocationDensityValue.get();
                double dislocationDensityDeviation = frontDislocationDensityDeviationValue.get();
                double dislocationDensityLoadTime = frontDislocationDensityLoadTimeValue.get();
                double averageMomentX = frontAverageXMomentValue.get();
                double momentXDeviation = frontXMomentDeviationValue.get();
                double momentXLoadTime = frontXMomentLoadTimeValue.get();
                double averageMomentY = frontAverageYMomentValue.get();
                double momentYDeviation = frontYMomentDeviationValue.get();
                double momentYLoadTime = frontYMomentLoadTimeValue.get();
                double averageMomentZ = frontAverageZMomentValue.get();
                double momentZDeviation = frontZMomentDeviationValue.get();
                double momentZLoadTime = frontZMomentLoadTimeValue.get();

                BoundaryCondition frontBoundaryCondition = new BoundaryCondition(
                        conditionName, facet,
                        averageTemperature, temperatureDeviation, temperatureLoadTime,
                        averageElasticEnergy, elasticEnergryDeviation, elasticEnergyLoadTime,
                        averageDislocationDensity, dislocationDensityDeviation, dislocationDensityLoadTime,
                        averageMomentX, momentXDeviation, momentXLoadTime,
                        averageMomentY, momentYDeviation, momentYLoadTime,
                        averageMomentZ, momentZDeviation, momentZLoadTime);

                if (editing)
                    DataBaseUtils.boundaryConditionDataBase.updateBoundaryCondition(frontBoundaryCondition, boundCondName);
                else
                    DataBaseUtils.boundaryConditionDataBase.addBoundaryCondition(frontBoundaryCondition);
            }

            if (backFacetCheckBox.isSelected()){
                String facet = UICommon.BACK;

                double averageTemperature = backAverageTemperatureValue.get();
                double temperatureDeviation = backTemperatureDeviationValue.get();
                double temperatureLoadTime = backTemperatureLoadTimeValue.get();
                double averageElasticEnergy = backAverageElasticEnergyValue.get();
                double elasticEnergryDeviation = backElasticEnergyDeviationValue.get();
                double elasticEnergyLoadTime = backElasticEnergyLoadTimeValue.get();
                double averageDislocationDensity = backAverageDislocationDensityValue.get();
                double dislocationDensityDeviation = backDislocationDensityDeviationValue.get();
                double dislocationDensityLoadTime = backDislocationDensityLoadTimeValue.get();
                double averageMomentX = backAverageXMomentValue.get();
                double momentXDeviation = backXMomentDeviationValue.get();
                double momentXLoadTime = backXMomentLoadTimeValue.get();
                double averageMomentY = backAverageYMomentValue.get();
                double momentYDeviation = backYMomentDeviationValue.get();
                double momentYLoadTime = backYMomentLoadTimeValue.get();
                double averageMomentZ = backAverageZMomentValue.get();
                double momentZDeviation = backZMomentDeviationValue.get();
                double momentZLoadTime = backZMomentLoadTimeValue.get();

                BoundaryCondition backBoundaryCondition = new BoundaryCondition(
                        conditionName, facet,
                        averageTemperature, temperatureDeviation, temperatureLoadTime,
                        averageElasticEnergy, elasticEnergryDeviation, elasticEnergyLoadTime,
                        averageDislocationDensity, dislocationDensityDeviation, dislocationDensityLoadTime,
                        averageMomentX, momentXDeviation, momentXLoadTime,
                        averageMomentY, momentYDeviation, momentYLoadTime,
                        averageMomentZ, momentZDeviation, momentZLoadTime);

                if (editing)
                    DataBaseUtils.boundaryConditionDataBase.updateBoundaryCondition(backBoundaryCondition, boundCondName);
                else
                    DataBaseUtils.boundaryConditionDataBase.addBoundaryCondition(backBoundaryCondition);
            }
            comboBox.getItems().add(conditionName);
            comboBox.getSelectionModel().select(conditionName);
            this.close();
        });
    }

    private void unbindAllValues(){
        // Unbinding values for top facet
        averageTemperatureTextField.textProperty().unbindBidirectional(topAverageTemperatureValue);
        temperatureDeviationTextField.textProperty().unbindBidirectional(topTemperatureDeviationValue);
        temperatureLoadTimeTextField.textProperty().unbindBidirectional(topTemperatureLoadTimeValue);

        averageElasticEnergyTextField.textProperty().unbindBidirectional(topAverageElasticEnergyValue);
        elasticEnergyDeviationTextField.textProperty().unbindBidirectional(topElasticEnergyDeviationValue);
        elasticEnergyLoadTimeTextField.textProperty().unbindBidirectional(topElasticEnergyLoadTimeValue);

        averageDislocationDensTextField.textProperty().unbindBidirectional(topAverageDislocationDensityValue);
        dislocationDensDeviationTextField.textProperty().unbindBidirectional(topDislocationDensityDeviationValue);
        dislocationDensLoadTimeTextField.textProperty().unbindBidirectional(topDislocationDensityLoadTimeValue);

        averageXMomentTextField.textProperty().unbindBidirectional(topAverageXMomentValue);
        xMomentDeviationTextField.textProperty().unbindBidirectional(topXMomentDeviationValue);
        xMomentLoadTimeTextField.textProperty().unbindBidirectional(topXMomentLoadTimeValue);

        averageYMomentTextField.textProperty().unbindBidirectional(topAverageYMomentValue);
        yMomentDeviationTextField.textProperty().unbindBidirectional(topYMomentDeviationValue);
        yMomentLoadTimeTextField.textProperty().unbindBidirectional(topYMomentLoadTimeValue);

        averageZMomentTextField.textProperty().unbindBidirectional(topAverageZMomentValue);
        zMomentDeviationTextField.textProperty().unbindBidirectional(topZMomentDeviationValue);
        zMomentLoadTimeTextField.textProperty().unbindBidirectional(topZMomentLoadTimeValue);
        //------------------------------------------------------------------------------------

        // Unbinding values for bottom facet
        averageTemperatureTextField.textProperty().unbindBidirectional(bottomAverageTemperatureValue);
        temperatureDeviationTextField.textProperty().unbindBidirectional(bottomTemperatureDeviationValue);
        temperatureLoadTimeTextField.textProperty().unbindBidirectional(bottomTemperatureLoadTimeValue);

        averageElasticEnergyTextField.textProperty().unbindBidirectional(bottomAverageElasticEnergyValue);
        elasticEnergyDeviationTextField.textProperty().unbindBidirectional(bottomElasticEnergyDeviationValue);
        elasticEnergyLoadTimeTextField.textProperty().unbindBidirectional(bottomElasticEnergyLoadTimeValue);

        averageDislocationDensTextField.textProperty().unbindBidirectional(bottomAverageDislocationDensityValue);
        dislocationDensDeviationTextField.textProperty().unbindBidirectional(bottomDislocationDensityDeviationValue);
        dislocationDensLoadTimeTextField.textProperty().unbindBidirectional(bottomDislocationDensityLoadTimeValue);

        averageXMomentTextField.textProperty().unbindBidirectional(bottomAverageXMomentValue);
        xMomentDeviationTextField.textProperty().unbindBidirectional(bottomXMomentDeviationValue);
        xMomentLoadTimeTextField.textProperty().unbindBidirectional(bottomXMomentLoadTimeValue);

        averageYMomentTextField.textProperty().unbindBidirectional(bottomAverageYMomentValue);
        yMomentDeviationTextField.textProperty().unbindBidirectional(bottomYMomentDeviationValue);
        yMomentLoadTimeTextField.textProperty().unbindBidirectional(bottomYMomentLoadTimeValue);

        averageZMomentTextField.textProperty().unbindBidirectional(bottomAverageZMomentValue);
        zMomentDeviationTextField.textProperty().unbindBidirectional(bottomZMomentDeviationValue);
        zMomentLoadTimeTextField.textProperty().unbindBidirectional(bottomZMomentLoadTimeValue);
        //------------------------------------------------------------------------------------

        // Unbinding values for left facet
        averageTemperatureTextField.textProperty().unbindBidirectional(leftAverageTemperatureValue);
        temperatureDeviationTextField.textProperty().unbindBidirectional(leftTemperatureDeviationValue);
        temperatureLoadTimeTextField.textProperty().unbindBidirectional(leftTemperatureLoadTimeValue);

        averageElasticEnergyTextField.textProperty().unbindBidirectional(leftAverageElasticEnergyValue);
        elasticEnergyDeviationTextField.textProperty().unbindBidirectional(leftElasticEnergyDeviationValue);
        elasticEnergyLoadTimeTextField.textProperty().unbindBidirectional(leftElasticEnergyLoadTimeValue);

        averageDislocationDensTextField.textProperty().unbindBidirectional(leftAverageDislocationDensityValue);
        dislocationDensDeviationTextField.textProperty().unbindBidirectional(leftDislocationDensityDeviationValue);
        dislocationDensLoadTimeTextField.textProperty().unbindBidirectional(leftDislocationDensityLoadTimeValue);

        averageXMomentTextField.textProperty().unbindBidirectional(leftAverageXMomentValue);
        xMomentDeviationTextField.textProperty().unbindBidirectional(leftXMomentDeviationValue);
        xMomentLoadTimeTextField.textProperty().unbindBidirectional(leftXMomentLoadTimeValue);

        averageYMomentTextField.textProperty().unbindBidirectional(leftAverageYMomentValue);
        yMomentDeviationTextField.textProperty().unbindBidirectional(leftYMomentDeviationValue);
        yMomentLoadTimeTextField.textProperty().unbindBidirectional(leftYMomentLoadTimeValue);

        averageZMomentTextField.textProperty().unbindBidirectional(leftAverageZMomentValue);
        zMomentDeviationTextField.textProperty().unbindBidirectional(leftZMomentDeviationValue);
        zMomentLoadTimeTextField.textProperty().unbindBidirectional(leftZMomentLoadTimeValue);
        //------------------------------------------------------------------------------------

        // Unbinding values for right facet
        averageTemperatureTextField.textProperty().unbindBidirectional(rightAverageTemperatureValue);
        temperatureDeviationTextField.textProperty().unbindBidirectional(rightTemperatureDeviationValue);
        temperatureLoadTimeTextField.textProperty().unbindBidirectional(rightTemperatureLoadTimeValue);

        averageElasticEnergyTextField.textProperty().unbindBidirectional(rightAverageElasticEnergyValue);
        elasticEnergyDeviationTextField.textProperty().unbindBidirectional(rightElasticEnergyDeviationValue);
        elasticEnergyLoadTimeTextField.textProperty().unbindBidirectional(rightElasticEnergyLoadTimeValue);

        averageDislocationDensTextField.textProperty().unbindBidirectional(rightAverageDislocationDensityValue);
        dislocationDensDeviationTextField.textProperty().unbindBidirectional(rightDislocationDensityDeviationValue);
        dislocationDensLoadTimeTextField.textProperty().unbindBidirectional(rightDislocationDensityLoadTimeValue);

        averageXMomentTextField.textProperty().unbindBidirectional(rightAverageXMomentValue);
        xMomentDeviationTextField.textProperty().unbindBidirectional(rightXMomentDeviationValue);
        xMomentLoadTimeTextField.textProperty().unbindBidirectional(rightXMomentLoadTimeValue);

        averageYMomentTextField.textProperty().unbindBidirectional(rightAverageYMomentValue);
        yMomentDeviationTextField.textProperty().unbindBidirectional(rightYMomentDeviationValue);
        yMomentLoadTimeTextField.textProperty().unbindBidirectional(rightYMomentLoadTimeValue);

        averageZMomentTextField.textProperty().unbindBidirectional(rightAverageZMomentValue);
        zMomentDeviationTextField.textProperty().unbindBidirectional(rightZMomentDeviationValue);
        zMomentLoadTimeTextField.textProperty().unbindBidirectional(rightZMomentLoadTimeValue);
        //------------------------------------------------------------------------------------

        // Unbinding values for front facet
        averageTemperatureTextField.textProperty().unbindBidirectional(frontAverageTemperatureValue);
        temperatureDeviationTextField.textProperty().unbindBidirectional(frontTemperatureDeviationValue);
        temperatureLoadTimeTextField.textProperty().unbindBidirectional(frontTemperatureLoadTimeValue);

        averageElasticEnergyTextField.textProperty().unbindBidirectional(frontAverageElasticEnergyValue);
        elasticEnergyDeviationTextField.textProperty().unbindBidirectional(frontElasticEnergyDeviationValue);
        elasticEnergyLoadTimeTextField.textProperty().unbindBidirectional(frontElasticEnergyLoadTimeValue);

        averageDislocationDensTextField.textProperty().unbindBidirectional(frontAverageDislocationDensityValue);
        dislocationDensDeviationTextField.textProperty().unbindBidirectional(frontDislocationDensityDeviationValue);
        dislocationDensLoadTimeTextField.textProperty().unbindBidirectional(frontDislocationDensityLoadTimeValue);

        averageXMomentTextField.textProperty().unbindBidirectional(frontAverageXMomentValue);
        xMomentDeviationTextField.textProperty().unbindBidirectional(frontXMomentDeviationValue);
        xMomentLoadTimeTextField.textProperty().unbindBidirectional(frontXMomentLoadTimeValue);

        averageYMomentTextField.textProperty().unbindBidirectional(frontAverageYMomentValue);
        yMomentDeviationTextField.textProperty().unbindBidirectional(frontYMomentDeviationValue);
        yMomentLoadTimeTextField.textProperty().unbindBidirectional(frontYMomentLoadTimeValue);

        averageZMomentTextField.textProperty().unbindBidirectional(frontAverageZMomentValue);
        zMomentDeviationTextField.textProperty().unbindBidirectional(frontZMomentDeviationValue);
        zMomentLoadTimeTextField.textProperty().unbindBidirectional(frontZMomentLoadTimeValue);
        //------------------------------------------------------------------------------------

        // Unbinding values for back facet
        averageTemperatureTextField.textProperty().unbindBidirectional(backAverageTemperatureValue);
        temperatureDeviationTextField.textProperty().unbindBidirectional(backTemperatureDeviationValue);
        temperatureLoadTimeTextField.textProperty().unbindBidirectional(backTemperatureLoadTimeValue);

        averageElasticEnergyTextField.textProperty().unbindBidirectional(backAverageElasticEnergyValue);
        elasticEnergyDeviationTextField.textProperty().unbindBidirectional(backElasticEnergyDeviationValue);
        elasticEnergyLoadTimeTextField.textProperty().unbindBidirectional(backElasticEnergyLoadTimeValue);

        averageDislocationDensTextField.textProperty().unbindBidirectional(backAverageDislocationDensityValue);
        dislocationDensDeviationTextField.textProperty().unbindBidirectional(backDislocationDensityDeviationValue);
        dislocationDensLoadTimeTextField.textProperty().unbindBidirectional(backDislocationDensityLoadTimeValue);

        averageXMomentTextField.textProperty().unbindBidirectional(backAverageXMomentValue);
        xMomentDeviationTextField.textProperty().unbindBidirectional(backXMomentDeviationValue);
        xMomentLoadTimeTextField.textProperty().unbindBidirectional(backXMomentLoadTimeValue);

        averageYMomentTextField.textProperty().unbindBidirectional(backAverageYMomentValue);
        yMomentDeviationTextField.textProperty().unbindBidirectional(backYMomentDeviationValue);
        yMomentLoadTimeTextField.textProperty().unbindBidirectional(backYMomentLoadTimeValue);

        averageZMomentTextField.textProperty().unbindBidirectional(backAverageZMomentValue);
        zMomentDeviationTextField.textProperty().unbindBidirectional(backZMomentDeviationValue);
        zMomentLoadTimeTextField.textProperty().unbindBidirectional(backZMomentLoadTimeValue);
        //------------------------------------------------------------------------------------
    }

    private void setTopFacetValues(){
        StringConverter stringConverter = new DoubleStringConverter();
        averageTemperatureTextField.textProperty().bindBidirectional(topAverageTemperatureValue, stringConverter);
        temperatureDeviationTextField.textProperty().bindBidirectional(topTemperatureDeviationValue,stringConverter);
        temperatureLoadTimeTextField.textProperty().bindBidirectional(topTemperatureLoadTimeValue,stringConverter);

        averageElasticEnergyTextField.textProperty().bindBidirectional(topAverageElasticEnergyValue,stringConverter);
        elasticEnergyDeviationTextField.textProperty().bindBidirectional(topElasticEnergyDeviationValue,stringConverter);
        elasticEnergyLoadTimeTextField.textProperty().bindBidirectional(topElasticEnergyLoadTimeValue,stringConverter);

        averageDislocationDensTextField.textProperty().bindBidirectional(topAverageDislocationDensityValue,stringConverter);
        dislocationDensDeviationTextField.textProperty().bindBidirectional(topDislocationDensityDeviationValue,stringConverter);
        dislocationDensLoadTimeTextField.textProperty().bindBidirectional(topDislocationDensityLoadTimeValue,stringConverter);

        averageXMomentTextField.textProperty().bindBidirectional(topAverageXMomentValue,stringConverter);
        xMomentDeviationTextField.textProperty().bindBidirectional(topXMomentDeviationValue,stringConverter);
        xMomentLoadTimeTextField.textProperty().bindBidirectional(topXMomentLoadTimeValue,stringConverter);

        averageYMomentTextField.textProperty().bindBidirectional(topAverageYMomentValue,stringConverter);
        yMomentDeviationTextField.textProperty().bindBidirectional(topYMomentDeviationValue,stringConverter);
        yMomentLoadTimeTextField.textProperty().bindBidirectional(topYMomentLoadTimeValue,stringConverter);

        averageZMomentTextField.textProperty().bindBidirectional(topAverageZMomentValue,stringConverter);
        zMomentDeviationTextField.textProperty().bindBidirectional(topZMomentDeviationValue,stringConverter);
        zMomentLoadTimeTextField.textProperty().bindBidirectional(topZMomentLoadTimeValue,stringConverter);
    }

    private void setBottomFacetValues(){
        StringConverter stringConverter = new DoubleStringConverter();
        averageTemperatureTextField.textProperty().bindBidirectional(bottomAverageTemperatureValue, stringConverter);
        temperatureDeviationTextField.textProperty().bindBidirectional(bottomTemperatureDeviationValue,stringConverter);
        temperatureLoadTimeTextField.textProperty().bindBidirectional(bottomTemperatureLoadTimeValue,stringConverter);

        averageElasticEnergyTextField.textProperty().bindBidirectional(bottomAverageElasticEnergyValue,stringConverter);
        elasticEnergyDeviationTextField.textProperty().bindBidirectional(bottomElasticEnergyDeviationValue,stringConverter);
        elasticEnergyLoadTimeTextField.textProperty().bindBidirectional(bottomElasticEnergyLoadTimeValue,stringConverter);

        averageDislocationDensTextField.textProperty().bindBidirectional(bottomAverageDislocationDensityValue,stringConverter);
        dislocationDensDeviationTextField.textProperty().bindBidirectional(bottomDislocationDensityDeviationValue,stringConverter);
        dislocationDensLoadTimeTextField.textProperty().bindBidirectional(bottomDislocationDensityLoadTimeValue,stringConverter);

        averageXMomentTextField.textProperty().bindBidirectional(bottomAverageXMomentValue,stringConverter);
        xMomentDeviationTextField.textProperty().bindBidirectional(bottomXMomentDeviationValue,stringConverter);
        xMomentLoadTimeTextField.textProperty().bindBidirectional(bottomXMomentLoadTimeValue,stringConverter);

        averageYMomentTextField.textProperty().bindBidirectional(bottomAverageYMomentValue,stringConverter);
        yMomentDeviationTextField.textProperty().bindBidirectional(bottomYMomentDeviationValue,stringConverter);
        yMomentLoadTimeTextField.textProperty().bindBidirectional(bottomYMomentLoadTimeValue,stringConverter);

        averageZMomentTextField.textProperty().bindBidirectional(bottomAverageZMomentValue,stringConverter);
        zMomentDeviationTextField.textProperty().bindBidirectional(bottomZMomentDeviationValue,stringConverter);
        zMomentLoadTimeTextField.textProperty().bindBidirectional(bottomZMomentLoadTimeValue,stringConverter);
    }

    private void setLeftFacetValues(){
        StringConverter stringConverter = new DoubleStringConverter();
        averageTemperatureTextField.textProperty().bindBidirectional(leftAverageTemperatureValue, stringConverter);
        temperatureDeviationTextField.textProperty().bindBidirectional(leftTemperatureDeviationValue,stringConverter);
        temperatureLoadTimeTextField.textProperty().bindBidirectional(leftTemperatureLoadTimeValue,stringConverter);

        averageElasticEnergyTextField.textProperty().bindBidirectional(leftAverageElasticEnergyValue,stringConverter);
        elasticEnergyDeviationTextField.textProperty().bindBidirectional(leftElasticEnergyDeviationValue,stringConverter);
        elasticEnergyLoadTimeTextField.textProperty().bindBidirectional(leftElasticEnergyLoadTimeValue,stringConverter);

        averageDislocationDensTextField.textProperty().bindBidirectional(leftAverageDislocationDensityValue,stringConverter);
        dislocationDensDeviationTextField.textProperty().bindBidirectional(leftDislocationDensityDeviationValue,stringConverter);
        dislocationDensLoadTimeTextField.textProperty().bindBidirectional(leftDislocationDensityLoadTimeValue,stringConverter);

        averageXMomentTextField.textProperty().bindBidirectional(leftAverageXMomentValue,stringConverter);
        xMomentDeviationTextField.textProperty().bindBidirectional(leftXMomentDeviationValue,stringConverter);
        xMomentLoadTimeTextField.textProperty().bindBidirectional(leftXMomentLoadTimeValue,stringConverter);

        averageYMomentTextField.textProperty().bindBidirectional(leftAverageYMomentValue,stringConverter);
        yMomentDeviationTextField.textProperty().bindBidirectional(leftYMomentDeviationValue,stringConverter);
        yMomentLoadTimeTextField.textProperty().bindBidirectional(leftYMomentLoadTimeValue,stringConverter);

        averageZMomentTextField.textProperty().bindBidirectional(leftAverageZMomentValue,stringConverter);
        zMomentDeviationTextField.textProperty().bindBidirectional(leftZMomentDeviationValue,stringConverter);
        zMomentLoadTimeTextField.textProperty().bindBidirectional(leftZMomentLoadTimeValue,stringConverter);
    }

    private void setRightFacetValues(){
        StringConverter stringConverter = new DoubleStringConverter();
        averageTemperatureTextField.textProperty().bindBidirectional(rightAverageTemperatureValue, stringConverter);
        temperatureDeviationTextField.textProperty().bindBidirectional(rightTemperatureDeviationValue,stringConverter);
        temperatureLoadTimeTextField.textProperty().bindBidirectional(rightTemperatureLoadTimeValue,stringConverter);

        averageElasticEnergyTextField.textProperty().bindBidirectional(rightAverageElasticEnergyValue,stringConverter);
        elasticEnergyDeviationTextField.textProperty().bindBidirectional(rightElasticEnergyDeviationValue,stringConverter);
        elasticEnergyLoadTimeTextField.textProperty().bindBidirectional(rightElasticEnergyLoadTimeValue,stringConverter);

        averageDislocationDensTextField.textProperty().bindBidirectional(rightAverageDislocationDensityValue,stringConverter);
        dislocationDensDeviationTextField.textProperty().bindBidirectional(rightDislocationDensityDeviationValue,stringConverter);
        dislocationDensLoadTimeTextField.textProperty().bindBidirectional(rightDislocationDensityLoadTimeValue,stringConverter);

        averageXMomentTextField.textProperty().bindBidirectional(rightAverageXMomentValue,stringConverter);
        xMomentDeviationTextField.textProperty().bindBidirectional(rightXMomentDeviationValue,stringConverter);
        xMomentLoadTimeTextField.textProperty().bindBidirectional(rightXMomentLoadTimeValue,stringConverter);

        averageYMomentTextField.textProperty().bindBidirectional(rightAverageYMomentValue,stringConverter);
        yMomentDeviationTextField.textProperty().bindBidirectional(rightYMomentDeviationValue,stringConverter);
        yMomentLoadTimeTextField.textProperty().bindBidirectional(rightYMomentLoadTimeValue,stringConverter);

        averageZMomentTextField.textProperty().bindBidirectional(rightAverageZMomentValue,stringConverter);
        zMomentDeviationTextField.textProperty().bindBidirectional(rightZMomentDeviationValue,stringConverter);
        zMomentLoadTimeTextField.textProperty().bindBidirectional(rightZMomentLoadTimeValue,stringConverter);
    }

    private void setFrontFacetValues(){
        StringConverter stringConverter = new DoubleStringConverter();
        averageTemperatureTextField.textProperty().bindBidirectional(frontAverageTemperatureValue, stringConverter);
        temperatureDeviationTextField.textProperty().bindBidirectional(frontTemperatureDeviationValue,stringConverter);
        temperatureLoadTimeTextField.textProperty().bindBidirectional(frontTemperatureLoadTimeValue,stringConverter);

        averageElasticEnergyTextField.textProperty().bindBidirectional(frontAverageElasticEnergyValue,stringConverter);
        elasticEnergyDeviationTextField.textProperty().bindBidirectional(frontElasticEnergyDeviationValue,stringConverter);
        elasticEnergyLoadTimeTextField.textProperty().bindBidirectional(frontElasticEnergyLoadTimeValue,stringConverter);

        averageDislocationDensTextField.textProperty().bindBidirectional(frontAverageDislocationDensityValue,stringConverter);
        dislocationDensDeviationTextField.textProperty().bindBidirectional(frontDislocationDensityDeviationValue,stringConverter);
        dislocationDensLoadTimeTextField.textProperty().bindBidirectional(frontDislocationDensityLoadTimeValue,stringConverter);

        averageXMomentTextField.textProperty().bindBidirectional(frontAverageXMomentValue,stringConverter);
        xMomentDeviationTextField.textProperty().bindBidirectional(frontXMomentDeviationValue,stringConverter);
        xMomentLoadTimeTextField.textProperty().bindBidirectional(frontXMomentLoadTimeValue,stringConverter);

        averageYMomentTextField.textProperty().bindBidirectional(frontAverageYMomentValue,stringConverter);
        yMomentDeviationTextField.textProperty().bindBidirectional(frontYMomentDeviationValue,stringConverter);
        yMomentLoadTimeTextField.textProperty().bindBidirectional(frontYMomentLoadTimeValue,stringConverter);

        averageZMomentTextField.textProperty().bindBidirectional(frontAverageZMomentValue,stringConverter);
        zMomentDeviationTextField.textProperty().bindBidirectional(frontZMomentDeviationValue,stringConverter);
        zMomentLoadTimeTextField.textProperty().bindBidirectional(frontZMomentLoadTimeValue,stringConverter);
    }

    private void setBackFacetValues(){
        StringConverter stringConverter = new DoubleStringConverter();
        averageTemperatureTextField.textProperty().bindBidirectional(backAverageTemperatureValue, stringConverter);
        temperatureDeviationTextField.textProperty().bindBidirectional(backTemperatureDeviationValue,stringConverter);
        temperatureLoadTimeTextField.textProperty().bindBidirectional(backTemperatureLoadTimeValue,stringConverter);

        averageElasticEnergyTextField.textProperty().bindBidirectional(backAverageElasticEnergyValue,stringConverter);
        elasticEnergyDeviationTextField.textProperty().bindBidirectional(backElasticEnergyDeviationValue,stringConverter);
        elasticEnergyLoadTimeTextField.textProperty().bindBidirectional(backElasticEnergyLoadTimeValue,stringConverter);

        averageDislocationDensTextField.textProperty().bindBidirectional(backAverageDislocationDensityValue,stringConverter);
        dislocationDensDeviationTextField.textProperty().bindBidirectional(backDislocationDensityDeviationValue,stringConverter);
        dislocationDensLoadTimeTextField.textProperty().bindBidirectional(backDislocationDensityLoadTimeValue,stringConverter);

        averageXMomentTextField.textProperty().bindBidirectional(backAverageXMomentValue,stringConverter);
        xMomentDeviationTextField.textProperty().bindBidirectional(backXMomentDeviationValue,stringConverter);
        xMomentLoadTimeTextField.textProperty().bindBidirectional(backXMomentLoadTimeValue,stringConverter);

        averageYMomentTextField.textProperty().bindBidirectional(backAverageYMomentValue,stringConverter);
        yMomentDeviationTextField.textProperty().bindBidirectional(backYMomentDeviationValue,stringConverter);
        yMomentLoadTimeTextField.textProperty().bindBidirectional(backYMomentLoadTimeValue,stringConverter);

        averageZMomentTextField.textProperty().bindBidirectional(backAverageZMomentValue,stringConverter);
        zMomentDeviationTextField.textProperty().bindBidirectional(backZMomentDeviationValue,stringConverter);
        zMomentLoadTimeTextField.textProperty().bindBidirectional(backZMomentLoadTimeValue,stringConverter);
    }

    private void setBoundaryConditionData(BoundaryCondition boundaryCondition){

        switch (boundaryCondition.getFacet()){
            case "Top":
                topAverageTemperatureValue.set(boundaryCondition.getAverageTemperature());
                topTemperatureDeviationValue.set(boundaryCondition.getTemperatureRange());
                topTemperatureLoadTimeValue.set(boundaryCondition.getTemperatureLoadingTimePortion());

                topAverageElasticEnergyValue.set(boundaryCondition.getAverageElasticEnergy());
                topElasticEnergyDeviationValue.set(boundaryCondition.getElasticEnergyRange());
                topElasticEnergyLoadTimeValue.set(boundaryCondition.getElasticEnergyLoadingTimePortion());

                topAverageDislocationDensityValue.set(boundaryCondition.getAverageDislocationDensity());
                topDislocationDensityDeviationValue.set(boundaryCondition.getDislocationDensityRange());
                topDislocationDensityLoadTimeValue.set(boundaryCondition.getDislocationDensityLoadingTimePortion());

                topAverageXMomentValue.set(boundaryCondition.getAverageMomentX());
                topXMomentDeviationValue.set(boundaryCondition.getMomentRangeX());
                topXMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionX());

                topAverageYMomentValue.set(boundaryCondition.getAverageMomentY());
                topYMomentDeviationValue.set(boundaryCondition.getMomentRangeY());
                topYMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionY());

                topAverageZMomentValue.set(boundaryCondition.getAverageMomentZ());
                topZMomentDeviationValue.set(boundaryCondition.getMomentRangeZ());
                topZMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionZ());
                topFacetCheckBox.setSelected(true);
                facetsComboBox.getItems().add(UICommon.TOP);
                break;

            case "Bottom":
                bottomAverageTemperatureValue.set(boundaryCondition.getAverageTemperature());
                bottomTemperatureDeviationValue.set(boundaryCondition.getTemperatureRange());
                bottomTemperatureLoadTimeValue.set(boundaryCondition.getTemperatureLoadingTimePortion());

                bottomAverageElasticEnergyValue.set(boundaryCondition.getAverageElasticEnergy());
                bottomElasticEnergyDeviationValue.set(boundaryCondition.getElasticEnergyRange());
                bottomElasticEnergyLoadTimeValue.set(boundaryCondition.getElasticEnergyLoadingTimePortion());

                bottomAverageDislocationDensityValue.set(boundaryCondition.getAverageDislocationDensity());
                bottomDislocationDensityDeviationValue.set(boundaryCondition.getDislocationDensityRange());
                bottomDislocationDensityLoadTimeValue.set(boundaryCondition.getDislocationDensityLoadingTimePortion());

                bottomAverageXMomentValue.set(boundaryCondition.getAverageMomentX());
                bottomXMomentDeviationValue.set(boundaryCondition.getMomentRangeX());
                bottomXMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionX());

                bottomAverageYMomentValue.set(boundaryCondition.getAverageMomentY());
                bottomYMomentDeviationValue.set(boundaryCondition.getMomentRangeY());
                bottomYMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionY());

                bottomAverageZMomentValue.set(boundaryCondition.getAverageMomentZ());
                bottomZMomentDeviationValue.set(boundaryCondition.getMomentRangeZ());
                bottomZMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionZ());
                bottomFacetCheckBox.setSelected(true);
                facetsComboBox.getItems().add(UICommon.BOTTOM);
                break;

            case "Left":
                leftAverageTemperatureValue.set(boundaryCondition.getAverageTemperature());
                leftTemperatureDeviationValue.set(boundaryCondition.getTemperatureRange());
                leftTemperatureLoadTimeValue.set(boundaryCondition.getTemperatureLoadingTimePortion());

                leftAverageElasticEnergyValue.set(boundaryCondition.getAverageElasticEnergy());
                leftElasticEnergyDeviationValue.set(boundaryCondition.getElasticEnergyRange());
                leftElasticEnergyLoadTimeValue.set(boundaryCondition.getElasticEnergyLoadingTimePortion());

                leftAverageDislocationDensityValue.set(boundaryCondition.getAverageDislocationDensity());
                leftDislocationDensityDeviationValue.set(boundaryCondition.getDislocationDensityRange());
                leftDislocationDensityLoadTimeValue.set(boundaryCondition.getDislocationDensityLoadingTimePortion());

                leftAverageXMomentValue.set(boundaryCondition.getAverageMomentX());
                leftXMomentDeviationValue.set(boundaryCondition.getMomentRangeX());
                leftXMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionX());

                leftAverageYMomentValue.set(boundaryCondition.getAverageMomentY());
                leftYMomentDeviationValue.set(boundaryCondition.getMomentRangeY());
                leftYMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionY());

                leftAverageZMomentValue.set(boundaryCondition.getAverageMomentZ());
                leftZMomentDeviationValue.set(boundaryCondition.getMomentRangeZ());
                leftZMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionZ());
                leftFacetCheckBox.setSelected(true);
                facetsComboBox.getItems().add(UICommon.LEFT);
                break;

            case "Right":
                rightAverageTemperatureValue.set(boundaryCondition.getAverageTemperature());
                rightTemperatureDeviationValue.set(boundaryCondition.getTemperatureRange());
                rightTemperatureLoadTimeValue.set(boundaryCondition.getTemperatureLoadingTimePortion());

                rightAverageElasticEnergyValue.set(boundaryCondition.getAverageElasticEnergy());
                rightElasticEnergyDeviationValue.set(boundaryCondition.getElasticEnergyRange());
                rightElasticEnergyLoadTimeValue.set(boundaryCondition.getElasticEnergyLoadingTimePortion());

                rightAverageDislocationDensityValue.set(boundaryCondition.getAverageDislocationDensity());
                rightDislocationDensityDeviationValue.set(boundaryCondition.getDislocationDensityRange());
                rightDislocationDensityLoadTimeValue.set(boundaryCondition.getDislocationDensityLoadingTimePortion());

                rightAverageXMomentValue.set(boundaryCondition.getAverageMomentX());
                rightXMomentDeviationValue.set(boundaryCondition.getMomentRangeX());
                rightXMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionX());

                rightAverageYMomentValue.set(boundaryCondition.getAverageMomentY());
                rightYMomentDeviationValue.set(boundaryCondition.getMomentRangeY());
                rightYMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionY());

                rightAverageZMomentValue.set(boundaryCondition.getAverageMomentZ());
                rightZMomentDeviationValue.set(boundaryCondition.getMomentRangeZ());
                rightZMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionZ());
                rightFacetCheckBox.setSelected(true);
                facetsComboBox.getItems().add(UICommon.RIGHT);
                break;

            case "Front":
                frontAverageTemperatureValue.set(boundaryCondition.getAverageTemperature());
                frontTemperatureDeviationValue.set(boundaryCondition.getTemperatureRange());
                frontTemperatureLoadTimeValue.set(boundaryCondition.getTemperatureLoadingTimePortion());

                frontAverageElasticEnergyValue.set(boundaryCondition.getAverageElasticEnergy());
                frontElasticEnergyDeviationValue.set(boundaryCondition.getElasticEnergyRange());
                frontElasticEnergyLoadTimeValue.set(boundaryCondition.getElasticEnergyLoadingTimePortion());

                frontAverageDislocationDensityValue.set(boundaryCondition.getAverageDislocationDensity());
                frontDislocationDensityDeviationValue.set(boundaryCondition.getDislocationDensityRange());
                frontDislocationDensityLoadTimeValue.set(boundaryCondition.getDislocationDensityLoadingTimePortion());

                frontAverageXMomentValue.set(boundaryCondition.getAverageMomentX());
                frontXMomentDeviationValue.set(boundaryCondition.getMomentRangeX());
                frontXMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionX());

                frontAverageYMomentValue.set(boundaryCondition.getAverageMomentY());
                frontYMomentDeviationValue.set(boundaryCondition.getMomentRangeY());
                frontYMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionY());

                frontAverageZMomentValue.set(boundaryCondition.getAverageMomentZ());
                frontZMomentDeviationValue.set(boundaryCondition.getMomentRangeZ());
                frontZMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionZ());
                frontFacetCheckBox.setSelected(true);
                facetsComboBox.getItems().add(UICommon.FRONT);
                break;

            case "Back":
                backAverageTemperatureValue.set(boundaryCondition.getAverageTemperature());
                backTemperatureDeviationValue.set(boundaryCondition.getTemperatureRange());
                backTemperatureLoadTimeValue.set(boundaryCondition.getTemperatureLoadingTimePortion());

                backAverageElasticEnergyValue.set(boundaryCondition.getAverageElasticEnergy());
                backElasticEnergyDeviationValue.set(boundaryCondition.getElasticEnergyRange());
                backElasticEnergyLoadTimeValue.set(boundaryCondition.getElasticEnergyLoadingTimePortion());

                backAverageDislocationDensityValue.set(boundaryCondition.getAverageDislocationDensity());
                backDislocationDensityDeviationValue.set(boundaryCondition.getDislocationDensityRange());
                backDislocationDensityLoadTimeValue.set(boundaryCondition.getDislocationDensityLoadingTimePortion());

                backAverageXMomentValue.set(boundaryCondition.getAverageMomentX());
                backXMomentDeviationValue.set(boundaryCondition.getMomentRangeX());
                backXMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionX());

                backAverageYMomentValue.set(boundaryCondition.getAverageMomentY());
                backYMomentDeviationValue.set(boundaryCondition.getMomentRangeY());
                backYMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionY());

                backAverageZMomentValue.set(boundaryCondition.getAverageMomentZ());
                backZMomentDeviationValue.set(boundaryCondition.getMomentRangeZ());
                backZMomentLoadTimeValue.set(boundaryCondition.getMomentLoadingTimePortionZ());
                backFacetCheckBox.setSelected(true);
                facetsComboBox.getItems().add(UICommon.BACK);
                break;

            default:
                System.out.println("Incorrect facet description");
                break;
        }

    }

}
