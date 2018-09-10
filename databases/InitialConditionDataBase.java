package databases;

import utils.InitialCondition;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitialConditionDataBase {

    private Connection connection;
    private Statement statement;
    private String specimenName, tableName;


    public InitialConditionDataBase(Connection connection, Statement statement){
        this.connection = connection;
        this.statement = statement;
    }

    public void setSpecimenName(String specimenName){
        this.specimenName = specimenName;
        this.tableName = specimenName + "_" + DBCommon.INITIAL_CONDITION;
    }

    public void createInitialConditionsTable(){
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS " +
                "%s(%s TEXT, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL)",
                tableName, DBCommon.NAME, DBCommon.TEMPERATURE, DBCommon.ELASTIC_ENERGY, DBCommon.DISLOCATION_DENSITY,
                DBCommon.MOMENTS_X, DBCommon.MOMENTS_Y, DBCommon.MOMENTS_Z);
        try{
            statement = connection.createStatement();
            statement.execute(sqlQuery);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<InitialCondition> getAllInitialConditions(){
        String sqlQuery = String.format("SELECT %s,%s,%s,%s,%s,%s,%s FROM %s",
                DBCommon.NAME, DBCommon.TEMPERATURE, DBCommon.ELASTIC_ENERGY, DBCommon.DISLOCATION_DENSITY,
                DBCommon.MOMENTS_X, DBCommon.MOMENTS_Y, DBCommon.MOMENTS_Z, tableName);
        try{
            List<InitialCondition> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                list.add(new InitialCondition(
                        resultSet.getString(DBCommon.NAME),
                        resultSet.getDouble(DBCommon.TEMPERATURE),
                        resultSet.getDouble(DBCommon.ELASTIC_ENERGY),
                        resultSet.getDouble(DBCommon.DISLOCATION_DENSITY),
                        resultSet.getDouble(DBCommon.MOMENTS_X),
                        resultSet.getDouble(DBCommon.MOMENTS_Y),
                        resultSet.getDouble(DBCommon.MOMENTS_Z)
                ));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_INITIAL_COND_DATA);
            return Collections.EMPTY_LIST;
        }
    }

    public void addInitialCondition(InitialCondition initialCondition){
        String sqlQuery = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?)",
                tableName, DBCommon.NAME, DBCommon.TEMPERATURE, DBCommon.ELASTIC_ENERGY,
                DBCommon.DISLOCATION_DENSITY, DBCommon.MOMENTS_X, DBCommon.MOMENTS_Y, DBCommon.MOMENTS_Z);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, initialCondition.getName());
            statement.setObject(2, initialCondition.getTemperature());
            statement.setObject(3, initialCondition.getElasticEnergy());
            statement.setObject(4, initialCondition.getDislocationDensity());
            statement.setObject(5, initialCondition.getMomentX());
            statement.setObject(6, initialCondition.getMomentY());
            statement.setObject(7, initialCondition.getMomentZ());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_ADD_NEW_INIT_COND);
        }
    }

    public void updateInitialCondition(InitialCondition initialCondition, String oldInitialCondName){
        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s='%s'",
                tableName, DBCommon.NAME, DBCommon.TEMPERATURE, DBCommon.ELASTIC_ENERGY, DBCommon.DISLOCATION_DENSITY,
                DBCommon.MOMENTS_X, DBCommon.MOMENTS_Y, DBCommon.MOMENTS_Z, DBCommon.NAME, oldInitialCondName);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, initialCondition.getName());
            statement.setObject(2, initialCondition.getTemperature());
            statement.setObject(3, initialCondition.getElasticEnergy());
            statement.setObject(4, initialCondition.getDislocationDensity());
            statement.setObject(5, initialCondition.getMomentX());
            statement.setObject(6, initialCondition.getMomentY());
            statement.setObject(7, initialCondition.getMomentZ());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_UPDATE_INIT_COND_DATA);
        }
    }

    public void deleteInitialCondition(String initCondName){
        String sqlQuery = String.format("DELETE FROM %s WHERE %s='%s'", tableName, DBCommon.NAME, initCondName);
        try {
            statement = connection.createStatement();
            statement.execute(sqlQuery);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_DELETE_INIT_COND_DATA);
        }
    }

    public InitialCondition getInitialCondition(String initialConditionName){
        String sqlQuery = String.format("SELECT %s,%s,%s,%s,%s,%s,%s FROM %s WHERE %s='%s'",
                DBCommon.NAME, DBCommon.TEMPERATURE, DBCommon.ELASTIC_ENERGY, DBCommon.DISLOCATION_DENSITY,
                DBCommon.MOMENTS_X, DBCommon.MOMENTS_Y, DBCommon.MOMENTS_Z, tableName, DBCommon.NAME, initialConditionName);
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            InitialCondition initialCondition = new InitialCondition(
                    resultSet.getString(DBCommon.NAME),
                    resultSet.getDouble(DBCommon.TEMPERATURE),
                    resultSet.getDouble(DBCommon.ELASTIC_ENERGY),
                    resultSet.getDouble(DBCommon.DISLOCATION_DENSITY),
                    resultSet.getDouble(DBCommon.MOMENTS_X),
                    resultSet.getDouble(DBCommon.MOMENTS_Y),
                    resultSet.getDouble(DBCommon.MOMENTS_Z));
            return initialCondition;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_INITIAL_COND_DATA);
            return new InitialCondition();
        }
    }

}
