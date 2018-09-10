package view;

import databases.DataBaseUtils;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Task;

public class TaskDataEditor extends Stage {

    private Scene scene;
    private BorderPane root;

    private TextField nameTextField, timeStepTextField, totalTimeTextField;
    private CheckBox recrystallizationCheckBox;

    private Button okButton, cancelButton;

    public TaskDataEditor(){
        initAllComponents();
        addAllComponents();
        this.setTitle("Task - Editor");
        this.setScene(scene);
        this.show();
    }

    public TaskDataEditor(Task task){
        initAllComponents();
        addAllComponents();

        nameTextField.setText(task.getName());
        timeStepTextField.setText(String.valueOf(task.getTimeStep()));
        totalTimeTextField.setText(String.valueOf(task.getTotalTime()));

        this.setTitle("Task - Editor");
        this.setScene(scene);
        this.show();
    }

    private void initAllComponents(){
        root = new BorderPane();
        scene = new Scene(root);

        nameTextField = new TextField();
        timeStepTextField = new TextField();
        totalTimeTextField = new TextField();

        recrystallizationCheckBox = new CheckBox(UICommon.RECRYSTALLIZATION);

        okButton = new Button(UICommon.OK);
        okButton.setPadding(new Insets(10, 20, 10, 20));
        cancelButton = new Button(UICommon.CANCEL);
        cancelButton.setPadding(new Insets(10, 20, 10, 20));

        cancelButton.setOnAction(e -> this.close());
    }

    private void addAllComponents(){

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);

        Label l1 = new Label(UICommon.NAME);
        Label l2 = new Label(UICommon.TIME_STEP);
        Label l3 = new Label(UICommon.TOTAL_TIME);

        GridPane.setConstraints(l1, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(nameTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(timeStepTextField, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(totalTimeTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(recrystallizationCheckBox, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
                l1, l2, l3, nameTextField,
                timeStepTextField, totalTimeTextField,
                recrystallizationCheckBox
        );

        root.setCenter(gridPane);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setAlignment(Pos.CENTER);

        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPadding(new Insets(2, 10, 2, 10));

        hBox.getChildren().addAll(okButton, separator, cancelButton);

        root.setBottom(hBox);
    }

    public void handleOKButton(String specimenName, ComboBox comboBox, boolean editing, String oldTaskName){
        okButton.setOnAction(e -> {
            System.out.println("Action Event: ok button is pushed");
            try{
                String name = nameTextField.getText();
                double timeStep = Double.parseDouble(timeStepTextField.getText());
                double totalTime = Double.parseDouble(totalTimeTextField.getText());
                Task task = new Task(name, timeStep, totalTime);

                if (editing){
                    DataBaseUtils.taskDataBase.updateTask(task, oldTaskName);
                    comboBox.getItems().remove(oldTaskName);
                }
                else{
                    DataBaseUtils.taskDataBase.addTask(task);
                }

                comboBox.getItems().add(name);
                comboBox.getSelectionModel().select(name);
                this.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                new Alert(Alert.AlertType.ERROR, UICommon.INCORRECT_DATA_INPUT).show();
            }
        });
    }

}
