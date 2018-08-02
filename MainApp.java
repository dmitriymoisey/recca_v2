import databases.DataBaseUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MainTable;

public class MainApp extends Application {

    public void start(Stage primaryStage) throws Exception {

        DataBaseUtils.connect();
        DataBaseUtils.createDB();

        MainTable mainLayout = new MainTable();

        Scene scene = new Scene(mainLayout);

        primaryStage = new Stage();
        primaryStage.setTitle("RecCA - version 2");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(event.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST){
                    DataBaseUtils.closeDB();
                }
            }
        });
    }
}
