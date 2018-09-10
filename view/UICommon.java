package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import utils.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UICommon {

    public static final double CAMERA_INITIAL_DISTANCE = -200;

    public static final double CAMERA_INITIAL_X_ANGLE = 90.0;
    public static final double CAMERA_INITIAL_Y_ANGLE = 0.0;

    public static final double CAMERA_NEAR_CLIP = 0.1;
    public static final double CAMERA_FAR_CLIP = 10000.0;

    public static final double CONTROL_MULTIPLIER = 0.1;

    public static final double SHIFT_MULTIPLIER = 5.0;

    public static final double MOUSE_SPEED = 0.1;
    public static final double ROTATION_SPEED = 2.0;
    public static final double TRACK_SPEED = 0.3;

    public static final String CREATE_NEW = "Create New ...";
    public static final String EDIT = "Edit";
    public static final String ADD = "Add";
    public static final String DELETE = "Delete";
    public static final String OK = "OK";
    public static final String SAVE = "Save";
    public static final String CANCEL = "Cancel";
    public static final String STRUCTURE = "Structure";
    public static final String SHOW_RESULTS = "Show Results";
    public static final String SHOW = "SHOW";

    public static final String MAX_VALUE = "MAX:";
    public static final String MIN_VALUE = "MIN:";
    public static final String REVERSE = "Reverse";
    public static final String SCALE = "Scale";
    public static final String RAINBOW = "Rainbow";
    public static final String GRAY = "Gray";
    public static final String REDBLUE = "Red/Blue";

    public static final String OPEN_IN_NEW_TAB = "Show in the New Tab";

    public static final String CHOOSE_DIRECTORY = "...";
    public static final String TIME_AXIS = "Time Axis";
    public static final String VALUE_AXIS = "Value Axis";
    public static final String SHOW_GRID = "Show Grid";
    public static final String SHOW_LEGEND = "Show Legend";
    public static final String TICK_UNIT = "Tick Unit";
    public static final String LOWER_BOUND = "Lower Bound";
    public static final String UPPER_BOUND = "Upper Bound";
    public static final String TIME_AXIS_TITLE = "Time Axis Title";
    public static final String VALUE_AXIS_TITLE = "Value Axis Title";

    public static final String SELECT_PLANE_TYPE = "Select Plane Type";
    public static final String SELECT_AXIS = "Select Axis";
    public static final String TYPE_LAYER_NUMBER = "Type Layer Number";

    public static final String SIMPLE_PLANE = "Simple";
    public static final String SPECIAL_PLANE = "Special";

    public static final String TEMPERATURE = "Temperature";
    public static final String ELASTIC_ENERGY = "Elastic Energy";
    public static final String DISLOCATION_DENSITY = "Dislocation Density";
    public static final String MOMENTS_X = "Moments X";
    public static final String MOMENTS_Y = "Moments Y";
    public static final String MOMENTS_Z = "Moments Z";

    public static final String RECOMMENDED_SCALE = "Set Recommended Scale";
    public static final String SHOW_INFO = "Show Info";

    public static final int SCALE_IMAGE_WIDTH = 50;
    public static final int SCALE_IMAGE_HEIGHT = 150;

    /**
     * Specimen Data Editor Label Values
     */
    public static final String SPECIMEN_DATA_EDITOR = "Specimen - Data Editor";
    public static final String SPECIMEN_NAME = "Specimen Name";
    public static final String CELL_NUMBER_X = "Cell Number X";
    public static final String CELL_NUMBER_Y = "Cell Number Y";
    public static final String CELL_NUMBER_Z = "Cell Number Z";
    public static final String CELL_SIZE = "Cell Size";
    public static final String NUMBER_OF_GRAINS = "Number Of Grains";
    public static final String ANGLE_RANGE = "Angle Range";
    public static final String SELECT_INITIAL_CONDITIONS = "Select Initial Conditions";
    public static final String SELECT_BOUNDARY_CONDITIONS = "Select Boundary Conditions";
    public static final String SELECT_TASK = "Select Task";
    public static final String SELECT_MATERIAL = "Select Material";
    public static final String STOCHASTIC = "Stochastic";
    public static final String FIXED = "Fixed";

    public static final String INPUT_ANOTHER_NAME = "Input another name";

    /**
     * Material Data Editor Label Values
     */
    public static final String MATERIAL_DATA_EDITOR = "Material - Data Editor";
    public static final String MATERIAL_NAME = "Material Name";
    public static final String DENSITY = "Density";
    public static final String HEAT_EXPANSION = "Heat Expansion";
    public static final String PHONON_PORTION = "Phonon Portion";
    public static final String HEAT_CAPACITY = "Heat Capacity";
    public static final String HEAT_CONDUCTIVITY = "Heat Conductivity";
    public static final String ANGLE_LIMIT_HAGB = "Angle Limit HAGB";
    public static final String ENERGY_HAGB = "Energy HAGB";
    public static final String MAXIMAL_MOBILITY = "Maximal Mobility";
    public static final String LATTICE_PARAMETER = "Lattice Parameter";
    public static final String YOUNG_MODULUS = "Young Modulus";
    public static final String SHEAR_MODULUS = "Shear Modulus";
    public static final String MOLAR_MASS = "Molar Mass";
    public static final String ELAST_MODULUS = "Elasticity Modulus";
    public static final String DISL_MAX_MOBILITY = "Dislocation Max. Mobility";
    public static final String MECH_MAX_MOBILITY = "Mechanical Max. Mobility";
    public static final String YIELD_STRAIN = "Yield Strain";
    public static final String ULTIMATE_STRAIN = "Ultimate Strain";
    public static final String ENERGY_COEFF = "Energy Coefficient";
    public static final String THERMAL_CONDUCT_BOUND = "Thermal Boundary Conductivity";
    public static final String ACT_ENERGY = "Activation Energy";
    public static final String DISL_DIST_COEFF = "Dislocation Distortion Coefficient";
    public static final String YIELD_STATE_COEFF = "Yield State Coefficient";
    public static final String ULTIMATE_STATE_COEFF = "Ultimate State Coefficient";
    public static final String PART_VOL_FRACTION = "Partical Volume Fraction";
    public static final String PART_RADIUS = "Partical Radius";
    public static final String THRESHOLD_STRESS = "ThresholdS tress";
    public static final String TORSION_ENERGY_COEFF = "Torsion Energy Coefficient";
    public static final String TORSION_ENERGY_COEFF_GB1 = "Torsion Energy CoeffGB1";
    public static final String TORSION_ENERGY_COEFF_GB2 = "Torsion Energy CoeffGB2";
    public static final String LOW_TEMPER_THR_VALUE = "Low Temperature ThrValue";
    public static final String HIGH_TEMPER_THR_VALUE = "High Temperature ThrValue";
    public static final String MIN_TWIN_TEMPERATURE = "Min. Twinning Temperature";
    public static final String TWINNING_TEMPERATURE = "Twinning Temperature";
    public static final String LATTICE_VECTOR_A_LENGTH = "Lattice Vector A Length";
    public static final String LATTICE_VECTOR_B_LENGTH = "Lattice Vector B Length";
    public static final String LATTICE_VECTOR_C_LENGTH = "Lattice Vector C Length";
    public static final String LATTICE_ANGLE_VEC_A_VEC_B = "Lattice Angle VecA_VecB";
    public static final String LATTICE_ANGLE_VEC_B_VEC_C = "Lattice Angle VecB_VecC";
    public static final String LATTICE_ANGLE_VEC_C_VEC_A = "Lattic eAngle VecC_VecA";
    public static final String LATTICE_ANIS_COEFF = "Lattice Anisotropy Coefficient";
    public static final String MAX_PROB_RECRYST = "Max. Recrystallization Probability";
    public static final String MAX_PROB_TWINNING = "Max. Probality of Twinning";
    public static final String MIN_DISL_DENSITY = "Min. Dislocation Density";

    /**
     * Boundary Conditions Data Editor
     */
    public static final String BOUNDARY_CONDITION_DATA_EDITOR = "Boundary Condition - Data Editor";
    public static final String TOP = "Top";
    public static final String BOTTOM = "Bottom";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";
    public static final String FRONT = "Front";
    public static final String BACK = "Back";

    /**
     * Task Conditions Data Editor
     */
    public static final String TASK_DATA_EDITOR = "Task - Data Editor";
    public static final String NAME = "Name";
    public static final String TIME_STEP = "Time Step";
    public static final String TOTAL_TIME = "Total Time";


    /**
     * Notifications
     */
    public static final String NO_RESULTS = "There are no results!";
    public static final String NOTHING_IS_CHOSEN = "Nothing is chosen!";
    public static final String INCORRECT_DATA_INPUT = "Incorrect Data Input!";
    public static final String REFRESH = "Refresh";
    public static final String RECRYSTALLIZATION = "Recrystallization";

    public static boolean confirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?");
        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get() == ButtonType.OK){
            return true;
        }
        else
            return false;
    }

    public static List<Color> generateRandomColors(List<Element> listOfElements){

        List<Integer> grainIndices = new ArrayList<>();
        for (Element element : listOfElements){
            grainIndices.add(element.getGrainIndex());
        }
        System.out.println(grainIndices);
        int numberOfGrains = Collections.max(grainIndices) + 1;

        List<Color> listOfColors = new ArrayList<Color>();
        for (int i=0 ; i < numberOfGrains ; i++){
            listOfColors.add(Color.color(Math.random(), Math.random(), Math.random()));
        }
        return listOfColors;
    }

}
