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
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

public class ScaleViewer extends GridPane {

    private SimpleDoubleProperty maxValueProp, minValueProp;

    private SimpleStringProperty firstLabelProp, secondLabelProp,
            thirdLabelProp, fourthLabelProp, fifthLabelProp;

    private TextField maxValueTextField, minValueTextField;

    private Label firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel;

    private ComboBox scaleComboBox;
    private ObservableList scaleTypeList = FXCollections.observableArrayList("Rainbow", "Grey", "Red/Blue");
    private CheckBox reverseScaleCheckBox;

    private Button showThis;

    public ScaleViewer() {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setHgap(5.0);
        this.setVgap(5.0);

        initAllComponents();
        addAllComponents();
        initAllProperties();
        bindAllValues();
    }

    private void initAllComponents() {
        maxValueTextField = new TextField();
        minValueTextField = new TextField();

        firstLabel = new Label();
        secondLabel = new Label();
        thirdLabel = new Label();
        fourthLabel = new Label();
        fifthLabel = new Label();

        scaleComboBox = new ComboBox();
        scaleComboBox.getItems().addAll(this.scaleTypeList);

        reverseScaleCheckBox = new CheckBox(UICommon.REVERSE);

        showThis = new Button(UICommon.SHOW_THIS);
        showThis.setPadding(new Insets(7, 15, 7, 15));
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

        GridPane.setConstraints(showThis, 0, 6, 2, 1, HPos.CENTER, VPos.CENTER);

        this.getChildren().addAll(
                maxLabel, minLabel, maxValueTextField, minValueTextField,
                s1, s2, scaleComboBox, reverseScaleCheckBox, showThis
        );
    }

    private void initAllProperties() {
        maxValueProp = new SimpleDoubleProperty(0.0);
        minValueProp = new SimpleDoubleProperty(0.0);

        fifthLabelProp = new SimpleStringProperty();
        secondLabelProp = new SimpleStringProperty();
        thirdLabelProp = new SimpleStringProperty();
        fourthLabelProp = new SimpleStringProperty();
        fifthLabelProp = new SimpleStringProperty();
    }

    private void bindAllValues() {
        StringConverter stringConverter = new DoubleStringConverter();

        maxValueTextField.textProperty().bindBidirectional(maxValueProp, stringConverter);
        minValueTextField.textProperty().bindBidirectional(minValueProp, stringConverter);

    }

    public void setMaxValueProp(double valueProp) {
        this.maxValueProp.set(valueProp);
    }

    public void setMinValueProp(double valueProp) {
        this.minValueProp.set(valueProp);
    }

}