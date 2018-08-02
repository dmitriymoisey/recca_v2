package view;

import databases.DataBaseUtils;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.InitialCondition;
import utils.MaterialData;
import utils.SpecimenData;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;


public class SpecimenDataEditor extends Stage {

    private Scene scene;
    private BorderPane root;

    private TextField specimenNameTextField,
            numberOfGrainsTextField,
            angleRangeTextField,
            cellNumberXTextField,
            cellNumberYTextField,
            cellNumberZTextField;

    private ComboBox materialsComboBox, initialConditionsComboBox,
            boundaryConditionsComboBox, tasksComboBox;

    private Button okButton, cancelButton;
    private Button editMaterial, editInitCond, editBoundCond, editTask;

    public SpecimenDataEditor(){
        root = new BorderPane();
        scene = new Scene(root);

        initAllComponents();
        addAllComponents();
        handleEvents();

        this.setScene(scene);
        this.setTitle("TEST");
        this.show();
    }

    public void setSpecimenData(SpecimenData specimenData){
        specimenNameTextField.setText(specimenData.getSpecimenName());
        numberOfGrainsTextField.setText(String.valueOf(specimenData.getNumberOfGrains()));
        angleRangeTextField.setText(String.valueOf(specimenData.getAngleRange()));
        cellNumberXTextField.setText(String.valueOf(specimenData.getCellNumberX()));
        cellNumberYTextField.setText(String.valueOf(specimenData.getCellNumberX()));
        cellNumberZTextField.setText(String.valueOf(specimenData.getCellNumberZ()));

        List<String> initialConditionsNames = new ArrayList<>();
        for (InitialCondition condition : DataBaseUtils.getAllInitialConditions(specimenData.getSpecimenName())){
            initialConditionsNames.add(condition.getName());
        }
        initialConditionsComboBox.getItems().addAll(initialConditionsNames);

        boundaryConditionsComboBox.getItems().addAll(DataBaseUtils.getAllBoundaryConditionNames(specimenData.getSpecimenName()));
        tasksComboBox.getItems().addAll(DataBaseUtils.getAllTasks(specimenData.getSpecimenName()));

        initialConditionsComboBox.getSelectionModel().select(specimenData.getInitialConditions());
        boundaryConditionsComboBox.getSelectionModel().select(specimenData.getBoundaryConditions());
        tasksComboBox.getSelectionModel().select(specimenData.getTask());
    }

    private void initAllComponents(){

        specimenNameTextField = new TextField();
        specimenNameTextField.setPromptText("Type specimen name");

        numberOfGrainsTextField = new TextField();
        numberOfGrainsTextField.setText("0");

        angleRangeTextField = new TextField();
        angleRangeTextField.setText("0.0");

        cellNumberXTextField = new TextField();
        cellNumberXTextField.setText("0");
        cellNumberYTextField = new TextField();
        cellNumberYTextField.setText("0");
        cellNumberZTextField = new TextField();
        cellNumberZTextField.setText("0");

        okButton = new Button("OK");
        cancelButton = new Button("Cancel");

        materialsComboBox = new ComboBox();
        materialsComboBox.getItems().add(UICommon.CREATE_NEW);

        List<MaterialData> materialData = DataBaseUtils.getAllMaterials();
        for(MaterialData item : materialData){
            materialsComboBox.getItems().add(item.getMaterialName());
        }

        initialConditionsComboBox = new ComboBox();
        initialConditionsComboBox.getItems().add(UICommon.CREATE_NEW);

        boundaryConditionsComboBox = new ComboBox();
        boundaryConditionsComboBox.getItems().add(UICommon.CREATE_NEW);

        tasksComboBox = new ComboBox();
        tasksComboBox.getItems().add(UICommon.CREATE_NEW);

        editMaterial = new Button(UICommon.EDIT);
        editMaterial.setPadding(new Insets(5, 15, 5, 15));
        editMaterial.setDisable(true);

        editInitCond = new Button(UICommon.EDIT);
        editInitCond.setPadding(new Insets(5, 15, 5, 15));
        editInitCond.setDisable(true);

        editBoundCond = new Button(UICommon.EDIT);
        editBoundCond.setPadding(new Insets(5, 15, 5, 15));
        editBoundCond.setDisable(true);

        editTask = new Button(UICommon.EDIT);
        editTask.setPadding(new Insets(5, 15, 5, 15));
        editTask.setDisable(true);
    }

    private void addAllComponents(){
        GridPane centralLayout = new GridPane();
        centralLayout.setPadding(new Insets(10,10,10,10));
        centralLayout.setHgap(10.0);
        centralLayout.setVgap(10.0);
        centralLayout.setAlignment(Pos.CENTER);

        Label l1 = new Label("Specimen Name:");
        Label l2 = new Label("Cell Number X");
        Label l3 = new Label("Cell Number Y");
        Label l4 = new Label("Cell Number Z");
        Label l5 = new Label("Number of Grains:");
        Label l6 = new Label("Angle Range:");
        Label l7 = new Label("Select Material:");
        Label l8 = new Label("Select Initial Conditions:");
        Label l9 = new Label("Select Boundary Conditions:");
        Label l10 = new Label("Select Tasks:");

        Separator s1 = new Separator();
        Separator s2 = new Separator();
        Separator s3 = new Separator();

        GridPane.setConstraints(l1, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(specimenNameTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s1, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l2, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(cellNumberXTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(cellNumberYTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l4, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(cellNumberZTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s2, 0, 5, 2,1,HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l5, 0, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(numberOfGrainsTextField, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l6, 0, 7, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(angleRangeTextField, 1, 7, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s3, 0, 8, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l7, 0, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(materialsComboBox, 1, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editMaterial, 2, 9, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l8, 0, 10, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(initialConditionsComboBox, 1, 10, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editInitCond, 2, 10, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l9, 0, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(boundaryConditionsComboBox, 1, 11, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editBoundCond, 2, 11, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l10, 0, 12, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(tasksComboBox, 1, 12, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editTask, 2, 12, 1, 1, HPos.LEFT, VPos.CENTER);

        centralLayout.getChildren().addAll(
            l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, s1, s2, s3,
            specimenNameTextField, cellNumberXTextField, cellNumberYTextField, cellNumberZTextField,
            numberOfGrainsTextField, angleRangeTextField, materialsComboBox,
            initialConditionsComboBox, boundaryConditionsComboBox, tasksComboBox,
            editMaterial, editInitCond, editBoundCond, editTask
        );
        this.root.setCenter(centralLayout);

        okButton.setPadding(new Insets(10, 30, 10, 30));
        cancelButton.setPadding(new Insets(10, 30, 10, 30));

        Separator s4 = new Separator(Orientation.VERTICAL);
        s4.setPadding(new Insets(1, 10, 1, 10));

        HBox bottomLayout = new HBox();
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setPadding(new Insets(10, 10, 10, 10));
        bottomLayout.getChildren().addAll(okButton, s4, cancelButton);
        this.root.setBottom(bottomLayout);
    }

    public void handleOKButton(List<SpecimenData> list){
        okButton.setOnAction(e -> {
            try{
                String specimenName = specimenNameTextField.getText();
                int cellNumberX = Integer.parseInt(cellNumberXTextField.getText());
                int cellNumberY = Integer.parseInt(cellNumberYTextField.getText());
                int cellNumberZ = Integer.parseInt(cellNumberZTextField.getText());
                int numberOfGrains = Integer.parseInt(numberOfGrainsTextField.getText());
                double angleRange = Double.parseDouble(angleRangeTextField.getText());
                String material = materialsComboBox.getSelectionModel().getSelectedItem().toString();
                String initialCondition = initialConditionsComboBox.getSelectionModel().getSelectedItem().toString();
                String boundaryCondition = boundaryConditionsComboBox.getSelectionModel().getSelectedItem().toString();
                String task = tasksComboBox.getSelectionModel().getSelectedItem().toString();

                SpecimenData specimenData = new SpecimenData(specimenName, cellNumberX, cellNumberY, cellNumberZ,
                        numberOfGrains, angleRange, material, initialCondition,
                        boundaryCondition, task);
                list.add(specimenData);
                DataBaseUtils.addSpecimen(specimenData);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                new Alert(Alert.AlertType.ERROR, "Ошибка ввода данных").show();
            }
        });
    }

    private void handleEvents(){
        materialsComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue.toString())){
                MaterialDataEditor materialDataEditor = new MaterialDataEditor();
                materialDataEditor.handleOKButton(materialsComboBox);
            }
            else{
                editMaterial.setDisable(false);
            }
        });

        initialConditionsComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue.toString())){
                if(!specimenNameTextField.getText().isEmpty()){
                    String specimenName = specimenNameTextField.getText();
                    DataBaseUtils.createInitialConditionTable(specimenName);

                    InitialConditionDataEditor initialConditionDataEditor = new InitialConditionDataEditor();
                    initialConditionDataEditor.handleOKButton(specimenName, initialConditionsComboBox);
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Input Specimen Name First").show();
                    System.out.println("Set Specimen Name First!!!");
                }
            }
            else {
                editInitCond.setDisable(false);
            }
        });

        boundaryConditionsComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue.toString())){
                if(!specimenNameTextField.getText().isEmpty()){
                    String specimenName = specimenNameTextField.getText();
                    DataBaseUtils.createBoundaryConditionTable(specimenName);

                    BoundaryConditionDataEditor materialDataEditor = new BoundaryConditionDataEditor();
                    materialDataEditor.handleOKButton(specimenName, boundaryConditionsComboBox);
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Input Specimen Name First").show();
                    System.out.println("Set Specimen Name First!!!");
                }
            }
            else {
                editBoundCond.setDisable(false);
            }
        });

        tasksComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue.toString())){
                if(!specimenNameTextField.getText().isEmpty()){
                    String specimenName = specimenNameTextField.getText();
                    DataBaseUtils.createTaskTable(specimenName);

                    TaskDataEditor taskDataEditor = new TaskDataEditor();
                    taskDataEditor.handleOKButton(specimenName, tasksComboBox);
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Input Specimen Name First").show();
                    System.out.println("Set Specimen Name First!");
                }
            }
            else {
                editTask.setDisable(false);
            }
        });

        editMaterial.setOnAction(e -> {
            System.out.println("Action Event : Edit Material Button is pushed");
            String selectedMaterial = materialsComboBox.getSelectionModel().getSelectedItem().toString();
            MaterialDataEditor materialDataEditor = new MaterialDataEditor(DataBaseUtils.getMaterial(selectedMaterial));
        });

        editInitCond.setOnAction(e -> {
            System.out.println("Action Event : Edit Initial Condition Button is pushed");
            String selectedItem = initialConditionsComboBox.getSelectionModel().getSelectedItem().toString();
            String specimenName = specimenNameTextField.getText();
            InitialCondition initialCondition = DataBaseUtils.getInitialCondition(specimenName, selectedItem);
            InitialConditionDataEditor initialConditionDataEditor = new InitialConditionDataEditor(initialCondition);
            initialConditionDataEditor.handleOKButton(specimenName, initialConditionsComboBox);
        });

        editBoundCond.setOnAction(e -> {
            System.out.println("Action Event : Edit Boundary Condition Button is pushed");
            String selectedItem = boundaryConditionsComboBox.getSelectionModel().getSelectedItem().toString();
            BoundaryConditionDataEditor boundaryConditionDataEditor = new BoundaryConditionDataEditor();
        });

        editTask.setOnAction(e -> {
            System.out.println("Action Event : Edit Task Button is pushed");
            String selectedItem = tasksComboBox.getSelectionModel().getSelectedItem().toString();
            TaskDataEditor taskDataEditor = new TaskDataEditor();
        });

        cancelButton.setOnAction(e -> {
            this.close();
        });

    }
}
