package databases;

import utils.MaterialData;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialDataBase {

    /*
    SQL Queries
    */
    public static final String CREATE_TABLE_QUERY = String.format("CREATE TABLE IF NOT EXISTS " +
            "%s (%s TEXT, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL," +
                    "%s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL," +
                    "%s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL," +
                    "%s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL)",
            DBCommon.MATERIALS, DBCommon.NAME, DBCommon.HEAT_CONDUCTIVITY, DBCommon.DENSITY,
            DBCommon.HEAT_EXPANSION, DBCommon.HEAT_CAPACITY, DBCommon.PHONON_PORTION,
            DBCommon.ANGLE_LIMIT_HAGB, DBCommon.ENERGY_HAGB, DBCommon.MAX_MOBILITY, DBCommon.LATTICE_PARAMETER,
            DBCommon.YOUNG_MODULUS, DBCommon.SHEAR_MODULUS, DBCommon.MOLAR_MASS, DBCommon.ELAST_MODULUS,
            DBCommon.DISL_MAX_MOBILITY, DBCommon.MECH_MAX_MOBILITY, DBCommon.YIELD_STRAIN, DBCommon.ULTIMATE_STRAIN,
            DBCommon.ENERGY_COEFF, DBCommon.THERMAL_CONDUCT_BOUND, DBCommon.ACT_ENERGY, DBCommon.DISL_DIST_COEFF,
            DBCommon.YIELD_STATE_COEFF, DBCommon.ULTIMATE_STATE_COEFF, DBCommon.PART_VOL_FRACTION, DBCommon.PART_RADIUS,
            DBCommon.THRESHOLD_STRESS, DBCommon.TORSION_ENERGY_COEFF, DBCommon.TORSION_ENERGY_COEFF_GB1, DBCommon.TORSION_ENERGY_COEFF_GB2,
            DBCommon.LOW_TEMPER_THR_VALUE, DBCommon.HIGH_TEMPER_THR_VALUE, DBCommon.MIN_TWIN_TEMPERATURE, DBCommon.TWINNING_TEMPERATURE,
            DBCommon.LATTICE_VECTOR_A_LENGTH, DBCommon.LATTICE_VECTOR_B_LENGTH, DBCommon.LATTICE_VECTOR_C_LENGTH, DBCommon.LATTICE_ANGLE_VEC_A_VEC_B,
            DBCommon.LATTICE_ANGLE_VEC_B_VEC_C, DBCommon.LATTICE_ANGLE_VEC_C_VEC_A, DBCommon.LATTICE_ANIS_COEFF, DBCommon.MAX_PROB_RECRYST,
            DBCommon.MAX_PROB_TWINNING, DBCommon.MIN_DISL_DENSITY);

    public static final String GET_ALL_QUERY = String.format("SELECT %s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
                    "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s FROM %s",
            DBCommon.NAME, DBCommon.HEAT_CONDUCTIVITY, DBCommon.DENSITY,
            DBCommon.HEAT_EXPANSION, DBCommon.HEAT_CAPACITY, DBCommon.PHONON_PORTION,
            DBCommon.ANGLE_LIMIT_HAGB, DBCommon.ENERGY_HAGB, DBCommon.MAX_MOBILITY, DBCommon.LATTICE_PARAMETER,
            DBCommon.YOUNG_MODULUS, DBCommon.SHEAR_MODULUS, DBCommon.MOLAR_MASS, DBCommon.ELAST_MODULUS,
            DBCommon.DISL_MAX_MOBILITY, DBCommon.MECH_MAX_MOBILITY, DBCommon.YIELD_STRAIN, DBCommon.ULTIMATE_STRAIN,
            DBCommon.ENERGY_COEFF, DBCommon.THERMAL_CONDUCT_BOUND, DBCommon.ACT_ENERGY, DBCommon.DISL_DIST_COEFF,
            DBCommon.YIELD_STATE_COEFF, DBCommon.ULTIMATE_STATE_COEFF, DBCommon.PART_VOL_FRACTION, DBCommon.PART_RADIUS,
            DBCommon.THRESHOLD_STRESS, DBCommon.TORSION_ENERGY_COEFF, DBCommon.TORSION_ENERGY_COEFF_GB1, DBCommon.TORSION_ENERGY_COEFF_GB2,
            DBCommon.LOW_TEMPER_THR_VALUE, DBCommon.HIGH_TEMPER_THR_VALUE, DBCommon.MIN_TWIN_TEMPERATURE, DBCommon.TWINNING_TEMPERATURE,
            DBCommon.LATTICE_VECTOR_A_LENGTH, DBCommon.LATTICE_VECTOR_B_LENGTH, DBCommon.LATTICE_VECTOR_C_LENGTH, DBCommon.LATTICE_ANGLE_VEC_A_VEC_B,
            DBCommon.LATTICE_ANGLE_VEC_B_VEC_C, DBCommon.LATTICE_ANGLE_VEC_C_VEC_A, DBCommon.LATTICE_ANIS_COEFF, DBCommon.MAX_PROB_RECRYST,
            DBCommon.MAX_PROB_TWINNING, DBCommon.MIN_DISL_DENSITY, DBCommon.MATERIALS);

    public static final String ADD_NEW_QUERY = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
                    "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
            DBCommon.MATERIALS, DBCommon.NAME, DBCommon.HEAT_CONDUCTIVITY, DBCommon.DENSITY,
            DBCommon.HEAT_EXPANSION, DBCommon.HEAT_CAPACITY, DBCommon.PHONON_PORTION,
            DBCommon.ANGLE_LIMIT_HAGB, DBCommon.ENERGY_HAGB, DBCommon.MAX_MOBILITY, DBCommon.LATTICE_PARAMETER,
            DBCommon.YOUNG_MODULUS, DBCommon.SHEAR_MODULUS, DBCommon.MOLAR_MASS, DBCommon.ELAST_MODULUS,
            DBCommon.DISL_MAX_MOBILITY, DBCommon.MECH_MAX_MOBILITY, DBCommon.YIELD_STRAIN, DBCommon.ULTIMATE_STRAIN,
            DBCommon.ENERGY_COEFF, DBCommon.THERMAL_CONDUCT_BOUND, DBCommon.ACT_ENERGY, DBCommon.DISL_DIST_COEFF,
            DBCommon.YIELD_STATE_COEFF, DBCommon.ULTIMATE_STATE_COEFF, DBCommon.PART_VOL_FRACTION, DBCommon.PART_RADIUS,
            DBCommon.THRESHOLD_STRESS, DBCommon.TORSION_ENERGY_COEFF, DBCommon.TORSION_ENERGY_COEFF_GB1, DBCommon.TORSION_ENERGY_COEFF_GB2,
            DBCommon.LOW_TEMPER_THR_VALUE, DBCommon.HIGH_TEMPER_THR_VALUE, DBCommon.MIN_TWIN_TEMPERATURE, DBCommon.TWINNING_TEMPERATURE,
            DBCommon.LATTICE_VECTOR_A_LENGTH, DBCommon.LATTICE_VECTOR_B_LENGTH, DBCommon.LATTICE_VECTOR_C_LENGTH, DBCommon.LATTICE_ANGLE_VEC_A_VEC_B,
            DBCommon.LATTICE_ANGLE_VEC_B_VEC_C, DBCommon.LATTICE_ANGLE_VEC_C_VEC_A, DBCommon.LATTICE_ANIS_COEFF, DBCommon.MAX_PROB_RECRYST,
            DBCommon.MAX_PROB_TWINNING, DBCommon.MIN_DISL_DENSITY);

    /*
    ********************************************************
    */

    private Connection connection;
    private Statement statement;

    public MaterialDataBase(Connection connection, Statement statement){
        this.connection = connection;
        this.statement = statement;
    }

    public void createMaterialTable(){
        try{
            statement = connection.createStatement();
            statement.execute(CREATE_TABLE_QUERY);
            System.out.println(DBCommon.MATERIALS_TABLE_IS_CREATED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_CREATE_MATERIALS_TABLE);
        }
    }

    public List<MaterialData> getAllMaterials(){
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            List<MaterialData> list = new ArrayList<>();

            while (resultSet.next()){
                list.add(
                        new MaterialData(
                                resultSet.getString(DBCommon.NAME),
                                resultSet.getDouble(DBCommon.HEAT_CONDUCTIVITY),
                                resultSet.getDouble(DBCommon.DENSITY),
                                resultSet.getDouble(DBCommon.HEAT_EXPANSION),
                                resultSet.getDouble(DBCommon.HEAT_CAPACITY),
                                resultSet.getDouble(DBCommon.PHONON_PORTION),
                                resultSet.getDouble(DBCommon.ANGLE_LIMIT_HAGB),
                                resultSet.getDouble(DBCommon.ENERGY_HAGB),
                                resultSet.getDouble(DBCommon.MAX_MOBILITY),
                                resultSet.getDouble(DBCommon.LATTICE_PARAMETER),
                                resultSet.getDouble(DBCommon.YOUNG_MODULUS),
                                resultSet.getDouble(DBCommon.SHEAR_MODULUS),
                                resultSet.getDouble(DBCommon.MOLAR_MASS),
                                resultSet.getDouble(DBCommon.ELAST_MODULUS),
                                resultSet.getDouble(DBCommon.DISL_MAX_MOBILITY),
                                resultSet.getDouble(DBCommon.MECH_MAX_MOBILITY),
                                resultSet.getDouble(DBCommon.YIELD_STRAIN),
                                resultSet.getDouble(DBCommon.ULTIMATE_STRAIN),
                                resultSet.getDouble(DBCommon.ENERGY_COEFF),
                                resultSet.getDouble(DBCommon.THERMAL_CONDUCT_BOUND),
                                resultSet.getDouble(DBCommon.ACT_ENERGY),
                                resultSet.getDouble(DBCommon.DISL_DIST_COEFF),
                                resultSet.getDouble(DBCommon.YIELD_STATE_COEFF),
                                resultSet.getDouble(DBCommon.ULTIMATE_STATE_COEFF),
                                resultSet.getDouble(DBCommon.PART_VOL_FRACTION),
                                resultSet.getDouble(DBCommon.PART_RADIUS),
                                resultSet.getDouble(DBCommon.THRESHOLD_STRESS),
                                resultSet.getDouble(DBCommon.TORSION_ENERGY_COEFF),
                                resultSet.getDouble(DBCommon.TORSION_ENERGY_COEFF_GB1),
                                resultSet.getDouble(DBCommon.TORSION_ENERGY_COEFF_GB2),
                                resultSet.getDouble(DBCommon.LOW_TEMPER_THR_VALUE),
                                resultSet.getDouble(DBCommon.HIGH_TEMPER_THR_VALUE),
                                resultSet.getDouble(DBCommon.MIN_TWIN_TEMPERATURE),
                                resultSet.getDouble(DBCommon.TWINNING_TEMPERATURE),
                                resultSet.getDouble(DBCommon.LATTICE_VECTOR_A_LENGTH),
                                resultSet.getDouble(DBCommon.LATTICE_VECTOR_B_LENGTH),
                                resultSet.getDouble(DBCommon.LATTICE_VECTOR_C_LENGTH),
                                resultSet.getDouble(DBCommon.LATTICE_ANGLE_VEC_A_VEC_B),
                                resultSet.getDouble(DBCommon.LATTICE_ANGLE_VEC_B_VEC_C),
                                resultSet.getDouble(DBCommon.LATTICE_ANGLE_VEC_C_VEC_A),
                                resultSet.getDouble(DBCommon.LATTICE_ANIS_COEFF),
                                resultSet.getDouble(DBCommon.MAX_PROB_RECRYST),
                                resultSet.getDouble(DBCommon.MAX_PROB_TWINNING),
                                resultSet.getDouble(DBCommon.MIN_DISL_DENSITY))
                );
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_MATERIALS_DATA);
            return Collections.EMPTY_LIST;
        }
    }

    public void addNewMaterial(MaterialData materialData){
        try (PreparedStatement statement = connection.prepareStatement(ADD_NEW_QUERY)){
            statement.setObject(1, materialData.getMaterialName());
            statement.setObject(2, materialData.getHeatConductivity());
            statement.setObject(3, materialData.getDensity());
            statement.setObject(4, materialData.getHeatExpansion());
            statement.setObject(5, materialData.getHeatCapacity());
            statement.setObject(6, materialData.getPhononPortion());
            statement.setObject(7, materialData.getAngleLimitHAGB());
            statement.setObject(8, materialData.getEnergyHAGB());
            statement.setObject(9, materialData.getMaxMobility());
            statement.setObject(10, materialData.getLatticeParameter());
            statement.setObject(11, materialData.getYoungModulus());
            statement.setObject(12, materialData.getShearModulus());
            statement.setObject(13, materialData.getMolarMass());
            statement.setObject(14, materialData.getElastModulus());
            statement.setObject(15, materialData.getDislMaxMobility());
            statement.setObject(16, materialData.getMechMaxMobility());
            statement.setObject(17, materialData.getYieldStrain());
            statement.setObject(18, materialData.getUltimateStrain());
            statement.setObject(19, materialData.getEnergyCoeff());
            statement.setObject(20, materialData.getThermalConductBound());
            statement.setObject(21, materialData.getActEnergy());
            statement.setObject(22, materialData.getDislDistCoeff());
            statement.setObject(23, materialData.getYieldStateCoeff());
            statement.setObject(24, materialData.getUltimateStateCoeff());
            statement.setObject(25, materialData.getPartVolFraction());
            statement.setObject(26, materialData.getPartRadius());
            statement.setObject(27, materialData.getThresholdStress());
            statement.setObject(28, materialData.getTorsionEnergyCoeff());
            statement.setObject(29, materialData.getTorsionEnergyCoeffGB1());
            statement.setObject(30, materialData.getTorsionEnergyCoeffGB2());
            statement.setObject(31, materialData.getLowTemperThrValue());
            statement.setObject(32, materialData.getHighTemperThrValue());
            statement.setObject(33, materialData.getMinTwinTemperature());
            statement.setObject(34, materialData.getTwinningTemperature());
            statement.setObject(35, materialData.getLatticeVectorALength());
            statement.setObject(36, materialData.getLatticeVectorBLength());
            statement.setObject(37, materialData.getLatticeVectorCLength());
            statement.setObject(38, materialData.getLatticeAngle_VecA_VecB());
            statement.setObject(39, materialData.getLatticeAngle_VecB_VecC());
            statement.setObject(40, materialData.getLatticeAngle_VecC_VecA());
            statement.setObject(41, materialData.getLatticeAnisCoeff());
            statement.setObject(42, materialData.getMaxProbRecryst());
            statement.setObject(43, materialData.getMaxPropTwinning());
            statement.setObject(44, materialData.getMinDislDensity());
            statement.execute();
            System.out.println(DBCommon.NEW_MATERIAL_DATA_IS_ADDED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_ADD_NEW_MATERIAL);
        }
    }

    public void updateMaterial(MaterialData materialData, String oldMaterialName){
        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, " +
                        "%s=?, %s=?, %s=?, %s=?, %s=?, %s=?,%s=?,%s=?,%s=?,%s=?, %s=?, %s=?, %s=?, %s=?, %s=?,%s=?,%s=?,%s=?," +
                        "%s=?, %s=?, %s=?, %s=?, %s=?, %s=?,%s=?,%s=?,%s=?,%s=?, %s=?, %s=?, %s=?, %s=?, %s=?,%s=?,%s=?,%s=?," +
                        "%s=?,%s=?,%s=?,%s=?  WHERE %s='%s'",
                DBCommon.MATERIALS, DBCommon.NAME, DBCommon.HEAT_CONDUCTIVITY, DBCommon.DENSITY,
                DBCommon.HEAT_EXPANSION, DBCommon.HEAT_CAPACITY, DBCommon.PHONON_PORTION,
                DBCommon.ANGLE_LIMIT_HAGB, DBCommon.ENERGY_HAGB, DBCommon.MAX_MOBILITY,
                DBCommon.LATTICE_PARAMETER, DBCommon.YOUNG_MODULUS, DBCommon.SHEAR_MODULUS, DBCommon.MOLAR_MASS,
                DBCommon.ELAST_MODULUS,
                DBCommon.DISL_MAX_MOBILITY, DBCommon.MECH_MAX_MOBILITY, DBCommon.YIELD_STRAIN, DBCommon.ULTIMATE_STRAIN,
                DBCommon.ENERGY_COEFF, DBCommon.THERMAL_CONDUCT_BOUND, DBCommon.ACT_ENERGY, DBCommon.DISL_DIST_COEFF,
                DBCommon.YIELD_STATE_COEFF, DBCommon.ULTIMATE_STATE_COEFF, DBCommon.PART_VOL_FRACTION, DBCommon.PART_RADIUS,
                DBCommon.THRESHOLD_STRESS, DBCommon.TORSION_ENERGY_COEFF, DBCommon.TORSION_ENERGY_COEFF_GB1, DBCommon.TORSION_ENERGY_COEFF_GB2,
                DBCommon.LOW_TEMPER_THR_VALUE, DBCommon.HIGH_TEMPER_THR_VALUE, DBCommon.MIN_TWIN_TEMPERATURE, DBCommon.TWINNING_TEMPERATURE,
                DBCommon.LATTICE_VECTOR_A_LENGTH, DBCommon.LATTICE_VECTOR_B_LENGTH, DBCommon.LATTICE_VECTOR_C_LENGTH, DBCommon.LATTICE_ANGLE_VEC_A_VEC_B,
                DBCommon.LATTICE_ANGLE_VEC_B_VEC_C, DBCommon.LATTICE_ANGLE_VEC_C_VEC_A, DBCommon.LATTICE_ANIS_COEFF, DBCommon.MAX_PROB_RECRYST,
                DBCommon.MAX_PROB_TWINNING, DBCommon.MIN_DISL_DENSITY,
                DBCommon.NAME, oldMaterialName);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, materialData.getMaterialName());
            statement.setObject(2, materialData.getHeatConductivity());
            statement.setObject(3, materialData.getDensity());
            statement.setObject(4, materialData.getHeatExpansion());
            statement.setObject(5, materialData.getHeatCapacity());
            statement.setObject(6, materialData.getPhononPortion());
            statement.setObject(7, materialData.getAngleLimitHAGB());
            statement.setObject(8, materialData.getEnergyHAGB());
            statement.setObject(9, materialData.getMaxMobility());
            statement.setObject(10, materialData.getLatticeParameter());
            statement.setObject(11, materialData.getYoungModulus());
            statement.setObject(12, materialData.getShearModulus());
            statement.setObject(13, materialData.getMolarMass());
            statement.setObject(14, materialData.getElastModulus());
            statement.setObject(15, materialData.getDislMaxMobility());
            statement.setObject(16, materialData.getMechMaxMobility());
            statement.setObject(17, materialData.getYieldStrain());
            statement.setObject(18, materialData.getUltimateStrain());
            statement.setObject(19, materialData.getEnergyCoeff());
            statement.setObject(20, materialData.getThermalConductBound());
            statement.setObject(21, materialData.getActEnergy());
            statement.setObject(22, materialData.getDislDistCoeff());
            statement.setObject(23, materialData.getYieldStateCoeff());
            statement.setObject(24, materialData.getUltimateStateCoeff());
            statement.setObject(25, materialData.getPartVolFraction());
            statement.setObject(26, materialData.getPartRadius());
            statement.setObject(27, materialData.getThresholdStress());
            statement.setObject(28, materialData.getTorsionEnergyCoeff());
            statement.setObject(29, materialData.getTorsionEnergyCoeffGB1());
            statement.setObject(30, materialData.getTorsionEnergyCoeffGB2());
            statement.setObject(31, materialData.getLowTemperThrValue());
            statement.setObject(32, materialData.getHighTemperThrValue());
            statement.setObject(33, materialData.getMinTwinTemperature());
            statement.setObject(34, materialData.getTwinningTemperature());
            statement.setObject(35, materialData.getLatticeVectorALength());
            statement.setObject(36, materialData.getLatticeVectorBLength());
            statement.setObject(37, materialData.getLatticeVectorCLength());
            statement.setObject(38, materialData.getLatticeAngle_VecA_VecB());
            statement.setObject(39, materialData.getLatticeAngle_VecB_VecC());
            statement.setObject(40, materialData.getLatticeAngle_VecC_VecA());
            statement.setObject(41, materialData.getLatticeAnisCoeff());
            statement.setObject(42, materialData.getMaxProbRecryst());
            statement.setObject(43, materialData.getMaxPropTwinning());
            statement.setObject(44, materialData.getMinDislDensity());
            statement.execute();
            System.out.println(DBCommon.MATERIAL_DATA_IS_EDITED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_EDIT_MATERIAL);
        }
    }

    public void deleteMaterial(String materialName){
        String sqlQuery = String.format("DELETE FROM %s WHERE %s='%s'",
                DBCommon.MATERIALS, DBCommon.NAME, materialName);

        try{
            statement = connection.createStatement();
            statement.execute(sqlQuery);
            System.out.println(DBCommon.MATERIAL_IS_DELETED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_DELETE_MATERIAL);
        }
    }

    public MaterialData getMaterial(String materialName) {
        String sqlQuery = String.format("SELECT %s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
                                        "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s FROM %s WHERE %s='%s'",
                DBCommon.NAME, DBCommon.HEAT_CONDUCTIVITY, DBCommon.DENSITY,
                DBCommon.HEAT_EXPANSION, DBCommon.HEAT_CAPACITY, DBCommon.PHONON_PORTION,
                DBCommon.ANGLE_LIMIT_HAGB, DBCommon.ENERGY_HAGB, DBCommon.MAX_MOBILITY, DBCommon.LATTICE_PARAMETER,
                DBCommon.YOUNG_MODULUS, DBCommon.SHEAR_MODULUS, DBCommon.MOLAR_MASS,
                DBCommon.ELAST_MODULUS,
                DBCommon.DISL_MAX_MOBILITY, DBCommon.MECH_MAX_MOBILITY, DBCommon.YIELD_STRAIN, DBCommon.ULTIMATE_STRAIN,
                DBCommon.ENERGY_COEFF, DBCommon.THERMAL_CONDUCT_BOUND, DBCommon.ACT_ENERGY, DBCommon.DISL_DIST_COEFF,
                DBCommon.YIELD_STATE_COEFF, DBCommon.ULTIMATE_STATE_COEFF, DBCommon.PART_VOL_FRACTION, DBCommon.PART_RADIUS,
                DBCommon.THRESHOLD_STRESS, DBCommon.TORSION_ENERGY_COEFF, DBCommon.TORSION_ENERGY_COEFF_GB1, DBCommon.TORSION_ENERGY_COEFF_GB2,
                DBCommon.LOW_TEMPER_THR_VALUE, DBCommon.HIGH_TEMPER_THR_VALUE, DBCommon.MIN_TWIN_TEMPERATURE, DBCommon.TWINNING_TEMPERATURE,
                DBCommon.LATTICE_VECTOR_A_LENGTH, DBCommon.LATTICE_VECTOR_B_LENGTH, DBCommon.LATTICE_VECTOR_C_LENGTH, DBCommon.LATTICE_ANGLE_VEC_A_VEC_B,
                DBCommon.LATTICE_ANGLE_VEC_B_VEC_C, DBCommon.LATTICE_ANGLE_VEC_C_VEC_A, DBCommon.LATTICE_ANIS_COEFF, DBCommon.MAX_PROB_RECRYST,
                DBCommon.MAX_PROB_TWINNING, DBCommon.MIN_DISL_DENSITY,
                DBCommon.MATERIALS, DBCommon.NAME, materialName);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            MaterialData materialData = new MaterialData(
                    resultSet.getString(DBCommon.NAME),
                    resultSet.getDouble(DBCommon.HEAT_CONDUCTIVITY),
                    resultSet.getDouble(DBCommon.DENSITY),
                    resultSet.getDouble(DBCommon.HEAT_EXPANSION),
                    resultSet.getDouble(DBCommon.HEAT_CAPACITY),
                    resultSet.getDouble(DBCommon.PHONON_PORTION),
                    resultSet.getDouble(DBCommon.ANGLE_LIMIT_HAGB),
                    resultSet.getDouble(DBCommon.ENERGY_HAGB),
                    resultSet.getDouble(DBCommon.MAX_MOBILITY),
                    resultSet.getDouble(DBCommon.LATTICE_PARAMETER),
                    resultSet.getDouble(DBCommon.YOUNG_MODULUS),
                    resultSet.getDouble(DBCommon.SHEAR_MODULUS),
                    resultSet.getDouble(DBCommon.MOLAR_MASS),
                    resultSet.getDouble(DBCommon.ELAST_MODULUS),
                    resultSet.getDouble(DBCommon.DISL_MAX_MOBILITY),
                    resultSet.getDouble(DBCommon.MECH_MAX_MOBILITY),
                    resultSet.getDouble(DBCommon.YIELD_STRAIN),
                    resultSet.getDouble(DBCommon.ULTIMATE_STRAIN),
                    resultSet.getDouble(DBCommon.ENERGY_COEFF),
                    resultSet.getDouble(DBCommon.THERMAL_CONDUCT_BOUND),
                    resultSet.getDouble(DBCommon.ACT_ENERGY),
                    resultSet.getDouble(DBCommon.DISL_DIST_COEFF),
                    resultSet.getDouble(DBCommon.YIELD_STATE_COEFF),
                    resultSet.getDouble(DBCommon.ULTIMATE_STATE_COEFF),
                    resultSet.getDouble(DBCommon.PART_VOL_FRACTION),
                    resultSet.getDouble(DBCommon.PART_RADIUS),
                    resultSet.getDouble(DBCommon.THRESHOLD_STRESS),
                    resultSet.getDouble(DBCommon.TORSION_ENERGY_COEFF),
                    resultSet.getDouble(DBCommon.TORSION_ENERGY_COEFF_GB1),
                    resultSet.getDouble(DBCommon.TORSION_ENERGY_COEFF_GB2),
                    resultSet.getDouble(DBCommon.LOW_TEMPER_THR_VALUE),
                    resultSet.getDouble(DBCommon.HIGH_TEMPER_THR_VALUE),
                    resultSet.getDouble(DBCommon.MIN_TWIN_TEMPERATURE),
                    resultSet.getDouble(DBCommon.TWINNING_TEMPERATURE),
                    resultSet.getDouble(DBCommon.LATTICE_VECTOR_A_LENGTH),
                    resultSet.getDouble(DBCommon.LATTICE_VECTOR_B_LENGTH),
                    resultSet.getDouble(DBCommon.LATTICE_VECTOR_C_LENGTH),
                    resultSet.getDouble(DBCommon.LATTICE_ANGLE_VEC_A_VEC_B),
                    resultSet.getDouble(DBCommon.LATTICE_ANGLE_VEC_B_VEC_C),
                    resultSet.getDouble(DBCommon.LATTICE_ANGLE_VEC_C_VEC_A),
                    resultSet.getDouble(DBCommon.LATTICE_ANIS_COEFF),
                    resultSet.getDouble(DBCommon.MAX_PROB_RECRYST),
                    resultSet.getDouble(DBCommon.MAX_PROB_TWINNING),
                    resultSet.getDouble(DBCommon.MIN_DISL_DENSITY));
            return materialData;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_MATERIALS_DATA);
            return new MaterialData();
        }
    }

    public boolean checkName(String materialName){
        String sqlQuery = String.format("SELECT %s FROM %s", DBCommon.NAME, DBCommon.MATERIALS);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            List<String> materialNames = new ArrayList<>();
            while (resultSet.next()){
                materialNames.add(resultSet.getString(DBCommon.NAME));
            }

            if (materialNames.contains(materialName)){
                return false;
            }
            else {
                return true;
            }

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_CHECK_MATERIAL_NAME);
            return false;
        }
    }
}
