package utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Results {

    public SimpleStringProperty name;
    public SimpleIntegerProperty timeStep;

    public Results(String name, int timeStep){
        this.name = new SimpleStringProperty(name);
        this.timeStep = new SimpleIntegerProperty(timeStep);
    }

}
