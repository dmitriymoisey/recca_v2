package view.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import utils.BoundaryCondition;
import utils.Task;

public class BoundaryConditionsTable extends BorderPane{

    private TableView<BoundaryCondition> tableView;

    private ObservableList<BoundaryCondition> data;
    private ObservableList<String> facets = FXCollections.observableArrayList(
            "top", "bottom",
            "left", "right",
            "front", "back"
    );

    private TableColumn nameTableColumn, elementIndexTableColumn, facetTableColumn,
            temperatureTableColumn, elasticEnergyTableColumn,
            momentXTableColumn, momentYTableColumn, momentZTableColumn;

    //SubColumns
    private TableColumn averageTemperatureTableColumn, temperatureDeviationTableColumn,
            temperatureTimeTableColumn;

    private TableColumn averageElasticEnergyTableColumn, elasticEnergyDeviationTableColumn,
            elasticEnergyTimeTableColumn;

    private TableColumn averageMomentXTableColumn, momentXDeviationTableColumn,
            momentXTimeTableColumn;

    private TableColumn averageMomentYTableColumn, momentYDeviationTableColumn,
            momentYTimeTableColumn;

    private TableColumn averageMomentZTableColumn, momentZDeviationTableColumn,
            momentZTimeTableColumn;

    public BoundaryConditionsTable(){
        initAllComponents();
        addAllComponents();
        handleEvents();
    }

    private void initAllComponents(){
        tableView = new TableView<>();

        nameTableColumn = new TableColumn("Name");
        elementIndexTableColumn = new TableColumn("Element Index");

        facetTableColumn = new TableColumn("Facet");
        facetTableColumn.setCellValueFactory(
                new PropertyValueFactory<BoundaryCondition, String>("facet")
        );
        facetTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn());


        temperatureTableColumn = new TableColumn("Temperature");
        averageTemperatureTableColumn = new TableColumn("Average");
        temperatureDeviationTableColumn = new TableColumn("Deviation");
        temperatureTimeTableColumn = new TableColumn("Time");

        elasticEnergyTableColumn = new TableColumn("Elastic Energy");
        averageElasticEnergyTableColumn = new TableColumn("Average");
        elasticEnergyDeviationTableColumn = new TableColumn("Deviation");
        elasticEnergyTimeTableColumn = new TableColumn("Time");

        momentXTableColumn = new TableColumn("Moment X");
        averageMomentXTableColumn = new TableColumn("Average");
        momentXDeviationTableColumn = new TableColumn("Deviation");
        momentXTimeTableColumn = new TableColumn("Time");


        momentYTableColumn = new TableColumn("Moment Y");
        averageMomentYTableColumn = new TableColumn("Average");
        momentYDeviationTableColumn = new TableColumn("Deviation");
        momentYTimeTableColumn = new TableColumn("Time");

        momentZTableColumn = new TableColumn("Moment Z");
        averageMomentZTableColumn = new TableColumn("Average");
        momentZDeviationTableColumn = new TableColumn("Deviation");
        momentZTimeTableColumn = new TableColumn("Time");
    }

    private void addAllComponents(){
        temperatureTableColumn.getColumns().addAll(
                averageTemperatureTableColumn,
                temperatureDeviationTableColumn,
                temperatureTimeTableColumn
        );

        elasticEnergyTableColumn.getColumns().addAll(
                averageElasticEnergyTableColumn,
                elasticEnergyDeviationTableColumn,
                elasticEnergyTimeTableColumn
        );

        momentXTableColumn.getColumns().addAll(
                averageMomentXTableColumn,
                momentXDeviationTableColumn,
                momentXTimeTableColumn
        );

        momentYTableColumn.getColumns().addAll(
                averageMomentYTableColumn,
                momentYDeviationTableColumn,
                momentYTimeTableColumn
        );

        momentZTableColumn.getColumns().addAll(
                averageMomentZTableColumn,
                momentZDeviationTableColumn,
                momentZTimeTableColumn
        );

        tableView.getColumns().addAll(
                nameTableColumn, elementIndexTableColumn, facetTableColumn,
                temperatureTableColumn, elasticEnergyTableColumn,
                momentXTableColumn, momentYTableColumn, momentZTableColumn
        );
    }
    private void handleEvents(){}
}
