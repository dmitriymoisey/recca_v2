package view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

public class ScaleViewer extends GridPane {

    private SimpleDoubleProperty maxValueProp, minValueProp;

    public TextField maxValueTextField, minValueTextField;

    private Label firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel;

    public ComboBox<String> scaleComboBox;
    private ObservableList scaleTypeList = FXCollections.observableArrayList(UICommon.RAINBOW,
            UICommon.GRAY, UICommon.REDBLUE);
    public CheckBox reverseScaleCheckBox;

    public ScaleViewer() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgap(3.0);
        this.setVgap(3.0);

        initAllComponents();
        addAllComponents();
        initAllProperties();
        bindAllValues();
    }

    private void initAllComponents() {
        maxValueTextField = new TextField();
        maxValueTextField.setMaxWidth(70);
        minValueTextField = new TextField();
        minValueTextField.setMaxWidth(70);

        firstLabel = new Label();
        secondLabel = new Label();
        thirdLabel = new Label();
        fourthLabel = new Label();
        fifthLabel = new Label();

        scaleComboBox = new ComboBox();
        scaleComboBox.getItems().addAll(this.scaleTypeList);
        scaleComboBox.getSelectionModel().selectFirst();

        reverseScaleCheckBox = new CheckBox(UICommon.REVERSE);
    }

    private void addAllComponents() {
        Label maxLabel = new Label(UICommon.MAX_VALUE);
        Label minLabel = new Label(UICommon.MIN_VALUE);

        Separator s1 = new Separator();
        Separator s2 = new Separator();
        Separator s3 = new Separator();

        GridPane.setConstraints(maxLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(maxValueTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(minLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(minValueTextField, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(s1, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(scaleComboBox, 0, 3, 2, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(reverseScaleCheckBox, 0, 4, 2, 1, HPos.LEFT, VPos.CENTER);

        GridPane.setConstraints(s2, 0, 5, 2, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(firstLabel, 3, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(secondLabel, 3, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(thirdLabel, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fourthLabel, 3, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(fifthLabel, 3, 4, 1, 1, HPos.CENTER, VPos.CENTER);

        this.getChildren().addAll(
                maxLabel, minLabel, maxValueTextField, minValueTextField,
                s1, s2, scaleComboBox, reverseScaleCheckBox,
                firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel
        );
    }

    private void initAllProperties() {
        maxValueProp = new SimpleDoubleProperty(0.0);
        minValueProp = new SimpleDoubleProperty(0.0);
    }

    private void bindAllValues() {
        StringConverter stringConverter = new DoubleStringConverter();

        maxValueTextField.textProperty().bindBidirectional(maxValueProp, stringConverter);
        minValueTextField.textProperty().bindBidirectional(minValueProp, stringConverter);
    }

    public void setMaxValueProp(double valueProp) {
        this.maxValueProp.set(valueProp);
    }

    public double getMaxValue(){
        return maxValueProp.get();
    }

    public void setMinValueProp(double valueProp) {
        this.minValueProp.set(valueProp);
    }

    public double getMinValue(){
        return minValueProp.get();
    }

    public String getScaleType(){
        return scaleComboBox.getSelectionModel().getSelectedItem();
    }

    public boolean getIsReverse() {
        return reverseScaleCheckBox.isSelected();
    }

    private ImageView imageView;

    public void setScaleImage(WritableImage writableImage){
        if (this.getChildren().contains(imageView)){
            this.getChildren().remove(imageView);
        }

        imageView = new ImageView(writableImage);
        GridPane.setConstraints(imageView, 2, 0, 1, 5, HPos.CENTER, VPos.CENTER);
        this.getChildren().add(imageView);
        setTickLabels();
    }

    private void setTickLabels(){
        clearLabels();

        double tickValue = (maxValueProp.get() - minValueProp.get())/4.0;

        String firstLabelText = String.format( "%.2f", maxValueProp.get());
        String secondLabelText = String.format("%.2f", maxValueProp.get() - tickValue);
        String thirdLabelText = String.format("%.2f", minValueProp.get() + 2 * tickValue);
        String fourthLabelText = String.format("%.2f", minValueProp.get() + tickValue);
        String fifthLabelText = String.format("%.2f", minValueProp.get());

        firstLabel.setText(firstLabelText);
        secondLabel.setText(secondLabelText);
        thirdLabel.setText(thirdLabelText);
        fourthLabel.setText(fourthLabelText);
        fifthLabel.setText(fifthLabelText);
    }

    private void clearLabels(){
        this.firstLabel.setText("");
        this.secondLabel.setText("");
        this.thirdLabel.setText("");
        this.fourthLabel.setText("");
        this.fifthLabel.setText("");
    }

}