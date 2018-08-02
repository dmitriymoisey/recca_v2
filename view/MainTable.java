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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import utils.SpecimenData;

import java.util.Optional;

public class MainTable extends BorderPane{

    ObservableList<SpecimenData> data = FXCollections.observableArrayList();

    private TableView<SpecimenData> tableView;
    private ToolBar toolBar;
    private TextField searchField;
    private Button structure, delete, add, edit, showResults;

    public MainTable() {
        toolBar = new ToolBar();

        searchField = new TextField();
        searchField.setPromptText("Search");

        tableView = new TableView<>();

        TableColumn specimenNameColumn = new TableColumn("Specimen Name");
        specimenNameColumn.setCellValueFactory(
                new PropertyValueFactory<SpecimenData, String>("specimenName")
        );
        specimenNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        specimenNameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<SpecimenData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<SpecimenData, String> event) {
                        ((SpecimenData) event.getTableView().getItems()
                                .get(event.getTablePosition().getRow())
                        ).setSpecimenName(event.getNewValue());
                    }
                }
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
        data.addAll(DataBaseUtils.getAllSpecimens());
        tableView.setItems(data);

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

        toolBar = new ToolBar();
        toolBar.getItems().add(bottomLayout);
        this.setBottom(toolBar);

        handleEvents();
    }

    private void handleEvents(){

        structure.setOnAction(e -> {
            if(!tableView.getSelectionModel().isEmpty())
            {
                System.out.println("Action Event : Show Structure");
                String selectedSpecimenName = tableView.getSelectionModel().getSelectedItem().getSpecimenName();
                if(DataBaseUtils.checkStructure(selectedSpecimenName)){
                    SpecimenStructure specimenStructure = new SpecimenStructure();
                    specimenStructure.showStructure(selectedSpecimenName);
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
                new Alert(Alert.AlertType.ERROR, "Nothing is chosen").show();
            }

        });

        delete.setOnAction(e -> {
            System.out.println("Action Event : Delete ");
            if(!tableView.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> choice = alert.showAndWait();
                if(choice.get() == ButtonType.OK){
                    SpecimenData specimenData = tableView.getSelectionModel().getSelectedItem();
                    tableView.getItems().remove(specimenData);
                }
            }
            else{
                new Alert(Alert.AlertType.ERROR, "Nothing is chosen").show();
            }
        });

        add.setOnAction(e -> {
            SpecimenDataEditor specimenDataEditor = new SpecimenDataEditor();
            specimenDataEditor.handleOKButton(this.data);
            System.out.println("Action Event : Add button is pushed");
        });

        edit.setOnAction(e -> {
            System.out.println("Action Event: Edit Button is pushed");
            if(tableView.getSelectionModel().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Nothing is chosen").show();
            }
            else{
                SpecimenDataEditor specimenDataEditor = new SpecimenDataEditor();
                SpecimenData specimenData = tableView.getSelectionModel().getSelectedItem();
                specimenDataEditor.setSpecimenData(specimenData);
            }
        });

        showResults.setOnAction(e -> {
            System.out.println("Action Event: show results button is pushed");
            if(tableView.getSelectionModel().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Nothing is chosen").show();
            }
            else{
                String specimenName = tableView.getSelectionModel().getSelectedItem().getSpecimenName();
                String taskName = tableView.getSelectionModel().getSelectedItem().getTask();
                ResultsViewer resultsViewer = new ResultsViewer(specimenName, taskName);
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
