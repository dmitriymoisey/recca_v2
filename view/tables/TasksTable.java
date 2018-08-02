package view.tables;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import utils.Task;

public class TasksTable extends BorderPane {

    private TableView<Task> tableView;
    private ObservableList<Task> data;

    private TableColumn nameTableColumn, timeStepTableColumn, totalTimeTableColumn;

    public TasksTable(){
        initAllComponents();
        addAllComponents();
        handleEvents();
    }

    private void initAllComponents(){

        tableView = new TableView<>();

        nameTableColumn = new TableColumn("Name");
        nameTableColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("name")
        );

        timeStepTableColumn = new TableColumn("Time Step");
        timeStepTableColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("timeStep")
        );

        totalTimeTableColumn = new TableColumn("Total Time");
        totalTimeTableColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("totalTime")
        );

    }
    private void addAllComponents(){

        tableView.getColumns().addAll(
            nameTableColumn, timeStepTableColumn, totalTimeTableColumn
        );

        this.setCenter(tableView);
    }

    private void handleEvents(){}

}
