package databases;

public class DBCommon {

    //Specimens DataBase Columns
    public static final String SPECIMENS = "Specimens";
    public static final String CELL_NUMBER_X = "CellNumberX";
    public static final String CELL_NUMBER_Y = "CellNumberY";
    public static final String CELL_NUMBER_Z = "CellNumberZ";
    public static final String CELL_SIZE = "CellSize";
    public static final String NUMBER_OF_GRAINS = "NumberOfGrains";
    public static final String ANGLE_RANGE = "AngleRange";
    public static final String TYPE_OF_GRAIN_DISTRIB = "TypeOfGrainDistribution";
    public static final String MATERIAL = "Material";
    public static final String INITIAL_CONDITION = "InitialCondition";
    public static final String BOUNDARY_CONDITION = "BoundaryCondition";
    public static final String TASK = "Task";

    // Boundary Conditions DataBase Columns
    public static final String FACET = "Facet";
    public static final String TEMPERATURE_AVERAGE = "TemparatureAverage";
    public static final String TEMPERATURE_DEVIATION = "TemperatureDeviation";
    public static final String TEMPERATURE_LOADING_TIME = "TemperatureLoadingTime";
    public static final String ELASTIC_ENERGY_AVERAGE = "ElasticEnergyAverage";
    public static final String ELASTIC_ENERGY_DEVIATION = "ElasticEnergyDeviation";
    public static final String ELASTIC_ENERGY_LOADING_TIME = "ElasticEnergyLoadingTime";
    public static final String DISLOCATION_DENSITY_AVERAGE = "DislocationDensityAverage";
    public static final String DISLOCATION_DENSITY_DEVIATION = "DislocationDensityDeviation";
    public static final String DISLOCATION_DENSITY_LOADING_TIME = "DislocationDensityLoadingTime";
    public static final String MOMENTS_X_AVERAGE = "MomentsXAverage";
    public static final String MOMENTS_X_DEVIATION = "MomentsXDeviation";
    public static final String MOMENTS_X_LOADING_TIME = "MomentsXLoadingTime";
    public static final String MOMENTS_Y_AVERAGE = "MomentsYAverage";
    public static final String MOMENTS_Y_DEVIATION = "MomentsYDeviation";
    public static final String MOMENTS_Y_LOADING_TIME = "MomentsYLoadingTime";
    public static final String MOMENTS_Z_AVERAGE = "MomentsZAverage";
    public static final String MOMENTS_Z_DEVIATION = "MomentsZDeviation";
    public static final String MOMENTS_Z_LOADING_TIME = "MomentsZLoadingTime";

    // Results DataBase Columns
    public static final String GRAIN_INDEX = "GrainIndex";
    public static final String LOCATION_TYPE = "LocationType";
    public static final String COORDINATE_X = "CoordinateX";
    public static final String COORDINATE_Y = "CoordinateY";
    public static final String COORDINATE_Z = "CoordinateZ";
    public static final String TEMPERATURE = "Temperature";
    public static final String ELASTIC_ENERGY = "ElasticEnergy";
    public static final String DISLOCATION_DENSITY = "DislocationDensity";
    public static final String MOMENTS_X = "MomentsX";
    public static final String MOMENTS_Y = "MomentsY";
    public static final String MOMENTS_Z = "MomentsZ";


    // Material Data Base Columns
    public static final String MATERIALS = "Materials";
    public static final String NAME = "Name";
    public static final String HEAT_CONDUCTIVITY = "HeatConductivity";
    public static final String DENSITY = "Density";
    public static final String HEAT_EXPANSION = "HeatExpansion";
    public static final String HEAT_CAPACITY = "HeatCapacity";
    public static final String PHONON_PORTION = "PhononPortion";
    public static final String ANGLE_LIMIT_HAGB = "AngleLimitHAGB";
    public static final String ENERGY_HAGB = "EnergyHAGB";
    public static final String MAX_MOBILITY = "MaxMobility";
    public static final String LATTICE_PARAMETER = "LatticeParameter";
    public static final String YOUNG_MODULUS = "YoundModulus";
    public static final String SHEAR_MODULUS = "ShearModulus";
    public static final String MOLAR_MASS = "MolarMass";
    public static final String ELAST_MODULUS = "ElastModulus";
    public static final String DISL_MAX_MOBILITY = "DislMaxMobility";
    public static final String MECH_MAX_MOBILITY = "MechMaxMobility";
    public static final String YIELD_STRAIN = "YieldStrain";
    public static final String ULTIMATE_STRAIN = "UltimateStrain";
    public static final String ENERGY_COEFF = "EnergyCoeff";
    public static final String THERMAL_CONDUCT_BOUND = "ThermalConductBound";
    public static final String ACT_ENERGY = "ActEnergy";
    public static final String DISL_DIST_COEFF = "DislDistCoeff";
    public static final String YIELD_STATE_COEFF = "YieldStateCoeff";
    public static final String ULTIMATE_STATE_COEFF = "UltimateStateCoeff";
    public static final String PART_VOL_FRACTION = "PartVolFraction";
    public static final String PART_RADIUS = "PartRadius";
    public static final String THRESHOLD_STRESS = "ThresholdStress";
    public static final String TORSION_ENERGY_COEFF = "TorsionEnergyCoeff";
    public static final String TORSION_ENERGY_COEFF_GB1 = "TorsionEnergyCoeffGB1";
    public static final String TORSION_ENERGY_COEFF_GB2 = "TorsionEnergyCoeffGB2";
    public static final String LOW_TEMPER_THR_VALUE = "LowTemperThrValue";
    public static final String HIGH_TEMPER_THR_VALUE = "HighTemperThrValue";
    public static final String MIN_TWIN_TEMPERATURE = "MinTwinTemperature";
    public static final String TWINNING_TEMPERATURE = "TwinningTemperature";
    public static final String LATTICE_VECTOR_A_LENGTH = "LatticeVectorALength";
    public static final String LATTICE_VECTOR_B_LENGTH = "LatticeVectorBLength";
    public static final String LATTICE_VECTOR_C_LENGTH = "LatticeVectorCLength";
    public static final String LATTICE_ANGLE_VEC_A_VEC_B = "LatticeAngle_VecA_VecB";
    public static final String LATTICE_ANGLE_VEC_B_VEC_C = "LatticeAngle_VecB_VecC";
    public static final String LATTICE_ANGLE_VEC_C_VEC_A = "LatticeAngle_VecC_VecA";
    public static final String LATTICE_ANIS_COEFF = "LatticeAnisCoeff";
    public static final String MAX_PROB_RECRYST = "MaxProbRecryst";
    public static final String MAX_PROB_TWINNING = "MaxPropTwinning";
    public static final String MIN_DISL_DENSITY = "MinDislDensity";

    //Tasks DataBase Columns
    public static final String TIME_STEP = "TimeStep";
    public static final String TOTAL_TIME = "TotalTime";

    // DataBase Error and Notifications Terminal Messages
    public static final String FAILED_TO_CONNECT = "Failed to establish connections!";
    public static final String FAILED_TO_CREATE_SPECIMENS_TABLE = "Failed to create Specimens Table";
    public static final String SPECIMENS_TABLE_IS_CREATED = "Specimens Table Is Created or Already Exists";
    public static final String FAILED_TO_RETRIEVE_SPECIMENS_DATA = "Failed to retrieve Specimens Data from DataBase!";
    public static final String FAILED_TO_ADD_SPECIMEN_DATA = "Failed to add new specimen data to DataBase";
    public static final String NEW_SPECIMEN_DATA_IS_ADDED = "New Specimen Data is added to DataBase";
    public static final String FAILED_TO_EDIT_SPECIMEN_DATA = "Failed to edit specimen data in DataBase";
    public static final String SPECIMEN_DATA_IS_EDITED = "Specimen data has been successfully changed";
    public static final String FAILED_TO_DELETE_SPECIMEN_DATA = "Failed to delete specimen data from DataBase";
    public static final String SPECIMEN_IS_DELETED = "Specimen is deleted from DataBase";

    public static final String FAILED_TO_RETRIEVE_MATERIALS_DATA = "Failed to retrieve Material Data from DataBase";
    public static final String FAILED_TO_CREATE_MATERIALS_TABLE = "Failed to create Materials DataBase";
    public static final String MATERIALS_TABLE_IS_CREATED = "Materials Table is created or already exists";
    public static final String FAILED_TO_ADD_NEW_MATERIAL = "Failed to add new Material Data to DataBase";
    public static final String NEW_MATERIAL_DATA_IS_ADDED = "New Material Data is added to DataBase";
    public static final String FAILED_TO_DELETE_MATERIAL = "Failed to delete material fron DataBase";
    public static final String MATERIAL_IS_DELETED = "Material is successfully deleted from DataBase";
    public static final String FAILED_TO_EDIT_MATERIAL = "Failed to edit Material Data";
    public static final String MATERIAL_DATA_IS_EDITED = "Material Data has been successfully changed!";
    public static final String FAILED_TO_CHECK_MATERIAL_NAME = "Failed to check material name";

    public static final String FAILED_TO_CREATE_TASKS_TABLE = "Failed to create Tasks Table";
    public static final String TASKS_TABLE_IS_CREATED = "Tasks Table is created or already exists";
    public static final String FAILED_TO_RETRIEVE_TASK_DATA = "Failed to retrieve Task Data from DataBase";
    public static final String NEW_TASK_IS_ADDED = "New Task Data is added to DataBase";
    public static final String TASK_IS_DELETED = "Task Data is successfully deleted from Database";
    public static final String TASK_IS_EDITED = "Task Data is successfully changed";
    public static final String FAILED_TO_ADD_NEW_TASK = "Failed to add new Task Data";
    public static final String FAILED_TO_UPDATE_TASK_DATA = "Failed to update Task Data";
    public static final String FAILED_TO_DELETE_TASK_DATA = "Failed to delete Task Data";

    public static final String INITIAL_COND_TABLE_IS_CREATED = "Initial Conditions Table is created";
    public static final String FAILED_TO_RETRIEVE_INITIAL_COND_DATA = "Failed to retrieve Initial Condition data from Database";
    public static final String FAILED_TO_ADD_NEW_INIT_COND = "Failed to add new Initial Condition data to Database";
    public static final String FAILED_TO_UPDATE_INIT_COND_DATA = "Failed to update Initial Condition data";
    public static final String INIT_COND_DATA_IS_UPDATED = "Initial Condition data is updated";
    public static final String INIT_COND_DATA_IS_DELETED = "Initial Condition data is deleted from Database";
    public static final String FAILED_TO_DELETE_INIT_COND_DATA = "Failed to delete Initial Condition data";

    public static final String FAILED_TO_CREATE_BOUND_COND_TABLE = "Failed to create Boundary Condition table";
    public static final String FAILED_TO_RETRIEVE_BOUND_COND_DATA = "Failed to retrieve Boundary Condition Data from Database";
    public static final String FAILED_TO_ADD_NEW_BOUND_COND = "Failed to add new Boundary Condition Data to Database";
    public static final String FAILED_TO_UPDATE_BOUND_COND_DATA = "Failed to update Boundary Condition Data";
    public static final String FAILED_TO_DELETE_BOUND_COND_DATA = "Failed to delete Boundary Condition Data";


    public static final String STRUCTURE_DATA = "StructureData";
    public static final String FAILED_TO_RETRIEVE_STRUCTURE_DATA = "Failed to retrieve Structure Data from Database";
    public static final String FAILED_TO_RETRIEVE_TIME_STEPS = "Failed to retrieve Time Steps from DataBase";
    public static final String FAILED_TO_RETRIEVE_RESULT = "Failed to retrieve Result Data";

    public static final String AVERAGE_TEMPERATURE = "Temperature";
    public static final String AVERAGE_ELASTIC_ENERGY = "ElasticEnergy";
    public static final String AVERAGE_DISLOCATION_DENSITY = "DislocationDensity";
    public static final String AVERAGE_MOMENTS_X = "MomentsX";
    public static final String AVERAGE_MOMENTS_Y = "MomentsY";
    public static final String AVERAGE_MOMENTS_Z = "MomentsZ";

    public static final String PLOT_DATA = "PlotsData";
    public static final String  FAILED_TO_DELETE_RESULTS = "Failed to delete Result";
}
