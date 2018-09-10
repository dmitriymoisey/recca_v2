package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BoundaryCondition {

    private SimpleStringProperty name;
    private SimpleStringProperty facet;

    private SimpleDoubleProperty averageTemperature;
    private SimpleDoubleProperty temperatureRange;
    private SimpleDoubleProperty temperatureLoadingTimePortion;

    private SimpleDoubleProperty averageElasticEnergy;
    private SimpleDoubleProperty elasticEnergyRange;
    private SimpleDoubleProperty elasticEnergyLoadingTimePortion;

    private SimpleDoubleProperty averageDislocationDensity;
    private SimpleDoubleProperty dislocationDensityRange;
    private SimpleDoubleProperty dislocationDensityLoadingTimePortion;

    private SimpleDoubleProperty averageMomentX;
    private SimpleDoubleProperty momentRangeX;
    private SimpleDoubleProperty momentLoadingTimePortionX;

    private SimpleDoubleProperty averageMomentY;
    private SimpleDoubleProperty momentRangeY;
    private SimpleDoubleProperty momentLoadingTimePortionY;

    private SimpleDoubleProperty averageMomentZ;
    private SimpleDoubleProperty momentRangeZ;
    private SimpleDoubleProperty momentLoadingTimePortionZ;

    public BoundaryCondition(String name, String facet,
                             double averageTemperature, double temperatureRange, double temperatureLoadTimePortion,
                             double averageElasticEnergy, double elasticEnergyRange, double elasticEnergyLoadTimePortion,
                             double averageDislocationDensity, double dislDensityRange, double dislDensityLoadTimePortion,
                             double averageMomentX, double xMomentRange, double xMomentLoadTimePortion,
                             double averageMomentY, double yMomentRange, double yMomentLoadTimePortion,
                             double averageMomentZ, double zMomentRange, double zMomentLoadTimePortion)
    {
        this.name = new SimpleStringProperty(name);
        this.facet = new SimpleStringProperty(facet);

        this.averageTemperature = new SimpleDoubleProperty(averageTemperature);
        this.temperatureRange = new SimpleDoubleProperty(temperatureRange);
        this.temperatureLoadingTimePortion = new SimpleDoubleProperty(temperatureLoadTimePortion);

        this.averageElasticEnergy = new SimpleDoubleProperty(averageElasticEnergy);
        this.elasticEnergyRange = new SimpleDoubleProperty(elasticEnergyRange);
        this.elasticEnergyLoadingTimePortion = new SimpleDoubleProperty(elasticEnergyLoadTimePortion);

        this.averageDislocationDensity = new SimpleDoubleProperty(averageDislocationDensity);
        this.dislocationDensityRange = new SimpleDoubleProperty(dislDensityRange);
        this.dislocationDensityLoadingTimePortion = new SimpleDoubleProperty(dislDensityLoadTimePortion);

        this.averageMomentX = new SimpleDoubleProperty(averageMomentX);
        this.momentRangeX = new SimpleDoubleProperty(xMomentRange);
        this.momentLoadingTimePortionX = new SimpleDoubleProperty(xMomentLoadTimePortion);

        this.averageMomentY = new SimpleDoubleProperty(averageMomentY);
        this.momentRangeY = new SimpleDoubleProperty(yMomentRange);
        this.momentLoadingTimePortionY = new SimpleDoubleProperty(yMomentLoadTimePortion);

        this.averageMomentZ = new SimpleDoubleProperty(averageMomentZ);
        this.momentRangeZ = new SimpleDoubleProperty(zMomentRange);
        this.momentLoadingTimePortionZ = new SimpleDoubleProperty(zMomentLoadTimePortion);
    }

    public BoundaryCondition() {

    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFacet() {
        return facet.get();
    }

    public SimpleStringProperty facetProperty() {
        return facet;
    }

    public void setFacet(String facet) {
        this.facet.set(facet);
    }

    public double getAverageTemperature() {
        return averageTemperature.get();
    }

    public SimpleDoubleProperty averageTemperatureProperty() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature.set(averageTemperature);
    }

    public double getTemperatureRange() {
        return temperatureRange.get();
    }

    public SimpleDoubleProperty temperatureRangeProperty() {
        return temperatureRange;
    }

    public void setTemperatureRange(double temperatureRange) {
        this.temperatureRange.set(temperatureRange);
    }

    public double getTemperatureLoadingTimePortion() {
        return temperatureLoadingTimePortion.get();
    }

    public SimpleDoubleProperty temperatureLoadingTimePortionProperty() {
        return temperatureLoadingTimePortion;
    }

    public void setTemperatureLoadingTimePortion(double temperatureLoadingTimePortion) {
        this.temperatureLoadingTimePortion.set(temperatureLoadingTimePortion);
    }

    public double getAverageElasticEnergy() {
        return averageElasticEnergy.get();
    }

    public SimpleDoubleProperty averageElasticEnergyProperty() {
        return averageElasticEnergy;
    }

    public void setAverageElasticEnergy(double averageElasticEnergy) {
        this.averageElasticEnergy.set(averageElasticEnergy);
    }

    public double getElasticEnergyRange() {
        return elasticEnergyRange.get();
    }

    public SimpleDoubleProperty elasticEnergyRangeProperty() {
        return elasticEnergyRange;
    }

    public void setElasticEnergyRange(double elasticEnergyRange) {
        this.elasticEnergyRange.set(elasticEnergyRange);
    }

    public double getElasticEnergyLoadingTimePortion() {
        return elasticEnergyLoadingTimePortion.get();
    }

    public SimpleDoubleProperty elasticEnergyLoadingTimePortionProperty() {
        return elasticEnergyLoadingTimePortion;
    }

    public void setElasticEnergyLoadingTimePortion(double elasticEnergyLoadingTimePortion) {
        this.elasticEnergyLoadingTimePortion.set(elasticEnergyLoadingTimePortion);
    }

    public double getAverageDislocationDensity() {
        return averageDislocationDensity.get();
    }

    public SimpleDoubleProperty averageDislocationDensityProperty() {
        return averageDislocationDensity;
    }

    public void setAverageDislocationDensity(double averageDislocationDensity) {
        this.averageDislocationDensity.set(averageDislocationDensity);
    }

    public double getDislocationDensityRange() {
        return dislocationDensityRange.get();
    }

    public SimpleDoubleProperty dislocationDensityRangeProperty() {
        return dislocationDensityRange;
    }

    public void setDislocationDensityRange(double dislocationDensityRange) {
        this.dislocationDensityRange.set(dislocationDensityRange);
    }

    public double getDislocationDensityLoadingTimePortion() {
        return dislocationDensityLoadingTimePortion.get();
    }

    public SimpleDoubleProperty dislocationDensityLoadingTimePortionProperty() {
        return dislocationDensityLoadingTimePortion;
    }

    public void setDislocationDensityLoadingTimePortion(double dislocationDensityLoadingTimePortion) {
        this.dislocationDensityLoadingTimePortion.set(dislocationDensityLoadingTimePortion);
    }

    public double getAverageMomentX() {
        return averageMomentX.get();
    }

    public SimpleDoubleProperty averageMomentXProperty() {
        return averageMomentX;
    }

    public void setAverageMomentX(double averageMomentX) {
        this.averageMomentX.set(averageMomentX);
    }

    public double getMomentRangeX() {
        return momentRangeX.get();
    }

    public SimpleDoubleProperty momentRangeXProperty() {
        return momentRangeX;
    }

    public void setMomentRangeX(double momentRangeX) {
        this.momentRangeX.set(momentRangeX);
    }

    public double getMomentLoadingTimePortionX() {
        return momentLoadingTimePortionX.get();
    }

    public SimpleDoubleProperty momentLoadingTimePortionXProperty() {
        return momentLoadingTimePortionX;
    }

    public void setMomentLoadingTimePortionX(double momentLoadingTimePortionX) {
        this.momentLoadingTimePortionX.set(momentLoadingTimePortionX);
    }

    public double getAverageMomentY() {
        return averageMomentY.get();
    }

    public SimpleDoubleProperty averageMomentYProperty() {
        return averageMomentY;
    }

    public void setAverageMomentY(double averageMomentY) {
        this.averageMomentY.set(averageMomentY);
    }

    public double getMomentRangeY() {
        return momentRangeY.get();
    }

    public SimpleDoubleProperty momentRangeYProperty() {
        return momentRangeY;
    }

    public void setMomentRangeY(double momentRangeY) {
        this.momentRangeY.set(momentRangeY);
    }

    public double getMomentLoadingTimePortionY() {
        return momentLoadingTimePortionY.get();
    }

    public SimpleDoubleProperty momentLoadingTimePortionYProperty() {
        return momentLoadingTimePortionY;
    }

    public void setMomentLoadingTimePortionY(double momentLoadingTimePortionY) {
        this.momentLoadingTimePortionY.set(momentLoadingTimePortionY);
    }

    public double getAverageMomentZ() {
        return averageMomentZ.get();
    }

    public SimpleDoubleProperty averageMomentZProperty() {
        return averageMomentZ;
    }

    public void setAverageMomentZ(double averageMomentZ) {
        this.averageMomentZ.set(averageMomentZ);
    }

    public double getMomentRangeZ() {
        return momentRangeZ.get();
    }

    public SimpleDoubleProperty momentRangeZProperty() {
        return momentRangeZ;
    }

    public void setMomentRangeZ(double momentRangeZ) {
        this.momentRangeZ.set(momentRangeZ);
    }

    public double getMomentLoadingTimePortionZ() {
        return momentLoadingTimePortionZ.get();
    }

    public SimpleDoubleProperty momentLoadingTimePortionZProperty() {
        return momentLoadingTimePortionZ;
    }

    public void setMomentLoadingTimePortionZ(double momentLoadingTimePortionZ) {
        this.momentLoadingTimePortionZ.set(momentLoadingTimePortionZ);
    }
}
