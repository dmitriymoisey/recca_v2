package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Element;
import utils.SpecimenData;
import view.UICommon;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecimenDataBase {

    /*
    SQL Queries
    */
    public static final String CREATE_TABLE_QUERY = String.format("CREATE TABLE IF NOT EXISTS " +
            "%s (%s TEXT, %s INT, %s INT, %s INT, %s REAL, %s INT, %s REAL, %s TEXT, %s INT, %s TEXT, %s TEXT, %s TEXT)",
            DBCommon.SPECIMENS, DBCommon.NAME,
            DBCommon.CELL_NUMBER_X, DBCommon.CELL_NUMBER_Y, DBCommon.CELL_NUMBER_Z, DBCommon.CELL_SIZE,
            DBCommon.NUMBER_OF_GRAINS, DBCommon.ANGLE_RANGE, DBCommon.TYPE_OF_GRAIN_DISTRIB, DBCommon.MATERIAL,
            DBCommon.INITIAL_CONDITION, DBCommon.BOUNDARY_CONDITION, DBCommon.TASK);

    public static final String GET_ALL_SPECIMENS_QUERY = String.format("SELECT %s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s FROM %s",
            DBCommon.NAME,
            DBCommon.CELL_NUMBER_X, DBCommon.CELL_NUMBER_Y, DBCommon.CELL_NUMBER_Z, DBCommon.CELL_SIZE,
            DBCommon.NUMBER_OF_GRAINS, DBCommon.ANGLE_RANGE, DBCommon.TYPE_OF_GRAIN_DISTRIB, DBCommon.MATERIAL,
            DBCommon.INITIAL_CONDITION, DBCommon.BOUNDARY_CONDITION, DBCommon.TASK,
            DBCommon.SPECIMENS);

    public static final String ADD_NEW_SPECIMEN_QUERY = String.format("INSERT INTO " +
            "%s(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
            DBCommon.SPECIMENS, DBCommon.NAME,
            DBCommon.CELL_NUMBER_X, DBCommon.CELL_NUMBER_Y, DBCommon.CELL_NUMBER_Z, DBCommon.CELL_SIZE,
            DBCommon.NUMBER_OF_GRAINS, DBCommon.ANGLE_RANGE, DBCommon.TYPE_OF_GRAIN_DISTRIB, DBCommon.MATERIAL,
            DBCommon.INITIAL_CONDITION, DBCommon.BOUNDARY_CONDITION, DBCommon.TASK);

    /*
    *******************************************
    */

    private Connection connection;
    private Statement statement;

    public SpecimenDataBase(Connection connection, Statement statement){
        this.connection = connection;
        this.statement = statement;
    }

    public void createSpecimensTable(){
        try{
            statement = connection.createStatement();
            statement.execute(CREATE_TABLE_QUERY);
            System.out.println(DBCommon.SPECIMENS_TABLE_IS_CREATED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_CREATE_SPECIMENS_TABLE);
        }
    }

    public ObservableList<SpecimenData> getAllSpecimens(){
        try{
            statement = connection.createStatement();
            ObservableList<SpecimenData> specimenDataList = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery(GET_ALL_SPECIMENS_QUERY);

            while (resultSet.next()){
                specimenDataList.add(
                        new SpecimenData(
                                resultSet.getString(DBCommon.NAME),
                                resultSet.getInt(DBCommon.CELL_NUMBER_X),
                                resultSet.getInt(DBCommon.CELL_NUMBER_Y),
                                resultSet.getInt(DBCommon.CELL_NUMBER_Z),
                                resultSet.getDouble(DBCommon.CELL_SIZE),
                                resultSet.getInt(DBCommon.NUMBER_OF_GRAINS),
                                resultSet.getDouble(DBCommon.ANGLE_RANGE),
                                resultSet.getByte(DBCommon.TYPE_OF_GRAIN_DISTRIB),
                                resultSet.getString(DBCommon.MATERIAL),
                                resultSet.getString(DBCommon.INITIAL_CONDITION),
                                resultSet.getString(DBCommon.BOUNDARY_CONDITION),
                                resultSet.getString(DBCommon.TASK)
                        )
                );
            }
            return specimenDataList;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_SPECIMENS_DATA);
            return FXCollections.observableArrayList();
        }
    }

    public void addNewSpecimen(SpecimenData specimenData){
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SPECIMEN_QUERY)){
            preparedStatement.setObject(1, specimenData.getSpecimenName());
            preparedStatement.setObject(2, specimenData.getCellNumberX());
            preparedStatement.setObject(3, specimenData.getCellNumberY());
            preparedStatement.setObject(4, specimenData.getCellNumberZ());
            preparedStatement.setObject(5, specimenData.getCellSize());
            preparedStatement.setObject(6,specimenData.getNumberOfGrains());
            preparedStatement.setObject(7, specimenData.getAngleRange());
            preparedStatement.setObject(8, specimenData.getTypeOfGrainDistribution());
            preparedStatement.setObject(9, specimenData.getMaterial());
            preparedStatement.setObject(10, specimenData.getInitialConditions());
            preparedStatement.setObject(11, specimenData.getBoundaryConditions());
            preparedStatement.setObject(12, specimenData.getTask());
            // Выполняем запрос
            preparedStatement.execute();
            System.out.println(DBCommon.NEW_SPECIMEN_DATA_IS_ADDED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_ADD_SPECIMEN_DATA);
        }
    }

    public void updateSpecimen(SpecimenData specimenData, String oldSpecimenName){
        String sqlQuery = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, " +
                        "%s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s='%s'",
                DBCommon.SPECIMENS, DBCommon.NAME,
                DBCommon.CELL_NUMBER_X, DBCommon.CELL_NUMBER_Y, DBCommon.CELL_NUMBER_Z, DBCommon.CELL_SIZE,
                DBCommon.NUMBER_OF_GRAINS, DBCommon.ANGLE_RANGE, DBCommon.TYPE_OF_GRAIN_DISTRIB, DBCommon.MATERIAL,
                DBCommon.INITIAL_CONDITION, DBCommon.BOUNDARY_CONDITION, DBCommon.TASK, DBCommon.NAME, oldSpecimenName);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, specimenData.getSpecimenName());
            statement.setObject(2, specimenData.getCellNumberX());
            statement.setObject(3, specimenData.getCellNumberY());
            statement.setObject(4, specimenData.getCellNumberZ());
            statement.setObject(5, specimenData.getCellSize());
            statement.setObject(6,specimenData.getNumberOfGrains());
            statement.setObject(7, specimenData.getAngleRange());
            statement.setObject(8, specimenData.getTypeOfGrainDistribution());
            statement.setObject(9, specimenData.getMaterial());
            statement.setObject(10, specimenData.getInitialConditions());
            statement.setObject(11, specimenData.getBoundaryConditions());
            statement.setObject(12, specimenData.getTask());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_EDIT_SPECIMEN_DATA);
        }
    }

    public void deleteSpecimen(String specimenName){
        String sqlquery = String.format("DELETE FROM %s WHERE %s='%s'",
                DBCommon.SPECIMENS, DBCommon.NAME, specimenName);
        try{
            statement = connection.createStatement();
            statement.execute(sqlquery);
            System.out.println(DBCommon.SPECIMEN_IS_DELETED);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_DELETE_SPECIMEN_DATA);
        }
    }

    public List<Element> getSpecimenStructure(String specimenName){
        String tableName = String.format("%s_%s", specimenName, DBCommon.STRUCTURE_DATA);
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        try {
            statement = connection.createStatement();
            List<Element> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                list.add(new Element(
                        resultSet.getInt(DBCommon.LOCATION_TYPE),
                        resultSet.getInt(DBCommon.GRAIN_INDEX),
                        resultSet.getDouble(DBCommon.COORDINATE_X),
                        resultSet.getDouble(DBCommon.COORDINATE_Y),
                        resultSet.getDouble(DBCommon.COORDINATE_Z),
                        0.0,0.0,0.0,0.0,0.0,0.0
                ));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_STRUCTURE_DATA);
            return Collections.EMPTY_LIST;
        }
    }
}
