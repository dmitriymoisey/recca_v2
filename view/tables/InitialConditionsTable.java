package view.tables;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import utils.InitialCondition;

public class InitialConditionsTable extends BorderPane {

    private TableView<InitialCondition> tableView;
    private ObservableList<InitialCondition> data;

    private TableColumn nameTableColumn, elementIndexTableColumn,
            elasticEnergyTableColumn, temperatureTableColumn, dislocationDensityTableColumn,
            momentXTableColumn, momentYTableColumn, momentZTableColumn;

    public InitialConditionsTable(){
        initAllComponents();
        addAllComponents();
        handleEvents();
    }

    private void initAllComponents(){
        tableView = new TableView<>();

        nameTableColumn = new TableColumn("Name");
        elementIndexTableColumn = new TableColumn("Element Index");
        temperatureTableColumn = new TableColumn("Temperature");
        elasticEnergyTableColumn = new TableColumn("Elastic Energy");
        dislocationDensityTableColumn = new TableColumn("Dislocation Density");
        momentXTableColumn = new TableColumn("Moment X");
        momentYTableColumn = new TableColumn("Moment Y");
        momentZTableColumn = new TableColumn("Moment Z");
    }

    private void addAllComponents(){
        tableView.getColumns().addAll(
                nameTableColumn, elementIndexTableColumn,
                temperatureTableColumn, elasticEnergyTableColumn,
                dislocationDensityTableColumn, momentXTableColumn,
                momentYTableColumn, momentZTableColumn
        );
        this.setCenter(tableView);
    }

    private void handleEvents(){}

}
