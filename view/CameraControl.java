package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class CameraControl extends GridPane {

    private Slider rotateX, rotateY, rotateZ, zoom, translateX, translateY;

    private TextField rotateXTextField, rotateYTextField, rotateZTextField,
                    zoomTextField, translateXTextField, translateYTextField;

    private Label rotateXLabel, rotateYLabel, rotateZLabel, zoomLabel, translateXLabel, translateYLabel;

    public CameraControl(){
        initAllComponents();
        addAllComponents();
    }

    private void initAllComponents(){
        rotateX = new Slider(-180.0d, 180.0d, 0.0d);
        rotateX.setMaxWidth(250.0d);
        rotateY = new Slider(-180.0d, 180.0d, 0.0d);
        rotateY.setMaxWidth(250.0d);
        rotateZ = new Slider(-180.0d, 180.0d, 0.0d);
        rotateZ.setMaxWidth(250.0d);
        zoom = new Slider(-1000.0d, 0.0d, -500.d);
        zoom.setMaxWidth(250.0d);
        translateX = new Slider(-1000.0d, 1000.0d, 0.0d);
        translateX.setMaxWidth(250.0d);
        translateY = new Slider(-1000.0d, 1000.0d, 0.0d);
        translateY.setMaxWidth(250.0d);

        rotateXTextField = new TextField();
        rotateXTextField.setMaxWidth(50.0d);
        rotateYTextField = new TextField();
        rotateYTextField.setMaxWidth(50.0d);
        rotateZTextField = new TextField();
        rotateZTextField.setMaxWidth(50.0d);
        zoomTextField = new TextField();
        zoomTextField.setMaxWidth(50.0d);
        translateXTextField = new TextField();
        translateXTextField.setMaxWidth(50.0d);
        translateYTextField = new TextField();
        translateYTextField.setMaxWidth(50.0d);

        rotateXLabel = new Label();
        rotateYLabel = new Label();
        rotateZLabel = new Label();
        zoomLabel = new Label();
        translateXLabel = new Label();
        translateYLabel = new Label();

    }

    private void addAllComponents(){
        this.setHgap(3.0);
        this.setVgap(3.0);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10,10,10,10));

        Label xLabel = new Label("Rotate X");
        Label yLabel = new Label("Rotate Y");
        Label zLabel = new Label("Rotate Z");
        Label zoomLabel_ = new Label("Zoom");
        Label transXLabel = new Label("Translate X");
        Label transYLabel = new Label("Translate Y");

        Separator sep = new Separator();

        GridPane.setConstraints(xLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(rotateX, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(rotateXTextField, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(rotateXLabel, 1, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(yLabel, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(rotateY, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(rotateYTextField, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(rotateYLabel, 1, 3, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(zLabel, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(rotateZ, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(rotateZTextField, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(rotateZLabel, 1, 5, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(zoomLabel_, 0, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(zoom, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(zoomTextField, 2, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(zoomLabel, 1, 7, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(sep, 0, 8, 3, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(transXLabel, 0, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(translateX, 1, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(translateXTextField, 2, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(translateXLabel, 1, 10, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(transYLabel, 0, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(translateY, 1, 11, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(translateYTextField, 2, 11, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(translateYLabel, 1, 12, 2, 1, HPos.CENTER, VPos.CENTER);

        this.getChildren().addAll(
                xLabel, yLabel, zLabel, zoomLabel_, transXLabel, transYLabel,
                rotateX, rotateY, rotateZ, zoom, translateX, translateY,
                rotateXTextField, rotateYTextField, rotateZTextField,
                zoomTextField, translateXTextField, translateYTextField,
                rotateXLabel, rotateYLabel, rotateZLabel, zoomLabel,
                translateXLabel, translateYLabel, sep
        );
    }

    public void bindAllValues(Specimen3DView specimen3DView){
        rotateX.valueProperty().bindBidirectional(specimen3DView.cameraXform.rx.angleProperty());
        rotateY.valueProperty().bindBidirectional(specimen3DView.cameraXform.ry.angleProperty());
        rotateZ.valueProperty().bindBidirectional(specimen3DView.cameraXform.rz.angleProperty());

        translateX.valueProperty().bindBidirectional(specimen3DView.camera.translateXProperty());
        translateY.valueProperty().bindBidirectional(specimen3DView.camera.translateYProperty());
        zoom.valueProperty().bindBidirectional(specimen3DView.camera.translateZProperty());

        handleSliders(rotateX, rotateXLabel, rotateXTextField);
        handleSliders(rotateY, rotateYLabel, rotateYTextField);
        handleSliders(rotateZ, rotateZLabel, rotateZTextField);
        handleSliders(zoom, zoomLabel, zoomTextField);
        handleSliders(translateX, translateXLabel, translateXTextField);
        handleSliders(translateY, translateYLabel, translateYTextField);

        handleTextFieldInput(rotateXTextField, rotateX);
        handleTextFieldInput(rotateYTextField, rotateY);
        handleTextFieldInput(rotateZTextField, rotateZ);
        handleTextFieldInput(translateXTextField, translateX);
        handleTextFieldInput(translateYTextField, translateY);
        handleTextFieldInput(zoomTextField, zoom);
    }

    private void handleTextFieldInput(TextField textField, Slider slider){
        textField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                double newValue = 0.0;
                try{
                    newValue = Double.parseDouble(textField.getText());
                }
                catch (NumberFormatException ex){
                    System.out.println(ex.getMessage());
                    new Alert(Alert.AlertType.ERROR, "Incorrect data input").show();
                }
                slider.setValue(newValue);
            }
        });
    }

    private void handleSliders(Slider slider, Label label, TextField textField){
        slider.valueProperty().addListener((ov, oldValue, newValue) -> {
            label.setText(String.format("%.2f", newValue));
            textField.setText(String.format("%.2f", newValue));
        });
    }
}
