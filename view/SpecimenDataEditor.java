package view;

import databases.DataBaseUtils;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SpecimenDataEditor extends Stage {

    private Scene scene;
    private BorderPane root;

    private TextField specimenNameTextField,
            numberOfGrainsTextField,
            angleRangeTextField,
            cellNumberXTextField,
            cellNumberYTextField,
            cellNumberZTextField,
            cellSizeTextField;

    private RadioButton stochasticRadioButton, fixedRadioButton;
    private ToggleGroup structureTypeToggleGroup;

    private ComboBox<String> materialsComboBox, initialConditionsComboBox,
            boundaryConditionsComboBox, tasksComboBox;

    private Button okButton, cancelButton;
    private Button editMaterial, editInitCond, editBoundCond, editTask;
    private Button deleteMaterial, deleteInitCond, deleteBoundCond, deleteTask;

    public SpecimenDataEditor(){
        root = new BorderPane();
        scene = new Scene(root);

        initAllComponents();
        addAllComponents();
        handleEvents();

        this.setScene(scene);
        this.setTitle("Specimen - Data Editor");
        this.show();
    }

    public void setSpecimenData(SpecimenData specimenData){
        specimenNameTextField.setText(specimenData.getSpecimenName());
        numberOfGrainsTextField.setText(String.valueOf(specimenData.getNumberOfGrains()));
        angleRangeTextField.setText(String.valueOf(specimenData.getAngleRange()));
        cellNumberXTextField.setText(String.valueOf(specimenData.getCellNumberX()));
        cellNumberYTextField.setText(String.valueOf(specimenData.getCellNumberX()));
        cellNumberZTextField.setText(String.valueOf(specimenData.getCellNumberZ()));
        cellSizeTextField.setText(String.valueOf(specimenData.getCellSize()));

        if (specimenData.getTypeOfGrainDistribution() == Common.FIXED_GRAINS_DISTRIB)
            fixedRadioButton.setSelected(true);
        else
            stochasticRadioButton.setSelected(true);

        materialsComboBox.getSelectionModel().select(specimenData.getMaterial());

        DataBaseUtils.initialConditionDataBase.setSpecimenName(specimenData.getSpecimenName());
        for (InitialCondition condition : DataBaseUtils.initialConditionDataBase.getAllInitialConditions()){
            initialConditionsComboBox.getItems().add(condition.getName());
        }

        DataBaseUtils.boundaryConditionDataBase.setSpecimenName(specimenData.getSpecimenName());
        List<BoundaryCondition> boundaryConditions = DataBaseUtils.boundaryConditionDataBase.getBoundaryConditions();
        List<String> boundaryConditionNames = new ArrayList<>();
        for (BoundaryCondition boundaryCondition : boundaryConditions){
            boundaryConditionNames.add(boundaryCondition.getName());
        }
        boundaryConditionsComboBox.getItems().addAll(boundaryConditionNames.stream().distinct().collect(Collectors.toList()));

        DataBaseUtils.taskDataBase.setSpecimenName(specimenData.getSpecimenName());
        for (Task task : DataBaseUtils.taskDataBase.getAllTasks()){
            tasksComboBox.getItems().add(task.getName());
        }

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

        stochasticRadioButton = new RadioButton();
        stochasticRadioButton.setText(UICommon.STOCHASTIC);
        fixedRadioButton = new RadioButton();
        fixedRadioButton.setText(UICommon.FIXED);

        structureTypeToggleGroup = new ToggleGroup();
        structureTypeToggleGroup.getToggles().addAll(stochasticRadioButton, fixedRadioButton);

        cellSizeTextField = new TextField("0.0");

        okButton = new Button("OK");
        cancelButton = new Button("Cancel");

        materialsComboBox = new ComboBox();
        materialsComboBox.getItems().add(UICommon.CREATE_NEW);

        List<MaterialData> materialData = DataBaseUtils.materialDataBase.getAllMaterials();
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

        deleteMaterial = new Button(UICommon.DELETE);
        deleteMaterial.setPadding(new Insets(5, 15, 5, 15));
        deleteMaterial.setDisable(true);

        editInitCond = new Button(UICommon.EDIT);
        editInitCond.setPadding(new Insets(5, 15, 5, 15));
        editInitCond.setDisable(true);

        deleteInitCond = new Button(UICommon.DELETE);
        deleteInitCond.setPadding(new Insets(5, 15, 5, 15));
        deleteInitCond.setDisable(true);

        editBoundCond = new Button(UICommon.EDIT);
        editBoundCond.setPadding(new Insets(5, 15, 5, 15));
        editBoundCond.setDisable(true);

        deleteBoundCond = new Button(UICommon.DELETE);
        deleteBoundCond.setPadding(new Insets(5, 15, 5, 15));
        deleteBoundCond.setDisable(true);

        editTask = new Button(UICommon.EDIT);
        editTask.setPadding(new Insets(5, 15, 5, 15));
        editTask.setDisable(true);

        deleteTask = new Button(UICommon.DELETE);
        deleteTask.setPadding(new Insets(5, 15, 5, 15));
        deleteTask.setDisable(true);
    }

    private void addAllComponents(){
        GridPane centralLayout = new GridPane();
        centralLayout.setPadding(new Insets(10,10,10,10));
        centralLayout.setHgap(10.0);
        centralLayout.setVgap(10.0);
        centralLayout.setAlignment(Pos.CENTER);

        Label l1 = new Label(UICommon.SPECIMEN_NAME);
        Label l2 = new Label(UICommon.CELL_NUMBER_X);
        Label l3 = new Label(UICommon.CELL_NUMBER_Y);
        Label l4 = new Label(UICommon.CELL_NUMBER_Z);
        Label l11 = new Label(UICommon.CELL_SIZE);
        Label l5 = new Label(UICommon.NUMBER_OF_GRAINS);
        Label l6 = new Label(UICommon.ANGLE_RANGE);
        Label l7 = new Label(UICommon.SELECT_MATERIAL);
        Label l8 = new Label(UICommon.SELECT_INITIAL_CONDITIONS);
        Label l9 = new Label(UICommon.SELECT_BOUNDARY_CONDITIONS);
        Label l10 = new Label(UICommon.SELECT_TASK);

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

        GridPane.setConstraints(l11, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(cellSizeTextField, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s2, 0, 6, 2,1,HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l5, 0, 7, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(numberOfGrainsTextField, 1, 7, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l6, 0, 8, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(angleRangeTextField, 1, 8, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(stochasticRadioButton, 0, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(fixedRadioButton, 1, 9, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s3, 0, 10, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l7, 0, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(materialsComboBox, 1, 11, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editMaterial, 2, 11, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(deleteMaterial, 3, 11, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l8, 0, 12, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(initialConditionsComboBox, 1, 12, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editInitCond, 2, 12, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(deleteInitCond, 3, 12, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l9, 0, 13, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(boundaryConditionsComboBox, 1, 13, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editBoundCond, 2, 13, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(deleteBoundCond, 3, 13, 1, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l10, 0, 14, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(tasksComboBox, 1, 14, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(editTask, 2, 14, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(deleteTask, 3, 14, 1, 1, HPos.LEFT, VPos.CENTER);

        centralLayout.getChildren().addAll(
            l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, s1, s2, s3,
            specimenNameTextField, cellNumberXTextField, cellNumberYTextField, cellNumberZTextField,
            numberOfGrainsTextField, angleRangeTextField, materialsComboBox,
            initialConditionsComboBox, boundaryConditionsComboBox, tasksComboBox,
            editMaterial, editInitCond, editBoundCond, editTask, cellSizeTextField, l11,
            deleteMaterial, deleteInitCond, deleteBoundCond, deleteTask,
            stochasticRadioButton, fixedRadioButton
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

    public void handleOKButton(TableView<SpecimenData> tableView, boolean editing, SpecimenData oldSpecimenData){
        okButton.setOnAction(e -> {
            try{
                String specimenName = specimenNameTextField.getText();
                int cellNumberX = Integer.parseInt(cellNumberXTextField.getText());
                int cellNumberY = Integer.parseInt(cellNumberYTextField.getText());
                int cellNumberZ = Integer.parseInt(cellNumberZTextField.getText());
                double cellSize = Double.parseDouble(cellSizeTextField.getText());
                int numberOfGrains = Integer.parseInt(numberOfGrainsTextField.getText());
                double angleRange = Double.parseDouble(angleRangeTextField.getText());

                byte typeOfGrainStructure = 0;
                if (this.stochasticRadioButton.isSelected())
                    typeOfGrainStructure = 1;

                String material = materialsComboBox.getSelectionModel().getSelectedItem().toString();
                String initialCondition = initialConditionsComboBox.getSelectionModel().getSelectedItem().toString();
                String boundaryCondition = boundaryConditionsComboBox.getSelectionModel().getSelectedItem().toString();
                String task = tasksComboBox.getSelectionModel().getSelectedItem().toString();

                SpecimenData specimenData = new SpecimenData(specimenName, cellNumberX, cellNumberY, cellNumberZ,
                        cellSize, numberOfGrains, angleRange, typeOfGrainStructure,
                        material, initialCondition,
                        boundaryCondition, task);

                if (editing){
                    tableView.getItems().remove(oldSpecimenData);
                    DataBaseUtils.specimenDataBase.updateSpecimen(specimenData, oldSpecimenData.getSpecimenName());
                    tableView.getItems().add(specimenData);
                }
                else {
                    DataBaseUtils.specimenDataBase.addNewSpecimen(specimenData);
                    tableView.getItems().add(specimenData);
                }
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
                materialDataEditor.handleOKButton(materialsComboBox, false, new MaterialData());
            }
            else{
                editMaterial.setDisable(false);
                deleteMaterial.setDisable(false);
            }
        });

        initialConditionsComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue.toString())){
                if(!specimenNameTextField.getText().isEmpty()){
                    String specimenName = specimenNameTextField.getText();
                    DataBaseUtils.initialConditionDataBase.setSpecimenName(specimenName);
                    DataBaseUtils.initialConditionDataBase.createInitialConditionsTable();

                    InitialConditionDataEditor initialConditionDataEditor = new InitialConditionDataEditor();
                    initialConditionDataEditor.handleOKButton(specimenName, initialConditionsComboBox, false, new InitialCondition());
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Input Specimen Name First").show();
                    System.out.println("Set Specimen Name First!!!");
                }
            }
            else {
                editInitCond.setDisable(false);
                deleteInitCond.setDisable(false);
            }
        });

        boundaryConditionsComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue)){
                if(!specimenNameTextField.getText().isEmpty()){
                    String specimenName = specimenNameTextField.getText();

                    DataBaseUtils.boundaryConditionDataBase.setSpecimenName(specimenName);
                    DataBaseUtils.boundaryConditionDataBase.createBoundaryConditionsTable();

                    BoundaryConditionDataEditor materialDataEditor = new BoundaryConditionDataEditor();
                    materialDataEditor.handleOKButton(specimenName, boundaryConditionsComboBox, false, null);
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Input Specimen Name First").show();
                    System.out.println("Set Specimen Name First!!!");
                }
            }
            else {
                editBoundCond.setDisable(false);
                deleteBoundCond.setDisable(false);
            }
        });

        tasksComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue)-> {
            if(UICommon.CREATE_NEW.equals(newValue)){
                if(!specimenNameTextField.getText().isEmpty()){
                    String specimenName = specimenNameTextField.getText();

                    DataBaseUtils.taskDataBase.setSpecimenName(specimenName);
                    DataBaseUtils.taskDataBase.createTasksTable();

                    TaskDataEditor taskDataEditor = new TaskDataEditor();
                    taskDataEditor.handleOKButton(specimenName, tasksComboBox, false, null);
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Input Specimen Name First").show();
                    System.out.println("Set Specimen Name First!");
                }
            }
            else {
                editTask.setDisable(false);
                deleteTask.setDisable(false);
            }
        });

        editMaterial.setOnAction(e -> {
            System.out.println("Action Event : Edit Material Button is pushed");
            String selectedMaterial = materialsComboBox.getSelectionModel().getSelectedItem();
            MaterialData materialData = DataBaseUtils.materialDataBase.getMaterial(selectedMaterial);
            MaterialDataEditor materialDataEditor = new MaterialDataEditor(materialData);
            materialDataEditor.handleOKButton(materialsComboBox, true, materialData);
        });

        deleteMaterial.setOnAction(e -> {
            System.out.println("Action Event : Delete Material Button is pushed");
            String selectedMaterial = materialsComboBox.getSelectionModel().getSelectedItem();
            if (UICommon.confirmation()){
                DataBaseUtils.materialDataBase.deleteMaterial(selectedMaterial);
                materialsComboBox.getItems().remove(selectedMaterial);
            }
        });

        editInitCond.setOnAction(e -> {
            System.out.println("Action Event : Edit Initial Condition Button is pushed");
            String initCondName = initialConditionsComboBox.getSelectionModel().getSelectedItem();
            String specimenName = specimenNameTextField.getText();
            InitialCondition initialCondition = DataBaseUtils.initialConditionDataBase.getInitialCondition(initCondName);
            InitialConditionDataEditor initialConditionDataEditor = new InitialConditionDataEditor(initialCondition);
            initialConditionDataEditor.handleOKButton(specimenName, initialConditionsComboBox, true, initialCondition);
        });

        deleteInitCond.setOnAction(e -> {
            System.out.println("Action Event : Delete Initial Condition Button is pushed");
            String initCondName = initialConditionsComboBox.getSelectionModel().getSelectedItem();
            if (UICommon.confirmation()){
                DataBaseUtils.initialConditionDataBase.deleteInitialCondition(initCondName);
                initialConditionsComboBox.getItems().remove(initCondName);
                initialConditionsComboBox.getSelectionModel().selectPrevious();
            }
        });

        editBoundCond.setOnAction(e -> {
            System.out.println("Action Event : Edit Boundary Condition Button is pushed");
            String boundCondName = boundaryConditionsComboBox.getSelectionModel().getSelectedItem();
            String specimenName = specimenNameTextField.getText();
            List<BoundaryCondition> boundaryCondition = DataBaseUtils.boundaryConditionDataBase.getBoundaryCondition(boundCondName);
            BoundaryConditionDataEditor boundaryConditionDataEditor = new BoundaryConditionDataEditor(boundaryCondition);
            boundaryConditionDataEditor.handleOKButton(specimenName, boundaryConditionsComboBox, true, boundCondName);
        });

        deleteBoundCond.setOnAction(e -> {
            System.out.println("Action Event : Delete Boundary Condition is pushed");
            String boundCondName = boundaryConditionsComboBox.getSelectionModel().getSelectedItem();
            if (UICommon.confirmation()){
                DataBaseUtils.boundaryConditionDataBase.deleteBoundaryCondition(boundCondName);
                boundaryConditionsComboBox.getItems().remove(boundCondName);
            }
        });

        editTask.setOnAction(e -> {
            System.out.println("Action Event : Edit Task Button is pushed");
            String taskName = tasksComboBox.getSelectionModel().getSelectedItem();
            String specimenName = specimenNameTextField.getText();
            Task task = DataBaseUtils.taskDataBase.getTask(taskName);
            TaskDataEditor taskDataEditor = new TaskDataEditor(task);
            taskDataEditor.handleOKButton(specimenName, tasksComboBox, true, taskName);
        });

        deleteTask.setOnAction(e -> {
            System.out.println("Action Event : Delete Task Button is pushed");
            String taskName = tasksComboBox.getSelectionModel().getSelectedItem();
            if (UICommon.confirmation()){
                DataBaseUtils.taskDataBase.deleteTask(taskName);
                tasksComboBox.getItems().remove(taskName);
            }
        });

        cancelButton.setOnAction(e -> {
            this.close();
        });
    }

}
