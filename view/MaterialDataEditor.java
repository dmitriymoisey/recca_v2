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
            maxMobilityTextField, latticeParemeterTextField,
            youngModulusTextField, shearModulusTextField, molarMassTextField,
            elastModulusTextField,
            dislMaxMobilityTextField, mechMaxMobilityTextField,
            yieldStrainTextField, ultimateStrainTextField,
            energyCoeffTextField, thermalConductBoundTextField,
            actEnergyTextField, dislDistCoeffTextField,
            yieldStateCoeffTextField, ultimateStateCoeffTextField,
            partVolFractionTextField, partRadiusTextField,
            thresholdStressTextField, torsionEnergyCoeffTextField,
            torsionEnergyCoeffGB1TextField, torsionEnergyCoeffGB2TextField,
            lowTemperThrValueTextField, highTemperThrValueTextField,
            minTwinTemperatureTextField, twinningTemperatureTextField,
            latticeVectorALengthTextField, latticeVectorBLengthTextField,
            latticeVectorCLengthTextField, latticeAngle_VecA_VecBTextField,
            latticeAngle_VecB_VecCTextField, latticeAngle_VecC_VecATextField,
            latticeAnisCoeffTextField, maxProbRecrystTextField,
            maxPropTwinningTextField, minDislDensityTextField;

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
        this.setTitle("Material - Data Editor");
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
        youngModulusTextField = new TextField();
        youngModulusTextField.setText("0.0");
        shearModulusTextField = new TextField();
        shearModulusTextField.setText("0.0");
        molarMassTextField = new TextField();
        molarMassTextField.setText("0.0");
        elastModulusTextField = new TextField("0.0");
        dislMaxMobilityTextField = new TextField("0.0");
        mechMaxMobilityTextField = new TextField("0.0");
        yieldStrainTextField = new TextField("0.0");
        ultimateStrainTextField = new TextField("0.0");
        energyCoeffTextField = new TextField("0.0");
        thermalConductBoundTextField = new TextField("0.0");
        actEnergyTextField = new TextField("0.0");
        dislDistCoeffTextField = new TextField("0.0");
        yieldStateCoeffTextField = new TextField("0.0");
        ultimateStateCoeffTextField = new TextField("0.0");
        partVolFractionTextField = new TextField("0.0");
        partRadiusTextField = new TextField("0.0");
        thresholdStressTextField = new TextField("0.0");
        torsionEnergyCoeffTextField = new TextField("0.0");
        torsionEnergyCoeffGB1TextField = new TextField("0.0");
        torsionEnergyCoeffGB2TextField = new TextField("0.0");
        lowTemperThrValueTextField = new TextField("0.0");
        highTemperThrValueTextField = new TextField("0.0");
        minTwinTemperatureTextField = new TextField("0.0");
        twinningTemperatureTextField = new TextField("0.0");
        latticeVectorALengthTextField = new TextField("0.0");
        latticeVectorBLengthTextField = new TextField("0.0");
        latticeVectorCLengthTextField = new TextField("0.0");
        latticeAngle_VecA_VecBTextField = new TextField("0.0");
        latticeAngle_VecB_VecCTextField = new TextField("0.0");
        latticeAngle_VecC_VecATextField = new TextField("0.0");
        latticeAnisCoeffTextField = new TextField("0.0");
        maxProbRecrystTextField = new TextField("0.0");
        maxPropTwinningTextField = new TextField("0.0");
        minDislDensityTextField = new TextField("0.0");
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

        Label l1 = new Label(UICommon.MATERIAL_NAME);
        Label l2 = new Label(UICommon.DENSITY);
        Label l3 = new Label(UICommon.HEAT_EXPANSION);
        Label l4 = new Label(UICommon.PHONON_PORTION);
        Label l5 = new Label(UICommon.HEAT_CAPACITY);
        Label l6 = new Label(UICommon.HEAT_CONDUCTIVITY);
        Label l7 = new Label(UICommon.ANGLE_LIMIT_HAGB);
        Label l8 = new Label(UICommon.ENERGY_HAGB);
        Label l9 = new Label(UICommon.MAXIMAL_MOBILITY);
        Label l10 = new Label(UICommon.LATTICE_PARAMETER);
        Label l11 = new Label(UICommon.YOUNG_MODULUS);
        Label l12 = new Label(UICommon.SHEAR_MODULUS);
        Label l13 = new Label(UICommon.MOLAR_MASS);
        Label l14 = new Label(UICommon.ELAST_MODULUS);
        Label l15 = new Label(UICommon.DISL_MAX_MOBILITY);
        Label l16 = new Label(UICommon.MECH_MAX_MOBILITY);
        Label l17 = new Label(UICommon.YIELD_STRAIN);
        Label l18 = new Label(UICommon.ULTIMATE_STRAIN);
        Label l19 = new Label(UICommon.ENERGY_COEFF);
        Label l20 = new Label(UICommon.THERMAL_CONDUCT_BOUND);
        Label l21 = new Label(UICommon.ACT_ENERGY);
        Label l22 = new Label(UICommon.DISL_DIST_COEFF);
        Label l23 = new Label(UICommon.YIELD_STATE_COEFF);
        Label l24 = new Label(UICommon.ULTIMATE_STATE_COEFF);
        Label l25 = new Label(UICommon.PART_VOL_FRACTION);
        Label l26 = new Label(UICommon.PART_RADIUS);
        Label l27 = new Label(UICommon.THRESHOLD_STRESS);
        Label l28 = new Label(UICommon.TORSION_ENERGY_COEFF);
        Label l29 = new Label(UICommon.TORSION_ENERGY_COEFF_GB1);
        Label l30 = new Label(UICommon.TORSION_ENERGY_COEFF_GB2);
        Label l31 = new Label(UICommon.LOW_TEMPER_THR_VALUE);
        Label l32 = new Label(UICommon.HIGH_TEMPER_THR_VALUE);
        Label l33 = new Label(UICommon.MIN_TWIN_TEMPERATURE);
        Label l34 = new Label(UICommon.TWINNING_TEMPERATURE);
        Label l35 = new Label(UICommon.LATTICE_VECTOR_A_LENGTH);
        Label l36 = new Label(UICommon.LATTICE_VECTOR_B_LENGTH);
        Label l37 = new Label(UICommon.LATTICE_VECTOR_C_LENGTH);
        Label l38 = new Label(UICommon.LATTICE_ANGLE_VEC_A_VEC_B);
        Label l39 = new Label(UICommon.LATTICE_ANGLE_VEC_B_VEC_C);
        Label l40 = new Label(UICommon.LATTICE_ANGLE_VEC_C_VEC_A);
        Label l41 = new Label(UICommon.LATTICE_ANIS_COEFF);
        Label l42 = new Label(UICommon.MAX_PROB_RECRYST);
        Label l43 = new Label(UICommon.MAX_PROB_TWINNING);
        Label l44 = new Label(UICommon.MIN_DISL_DENSITY);

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
        GridPane.setConstraints(l11, 0, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(youngModulusTextField, 1, 11, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l12, 0, 12, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(shearModulusTextField, 1, 12, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l41, 0, 13, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeAnisCoeffTextField, 1, 13, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l44, 0, 14, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(minDislDensityTextField, 1, 14, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l13, 2, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(molarMassTextField, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l14, 2, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(elastModulusTextField, 3, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l15, 2, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(dislMaxMobilityTextField, 3, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l16, 2, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(mechMaxMobilityTextField, 3, 5, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l17, 2, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(yieldStrainTextField, 3, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l18, 2, 7, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(ultimateStrainTextField, 3, 7, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l19, 2, 8, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(energyCoeffTextField, 3, 8, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l20, 2, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(thermalConductBoundTextField, 3, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l21, 2, 10, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(actEnergyTextField, 3, 10, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l22, 2, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(dislDistCoeffTextField, 3, 11, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l23, 4, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(yieldStateCoeffTextField, 5, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l24, 4, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(ultimateStateCoeffTextField, 5, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l25, 4, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(partVolFractionTextField, 5, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l26, 4, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(partRadiusTextField, 5, 5, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l27, 4, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(thresholdStressTextField, 5, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l28, 4, 7, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(torsionEnergyCoeffTextField, 5, 7, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l29, 4, 8, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(torsionEnergyCoeffGB1TextField, 5, 8, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l30, 4, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(torsionEnergyCoeffGB2TextField, 5, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l31, 4, 10, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(lowTemperThrValueTextField, 5, 10, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l32, 4, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(highTemperThrValueTextField, 5, 11, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(l33, 6, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(minTwinTemperatureTextField, 7, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l34, 6, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(twinningTemperatureTextField, 7, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l35, 6, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeVectorALengthTextField, 7, 4, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l36, 6, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeVectorBLengthTextField, 7, 5, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l37, 6, 6, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeVectorCLengthTextField, 7, 6, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l38, 6, 7, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeAngle_VecA_VecBTextField, 7, 7, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l39, 6, 8, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeAngle_VecB_VecCTextField, 7, 8, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l40, 6, 9, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(latticeAngle_VecC_VecATextField, 7, 9, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l42, 6, 10, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(maxProbRecrystTextField, 7, 10, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(l43, 6, 11, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(maxPropTwinningTextField, 7, 11, 1, 1, HPos.CENTER, VPos.CENTER);

        centralLayout.getChildren().addAll(
                s1, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13,
                l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,
                l31,l32,l33,l34,l35,l36,l37,l38,l39,l40,l41,l42,l43,l44,
                materialNameTextField,
                densityTextField,
                heatExpansionTextField,
                phononPortionTextField,
                heatCapacityTextField,
                heatConductivityTextField,
                angleLimitHAGBTextField,
                energyHAGBTextField,
                maxMobilityTextField,
                latticeParemeterTextField,
                youngModulusTextField,
                shearModulusTextField,
                molarMassTextField,
                elastModulusTextField,
                dislMaxMobilityTextField, mechMaxMobilityTextField,
                yieldStrainTextField, ultimateStrainTextField,
                energyCoeffTextField, thermalConductBoundTextField,
                actEnergyTextField, dislDistCoeffTextField,
                yieldStateCoeffTextField, ultimateStateCoeffTextField,
                partVolFractionTextField, partRadiusTextField,
                thresholdStressTextField, torsionEnergyCoeffTextField,
                torsionEnergyCoeffGB1TextField, torsionEnergyCoeffGB2TextField,
                lowTemperThrValueTextField, highTemperThrValueTextField,
                minTwinTemperatureTextField, twinningTemperatureTextField,
                latticeVectorALengthTextField, latticeVectorBLengthTextField,
                latticeVectorCLengthTextField, latticeAngle_VecA_VecBTextField,
                latticeAngle_VecB_VecCTextField, latticeAngle_VecC_VecATextField,
                latticeAnisCoeffTextField, maxProbRecrystTextField,
                maxPropTwinningTextField, minDislDensityTextField
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
        youngModulusTextField.setText(String.valueOf(materialData.getYoungModulus()));
        shearModulusTextField.setText(String.valueOf(materialData.getShearModulus()));
        molarMassTextField.setText(String.valueOf(materialData.getMolarMass()));
        elastModulusTextField.setText(String.valueOf(materialData.getElastModulus()));
        dislMaxMobilityTextField.setText(String.valueOf(materialData.getDislMaxMobility()));
        mechMaxMobilityTextField.setText(String.valueOf(materialData.getMechMaxMobility()));
        yieldStrainTextField.setText(String.valueOf(materialData.getYieldStateCoeff()));
        ultimateStrainTextField.setText(String.valueOf(materialData.getUltimateStrain()));
        energyCoeffTextField.setText(String.valueOf(materialData.getEnergyCoeff()));
        thermalConductBoundTextField.setText(String.valueOf(materialData.getThermalConductBound()));
        actEnergyTextField.setText(String.valueOf(materialData.getActEnergy()));
        dislDistCoeffTextField.setText(String.valueOf(materialData.getDislDistCoeff()));
        yieldStateCoeffTextField.setText(String.valueOf(materialData.getYieldStateCoeff()));
        ultimateStateCoeffTextField.setText(String.valueOf(materialData.getUltimateStateCoeff()));
        partVolFractionTextField.setText(String.valueOf(materialData.getPartVolFraction()));
        partRadiusTextField.setText(String.valueOf(materialData.getPartRadius()));
        thresholdStressTextField.setText(String.valueOf(materialData.getThresholdStress()));
        torsionEnergyCoeffTextField.setText(String.valueOf(materialData.getTorsionEnergyCoeff()));
        torsionEnergyCoeffGB1TextField.setText(String.valueOf(materialData.getTorsionEnergyCoeffGB1()));
        torsionEnergyCoeffGB2TextField.setText(String.valueOf(materialData.getTorsionEnergyCoeffGB2()));
        lowTemperThrValueTextField.setText(String.valueOf(materialData.getLowTemperThrValue()));
        highTemperThrValueTextField.setText(String.valueOf(materialData.getHighTemperThrValue()));
        minTwinTemperatureTextField.setText(String.valueOf(materialData.getMinTwinTemperature()));
        twinningTemperatureTextField.setText(String.valueOf(materialData.getTwinningTemperature()));
        latticeVectorALengthTextField.setText(String.valueOf(materialData.getLatticeVectorALength()));
        latticeVectorBLengthTextField.setText(String.valueOf(materialData.getLatticeVectorBLength()));
        latticeVectorCLengthTextField.setText(String.valueOf(materialData.getLatticeVectorCLength()));
        latticeAngle_VecA_VecBTextField.setText(String.valueOf(materialData.getLatticeAngle_VecA_VecB()));
        latticeAngle_VecB_VecCTextField.setText(String.valueOf(materialData.getLatticeAngle_VecB_VecC()));
        latticeAngle_VecC_VecATextField.setText(String.valueOf(materialData.getLatticeAngle_VecC_VecA()));
        latticeAnisCoeffTextField.setText(String.valueOf(materialData.getLatticeAnisCoeff()));
        maxProbRecrystTextField.setText(String.valueOf(materialData.getMaxProbRecryst()));
        maxPropTwinningTextField.setText(String.valueOf(materialData.getMaxPropTwinning()));
        minDislDensityTextField.setText(String.valueOf(materialData.getMinDislDensity()));
    }

    public void handleOKButton(ComboBox comboBox, boolean editing, MaterialData oldMaterialData){
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
                double youngModulus = Double.parseDouble(youngModulusTextField.getText());
                double shearModulus = Double.parseDouble(shearModulusTextField.getText());
                double molarMass = Double.parseDouble(molarMassTextField.getText());
                double elastModulus = Double.parseDouble(elastModulusTextField.getText());
                double dislMaxMobility = Double.parseDouble(dislMaxMobilityTextField.getText());
                double mechMaxMobility = Double.parseDouble(mechMaxMobilityTextField.getText());
                double yieldStrain = Double.parseDouble(yieldStrainTextField.getText());
                double ultimateStrain = Double.parseDouble(ultimateStrainTextField.getText());
                double energyCoeff = Double.parseDouble(energyCoeffTextField.getText());
                double thermalConductBound = Double.parseDouble(thermalConductBoundTextField.getText());
                double actEnergy = Double.parseDouble(actEnergyTextField.getText());
                double dislDistCoeff = Double.parseDouble(dislDistCoeffTextField.getText());
                double yieldStateCoeff = Double.parseDouble(yieldStateCoeffTextField.getText());
                double ultimateStateCoeff = Double.parseDouble(ultimateStateCoeffTextField.getText());
                double partVolFraction = Double.parseDouble(partVolFractionTextField.getText());
                double partRadius = Double.parseDouble(partRadiusTextField.getText());
                double thresholdStress = Double.parseDouble(thresholdStressTextField.getText());
                double torsionEnergyCoeff = Double.parseDouble(torsionEnergyCoeffTextField.getText());
                double torsionEnergyCoeffGB1 = Double.parseDouble(torsionEnergyCoeffGB1TextField.getText());
                double torsionEnergyCoeffGB2 = Double.parseDouble(torsionEnergyCoeffGB2TextField.getText());
                double lowTemperThrValue = Double.parseDouble(lowTemperThrValueTextField.getText());
                double highTemperThrValue = Double.parseDouble(highTemperThrValueTextField.getText());
                double minTwinTemperature = Double.parseDouble(minTwinTemperatureTextField.getText());
                double twinningTemperature = Double.parseDouble(twinningTemperatureTextField.getText());
                double latticeVectorALength = Double.parseDouble(latticeVectorALengthTextField.getText());
                double latticeVectorBLength = Double.parseDouble(latticeVectorBLengthTextField.getText());
                double latticeVectorCLength = Double.parseDouble(latticeVectorCLengthTextField.getText());
                double latticeAngle_VecA_VecB = Double.parseDouble(latticeAngle_VecA_VecBTextField.getText());
                double latticeAngle_VecB_VecC = Double.parseDouble(latticeAngle_VecB_VecCTextField.getText());
                double latticeAngle_VecC_VecA = Double.parseDouble(latticeAngle_VecC_VecATextField.getText());
                double latticeAnisCoeff = Double.parseDouble(latticeAnisCoeffTextField.getText());
                double maxProbRecryst = Double.parseDouble(maxProbRecrystTextField.getText());
                double maxPropTwinning = Double.parseDouble(maxPropTwinningTextField.getText());
                double minDislDensity = Double.parseDouble(minDislDensityTextField.getText());


                MaterialData materialData = new MaterialData(materialName, heatConductivity,
                        density, heatExpansion, heatCapacity, phononPortion, angleLimitHAGB,
                        energyHAGB, maxMobility, latticeParameter, youngModulus, shearModulus, molarMass,
                        elastModulus,
                        dislMaxMobility, mechMaxMobility,
                        yieldStrain, ultimateStrain,
                        energyCoeff, thermalConductBound,
                        actEnergy, dislDistCoeff,
                        yieldStateCoeff, ultimateStateCoeff,
                        partVolFraction, partRadius,
                        thresholdStress, torsionEnergyCoeff,
                        torsionEnergyCoeffGB1, torsionEnergyCoeffGB2,
                        lowTemperThrValue, highTemperThrValue,
                        minTwinTemperature, twinningTemperature,
                        latticeVectorALength, latticeVectorBLength,
                        latticeVectorCLength, latticeAngle_VecA_VecB,
                        latticeAngle_VecB_VecC, latticeAngle_VecC_VecA,
                        latticeAnisCoeff, maxProbRecryst,
                        maxPropTwinning, minDislDensity);

                if (editing){
                    comboBox.getItems().remove(oldMaterialData.getMaterialName());
                    DataBaseUtils.materialDataBase.updateMaterial(materialData, oldMaterialData.getMaterialName());
                    comboBox.getItems().add(materialData.getMaterialName());
                    comboBox.getSelectionModel().select(materialData.getMaterialName());
                    this.close();
                }
                else{
                    if (DataBaseUtils.materialDataBase.checkName(materialName)){
                        DataBaseUtils.materialDataBase.addNewMaterial(materialData);
                        comboBox.getItems().add(materialName);
                        comboBox.getSelectionModel().select(materialName);
                        this.close();
                    }
                    else {
                        new Alert(Alert.AlertType.ERROR, UICommon.INPUT_ANOTHER_NAME).show();
                    }
                }
            }
            catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                new Alert(Alert.AlertType.ERROR, "Incorrect data input").show();
            }
        });

    }

}
