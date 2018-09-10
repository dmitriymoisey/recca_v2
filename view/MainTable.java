package view;

import databases.DataBaseUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import utils.SpecimenData;

import java.util.Optional;

public class MainTable extends BorderPane{

    private TableView<SpecimenData> tableView;
    private ToolBar toolBar, topToolBar;
    private TextField searchField;
    private Button structure, delete, add, edit, showResults;

    public MainTable() {
        toolBar = new ToolBar();
        topToolBar = new ToolBar();

        searchField = new TextField();
        searchField.setPromptText("Search");

        tableView = new TableView<>();
        TableColumn specimenNameColumn = new TableColumn("Specimen Name");
        specimenNameColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, String>("specimenName")
        );

        TableColumn sizeColumn = new TableColumn("Size");

        TableColumn sizeXColumn = new TableColumn("X");
        sizeXColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, Integer>("cellNumberX")
        );


        TableColumn sizeYColumn = new TableColumn("Y");
        sizeYColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, Integer>("cellNumberY")
        );

        TableColumn sizeZColumn = new TableColumn("Z");
        sizeZColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, Integer>("cellNumberZ")
        );
        sizeColumn.getColumns().addAll(sizeXColumn, sizeYColumn, sizeZColumn);

        TableColumn numberOfGrainsColumn = new TableColumn("Number of Grains");
        numberOfGrainsColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, Integer>("numberOfGrains")
        );

        TableColumn angleRangeColumn = new TableColumn("Angle Range");
        angleRangeColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, Double>("angleRange")
        );

        TableColumn materialColumn = new TableColumn("Material");
        materialColumn.setContextMenu(getMaterialContextMenu());
        materialColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, String>("material")
        );

        TableColumn initialConditionsColumn = new TableColumn("Initial Condition");
        initialConditionsColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, String>("initialConditions")
        );

        TableColumn boundaryConditionColumn = new TableColumn("Boundary Condition");
        boundaryConditionColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, String>("boundaryConditions")
        );

        TableColumn taskColumn = new TableColumn("Task");
        taskColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, String>("task")
        );

        tableView.getColumns().addAll(
                specimenNameColumn, sizeColumn,
                numberOfGrainsColumn, angleRangeColumn, materialColumn,
                initialConditionsColumn, boundaryConditionColumn, taskColumn
        );

        tableView.setEditable(true);

        tableView.setItems(DataBaseUtils.specimenDataBase.getAllSpecimens());

        this.setCenter(tableView);

        structure = new Button(UICommon.STRUCTURE);
        structure.setPadding(new Insets(7,20,7,20));
        showResults = new Button(UICommon.SHOW_RESULTS);
        showResults.setPadding(new Insets(7,20,7,20));

        add = new Button(UICommon.ADD);
        add.setPadding(new Insets(7,20,7,20));
        edit = new Button(UICommon.EDIT);
        edit.setPadding(new Insets(7,20,7,20));
        delete = new Button(UICommon.DELETE);
        delete.setPadding(new Insets(7,20,7,20));

        Separator s1 = new Separator(Orientation.VERTICAL);
        s1.setPadding(new Insets(2, 20, 2, 20));
        Separator s2 = new Separator(Orientation.VERTICAL);
        s2.setPadding(new Insets(2, 7, 2, 7));
        Separator s3 = new Separator(Orientation.VERTICAL);
        s3.setPadding(new Insets(2, 7, 2, 7));
        Separator s4 = new Separator(Orientation.VERTICAL);
        s4.setPadding(new Insets(2, 7, 2, 7));

        HBox bottomLayout = new HBox();
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.getChildren().addAll(
                add, s2, edit, s3, delete, s1, structure, s4, showResults
                );

        topToolBar.getItems().add(searchField);

        toolBar = new ToolBar();
        toolBar.getItems().add(bottomLayout);
        this.setBottom(toolBar);
        this.setTop(topToolBar);

        handleEvents();
    }

    private void handleEvents(){

        structure.setOnAction(e -> {
            if(!tableView.getSelectionModel().isEmpty())
            {
                System.out.println("Action Event : Show Structure");
                SpecimenData specimenData = tableView.getSelectionModel().getSelectedItem();

                if(DataBaseUtils.checkStructure(specimenData.getSpecimenName())){
                    SpecimenStructure specimenStructure = new SpecimenStructure();
                    specimenStructure.showStructure(specimenData);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Structure not created, yet!\nWould you like to create it?");
                    Optional<ButtonType> choice = alert.showAndWait();
                    if (choice.get() == ButtonType.OK){
                        System.out.println("Creation of grain structure is started...");
                        alert.close();
                    }
                }
            }
            else
            {
                new Alert(Alert.AlertType.ERROR, UICommon.NOTHING_IS_CHOSEN).show();
            }

        });

        delete.setOnAction(e -> {
            System.out.println("Action Event : Delete ");
            if(!tableView.getSelectionModel().isEmpty()){
                if (UICommon.confirmation()){
                    SpecimenData specimen = tableView.getSelectionModel().getSelectedItem();
                    DataBaseUtils.specimenDataBase.deleteSpecimen(specimen.getSpecimenName());
                    tableView.getItems().remove(specimen);
                }
            }
            else{
                new Alert(Alert.AlertType.ERROR, UICommon.NOTHING_IS_CHOSEN).show();
            }
        });

        add.setOnAction(e -> {
            SpecimenDataEditor specimenDataEditor = new SpecimenDataEditor();
            specimenDataEditor.handleOKButton(tableView, false, new SpecimenData());
            System.out.println("Action Event : Add button is pushed");
        });

        edit.setOnAction(e -> {
            System.out.println("Action Event: Edit Button is pushed");
            if(tableView.getSelectionModel().isEmpty()){
                new Alert(Alert.AlertType.ERROR, UICommon.NOTHING_IS_CHOSEN).show();
            }
            else{
                SpecimenDataEditor specimenDataEditor = new SpecimenDataEditor();
                SpecimenData specimenData = tableView.getSelectionModel().getSelectedItem();
                specimenDataEditor.setSpecimenData(specimenData);
                specimenDataEditor.handleOKButton(tableView, true, specimenData);
            }
        });

        showResults.setOnAction(e -> {
            System.out.println("Action Event: show results button is pushed");
            if(tableView.getSelectionModel().isEmpty()){
                new Alert(Alert.AlertType.ERROR, UICommon.NOTHING_IS_CHOSEN).show();
            }
            else{
                SpecimenData specimenData = tableView.getSelectionModel().getSelectedItem();
                if (DataBaseUtils.checkResults(specimenData)){
                    ResultsViewer resultsViewer = new ResultsViewer(specimenData);
                }
                else {
                    new Alert(Alert.AlertType.ERROR, UICommon.NO_RESULTS).showAndWait();
                }

            }
        });

        searchField.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                String search = searchField.getText();
                for (SpecimenData specimenData : tableView.getItems()){
                    if (specimenData.getSpecimenName().contains(search)){
                        tableView.getSelectionModel().select(specimenData);
                        break;
                    }
                }
            }
        });

    }

    private ContextMenu getMaterialContextMenu(){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem show = new MenuItem("Show");
        MenuItem edit = new MenuItem("Edit");
        contextMenu.getItems().addAll(show, edit);

        show.setOnAction(e -> {
            String selectedMaterial =  tableView.getSelectionModel().getSelectedItem().getMaterial();
            MaterialDataEditor materialDataEditor = new MaterialDataEditor();
        });

        edit.setOnAction(e -> {
            String selectedMaterial =  tableView.getSelectionModel().getSelectedItem().getMaterial();
            MaterialDataEditor materialDataEditor = new MaterialDataEditor();
        });
        return contextMenu;
    }

}
