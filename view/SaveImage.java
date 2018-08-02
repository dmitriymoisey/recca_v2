package view;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SaveImage extends Stage {

    private Scene scene;
    private BorderPane root;

    private Button save, cancel, chooseDirectory;
    private TextField fileNameTextField, fileDirectoryTextField;

    private ImageView imageView;

    private double screenWidth, screenHeight;

    public SaveImage(ImageView imageView){

        this.screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        this.screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        this.imageView = imageView;
        this.imageView.setFitHeight(screenHeight * 0.4);
        this.imageView.setFitWidth(screenWidth * 0.4);

        root = new BorderPane();
        scene = new Scene(root);

        initAllComponents();
        addAllComponents();
        handleEvents();

        this.setTitle("Save Image");
        this.setScene(scene);
        this.show();
    }

    private void initAllComponents(){

        fileNameTextField = new TextField();
        fileNameTextField.setPromptText("Type File Name");
        fileDirectoryTextField = new TextField();
        fileDirectoryTextField.setPromptText("Type File Directory");

        save = new Button(UICommon.SAVE);
        save.setPadding(new Insets(7, 15, 5, 15));
        cancel = new Button(UICommon.CANCEL);
        cancel.setPadding(new Insets(7, 15, 5, 15));
        chooseDirectory = new Button(UICommon.CHOOSE_DIRECTORY);
        chooseDirectory.setPadding(new Insets(7,15,7,15));

    }

    private void addAllComponents(){
        Label fileNameLabel = new Label("File Name :");
        Label fileDirectoryLabel = new Label("File Directory :");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        GridPane.setConstraints(fileNameLabel, 0, 0,1,1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(fileNameTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fileDirectoryLabel, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fileDirectoryTextField, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(chooseDirectory, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        gridPane.getChildren().addAll(
            fileNameLabel, fileDirectoryLabel,
            fileNameTextField, fileDirectoryTextField, chooseDirectory
        );

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(imageView, gridPane);
        vBox.setPadding(new Insets(10));

        root.setCenter(vBox);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(
                save, cancel
        );
        hBox.setPadding(new Insets(10));
        root.setBottom(hBox);
    }

    private void handleEvents(){

        save.setOnAction(e -> {
            System.out.println("Action Event : save button is pushed");

            if (fileNameTextField.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Type file name!").show();
            }
            else if (fileDirectoryTextField.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Choose file directory").show();
            }
            else {
                String fileName = fileNameTextField.getText();
                String fileDirectory = fileDirectoryTextField.getText();

                WritableImage image = imageView.snapshot(new SnapshotParameters(), null);

                File file = new File(fileDirectory + "/" + fileName + ".png");
                try{
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                    new Alert(Alert.AlertType.INFORMATION, "Image is saved!").show();
                    this.close();
                }
                catch (IOException ex){
                    System.out.println(ex.getMessage());
                    new Alert(Alert.AlertType.ERROR, "Failed to save image!").show();
                }
            }
        });

        cancel.setOnAction(e -> this.close());

        chooseDirectory.setOnAction(e -> {
            System.out.println("Action Event : choose directory button is pushed");
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(this);
            try{
                this.fileDirectoryTextField.setText(file.getAbsolutePath());
            }
            catch (NullPointerException ex){
                System.out.println(ex.getMessage());
            }

        });
    }

}
