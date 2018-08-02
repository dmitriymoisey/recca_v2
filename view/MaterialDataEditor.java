package view;

import databases.DataBaseUtils;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.MaterialData;

public class MaterialDataEditor extends Stage {

    private BorderPane root;
    private Scene scene;

    private TextField materialNameTextField, densityTextField,
            heatExpansionTextField, phononPortionTextField,
            heatCapacityTextField, heatConductivityTextField,
            angleLimitHAGBTextField, energyHAGBTextField,
            maxMobilityTextField, latticeParemeterTextField;

    private Button okButton, cancelButton;

    public MaterialDataEditor(){
        root = new BorderPane();
        scene = new Scene(root);
        initAllComponents();
        addAllComponents();
        this.setTitle("DEMO");
        this.setScene(scene);
        this.show();
    }

    public MaterialDataEditor(MaterialData materialData){
        root = new BorderPane();
        scene = new Scene(root);
        initAllComponents();
        addAllComponents();
        setMaterialData(materialData);
        this.setTitle("DEMO");
        this.setScene(scene);
        this.show();
    }

    private void initAllComponents(){
        materialNameTextField = new TextField();
        materialNameTextField.setPromptText("Type material name");

        densityTextField = new TextField();
        densityTextField.setText("0.0");

        heatExpansionTextField = new TextField();
        heatExpansionTextField.setText("0.0");
        phononPortionTextField = new TextField();
        phononPortionTextField.setText("0.0");
        heatCapacityTextField = new TextField();
        heatCapacityTextField.setText("0.0");
        heatConductivityTextField = new TextField();
        heatConductivityTextField.setText("0.0");
        angleLimitHAGBTextField = new TextField();
        angleLimitHAGBTextField.setText("0.0");
        energyHAGBTextField = new TextField();
        energyHAGBTextField.setText("0.0");
        maxMobilityTextField = new TextField();
        maxMobilityTextField.setText("0.0");
        latticeParemeterTextField = new TextField();
        latticeParemeterTextField.setText("0.0");

        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            this.close();
        });
    }

    private void addAllComponents(){
        GridPane centralLayout = new GridPane();
        centralLayout.setPadding(new Insets(10, 10, 10, 10));
        centralLayout.setHgap(10.0);
        centralLayout.setVgap(10.0);
        centralLayout.setAlignment(Pos.CENTER);

        Label l1 = new Label("Material Name");
        Label l2 = new Label("Density");
        Label l3 = new Label("Heat Expansion");
        Label l4 = new Label("Phonon Portion");
        Label l5 = new Label("Heat Capacity");
        Label l6 = new Label("Heat Conductivity");
        Label l7 = new Label("Angle Limit HAGB");
        Label l8 = new Label("Energy HAGB");
        Label l9 = new Label("Maximal Mobility");
        Label l10 = new Label("Lattice Parameter");

        Separator s1 = new Separator();

        GridPane.setConstraints(l1, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(materialNameTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s1, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l2, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(densityTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l3, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(heatExpansionTextField, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l4, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(phononPortionTextField, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l5, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(heatCapacityTextField, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l6, 0, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(heatConductivityTextField, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l7, 0, 7, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(angleLimitHAGBTextField, 1, 7, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l8, 0, 8, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(energyHAGBTextField, 1, 8, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l9, 0, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(maxMobilityTextField, 1, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l10, 0, 10, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeParemeterTextField, 1, 10, 1, 1, HPos.CENTER, VPos.CENTER);

        centralLayout.getChildren().addAll(
                s1, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10,
                materialNameTextField,
                densityTextField,
                heatExpansionTextField,
                phononPortionTextField,
                heatCapacityTextField,
                heatConductivityTextField,
                angleLimitHAGBTextField,
                energyHAGBTextField,
                maxMobilityTextField,
                latticeParemeterTextField
        );

        HBox bottomLayout = new HBox();
        bottomLayout.setPadding(new Insets(10,10,10,10));
        bottomLayout.setAlignment(Pos.CENTER);
        okButton.setPadding(new Insets(10, 30, 10, 30));
        cancelButton.setPadding(new Insets(10, 30, 10, 30));

        Separator s2 = new Separator(Orientation.VERTICAL);
        s2.setPadding(new Insets(1, 10, 1, 10));

        bottomLayout.getChildren().addAll(okButton, s2, cancelButton);

        root.setCenter(centralLayout);
        root.setBottom(bottomLayout);
    }

    public void setMaterialData(MaterialData materialData){
        materialNameTextField.setText(materialData.getMaterialName());
        densityTextField.setText(String.valueOf(materialData.getDensity()));
        heatExpansionTextField.setText(String.valueOf(materialData.getHeatExpansion()));
        phononPortionTextField.setText(String.valueOf(materialData.getPhononPortion()));
        heatCapacityTextField.setText(String.valueOf(materialData.getHeatCapacity()));
        heatConductivityTextField.setText(String.valueOf(materialData.getHeatConductivity()));
        angleLimitHAGBTextField.setText(String.valueOf(materialData.getAngleLimitHAGB()));
        energyHAGBTextField.setText(String.valueOf(materialData.getEnergyHAGB()));
        maxMobilityTextField.setText(String.valueOf(materialData.getMaxMobility()));
        latticeParemeterTextField.setText(String.valueOf(materialData.getLatticeParameter()));
    }

    public void handleOKButton(ComboBox comboBox){
        okButton.setOnAction(e -> {
            try{
                String materialName = materialNameTextField.getText();
                double density = Double.parseDouble(densityTextField.getText());
                double heatExpansion = Double.parseDouble(heatExpansionTextField.getText());
                double phononPortion = Double.parseDouble(phononPortionTextField.getText());
                double heatCapacity = Double.parseDouble(heatCapacityTextField.getText());
                double heatConductivity = Double.parseDouble(heatConductivityTextField.getText());
                double angleLimitHAGB = Double.parseDouble(angleLimitHAGBTextField.getText());
                double energyHAGB = Double.parseDouble(energyHAGBTextField.getText());
                double maxMobility = Double.parseDouble(maxMobilityTextField.getText());
                double latticeParameter = Double.parseDouble(latticeParemeterTextField.getText());

                MaterialData materialData = new MaterialData(materialName, heatConductivity,
                        density, heatExpansion, heatCapacity, phononPortion, angleLimitHAGB,
                        energyHAGB, maxMobility, latticeParameter);

                DataBaseUtils.addMaterial(materialData);
                comboBox.getItems().add(materialName);
                comboBox.getSelectionModel().select(materialName);
                this.close();
            }
            catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                new Alert(Alert.AlertType.ERROR, "Incorrect data input").show();
            }
        });

    }

}
