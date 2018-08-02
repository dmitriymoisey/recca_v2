package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InitialCondition {
    private SimpleStringProperty name;
//    private SimpleIntegerProperty elementIndex;
    private SimpleDoubleProperty temperature;
    private SimpleDoubleProperty elasticEnergy;
    private SimpleDoubleProperty dislocationDensity;
    private SimpleDoubleProperty momentX;
    private SimpleDoubleProperty momentY;
    private SimpleDoubleProperty momentZ;

    public InitialCondition(String name, double temperature, double elasticEnergy,
                            double dislocationDensity, double xMoment, double yMoment, double zMoment) {
        this.name = new SimpleStringProperty(name);
        this.temperature = new SimpleDoubleProperty(temperature);
        this.elasticEnergy = new SimpleDoubleProperty(elasticEnergy);
        this.dislocationDensity = new SimpleDoubleProperty(dislocationDensity);
        this.momentX = new SimpleDoubleProperty(xMoment);
        this.momentY = new SimpleDoubleProperty(yMoment);
        this.momentZ = new SimpleDoubleProperty(zMoment);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

//    public int getElementIndex() {
//        return elementIndex.get();
//    }
//
//    public void setElementIndex(int elementIndex) {
//        this.elementIndex.set(elementIndex);
//    }

    public double getTemperature() {
        return temperature.get();
    }

    public void setTemperature(double temperature) {
        this.temperature.set(temperature);
    }

    public double getElasticEnergy() {
        return elasticEnergy.get();
    }

    public void setElasticEnergy(double elasticEnergy) {
        this.elasticEnergy.set(elasticEnergy);
    }

    public double getDislocationDensity() {
        return dislocationDensity.get();
    }

    public void setDislocationDensity(double dislocationDensity) {
        this.dislocationDensity.set(dislocationDensity);
    }

    public double getMomentX() {
        return momentX.get();
    }

    public void setMomentX(double momentX) {
        this.momentX.set(momentX);
    }

    public double getMomentY() {
        return momentY.get();
    }

    public void setMomentY(double momentY) {
        this.momentY.set(momentY);
    }

    public double getMomentZ() {
        return momentZ.get();
    }

    public void setMomentZ(double momentZ) {
        this.momentZ.set(momentZ);
    }
}
