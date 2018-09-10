package utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {

    private SimpleStringProperty name;
    private SimpleDoubleProperty timeStep;
    private SimpleDoubleProperty totalTime;

    public Task(String name, double timeStep, double totalTime){
        this.name = new SimpleStringProperty(name);
        this.timeStep = new SimpleDoubleProperty(timeStep);
        this.totalTime = new SimpleDoubleProperty(totalTime);
    }

    public Task() {
        this.name = new SimpleStringProperty(null);
        this.timeStep = new SimpleDoubleProperty(0.0);
        this.totalTime = new SimpleDoubleProperty(0.0);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getTimeStep() {
        return timeStep.get();
    }

    public void setTimeStep(double timeStep) {
        this.timeStep.set(timeStep);
    }

    public double getTotalTime() {
        return totalTime.get();
    }

    public void setTotalTime(double totalTime) {
        this.totalTime.set(totalTime);
    }
}
