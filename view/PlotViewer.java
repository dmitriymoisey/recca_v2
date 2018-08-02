package view;

import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlotViewer extends Stage {

    private Scene scene;
    private BorderPane root;

    private PlotViewerToolBar toolBar;

    public PlotViewer(){
        root = new BorderPane();
        scene = new Scene(root);

        initAllComponents();
        addAllComponents();
        handleEvents();

        this.setTitle("PlotView - DEMO");
        this.setScene(scene);
        this.show();
    }

    private void initAllComponents(){
        toolBar = new PlotViewerToolBar();
    }

    private void addAllComponents(){
        root.setRight(toolBar);
    }

    private void handleEvents(){

    }

}
