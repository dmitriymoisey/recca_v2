package view;

import databases.DataBaseUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.Element;
import utils.Specimen;
import utils.SpecimenData;

import java.io.IOException;
import java.util.List;

public class SpecimenStructure extends Stage {

    private BorderPane root;
    private Scene scene;

    public SpecimenStructure(){
        root = new BorderPane();
        scene = new Scene(root);
        this.setTitle("Specimen Structure");
        this.setScene(scene);

    }

    public void showStructure(SpecimenData specimenData){
        int axisLength = UIUtils.getAxisLength(specimenData);
        Specimen3DView specimen3DView = new Specimen3DView(axisLength);
        SubScene subScene =
                new SubScene(specimen3DView.world, 800, 800, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(specimen3DView.camera);
        List<Element> listOfElements = DataBaseUtils.specimenDataBase.getSpecimenStructure(specimenData.getSpecimenName());
        List<Color> colorList = UICommon.generateRandomColors(listOfElements);
        specimen3DView.showStructure(listOfElements, colorList);
        handle3DArea(subScene, specimen3DView);
        root.setCenter(subScene);
        this.show();
    }

    double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
    private void handle3DArea(final SubScene subScene, final Specimen3DView specimen3DView){
        subScene.setOnMousePressed(
                event -> {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    mouseOldX = event.getSceneX();
                    mouseOldY = event.getSceneY();
                }
        );

        subScene.setOnMouseDragged(
                event -> {
                    mouseOldX = mousePosX;
                    mouseOldY = mousePosY;
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    mouseDeltaX = mousePosX - mouseOldX;
                    mouseDeltaY = mousePosY - mouseOldY;

                    if(event.isPrimaryButtonDown()){
                        specimen3DView.cameraXform.ry.setAngle(specimen3DView.cameraXform.ry.getAngle() - mouseDeltaY*UICommon.MOUSE_SPEED*UICommon.ROTATION_SPEED);
                        specimen3DView.cameraXform.rx.setAngle(specimen3DView.cameraXform.rx.getAngle() + mouseDeltaX*UICommon.MOUSE_SPEED*UICommon.ROTATION_SPEED);
                    }
                    else if(event.isMiddleButtonDown()){
                        specimen3DView.camera.setTranslateX(specimen3DView.camera.getTranslateX() - mouseDeltaX*UICommon.MOUSE_SPEED*UICommon.TRACK_SPEED);
                        specimen3DView.camera.setTranslateY(specimen3DView.camera.getTranslateY() - mouseDeltaY*UICommon.MOUSE_SPEED*UICommon.TRACK_SPEED);
                    }

                }
        );

        subScene.setOnScroll(event -> {
            double z = specimen3DView.camera.getTranslateZ();
            double newZ = z + event.getDeltaY()*0.5;
            specimen3DView.camera.setTranslateZ(newZ);
        });
    }


}
