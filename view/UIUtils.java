package view;

import javafx.collections.FXCollections;
import utils.Element;
import utils.SpecimenData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class UIUtils {

    public static int getAxisLength(SpecimenData specimenData){
        List<Integer> sizes = FXCollections.observableArrayList(specimenData.getCellNumberX(),
                specimenData.getCellNumberY(),
                specimenData.getCellNumberZ());
        return 2*Collections.max(sizes);
    }

    public static HashMap<String, Double> getMaxAndMinValues(List<Element> elementList, String parameter){
        HashMap<String, Double> rv = new HashMap<>();
        List<Double> values = new ArrayList<>();
        switch (parameter){
            case UICommon.TEMPERATURE:
                for (Element element : elementList){
                    values.add(element.getTemperature());
                }
                break;
            case UICommon.ELASTIC_ENERGY:
                for (Element element : elementList){
                    values.add(element.getElasticEnergy());
                }
                break;
            case UICommon.DISLOCATION_DENSITY:
                for (Element element : elementList){
                    values.add(element.getDislocationDensity());
                }
                break;
            case UICommon.MOMENTS_X:
                for (Element element : elementList){
                    values.add(element.getMomentsX());
                }
                break;
            case UICommon.MOMENTS_Y:
                for (Element element : elementList){
                    values.add(element.getMomentsY());
                }
                break;
            case UICommon.MOMENTS_Z:
                for (Element element : elementList){
                    values.add(element.getMomentsZ());
                }
                break;
        }
        rv.put("Max", Collections.max(values));
        rv.put("Min", Collections.min(values));
        return rv;
    }

}
