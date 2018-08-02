package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PlotViewerToolBar extends ToolBar {

    private VBox vBox;

    private CheckBox showGridCheckBox, showLegendCheckBox;

    private TitledPane valueAxisTitledPane, timeAxisTitledPane;

    private TextField timeAxisTickUnitTextField, timeAxisLowerBoundTextField, timeAxisUpperBoundTextField,
            valueAxisTickUnitTextField, valueAxisLowerBoundTextField, valueAxisUpperBoundTextField;

    private TextField timeAxisTitleTextField, valueAxisTitleTextField;

    private ComboBox valuesComboBox;
    private ObservableList valuesList = FXCollections.observableArrayList(
            "Temperature", "Elastic Energy",
            "Dislocation Density", "Moment X", "Moment Y", "Moment Z"
    );
    private Button showButton;

    public PlotViewerToolBar(){
        initAllComponents();
        addAllComponents();
        handleEvents();
    }

    private void initAllComponents(){
        vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);

        valuesComboBox = new ComboBox();
        valuesComboBox.getItems().addAll(valuesList);
        valuesComboBox.getSelectionModel().selectFirst();

        showGridCheckBox = new CheckBox(UICommon.SHOW_GRID);
        showLegendCheckBox = new CheckBox(UICommon.SHOW_LEGEND);

        valueAxisTitledPane = new TitledPane();
        valueAxisTitledPane.setText(UICommon.VALUE_AXIS);

        timeAxisTitledPane = new TitledPane();
        timeAxisTitledPane.setText(UICommon.TIME_AXIS);

        timeAxisTickUnitTextField = new TextField();
        timeAxisLowerBoundTextField = new TextField();
        timeAxisUpperBoundTextField = new TextField();

        valueAxisTickUnitTextField = new TextField();
        valueAxisLowerBoundTextField = new TextField();
        valueAxisUpperBoundTextField = new TextField();

        timeAxisTitleTextField = new TextField();
        valueAxisTitleTextField = new TextField();

        showButton = new Button(UICommon.SHOW);
        showButton.setPadding(new Insets(7, 15, 7, 15));
    }

    private void addAllComponents(){

        GridPane axisTitlesLayout = new GridPane();
        axisTitlesLayout.setAlignment(Pos.CENTER);
        axisTitlesLayout.setPadding(new Insets(5));
        axisTitlesLayout.setVgap(5);
        axisTitlesLayout.setHgap(5);

        Label timeAxisTitleLabel = new Label(UICommon.TIME_AXIS_TITLE);
        Label valueAxisTitleLabel = new Label(UICommon.VALUE_AXIS_TITLE);

        GridPane.setConstraints(timeAxisTitleLabel, 0,0,1,1,HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(timeAxisTitleTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(valueAxisTitleLabel, 0,1,1,1,HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(valueAxisTitleTextField, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        axisTitlesLayout.getChildren().addAll(
                timeAxisTitleLabel, timeAxisTitleTextField,
                valueAxisTitleLabel, valueAxisTitleTextField
        );

        GridPane timeAxisTitledPaneLayout = new GridPane();
        timeAxisTitledPaneLayout.setAlignment(Pos.CENTER);
        timeAxisTitledPaneLayout.setPadding(new Insets(5));
        timeAxisTitledPaneLayout.setVgap(5);
        timeAxisTitledPaneLayout.setHgap(5);

        Label timeAxisTickUnitLabel = new Label(UICommon.TICK_UNIT);
        Label timeAxisLowerBoundLabel = new Label(UICommon.LOWER_BOUND);
        Label timeAxisUpperBoundLabel = new Label(UICommon.UPPER_BOUND);

        GridPane.setConstraints(timeAxisTickUnitLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(timeAxisTickUnitTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(timeAxisLowerBoundLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(timeAxisLowerBoundTextField, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(timeAxisUpperBoundLabel, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(timeAxisUpperBoundTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        timeAxisTitledPaneLayout.getChildren().addAll(
                timeAxisTickUnitLabel, timeAxisTickUnitTextField,
                timeAxisLowerBoundLabel, timeAxisLowerBoundTextField,
                timeAxisUpperBoundLabel, timeAxisUpperBoundTextField
        );
        timeAxisTitledPane.setContent(timeAxisTitledPaneLayout);


        GridPane valueAxisTitledPaneLayout = new GridPane();
        valueAxisTitledPaneLayout.setAlignment(Pos.CENTER);
        valueAxisTitledPaneLayout.setPadding(new Insets(5));
        valueAxisTitledPaneLayout.setVgap(5);
        valueAxisTitledPaneLayout.setHgap(5);

        Label valueAxisTickUnitLabel = new Label(UICommon.TICK_UNIT);
        Label valueAxisLowerBoundLabel = new Label(UICommon.LOWER_BOUND);
        Label valueAxisUpperBoundLabel = new Label(UICommon.UPPER_BOUND);

        GridPane.setConstraints(valueAxisTickUnitLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(valueAxisTickUnitTextField, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(valueAxisLowerBoundLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(valueAxisLowerBoundTextField, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(valueAxisUpperBoundLabel, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(valueAxisUpperBoundTextField, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        valueAxisTitledPaneLayout.getChildren().addAll(
                valueAxisTickUnitLabel, valueAxisTickUnitTextField,
                valueAxisLowerBoundLabel, valueAxisLowerBoundTextField,
                valueAxisUpperBoundLabel, valueAxisUpperBoundTextField
        );
        valueAxisTitledPane.setContent(valueAxisTitledPaneLayout);

        vBox.getChildren().addAll(
                valuesComboBox, axisTitlesLayout,
                showGridCheckBox, showLegendCheckBox,
                timeAxisTitledPane, valueAxisTitledPane,
                showButton
        );
        this.setOrientation(Orientation.VERTICAL);
        this.getItems().add(vBox);
    }

    private void handleEvents(){
        showButton.setOnAction(e -> {
            System.out.println("Action Event : show button is pushed");
        });
    }

}
