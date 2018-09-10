package databases;

import utils.Element;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultDataBase {

    private Connection connection;
    private Statement statement;
    private String specimenName, taskName;

    public ResultDataBase(Connection connection, String specimenName, String taskName){
        this.connection = connection;
        this.specimenName = specimenName;
        this.taskName = taskName;
    }

    public List<Integer> getTimeSteps(){
        String tableName = String.format("%s_%s", specimenName, taskName);
        String sqlQuery = String.format("SELECT %s FROM %s", DBCommon.TIME_STEP, tableName);
        try {
            statement = connection.createStatement();
            List<Integer> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                list.add(resultSet.getInt(DBCommon.TIME_STEP));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_TIME_STEPS);
            return Collections.EMPTY_LIST;
        }
    }

    public List<Element> getResult(int timeStep){
        String tableName = String.format("%s_%s_%s", specimenName, taskName, String.valueOf(timeStep));
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
                        resultSet.getDouble(DBCommon.TEMPERATURE),
                        0.0,0.0,0.0,0.0,0.0
                ));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_RESULT);
            return Collections.EMPTY_LIST;
        }
    }

    //TODO: fix this
    public void deleteResult(int timeStep){
        String tableName = String.format("%s_%s_%s", specimenName, taskName, String.valueOf(timeStep));
        String sqlQuery = String.format("DROP TABLE %s", tableName);
        try{
            statement = connection.createStatement();
            statement.execute(sqlQuery);
            System.out.println();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_DELETE_RESULTS);
        }
    }
}
