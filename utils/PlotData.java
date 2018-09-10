package utils;

import java.util.Collections;
import java.util.List;

public class PlotData {

    private List<Double> temperature;
    private List<Double> elasticEnergy;
    private List<Double> dislocationDensity;
    private List<Double> momentsX;
    private List<Double> momentsY;
    private List<Double> momentsZ;

    public PlotData(List<Double> temperature, List<Double> elasticEnergy,
                    List<Double> dislocationDensity,
                    List<Double> xMoment, List<Double> yMoment, List<Double> zMoment)
    {
        this.temperature = temperature;
        this.elasticEnergy = elasticEnergy;
        this.dislocationDensity = dislocationDensity;
        this.momentsX = xMoment;
        this.momentsY = yMoment;
        this.momentsZ = zMoment;
    }

    public PlotData(){
        this.temperature = Collections.EMPTY_LIST;
        this.elasticEnergy = Collections.EMPTY_LIST;
        this.dislocationDensity = Collections.EMPTY_LIST;
        this.momentsX = Collections.EMPTY_LIST;
        this.momentsY = Collections.EMPTY_LIST;
        this.momentsZ = Collections.EMPTY_LIST;
    }

    public List<Double> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }

    public List<Double> getElasticEnergy() {
        return elasticEnergy;
    }

    public void setElasticEnergy(List<Double> elasticEnergy) {
        this.elasticEnergy = elasticEnergy;
    }

    public List<Double> getDislocationDensity() {
        return dislocationDensity;
    }

    public void setDislocationDensity(List<Double> dislocationDensity) {
        this.dislocationDensity = dislocationDensity;
    }

    public List<Double> getMomentsX() {
        return momentsX;
    }

    public void setMomentsX(List<Double> momentsX) {
        this.momentsX = momentsX;
    }

    public List<Double> getMomentsY() {
        return momentsY;
    }

    public void setMomentsY(List<Double> momentsY) {
        this.momentsY = momentsY;
    }

    public List<Double> getMomentsZ() {
        return momentsZ;
    }

    public void setMomentsZ(List<Double> momentsZ) {
        this.momentsZ = momentsZ;
    }
}
