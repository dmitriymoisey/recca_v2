package view;

import databases.DataBaseUtils;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import utils.Element;
import utils.Specimen;

import java.util.*;

public class Specimen3DView {

    final BorderPane root = new BorderPane();
    final Xform world = new Xform();
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final Xform axisGroup = new Xform();
    final Xform elementsGroup = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);

    public Specimen3DView(){
        buildCamera();
        buildAxes();
    }

    public void buildCamera(){
        System.out.println("RecCA_3DPictureCreator method buildCamera(): start");

        root.getChildren().add(cameraXform);

        cameraXform.getChildren().add(cameraXform2);

        cameraXform.getChildren().add(cameraXform3);

        cameraXform3.getChildren().add(camera);

        cameraXform3.setRotateZ(90.0);

        camera.setNearClip(UICommon.CAMERA_NEAR_CLIP);
        camera.setFarClip(UICommon.CAMERA_FAR_CLIP);
    }

    public void buildAxes(){
        System.out.println("RecCA_3DPictureCreator method buildAxes(): start");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        /**
         *Create an Axis
         */
        final Box xAxis = new Box(UICommon.AXIS_LENGTH , 1, 1);
        final Box yAxis = new Box(1, UICommon.AXIS_LENGTH , 1);
        final Box zAxis = new Box(1, 1, UICommon.AXIS_LENGTH );

        /**
         *set the Text to every axis
         */

        final Box x_axis_lbl_1 = new Box(11, 1, 1);
        final Box x_axis_lbl_2 = new Box(11, 1, 1);
        x_axis_lbl_1.setRotate(50);
        x_axis_lbl_1.setMaterial(new PhongMaterial(Color.BLACK));
        x_axis_lbl_2.setRotate(-50);
        x_axis_lbl_2.setMaterial(new PhongMaterial(Color.BLACK));
        Xform x_axis_lbl = new Xform();
        x_axis_lbl.getChildren().addAll(x_axis_lbl_1,x_axis_lbl_2);
        x_axis_lbl.setTx(UICommon.AXIS_LENGTH/1.9d);
        x_axis_lbl.setTy(-5.0d);

        final Box y_axis_box_1 = new Box(6, 1, 1);
        final Box y_axis_box_2 = new Box(4, 1, 1);
        final Box y_axis_box_3 = new Box(4, 1, 1);
        y_axis_box_1.setMaterial(new PhongMaterial(Color.BLACK));
        y_axis_box_1.setRotate(90);
        y_axis_box_1.setTranslateY(-2.0d);
        y_axis_box_2.setMaterial(new PhongMaterial(Color.BLACK));
        y_axis_box_2.setRotate(50);
        y_axis_box_2.setTranslateX(1.9d);
        y_axis_box_2.setTranslateY(2.0d);
        y_axis_box_3.setMaterial(new PhongMaterial(Color.BLACK));
        y_axis_box_3.setRotate(-50);
        y_axis_box_3.setTranslateX(-1.9d);
        y_axis_box_3.setTranslateY(2.0d);
        Xform y_axis_lbl = new Xform();
        y_axis_lbl.getChildren().addAll(y_axis_box_1,
                y_axis_box_2,
                y_axis_box_3);
        y_axis_lbl.setTy(UICommon.AXIS_LENGTH/1.9d);
        y_axis_lbl.setTx(5.0d);

        final Box z_axis_box_1 = new Box(10, 1, 1);
        final Box z_axis_box_2 = new Box(8, 1, 1);
        final Box z_axis_box_3 = new Box(8, 1, 1);
        z_axis_box_1.setMaterial(new PhongMaterial(Color.BLACK));
        z_axis_box_2.setMaterial(new PhongMaterial(Color.BLACK));
        z_axis_box_3.setMaterial(new PhongMaterial(Color.BLACK));

        z_axis_box_1.setRotate(45.0d);
        z_axis_box_2.setTranslateY(4.0d);
        z_axis_box_3.setTranslateY(-4.0d);

        Xform z_axis_lbl = new Xform();
        z_axis_lbl.getChildren().addAll(z_axis_box_1,
                z_axis_box_2,
                z_axis_box_3);
        z_axis_lbl.setTz(UICommon.AXIS_LENGTH/1.9d);
        z_axis_lbl.setTx(5.0d);
        /**
         *set the color to every axis
         */

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis,x_axis_lbl, yAxis,y_axis_lbl, zAxis, z_axis_lbl);
        axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }

    public void showStructure(String specimenName){

        world.getChildren().remove(elementsGroup);

        List<Element> listOfElements = DataBaseUtils.getSpecimenStructureData(specimenName);
        System.out.println(listOfElements);
        List<Integer> grainIndices = new ArrayList<>();
        for (Element element : listOfElements){
            grainIndices.add(element.getGrainIndex());
        }

        int numberOfGrains = Collections.max(grainIndices) + 1;
        List<Color> listOfColors = generateRandomColors(numberOfGrains);

        for (Element element : listOfElements){
            if(element.getLocationType() == 1){
                Sphere sphere = new Sphere(0.8);
                sphere.setTranslateX(element.getCoordX());
                sphere.setTranslateY(element.getCoordY());
                sphere.setTranslateZ(element.getCoordZ());
                sphere.setMaterial(new PhongMaterial(listOfColors.get(element.getGrainIndex())));
                elementsGroup.getChildren().add(sphere);
            }
        }
        world.getChildren().add(elementsGroup);
    }

    private List<Color> generateRandomColors(int numberOfColors){
        List<Color> listOfColors = new ArrayList<Color>();
        for (int i=0 ; i < numberOfColors ; i++){
            listOfColors.add(Color.color(Math.random(), Math.random(), Math.random()));
        }
        return listOfColors;
    }

    public void showParameterDistribution(String specimenName, String taskName, int timeStep){

        List<Element> elementList = DataBaseUtils.getResult(specimenName, taskName, timeStep);

        ColorScale colorScale = new ColorScale("Rainbow", 300, 1300.0);

        for (Element element : elementList){
//            if(element.getLocationType() == 1){
                Sphere cell = new Sphere(0.8);
                cell.setTranslateX(element.getCoordX());
                cell.setTranslateY(element.getCoordY());
                cell.setTranslateZ(element.getCoordZ());
                cell.setMaterial(new PhongMaterial(colorScale.getElementColor(element.getTemperature())));
                elementsGroup.getChildren().add(cell);
//            }
        }
        world.getChildren().add(elementsGroup);
    }

}
