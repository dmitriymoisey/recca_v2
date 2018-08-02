package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialData {

    private SimpleStringProperty materialName;
    private SimpleDoubleProperty heatConductivity;
    private SimpleDoubleProperty density;
    private SimpleDoubleProperty heatExpansion;
    private SimpleDoubleProperty heatCapacity;
    private SimpleDoubleProperty phononPortion;
    private SimpleDoubleProperty angleLimitHAGB;
    private SimpleDoubleProperty energyHAGB;
    private SimpleDoubleProperty maxMobility;
    private SimpleDoubleProperty latticeParameter;

    public MaterialData(String materialName, double heatConductivity,
                         double density, double heatExpansion,
                         double heatCapacity, double phononPortion,
                         double angleLimitHAGB, double energyHAGB,
                         double maxMobility, double latticeParameter)
    {
        this.materialName = new SimpleStringProperty(materialName);
        this.heatConductivity = new SimpleDoubleProperty(heatConductivity);
        this.density = new SimpleDoubleProperty(density);
        this.heatExpansion = new SimpleDoubleProperty(heatExpansion);
        this.heatCapacity = new SimpleDoubleProperty(heatCapacity);
        this.phononPortion = new SimpleDoubleProperty(phononPortion);
        this.angleLimitHAGB = new SimpleDoubleProperty(angleLimitHAGB);
        this.energyHAGB = new SimpleDoubleProperty(energyHAGB);
        this.maxMobility = new SimpleDoubleProperty(maxMobility);
        this.latticeParameter = new SimpleDoubleProperty(latticeParameter);
    }

    public String getMaterialName() {
        return materialName.get();
    }

    public void setMaterialName(String materialName) {
        this.materialName.set(materialName);
    }

    public double getHeatConductivity() {
        return heatConductivity.get();
    }

    public void setHeatConductivity(double heatConductivity) {
        this.heatConductivity.set(heatConductivity);
    }

    public double getDensity() {
        return density.get();
    }
    public void setDensity(double density) {
        this.density.set(density);
    }

    public double getHeatCapacity() {
        return heatCapacity.get();
    }

    public void setHeatCapacity(double heatCapacity) {
        this.heatCapacity.set(heatCapacity);
    }

    public double getHeatExpansion() {
        return heatExpansion.get();
    }
    public void setHeatExpansion(double heatExpansion) {
        this.heatExpansion.set(heatExpansion);
    }

    public double getPhononPortion() {
        return phononPortion.get();
    }

    public void setPhononPortion(double phononPortion) {
        this.phononPortion.set(phononPortion);
    }

    public void setAngleLimitHAGB(double angleLimitHAGB) {
        this.angleLimitHAGB.set(angleLimitHAGB);
    }
    public double getAngleLimitHAGB() {
        return angleLimitHAGB.get();
    }

    public void setEnergyHAGB(double energyHAGB) {
        this.energyHAGB.set(energyHAGB);
    }
    public double getEnergyHAGB() {
        return energyHAGB.get();
    }

    public void setLatticeParameter(double latticeParameter){
        this.latticeParameter.set(latticeParameter);
    }

    public double getLatticeParameter() {
        return latticeParameter.get();
    }

    public void setMaxMobility(double maxMobility) {
        this.maxMobility.set(maxMobility);
    }

    public double getMaxMobility() {
        return maxMobility.get();
    }
}
