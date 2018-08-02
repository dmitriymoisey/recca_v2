package view;

import javafx.scene.paint.Color;

public class ColorScale {

    private String scaleType;
    private double maxValue, minValue;

    private final static double RED_HUE = Color.RED.getHue() ;
    private final static double PURPLE_HUE = Color.PURPLE.getHue();

    public ColorScale(String scaleType, double minValue, double maxValue){
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.scaleType = scaleType;
    }

    public Color getElementColor(double value){
        if (value < minValue)
            return Color.WHITE;
        else if (value > maxValue)
            return Color.BLACK;
        else{
            double hue = PURPLE_HUE + (RED_HUE - PURPLE_HUE) * (value - minValue) / (maxValue - minValue);
            return Color.hsb(hue, 1.0, 1.0);
        }
    }

}
