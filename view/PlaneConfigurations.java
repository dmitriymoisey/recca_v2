package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PlaneConfigurations extends GridPane {

    private String specimenName;

    private ComboBox<Character> axisComboBox;
    private ComboBox<String> planeTypeComboBox;
    private TextField layerNumberTextField;
    private Label maxLayerNumberLabel;

    public PlaneConfigurations(String specimenName){
        this.specimenName = specimenName;

        initAllComponents();
        addAllComponents();
        handleEvents();
    }

    private void initAllComponents(){
        axisComboBox = new ComboBox();
        axisComboBox.getItems().addAll('X', 'Y','Z');
        axisComboBox.getSelectionModel().selectFirst();

        planeTypeComboBox = new ComboBox();
        planeTypeComboBox.getItems().addAll(UICommon.SIMPLE_PLANE, UICommon.SPECIAL_PLANE);
        planeTypeComboBox.getSelectionModel().selectFirst();

        layerNumberTextField = new TextField();
        layerNumberTextField.setPromptText("Type layer number");
        layerNumberTextField.setMaxWidth(70);

        maxLayerNumberLabel = new Label();
        maxLayerNumberLabel.setText("(Max Layer Number : )");
    }

    private void addAllComponents(){
        this.setHgap(3.0);
        this.setVgap(3.0);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10,10,10,10));

        Label l1 = new Label(UICommon.SELECT_AXIS);
        Label l2 = new Label(UICommon.SELECT_PLANE_TYPE);
        Label l3 = new Label(UICommon.TYPE_LAYER_NUMBER);

        GridPane.setConstraints(l1, 0, 0, 1,1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(l2, 0, 1, 1,1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(axisComboBox, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(planeTypeComboBox, 1,1, 1, 1,HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(l3, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(layerNumberTextField, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        this.getChildren().addAll(
                l1, l2, l3, axisComboBox,
                planeTypeComboBox, layerNumberTextField
        );
    }

    private void handleEvents(){
        axisComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            switch (newValue){
                case 'X':
                    maxLayerNumberLabel.setText("(Max Layer Number : )");
                    break;
                case 'Y':
                    maxLayerNumberLabel.setText("(Max Layer Number : )");
                    break;
                case 'Z':
                    maxLayerNumberLabel.setText("(Max Layer Number : )");
                    break;
            }
        });

        planeTypeComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            if (UICommon.SPECIAL_PLANE.equals(newValue)){
                new Alert(Alert.AlertType.INFORMATION, "Not implemented yet!").show();
            }
        });
    }

}
