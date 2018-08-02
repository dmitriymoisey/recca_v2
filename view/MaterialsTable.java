package view;

import databases.DataBaseUtils;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.MaterialData;

import java.util.Optional;

public class MaterialsTable extends Stage {

    private BorderPane root;
    private Scene scene;

    private TableView<MaterialData> tableView;

    private ToolBar toolBar;
    private Button add, delete;

    public MaterialsTable(){
        root = new BorderPane();
        scene = new Scene(root);

        tableView = new TableView<>();

        TableColumn materialNameTableColumn = new TableColumn("Material Name");
        materialNameTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, String>("materialName")
        );
        materialNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        materialNameTableColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MaterialData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MaterialData, String> event) {
                        ((MaterialData) event.getTableView().getItems().get(event.getTablePosition().getRow()))
                                .setMaterialName(event.getNewValue());
                    }
                }
        );

        TableColumn heatConductivityTableColumn = new TableColumn("Heat Conductivity");
        heatConductivityTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("heatConductivity")
        );

        TableColumn densityTableColumn = new TableColumn("Density");
        densityTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("density")
        );

        TableColumn heatExpansionTableColumn = new TableColumn("Heat Expansion");
        heatExpansionTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("heatExpansion")
        );

        TableColumn heatCapacityTableColumn = new TableColumn("Heat Capacity");
        heatCapacityTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("heatCapacity")
        );

        TableColumn phononPortionTableColumn = new TableColumn("Phonon Portion");
        phononPortionTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("phononPortion")
        );

        TableColumn angleLimitTableColumn = new TableColumn("Angle Limit HAGB");
        angleLimitTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("angleLimitHAGB")
        );

        TableColumn energyTableColumn = new TableColumn("Energy HAGB");
        energyTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("energyHAGB")
        );

        TableColumn maxMobilityTableColumn = new TableColumn("Max. Mobility");
        maxMobilityTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("maxMobility")
        );

        TableColumn latticeParameterTableColumn = new TableColumn("Lattice Parameter");
        latticeParameterTableColumn.setCellValueFactory(
                new PropertyValueFactory<MaterialData, Double>("latticeParameter")
        );

        tableView.setEditable(true);
        tableView.getColumns().addAll(
            materialNameTableColumn, heatConductivityTableColumn, densityTableColumn,
            heatExpansionTableColumn, heatCapacityTableColumn, phononPortionTableColumn,
            angleLimitTableColumn, energyTableColumn,
            maxMobilityTableColumn, latticeParameterTableColumn
        );

//        tableView.setItems(data);
        root.setCenter(tableView);

        toolBar = new ToolBar();
        add = new Button("Add");
        add.setPadding(new Insets(15, 30, 15, 30));
        delete = new Button("Delete");
        delete.setPadding(new Insets(15, 30, 15, 30));
        HBox bottomLayout = new HBox();
        bottomLayout.setPadding(new Insets(5, 5,5,5));
        bottomLayout.setAlignment(Pos.CENTER);

        Separator s1 = new Separator();
        s1.setOrientation(Orientation.VERTICAL);
        s1.setPadding(new Insets(2, 10, 2, 10));

        bottomLayout.getChildren().addAll(
                add, s1, delete
        );
        toolBar.getItems().add(bottomLayout);
        toolBar.setOrientation(Orientation.HORIZONTAL);
        root.setBottom(toolBar);
        handleEvents();

        this.setScene(scene);
        this.setTitle("Material Table");
        this.show();
    }

    private void handleEvents(){
        add.setOnAction(e -> {
            MaterialDataEditor materialDataEditor = new MaterialDataEditor();
//            materialDataEditor.handleOKButton(data);
        });

        delete.setOnAction(e -> {
            System.out.println("Action Event : Delete ");
            if(!tableView.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> choice = alert.showAndWait();
                if(choice.get() == ButtonType.OK){
                    MaterialData materialData = tableView.getSelectionModel().getSelectedItem();
                    tableView.getItems().remove(materialData);
                }
            }
            else{
                new Alert(Alert.AlertType.INFORMATION, "Nothing is chosen").show();
            }
        });
    }
}
