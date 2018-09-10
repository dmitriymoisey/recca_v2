package view;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ColorScale {

    private String scaleType;
    private double maxValue, minValue;
    private boolean isReverse;

    private final static double RED_HUE = Color.RED.getHue() ;
    private final static double PURPLE_HUE = Color.PURPLE.getHue();

    public ColorScale(String scaleType, double minValue, double maxValue, boolean isReverse){
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.scaleType = scaleType;
        this.isReverse = isReverse;
    }

    public Color getElementColor(double value){
        if (!isReverse){
            switch (scaleType){
                case UICommon.RAINBOW:
                    if (value < minValue)
                        return Color.WHITE;
                    else if (value > maxValue)
                        return Color.BLACK;
                    else{
                        double hue = PURPLE_HUE + (RED_HUE - PURPLE_HUE) * (value - minValue) / (maxValue - minValue);
                        return Color.hsb(hue, 1.0, 1.0);
                    }
                case UICommon.GRAY:
                    if (value < minValue)
                        return Color.WHITE;
                    else if (value > maxValue)
                        return Color.BLACK;
                    else {
                        double brightness = (value - minValue) / (maxValue - minValue) ;
                        return Color.hsb(120, 0 , 1 -brightness);
                    }
                case UICommon.REDBLUE:
                    if (value < minValue)
                        return Color.WHITE;
                    else if (value > maxValue)
                        return Color.BLACK;
                    else if (value > 0){
                        return Color.RED;
                    }
                    else{
                        return Color.BLUE;
                    }
                default:
                    return Color.WHITE;
            }
        }
        else {
            switch (scaleType){
                case UICommon.RAINBOW:
                    if (value > maxValue)
                        return Color.WHITE;
                    else if (value < minValue)
                        return Color.BLACK;
                    else{
                        double hue = RED_HUE + (PURPLE_HUE - RED_HUE) * (value - minValue) / (maxValue - minValue);
                        return Color.hsb(hue, 1.0, 1.0);
                    }
                case UICommon.GRAY:
                    if (value > maxValue)
                        return Color.WHITE;
                    else if (value < minValue)
                        return Color.BLACK;
                    else{
                        double brightness = (value - minValue) / (maxValue - minValue) ;
                        return Color.hsb(120, 0 , brightness);
                    }
                case UICommon.REDBLUE:
                    if (value > maxValue)
                        return Color.WHITE;
                    else if (value < minValue)
                        return Color.BLACK;
                    else if (value > 0.0)
                        return Color.BLUE;
                    else if (value < 0.0)
                        return Color.RED;
                default:
                    return Color.WHITE;
            }
        }
    }

    public WritableImage createScaleImage(){
        WritableImage writableImage = new WritableImage(UICommon.SCALE_IMAGE_WIDTH, UICommon.SCALE_IMAGE_HEIGHT);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        int y;

        for (y=0 ; y < 10 ; y++){
            for (int x = 0 ; x < UICommon.SCALE_IMAGE_WIDTH ; x++){
                pixelWriter.setColor(x, y, Color.BLACK);
            }
        }

        for (y = 10 ; y < UICommon.SCALE_IMAGE_HEIGHT - 10 ; y ++){
            double value = maxValue - (maxValue - minValue) * (y-10) / (UICommon.SCALE_IMAGE_HEIGHT-10) ;
            for (int x = 0 ; x < UICommon.SCALE_IMAGE_WIDTH ; x ++){
                pixelWriter.setColor(x, y, getElementColor(value));
            }
        }

        for (y = UICommon.SCALE_IMAGE_HEIGHT - 10 ; y < UICommon.SCALE_IMAGE_HEIGHT ; y ++){
            for (int x = 0 ; x < UICommon.SCALE_IMAGE_WIDTH ; x ++){
                pixelWriter.setColor(x, y, Color.WHITE);
            }
        }

        return writableImage;
    }

}
