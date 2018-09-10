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
    private SimpleDoubleProperty youngModulus;
    private SimpleDoubleProperty shearModulus;
    private SimpleDoubleProperty molarMass, elastModulus,
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
                                maxPropTwinning, minDislDensity;

    public MaterialData(String materialName, double heatConductivity,
                        double density, double heatExpansion,
                        double heatCapacity, double phononPortion,
                        double angleLimitHAGB, double energyHAGB,
                        double maxMobility, double latticeParameter,
                        double youndModulus, double shearModulus, double molarMass,
                        double elastModulus, double dislMaxMobility, double mechMaxMobility,
                        double yieldStrain, double ultimateStrain,
                        double energyCoeff, double thermalConductBound,
                        double actEnergy, double dislDistCoeff,
                        double yieldStateCoeff, double ultimateStateCoeff,
                        double partVolFraction, double partRadius,
                        double thresholdStress, double torsionEnergyCoeff,
                        double torsionEnergyCoeffGB1, double torsionEnergyCoeffGB2,
                        double lowTemperThrValue, double highTemperThrValue,
                        double minTwinTemperature, double twinningTemperature,
                        double latticeVectorALength, double latticeVectorBLength,
                        double latticeVectorCLength, double latticeAngle_VecA_VecB,
                        double latticeAngle_VecB_VecC, double latticeAngle_VecC_VecA,
                        double latticeAnisCoeff, double maxProbRecryst,
                        double maxPropTwinning, double minDislDensity)
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
        this.youngModulus = new SimpleDoubleProperty(youndModulus);
        this.shearModulus = new SimpleDoubleProperty(shearModulus);
        this.molarMass = new SimpleDoubleProperty(molarMass);
        this.elastModulus = new SimpleDoubleProperty(elastModulus);
        this.dislMaxMobility = new SimpleDoubleProperty(dislMaxMobility);
        this.mechMaxMobility = new SimpleDoubleProperty(mechMaxMobility);
        this.yieldStrain = new SimpleDoubleProperty(yieldStrain);
        this.ultimateStrain = new SimpleDoubleProperty(ultimateStrain);
        this.energyCoeff = new SimpleDoubleProperty(energyCoeff);
        this.thermalConductBound = new SimpleDoubleProperty(thermalConductBound);
        this.actEnergy = new SimpleDoubleProperty(actEnergy);
        this.dislDistCoeff = new SimpleDoubleProperty(dislDistCoeff);
        this.yieldStateCoeff = new SimpleDoubleProperty(yieldStateCoeff);
        this.ultimateStateCoeff = new SimpleDoubleProperty(ultimateStateCoeff);
        this.partVolFraction = new SimpleDoubleProperty(partVolFraction);
        this.partRadius = new SimpleDoubleProperty(partRadius);
        this.thresholdStress = new SimpleDoubleProperty(thresholdStress);
        this.torsionEnergyCoeff = new SimpleDoubleProperty(torsionEnergyCoeff);
        this.torsionEnergyCoeffGB1 = new SimpleDoubleProperty(torsionEnergyCoeffGB1);
        this.torsionEnergyCoeffGB2 = new SimpleDoubleProperty(torsionEnergyCoeffGB2);
        this.lowTemperThrValue = new SimpleDoubleProperty(lowTemperThrValue);
        this.highTemperThrValue = new SimpleDoubleProperty(highTemperThrValue);
        this.minTwinTemperature = new SimpleDoubleProperty(minTwinTemperature);
        this.twinningTemperature = new SimpleDoubleProperty(twinningTemperature);
        this.latticeVectorALength = new SimpleDoubleProperty(latticeVectorALength);
        this.latticeVectorBLength = new SimpleDoubleProperty(latticeVectorBLength);
        this.latticeVectorCLength = new SimpleDoubleProperty(latticeVectorCLength);
        this.latticeAngle_VecA_VecB = new SimpleDoubleProperty(latticeAngle_VecA_VecB);
        this.latticeAngle_VecB_VecC = new SimpleDoubleProperty(latticeAngle_VecB_VecC);
        this.latticeAngle_VecC_VecA = new SimpleDoubleProperty(latticeAngle_VecC_VecA);
        this.latticeAnisCoeff = new SimpleDoubleProperty(latticeAnisCoeff);
        this.maxProbRecryst = new SimpleDoubleProperty(maxProbRecryst);
        this.maxPropTwinning = new SimpleDoubleProperty(maxPropTwinning);
        this.minDislDensity = new SimpleDoubleProperty(minDislDensity);
    }

    public MaterialData() {
        this.materialName = new SimpleStringProperty(null);
        this.heatConductivity = new SimpleDoubleProperty(0.0);
        this.density = new SimpleDoubleProperty(0.0);
        this.heatExpansion = new SimpleDoubleProperty(0.0);
        this.heatCapacity = new SimpleDoubleProperty(0.0);
        this.phononPortion = new SimpleDoubleProperty(0.0);
        this.angleLimitHAGB = new SimpleDoubleProperty(0.0);
        this.energyHAGB = new SimpleDoubleProperty(0.0);
        this.maxMobility = new SimpleDoubleProperty(0.0);
        this.latticeParameter = new SimpleDoubleProperty(0.0);
        this.youngModulus = new SimpleDoubleProperty(0.0);
        this.shearModulus = new SimpleDoubleProperty(0.0);
        this.molarMass = new SimpleDoubleProperty(0.0);
        this.molarMass = new SimpleDoubleProperty(0.0);
        this.elastModulus = new SimpleDoubleProperty(0.0);
        this.dislMaxMobility = new SimpleDoubleProperty(0.0);
        this.mechMaxMobility = new SimpleDoubleProperty(0.0);
        this.yieldStrain = new SimpleDoubleProperty(0.0);
        this.ultimateStrain = new SimpleDoubleProperty(0.0);
        this.energyCoeff = new SimpleDoubleProperty(0.0);
        this.thermalConductBound = new SimpleDoubleProperty(0.0);
        this.actEnergy = new SimpleDoubleProperty(0.0);
        this.dislDistCoeff = new SimpleDoubleProperty(0.0);
        this.yieldStateCoeff = new SimpleDoubleProperty(0.0);
        this.ultimateStateCoeff = new SimpleDoubleProperty(0.0);
        this.partVolFraction = new SimpleDoubleProperty(0.0);
        this.partRadius = new SimpleDoubleProperty(0.0);
        this.thresholdStress = new SimpleDoubleProperty(0.0);
        this.torsionEnergyCoeff = new SimpleDoubleProperty(0.0);
        this.torsionEnergyCoeffGB1 = new SimpleDoubleProperty(0.0);
        this.torsionEnergyCoeffGB2 = new SimpleDoubleProperty(0.0);
        this.lowTemperThrValue = new SimpleDoubleProperty(0.0);
        this.highTemperThrValue = new SimpleDoubleProperty(0.0);
        this.minTwinTemperature = new SimpleDoubleProperty(0.0);
        this.twinningTemperature = new SimpleDoubleProperty(0.0);
        this.latticeVectorALength = new SimpleDoubleProperty(0.0);
        this.latticeVectorBLength = new SimpleDoubleProperty(0.0);
        this.latticeVectorCLength = new SimpleDoubleProperty(0.0);
        this.latticeAngle_VecA_VecB = new SimpleDoubleProperty(0.0);
        this.latticeAngle_VecB_VecC = new SimpleDoubleProperty(0.0);
        this.latticeAngle_VecC_VecA = new SimpleDoubleProperty(0.0);
        this.latticeAnisCoeff = new SimpleDoubleProperty(0.0);
        this.maxProbRecryst = new SimpleDoubleProperty(0.0);
        this.maxPropTwinning = new SimpleDoubleProperty(0.0);
        this.minDislDensity = new SimpleDoubleProperty(0.0);
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

    public double getYoungModulus() {
        return youngModulus.get();
    }

    public SimpleDoubleProperty youngModulusProperty() {
        return youngModulus;
    }

    public void setYoungModulus(double youngModulus) {
        this.youngModulus.set(youngModulus);
    }

    public double getShearModulus() {
        return shearModulus.get();
    }

    public SimpleDoubleProperty shearModulusProperty() {
        return shearModulus;
    }

    public void setShearModulus(double shearModulus) {
        this.shearModulus.set(shearModulus);
    }

    public double getMolarMass() {
        return molarMass.get();
    }

    public SimpleDoubleProperty molarMassProperty() {
        return molarMass;
    }

    public void setMolarMass(double molarMass) {
        this.molarMass.set(molarMass);
    }

    public double getElastModulus() {
        return elastModulus.get();
    }

    public SimpleDoubleProperty elastModulusProperty() {
        return elastModulus;
    }

    public void setElastModulus(double elastModulus) {
        this.elastModulus.set(elastModulus);
    }

    public double getDislMaxMobility() {
        return dislMaxMobility.get();
    }

    public SimpleDoubleProperty dislMaxMobilityProperty() {
        return dislMaxMobility;
    }

    public void setDislMaxMobility(double dislMaxMobility) {
        this.dislMaxMobility.set(dislMaxMobility);
    }

    public double getMechMaxMobility() {
        return mechMaxMobility.get();
    }

    public SimpleDoubleProperty mechMaxMobilityProperty() {
        return mechMaxMobility;
    }

    public void setMechMaxMobility(double mechMaxMobility) {
        this.mechMaxMobility.set(mechMaxMobility);
    }

    public double getYieldStrain() {
        return yieldStrain.get();
    }

    public SimpleDoubleProperty yieldStrainProperty() {
        return yieldStrain;
    }

    public void setYieldStrain(double yieldStrain) {
        this.yieldStrain.set(yieldStrain);
    }

    public double getUltimateStrain() {
        return ultimateStrain.get();
    }

    public SimpleDoubleProperty ultimateStrainProperty() {
        return ultimateStrain;
    }

    public void setUltimateStrain(double ultimateStrain) {
        this.ultimateStrain.set(ultimateStrain);
    }

    public double getEnergyCoeff() {
        return energyCoeff.get();
    }

    public SimpleDoubleProperty energyCoeffProperty() {
        return energyCoeff;
    }

    public void setEnergyCoeff(double energyCoeff) {
        this.energyCoeff.set(energyCoeff);
    }

    public double getThermalConductBound() {
        return thermalConductBound.get();
    }

    public SimpleDoubleProperty thermalConductBoundProperty() {
        return thermalConductBound;
    }

    public void setThermalConductBound(double thermalConductBound) {
        this.thermalConductBound.set(thermalConductBound);
    }

    public double getActEnergy() {
        return actEnergy.get();
    }

    public SimpleDoubleProperty actEnergyProperty() {
        return actEnergy;
    }

    public void setActEnergy(double actEnergy) {
        this.actEnergy.set(actEnergy);
    }

    public double getDislDistCoeff() {
        return dislDistCoeff.get();
    }

    public SimpleDoubleProperty dislDistCoeffProperty() {
        return dislDistCoeff;
    }

    public void setDislDistCoeff(double dislDistCoeff) {
        this.dislDistCoeff.set(dislDistCoeff);
    }

    public double getYieldStateCoeff() {
        return yieldStateCoeff.get();
    }

    public SimpleDoubleProperty yieldStateCoeffProperty() {
        return yieldStateCoeff;
    }

    public void setYieldStateCoeff(double yieldStateCoeff) {
        this.yieldStateCoeff.set(yieldStateCoeff);
    }

    public double getUltimateStateCoeff() {
        return ultimateStateCoeff.get();
    }

    public SimpleDoubleProperty ultimateStateCoeffProperty() {
        return ultimateStateCoeff;
    }

    public void setUltimateStateCoeff(double ultimateStateCoeff) {
        this.ultimateStateCoeff.set(ultimateStateCoeff);
    }

    public double getPartVolFraction() {
        return partVolFraction.get();
    }

    public SimpleDoubleProperty partVolFractionProperty() {
        return partVolFraction;
    }

    public void setPartVolFraction(double partVolFraction) {
        this.partVolFraction.set(partVolFraction);
    }

    public double getPartRadius() {
        return partRadius.get();
    }

    public SimpleDoubleProperty partRadiusProperty() {
        return partRadius;
    }

    public void setPartRadius(double partRadius) {
        this.partRadius.set(partRadius);
    }

    public double getThresholdStress() {
        return thresholdStress.get();
    }

    public SimpleDoubleProperty thresholdStressProperty() {
        return thresholdStress;
    }

    public void setThresholdStress(double thresholdStress) {
        this.thresholdStress.set(thresholdStress);
    }

    public double getTorsionEnergyCoeff() {
        return torsionEnergyCoeff.get();
    }

    public SimpleDoubleProperty torsionEnergyCoeffProperty() {
        return torsionEnergyCoeff;
    }

    public void setTorsionEnergyCoeff(double torsionEnergyCoeff) {
        this.torsionEnergyCoeff.set(torsionEnergyCoeff);
    }

    public double getTorsionEnergyCoeffGB1() {
        return torsionEnergyCoeffGB1.get();
    }

    public SimpleDoubleProperty torsionEnergyCoeffGB1Property() {
        return torsionEnergyCoeffGB1;
    }

    public void setTorsionEnergyCoeffGB1(double torsionEnergyCoeffGB1) {
        this.torsionEnergyCoeffGB1.set(torsionEnergyCoeffGB1);
    }

    public double getTorsionEnergyCoeffGB2() {
        return torsionEnergyCoeffGB2.get();
    }

    public SimpleDoubleProperty torsionEnergyCoeffGB2Property() {
        return torsionEnergyCoeffGB2;
    }

    public void setTorsionEnergyCoeffGB2(double torsionEnergyCoeffGB2) {
        this.torsionEnergyCoeffGB2.set(torsionEnergyCoeffGB2);
    }

    public double getLowTemperThrValue() {
        return lowTemperThrValue.get();
    }

    public SimpleDoubleProperty lowTemperThrValueProperty() {
        return lowTemperThrValue;
    }

    public void setLowTemperThrValue(double lowTemperThrValue) {
        this.lowTemperThrValue.set(lowTemperThrValue);
    }

    public double getHighTemperThrValue() {
        return highTemperThrValue.get();
    }

    public SimpleDoubleProperty highTemperThrValueProperty() {
        return highTemperThrValue;
    }

    public void setHighTemperThrValue(double highTemperThrValue) {
        this.highTemperThrValue.set(highTemperThrValue);
    }

    public double getMinTwinTemperature() {
        return minTwinTemperature.get();
    }

    public SimpleDoubleProperty minTwinTemperatureProperty() {
        return minTwinTemperature;
    }

    public void setMinTwinTemperature(double minTwinTemperature) {
        this.minTwinTemperature.set(minTwinTemperature);
    }

    public double getTwinningTemperature() {
        return twinningTemperature.get();
    }

    public SimpleDoubleProperty twinningTemperatureProperty() {
        return twinningTemperature;
    }

    public void setTwinningTemperature(double twinningTemperature) {
        this.twinningTemperature.set(twinningTemperature);
    }

    public double getLatticeVectorALength() {
        return latticeVectorALength.get();
    }

    public SimpleDoubleProperty latticeVectorALengthProperty() {
        return latticeVectorALength;
    }

    public void setLatticeVectorALength(double latticeVectorALength) {
        this.latticeVectorALength.set(latticeVectorALength);
    }

    public double getLatticeVectorBLength() {
        return latticeVectorBLength.get();
    }

    public SimpleDoubleProperty latticeVectorBLengthProperty() {
        return latticeVectorBLength;
    }

    public void setLatticeVectorBLength(double latticeVectorBLength) {
        this.latticeVectorBLength.set(latticeVectorBLength);
    }

    public double getLatticeVectorCLength() {
        return latticeVectorCLength.get();
    }

    public SimpleDoubleProperty latticeVectorCLengthProperty() {
        return latticeVectorCLength;
    }

    public void setLatticeVectorCLength(double latticeVectorCLength) {
        this.latticeVectorCLength.set(latticeVectorCLength);
    }

    public double getLatticeAngle_VecA_VecB() {
        return latticeAngle_VecA_VecB.get();
    }

    public SimpleDoubleProperty latticeAngle_VecA_VecBProperty() {
        return latticeAngle_VecA_VecB;
    }

    public void setLatticeAngle_VecA_VecB(double latticeAngle_VecA_VecB) {
        this.latticeAngle_VecA_VecB.set(latticeAngle_VecA_VecB);
    }

    public double getLatticeAngle_VecB_VecC() {
        return latticeAngle_VecB_VecC.get();
    }

    public SimpleDoubleProperty latticeAngle_VecB_VecCProperty() {
        return latticeAngle_VecB_VecC;
    }

    public void setLatticeAngle_VecB_VecC(double latticeAngle_VecB_VecC) {
        this.latticeAngle_VecB_VecC.set(latticeAngle_VecB_VecC);
    }

    public double getLatticeAngle_VecC_VecA() {
        return latticeAngle_VecC_VecA.get();
    }

    public SimpleDoubleProperty latticeAngle_VecC_VecAProperty() {
        return latticeAngle_VecC_VecA;
    }

    public void setLatticeAngle_VecC_VecA(double latticeAngle_VecC_VecA) {
        this.latticeAngle_VecC_VecA.set(latticeAngle_VecC_VecA);
    }

    public double getLatticeAnisCoeff() {
        return latticeAnisCoeff.get();
    }

    public SimpleDoubleProperty latticeAnisCoeffProperty() {
        return latticeAnisCoeff;
    }

    public void setLatticeAnisCoeff(double latticeAnisCoeff) {
        this.latticeAnisCoeff.set(latticeAnisCoeff);
    }

    public double getMaxProbRecryst() {
        return maxProbRecryst.get();
    }

    public SimpleDoubleProperty maxProbRecrystProperty() {
        return maxProbRecryst;
    }

    public void setMaxProbRecryst(double maxProbRecryst) {
        this.maxProbRecryst.set(maxProbRecryst);
    }

    public double getMaxPropTwinning() {
        return maxPropTwinning.get();
    }

    public SimpleDoubleProperty maxPropTwinningProperty() {
        return maxPropTwinning;
    }

    public void setMaxPropTwinning(double maxPropTwinning) {
        this.maxPropTwinning.set(maxPropTwinning);
    }

    public double getMinDislDensity() {
        return minDislDensity.get();
    }

    public SimpleDoubleProperty minDislDensityProperty() {
        return minDislDensity;
    }

    public void setMinDislDensity(double minDislDensity) {
        this.minDislDensity.set(minDislDensity);
    }
}
