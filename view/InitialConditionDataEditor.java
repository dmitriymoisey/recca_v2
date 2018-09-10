package view;

import databases.DataBaseUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.InitialCondition;

public class InitialConditionDataEditor extends Stage {

    private Scene scene;
    private BorderPane root;

    private TextField nameTextField, temperatureTextField, elasticEnergyTextField,
            dislocationDensityTextField, xMomentTextField, yMomentTextField, zMomentTextField;

    private ComboBox temperatureComboBox, elasticEnergyComboBox, dislocationDensityComboBox,
            xMomentComboBox, yMomentComboBox, zMomentComboBox;

    private ObservableList<String> conditionType = FXCollections.observableArrayList("Homogeneus");

    private Button okButton, cancelButton;

    public InitialConditionDataEditor(){
        initAllComponents();
        addAllComponents();
        this.show();
    }

    public InitialConditionDataEditor(InitialCondition initialCondition){

        initAllComponents();
        addAllComponents();

        nameTextField.setText(initialCondition.getName());
        temperatureTextField.setText(String.valueOf(initialCondition.getTemperature()));
        elasticEnergyTextField.setText(String.valueOf(initialCondition.getElasticEnergy()));
        dislocationDensityTextField.setText(String.valueOf(initialCondition.getDislocationDensity()));
        xMomentTextField.setText(String.valueOf(initialCondition.getMomentX()));
        yMomentTextField.setText(String.valueOf(initialCondition.getMomentY()));
        zMomentTextField.setText(String.valueOf(initialCondition.getMomentZ()));

        this.show();
    }

    private void initAllComponents(){
        root = new BorderPane();
        scene = new Scene(root);

        nameTextField = new TextField();
        nameTextField.setPromptText("Type condition name");

        temperatureTextField = new TextField();
        temperatureTextField.setText("0.0");
        elasticEnergyTextField = new TextField();
        elasticEnergyTextField.setText("0.0");
        dislocationDensityTextField = new TextField();
        dislocationDensityTextField.setText("0.0");
        xMomentTextField = new TextField();
        xMomentTextField.setText("0.0");
        yMomentTextField = new TextField();
        yMomentTextField.setText("0.0");
        zMomentTextField = new TextField();
        zMomentTextField.setText("0.0");

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

        okButton = new Button("OK");
        okButton.setPadding(new Insets(10, 20, 10, 20));
        cancelButton = new Button("Cancel");
        cancelButton.setPadding(new Insets(10, 20, 10, 20));
        cancelButton.setOnAction(e -> {
            this.close();
        });
    }

    private void addAllComponents(){

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);

        Label l0 = new Label("Name");
        Label l1 = new Label("Temperature");
        Label l2 = new Label("Elastic Energy");
        Label l3 = new Label("Dislocation Density");
        Label l4 = new Label("X Moments");
        Label l5 = new Label("Y Moments");
        Label l6 = new Label("Z Moments");

        GridPane.setConstraints(l0, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l1, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l4, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l5, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l6, 0, 6, 1, 1, HPos.RIGHT, VPos.CENTER);

        GridPane.setConstraints(temperatureComboBox, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(elasticEnergyComboBox, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(dislocationDensityComboBox, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(xMomentComboBox, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(yMomentComboBox, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(zMomentComboBox, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(nameTextField, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(temperatureTextField, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(elasticEnergyTextField, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(dislocationDensityTextField, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(xMomentTextField, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(yMomentTextField, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(zMomentTextField, 2, 6, 1, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
            l0, l1,l2,l3,l4,l5,l6, temperatureTextField, elasticEnergyTextField, nameTextField,
            dislocationDensityTextField, xMomentTextField, yMomentTextField, zMomentTextField,
            temperatureComboBox, elasticEnergyComboBox, dislocationDensityComboBox,
            xMomentComboBox, yMomentComboBox, zMomentComboBox
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
        this.setTitle("Initial Condition - Editor");
    }

    public void handleOKButton(String specimenName, ComboBox comboBox, boolean editing, InitialCondition oldInitialCondition){

        okButton.setOnAction(e -> {
            System.out.println("Action Event: ok button is pushed");

            try{
                String name = nameTextField.getText();
                double temperature = Double.parseDouble(temperatureTextField.getText());
                double elasticEnergy = Double.parseDouble(elasticEnergyTextField.getText());
                double dislocationDensity = Double.parseDouble(dislocationDensityTextField.getText());
                double momentX = Double.parseDouble(xMomentTextField.getText());
                double momentY = Double.parseDouble(yMomentTextField.getText());
                double momentZ = Double.parseDouble(zMomentTextField.getText());
                InitialCondition initialCondition = new InitialCondition(name, temperature,
                                                                         elasticEnergy, dislocationDensity,
                                                                         momentX, momentY, momentZ);

                if (editing){
                    DataBaseUtils.initialConditionDataBase.updateInitialCondition(initialCondition, oldInitialCondition.getName());
                    comboBox.getItems().remove(oldInitialCondition.getName());
                    comboBox.getItems().add(initialCondition.getName());
                    comboBox.getSelectionModel().select(initialCondition.getName());
                }
                else{
                    DataBaseUtils.initialConditionDataBase.addInitialCondition(initialCondition);
                    comboBox.getItems().add(name);
                    comboBox.getSelectionModel().select(name);
                }
                this.close();
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                new Alert(Alert.AlertType.ERROR, "Incorrect Data Input").show();
            }

        });
    }

}
