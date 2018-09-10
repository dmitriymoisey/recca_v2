package utils;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SpecimenData {

    private SimpleStringProperty specimenName;
    private SimpleStringProperty material;

    private SimpleIntegerProperty cellNumberX;
    private SimpleIntegerProperty cellNumberY;
    private SimpleIntegerProperty cellNumberZ;
    private SimpleDoubleProperty cellSize;

    private SimpleIntegerProperty numberOfGrains;
    private SimpleDoubleProperty angleRange;
    private byte typeOfGrainDistribution;

    private SimpleStringProperty initialConditions;
    private SimpleStringProperty boundaryConditions;
    private SimpleStringProperty task;

    public SpecimenData(String specimenName, int cellNumberX, int cellNumberY, int cellNumberZ,
                        double cellSize, int numberOfGrains, double angleRange, byte typeOfGrainDistribution,
                        String material, String initialConditions, String boundaryConditions, String task)
    {
        this.specimenName = new SimpleStringProperty(specimenName);
        this.material = new SimpleStringProperty(material);

        this.cellNumberX = new SimpleIntegerProperty(cellNumberX);
        this.cellNumberY = new SimpleIntegerProperty(cellNumberY);
        this.cellNumberZ = new SimpleIntegerProperty(cellNumberZ);

        this.cellSize = new SimpleDoubleProperty(cellSize);
        this.numberOfGrains = new SimpleIntegerProperty(numberOfGrains);
        this.angleRange = new SimpleDoubleProperty(angleRange);
        this.typeOfGrainDistribution = typeOfGrainDistribution;
        this.initialConditions = new SimpleStringProperty(initialConditions);
        this.boundaryConditions = new SimpleStringProperty(boundaryConditions);
        this.task = new SimpleStringProperty(task);
    }

    public SpecimenData() {
        this.specimenName = new SimpleStringProperty(null);
        this.material = new SimpleStringProperty(null);

        this.cellNumberX = new SimpleIntegerProperty(0);
        this.cellNumberY = new SimpleIntegerProperty(0);
        this.cellNumberZ = new SimpleIntegerProperty(0);

        this.cellSize = new SimpleDoubleProperty(0.0);
        this.numberOfGrains = new SimpleIntegerProperty(0);
        this.angleRange = new SimpleDoubleProperty(0.0);
        this.typeOfGrainDistribution = 0;

        this.initialConditions = new SimpleStringProperty(null);
        this.boundaryConditions = new SimpleStringProperty(null);
        this.task = new SimpleStringProperty(null);
    }

    public String getSpecimenName(){
        return specimenName.get();
    }
    public void setSpecimenName(String value){
        this.specimenName.set(value);
    }

    public String getMaterial(){
        return material.get();
    }
    public void setMaterial(String value){
        this.material.set(value);
    }

    public int getCellNumberX(){
        return cellNumberX.get();
    }
    public void setCellNumberX(int value){
        this.cellNumberX.set(value);
    }

    public int getCellNumberY(){
        return cellNumberY.get();
    }
    public void setCellNumberY(int value){
        this.cellNumberY.set(value);
    }

    public int getCellNumberZ(){
        return cellNumberZ.get();
    }
    public void setCellNumberZ(int value){
        this.cellNumberZ.set(value);
    }

    public int getNumberOfGrains(){
        return numberOfGrains.get();
    }
    public void setNumberOfGrains(int value){
        this.numberOfGrains.set(value);
    }

    public double getAngleRange() {
        return angleRange.get();
    }

    public void setAngleRange(double value) {
        this.angleRange.set(value);
    }

    public String getInitialConditions() {
        return initialConditions.get();
    }

    public SimpleStringProperty initialConditionsProperty() {
        return initialConditions;
    }

    public void setInitialConditions(String initialConditions) {
        this.initialConditions.set(initialConditions);
    }

    public String getBoundaryConditions() {
        return boundaryConditions.get();
    }

    public SimpleStringProperty boundaryConditionsProperty() {
        return boundaryConditions;
    }

    public void setBoundaryConditions(String boundaryConditions) {
        this.boundaryConditions.set(boundaryConditions);
    }

    public String getTask() {
        return task.get();
    }

    public SimpleStringProperty taskProperty() {
        return task;
    }

    public void setTask(String task) {
        this.task.set(task);
    }

    public double getCellSize() {
        return cellSize.get();
    }

    public SimpleDoubleProperty cellSizeProperty() {
        return cellSize;
    }

    public void setCellSize(double cellSize) {
        this.cellSize.set(cellSize);
    }

    public byte getTypeOfGrainDistribution() {
        return typeOfGrainDistribution;
    }

    public void setTypeOfGrainDistribution(byte typeOfGrainDistribution) {
        this.typeOfGrainDistribution = typeOfGrainDistribution;
    }
}
