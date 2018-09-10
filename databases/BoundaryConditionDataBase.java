package databases;

import utils.BoundaryCondition;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoundaryConditionDataBase {

    /*
    ****************************************************
     */

    private Connection connection;
    private Statement statement;
    private String specimenName, tableName;

    public BoundaryConditionDataBase(Connection connection, Statement statement){
        this.connection = connection;
        this.statement = statement;
    }

    public void setSpecimenName(String specimenName){
        this.specimenName = specimenName;
        this.tableName = specimenName + "_" + DBCommon.BOUNDARY_CONDITION;
    }

    public void createBoundaryConditionsTable(){
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s" +
                "(%s TEXT, %s TEXT, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, " +
                "%s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, " +
                "%s REAL, %s REAL, %s REAL, %s REAL)", tableName, DBCommon.NAME, DBCommon.FACET,
                DBCommon.TEMPERATURE_AVERAGE, DBCommon.TEMPERATURE_DEVIATION, DBCommon.TEMPERATURE_LOADING_TIME,
                DBCommon.ELASTIC_ENERGY_AVERAGE, DBCommon.ELASTIC_ENERGY_DEVIATION, DBCommon.ELASTIC_ENERGY_LOADING_TIME,
                DBCommon.DISLOCATION_DENSITY_AVERAGE, DBCommon.DISLOCATION_DENSITY_DEVIATION, DBCommon.DISLOCATION_DENSITY_LOADING_TIME,
                DBCommon.MOMENTS_X_AVERAGE, DBCommon.MOMENTS_X_DEVIATION, DBCommon.MOMENTS_X_LOADING_TIME,
                DBCommon.MOMENTS_Y_AVERAGE, DBCommon.MOMENTS_Y_DEVIATION, DBCommon.MOMENTS_Y_LOADING_TIME,
                DBCommon.MOMENTS_Z_AVERAGE, DBCommon.MOMENTS_Z_DEVIATION, DBCommon.MOMENTS_Z_LOADING_TIME);
        try{
            statement = connection.createStatement();
            statement.execute(sqlQuery);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_CREATE_BOUND_COND_TABLE);
        }
    }

    public List<BoundaryCondition> getBoundaryConditions(){
        String sqlQuery = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s,%s, %s, %s, %s, %s, %s, %s, %s,%s, %s, %s, %s FROM %s",
                DBCommon.NAME, DBCommon.FACET,
                DBCommon.TEMPERATURE_AVERAGE, DBCommon.TEMPERATURE_DEVIATION, DBCommon.TEMPERATURE_LOADING_TIME,
                DBCommon.ELASTIC_ENERGY_AVERAGE, DBCommon.ELASTIC_ENERGY_DEVIATION, DBCommon.ELASTIC_ENERGY_LOADING_TIME,
                DBCommon.DISLOCATION_DENSITY_AVERAGE, DBCommon.DISLOCATION_DENSITY_DEVIATION, DBCommon.DISLOCATION_DENSITY_LOADING_TIME,
                DBCommon.MOMENTS_X_AVERAGE, DBCommon.MOMENTS_X_DEVIATION, DBCommon.MOMENTS_X_LOADING_TIME,
                DBCommon.MOMENTS_Y_AVERAGE, DBCommon.MOMENTS_Y_DEVIATION, DBCommon.MOMENTS_Y_LOADING_TIME,
                DBCommon.MOMENTS_Z_AVERAGE, DBCommon.MOMENTS_Z_DEVIATION, DBCommon.MOMENTS_Z_LOADING_TIME, tableName);
        try{
            List<BoundaryCondition> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                list.add(new BoundaryCondition(
                        resultSet.getString(DBCommon.NAME),
                        resultSet.getString(DBCommon.FACET),
                        resultSet.getDouble(DBCommon.TEMPERATURE_AVERAGE),
                        resultSet.getDouble(DBCommon.TEMPERATURE_DEVIATION),
                        resultSet.getDouble(DBCommon.TEMPERATURE_LOADING_TIME),
                        resultSet.getDouble(DBCommon.ELASTIC_ENERGY_AVERAGE),
                        resultSet.getDouble(DBCommon.ELASTIC_ENERGY_DEVIATION),
                        resultSet.getDouble(DBCommon.ELASTIC_ENERGY_LOADING_TIME),
                        resultSet.getDouble(DBCommon.DISLOCATION_DENSITY_AVERAGE),
                        resultSet.getDouble(DBCommon.DISLOCATION_DENSITY_DEVIATION),
                        resultSet.getDouble(DBCommon.DISLOCATION_DENSITY_LOADING_TIME),
                        resultSet.getDouble(DBCommon.MOMENTS_X_AVERAGE),
                        resultSet.getDouble(DBCommon.MOMENTS_X_DEVIATION),
                        resultSet.getDouble(DBCommon.MOMENTS_X_LOADING_TIME),
                        resultSet.getDouble(DBCommon.MOMENTS_Y_AVERAGE),
                        resultSet.getDouble(DBCommon.MOMENTS_Y_DEVIATION),
                        resultSet.getDouble(DBCommon.MOMENTS_Y_LOADING_TIME),
                        resultSet.getDouble(DBCommon.MOMENTS_Z_AVERAGE),
                        resultSet.getDouble(DBCommon.MOMENTS_Z_DEVIATION),
                        resultSet.getDouble(DBCommon.MOMENTS_Z_LOADING_TIME)
                ));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_BOUND_COND_DATA);
            return Collections.EMPTY_LIST;
        }
    }

    public void addBoundaryCondition(BoundaryCondition boundaryCondition){
        String sqlQuery = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", tableName, DBCommon.NAME, DBCommon.FACET,
                DBCommon.TEMPERATURE_AVERAGE, DBCommon.TEMPERATURE_DEVIATION, DBCommon.TEMPERATURE_LOADING_TIME,
                DBCommon.ELASTIC_ENERGY_AVERAGE, DBCommon.ELASTIC_ENERGY_DEVIATION, DBCommon.ELASTIC_ENERGY_LOADING_TIME,
                DBCommon.DISLOCATION_DENSITY_AVERAGE, DBCommon.DISLOCATION_DENSITY_DEVIATION, DBCommon.DISLOCATION_DENSITY_LOADING_TIME,
                DBCommon.MOMENTS_X_AVERAGE, DBCommon.MOMENTS_X_DEVIATION, DBCommon.MOMENTS_X_LOADING_TIME,
                DBCommon.MOMENTS_Y_AVERAGE, DBCommon.MOMENTS_Y_DEVIATION, DBCommon.MOMENTS_Y_LOADING_TIME,
                DBCommon.MOMENTS_Z_AVERAGE, DBCommon.MOMENTS_Z_DEVIATION, DBCommon.MOMENTS_Z_LOADING_TIME);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, boundaryCondition.getName());
            statement.setObject(2, boundaryCondition.getFacet());
            statement.setObject(3, boundaryCondition.getAverageTemperature());
            statement.setObject(4, boundaryCondition.getTemperatureRange());
            statement.setObject(5, boundaryCondition.getTemperatureLoadingTimePortion());
            statement.setObject(6, boundaryCondition.getAverageElasticEnergy());
            statement.setObject(7, boundaryCondition.getElasticEnergyRange());
            statement.setObject(8, boundaryCondition.getElasticEnergyLoadingTimePortion());
            statement.setObject(9, boundaryCondition.getAverageDislocationDensity());
            statement.setObject(10, boundaryCondition.getDislocationDensityRange());
            statement.setObject(11, boundaryCondition.getDislocationDensityLoadingTimePortion());
            statement.setObject(12, boundaryCondition.getAverageMomentX());
            statement.setObject(13, boundaryCondition.getMomentRangeX());
            statement.setObject(14, boundaryCondition.getMomentLoadingTimePortionX());
            statement.setObject(15, boundaryCondition.getAverageMomentY());
            statement.setObject(16, boundaryCondition.getMomentRangeY());
            statement.setObject(17, boundaryCondition.getMomentLoadingTimePortionY());
            statement.setObject(18, boundaryCondition.getAverageMomentZ());
            statement.setObject(19, boundaryCondition.getMomentRangeZ());
            statement.setObject(20, boundaryCondition.getMomentLoadingTimePortionZ());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_ADD_NEW_BOUND_COND);
        }
    }

    public void updateBoundaryCondition(BoundaryCondition boundCond, String oldName){
        String sqlQuery = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?," +
                "%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s='%s'", tableName, DBCommon.NAME, DBCommon.FACET,
                DBCommon.TEMPERATURE_AVERAGE, DBCommon.TEMPERATURE_DEVIATION, DBCommon.TEMPERATURE_LOADING_TIME,
                DBCommon.ELASTIC_ENERGY_AVERAGE, DBCommon.ELASTIC_ENERGY_DEVIATION, DBCommon.ELASTIC_ENERGY_LOADING_TIME,
                DBCommon.DISLOCATION_DENSITY_AVERAGE, DBCommon.DISLOCATION_DENSITY_DEVIATION, DBCommon.DISLOCATION_DENSITY_LOADING_TIME,
                DBCommon.MOMENTS_X_AVERAGE, DBCommon.MOMENTS_X_DEVIATION, DBCommon.MOMENTS_X_LOADING_TIME,
                DBCommon.MOMENTS_Y_AVERAGE, DBCommon.MOMENTS_Y_DEVIATION, DBCommon.MOMENTS_Y_LOADING_TIME,
                DBCommon.MOMENTS_Z_AVERAGE, DBCommon.MOMENTS_Z_DEVIATION, DBCommon.MOMENTS_Z_LOADING_TIME, DBCommon.NAME, oldName);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, boundCond.getName());
            statement.setObject(2, boundCond.getFacet());
            statement.setObject(3, boundCond.getAverageTemperature());
            statement.setObject(4, boundCond.getTemperatureRange());
            statement.setObject(5, boundCond.getTemperatureLoadingTimePortion());
            statement.setObject(6, boundCond.getAverageElasticEnergy());
            statement.setObject(7, boundCond.getElasticEnergyRange());
            statement.setObject(8, boundCond.getElasticEnergyLoadingTimePortion());
            statement.setObject(9, boundCond.getAverageDislocationDensity());
            statement.setObject(10, boundCond.getDislocationDensityRange());
            statement.setObject(11, boundCond.getDislocationDensityLoadingTimePortion());
            statement.setObject(12, boundCond.getAverageMomentX());
            statement.setObject(13, boundCond.getMomentRangeX());
            statement.setObject(14, boundCond.getMomentLoadingTimePortionX());
            statement.setObject(15, boundCond.getAverageMomentY());
            statement.setObject(16, boundCond.getMomentRangeY());
            statement.setObject(17, boundCond.getMomentLoadingTimePortionY());
            statement.setObject(18, boundCond.getAverageMomentZ());
            statement.setObject(19, boundCond.getMomentRangeZ());
            statement.setObject(20, boundCond.getMomentLoadingTimePortionZ());
            statement.execute();

        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_UPDATE_BOUND_COND_DATA);
        }
    }


    public void deleteBoundaryCondition(String name){
        String sqlQuery = String.format("DELETE FROM %s WHERE %s='%s'", tableName, DBCommon.NAME, name);
        try {
            statement = connection.createStatement();
            statement.execute(sqlQuery);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_DELETE_BOUND_COND_DATA);
        }
    }

    // TODO: TEST
    public List<BoundaryCondition> getBoundaryCondition(String name){
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s='%s'", tableName, DBCommon.NAME, name);
        try {
            statement = connection.createStatement();
            List<BoundaryCondition> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                list.add(
                        new BoundaryCondition(
                                resultSet.getString(DBCommon.NAME),
                                resultSet.getString(DBCommon.FACET),
                                resultSet.getDouble(DBCommon.TEMPERATURE_AVERAGE),
                                resultSet.getDouble(DBCommon.TEMPERATURE_DEVIATION),
                                resultSet.getDouble(DBCommon.TEMPERATURE_LOADING_TIME),
                                resultSet.getDouble(DBCommon.ELASTIC_ENERGY_AVERAGE),
                                resultSet.getDouble(DBCommon.ELASTIC_ENERGY_DEVIATION),
                                resultSet.getDouble(DBCommon.ELASTIC_ENERGY_LOADING_TIME),
                                resultSet.getDouble(DBCommon.DISLOCATION_DENSITY_AVERAGE),
                                resultSet.getDouble(DBCommon.DISLOCATION_DENSITY_DEVIATION),
                                resultSet.getDouble(DBCommon.DISLOCATION_DENSITY_LOADING_TIME),
                                resultSet.getDouble(DBCommon.MOMENTS_X_AVERAGE),
                                resultSet.getDouble(DBCommon.MOMENTS_X_DEVIATION),
                                resultSet.getDouble(DBCommon.MOMENTS_X_LOADING_TIME),
                                resultSet.getDouble(DBCommon.MOMENTS_Y_AVERAGE),
                                resultSet.getDouble(DBCommon.MOMENTS_Y_DEVIATION),
                                resultSet.getDouble(DBCommon.MOMENTS_Y_LOADING_TIME),
                                resultSet.getDouble(DBCommon.MOMENTS_Z_AVERAGE),
                                resultSet.getDouble(DBCommon.MOMENTS_Z_DEVIATION),
                                resultSet.getDouble(DBCommon.MOMENTS_Z_LOADING_TIME)
                        )
                );
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_BOUND_COND_DATA);
            return Collections.EMPTY_LIST;
        }
    }

}
