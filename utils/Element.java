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

    public Element(int grainIndex, int locationType,
                   double xCoord, double yCoord, double zCoord,
                   double temperature)
    {
        this.grainIndex = new SimpleIntegerProperty(grainIndex);
        this.locationType = new SimpleIntegerProperty(locationType);

        this.coordX = new SimpleDoubleProperty(xCoord);
        this.coordY = new SimpleDoubleProperty(yCoord);
        this.coordZ = new SimpleDoubleProperty(zCoord);

        this.temperature = new SimpleDoubleProperty(temperature);
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
}
