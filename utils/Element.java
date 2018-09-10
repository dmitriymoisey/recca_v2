package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Element {

    private SimpleIntegerProperty grainIndex;
    private SimpleIntegerProperty locationType;

    private SimpleDoubleProperty coordX;
    private SimpleDoubleProperty coordY;
    private SimpleDoubleProperty coordZ;

    private SimpleDoubleProperty temperature;
    private SimpleDoubleProperty elasticEnergy;
    private SimpleDoubleProperty dislocationDensity;
    private SimpleDoubleProperty momentsX;
    private SimpleDoubleProperty momentsY;
    private SimpleDoubleProperty momentsZ;

    public Element(int locationType, int grainIndex,
                   double xCoord, double yCoord, double zCoord,
                   double temperature, double elasticEnergy, double dislocationDensity,
                   double momentX, double momentY, double momentZ)
    {
        this.grainIndex = new SimpleIntegerProperty(grainIndex);
        this.locationType = new SimpleIntegerProperty(locationType);

        this.coordX = new SimpleDoubleProperty(xCoord);
        this.coordY = new SimpleDoubleProperty(yCoord);
        this.coordZ = new SimpleDoubleProperty(zCoord);

        this.temperature = new SimpleDoubleProperty(temperature);
        this.elasticEnergy = new SimpleDoubleProperty(elasticEnergy);
        this.dislocationDensity = new SimpleDoubleProperty(dislocationDensity);

        this.momentsX = new SimpleDoubleProperty(momentX);
        this.momentsY = new SimpleDoubleProperty(momentY);
        this.momentsZ = new SimpleDoubleProperty(momentZ);
    }

    public int getGrainIndex() {
        return grainIndex.get();
    }

    public SimpleIntegerProperty grainIndexProperty() {
        return grainIndex;
    }

    public void setGrainIndex(int grainIndex) {
        this.grainIndex.set(grainIndex);
    }

    public int getLocationType() {
        return locationType.get();
    }

    public SimpleIntegerProperty locationTypeProperty() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType.set(locationType);
    }

    public double getCoordX() {
        return coordX.get();
    }

    public SimpleDoubleProperty coordXProperty() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX.set(coordX);
    }

    public double getCoordY() {
        return coordY.get();
    }

    public SimpleDoubleProperty coordYProperty() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY.set(coordY);
    }

    public double getCoordZ() {
        return coordZ.get();
    }

    public SimpleDoubleProperty coordZProperty() {
        return coordZ;
    }

    public void setCoordZ(double coordZ) {
        this.coordZ.set(coordZ);
    }

    public double getTemperature() {
        return temperature.get();
    }

    public SimpleDoubleProperty temperatureProperty() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature.set(temperature);
    }

    public double getElasticEnergy() {
        return elasticEnergy.get();
    }

    public SimpleDoubleProperty elasticEnergyProperty() {
        return elasticEnergy;
    }

    public void setElasticEnergy(double elasticEnergy) {
        this.elasticEnergy.set(elasticEnergy);
    }

    public double getDislocationDensity() {
        return dislocationDensity.get();
    }

    public SimpleDoubleProperty dislocationDensityProperty() {
        return dislocationDensity;
    }

    public void setDislocationDensity(double dislocationDensity) {
        this.dislocationDensity.set(dislocationDensity);
    }

    public double getMomentsX() {
        return momentsX.get();
    }

    public SimpleDoubleProperty momentsXProperty() {
        return momentsX;
    }

    public void setMomentsX(double momentsX) {
        this.momentsX.set(momentsX);
    }

    public double getMomentsY() {
        return momentsY.get();
    }

    public SimpleDoubleProperty momentsYProperty() {
        return momentsY;
    }

    public void setMomentsY(double momentsY) {
        this.momentsY.set(momentsY);
    }

    public double getMomentsZ() {
        return momentsZ.get();
    }

    public SimpleDoubleProperty momentsZProperty() {
        return momentsZ;
    }

    public void setMomentsZ(double momentsZ) {
        this.momentsZ.set(momentsZ);
    }
}
