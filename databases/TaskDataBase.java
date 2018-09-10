package databases;

import utils.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskDataBase {

    private Connection connection;
    private Statement statement;
    private String specimenName, tableName;

    public TaskDataBase(Connection connection, Statement statement){
        this.connection = connection;
        this.statement = statement;
    }

    public void setSpecimenName(String specimenName){
        this.specimenName = specimenName;
        this.tableName = specimenName + "_" + DBCommon.TASK;
    }

    public void createTasksTable(){
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s REAL, %s REAL)",
                tableName, DBCommon.NAME, DBCommon.TIME_STEP, DBCommon.TOTAL_TIME);
        try{
            statement = connection.createStatement();
            statement.execute(sqlQuery);
            System.out.println();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }
    }

    public List<Task> getAllTasks(){
        String sqlQuery = String.format("SELECT %s,%s,%s FROM %s",
                DBCommon.NAME, DBCommon.TIME_STEP, DBCommon.TOTAL_TIME, tableName);
        try{
            statement = connection.createStatement();
            List<Task> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                list.add(new Task(
                        resultSet.getString(DBCommon.NAME),
                        resultSet.getDouble(DBCommon.TIME_STEP),
                        resultSet.getDouble(DBCommon.TOTAL_TIME)
                ));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println();
            return Collections.EMPTY_LIST;
        }
    }

    public void addTask(Task task){
        String sqlQuery = String.format("INSERT INTO %s(%s,%s,%s) VALUES (?,?,?)",
                tableName, DBCommon.NAME, DBCommon.TIME_STEP, DBCommon.TOTAL_TIME);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {
            statement.setObject(1, task.getName());
            statement.setObject(2, task.getTimeStep());
            statement.setObject(3, task.getTotalTime());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_ADD_NEW_TASK);
        }
    }

    public void updateTask(Task newTask, String oldName){
        String sqlQuery = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s='%s'", tableName,
                DBCommon.NAME, DBCommon.TIME_STEP, DBCommon.TOTAL_TIME, DBCommon.NAME, oldName);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            statement.setObject(1, newTask.getName());
            statement.setObject(2, newTask.getTimeStep());
            statement.setObject(3, newTask.getTotalTime());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_UPDATE_TASK_DATA);
        }
    }

    public void deleteTask(String taskName){
        String sqlQuery = String.format("DELETE FROM %s WHERE %s='%s'", tableName, DBCommon.NAME, taskName);
        try{
            statement = connection.createStatement();
            statement.execute(sqlQuery);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_DELETE_TASK_DATA);
        }
    }

    public Task getTask(String taskName) {
        String sqlQuery = String.format("SELECT %s,%s,%s FROM %s WHERE %s='%s'",
                DBCommon.NAME, DBCommon.TIME_STEP, DBCommon.TOTAL_TIME, tableName, DBCommon.NAME, taskName);
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Task task = new Task(
                    resultSet.getString(DBCommon.NAME),
                    resultSet.getDouble(DBCommon.TIME_STEP),
                    resultSet.getDouble(DBCommon.TOTAL_TIME)
            );
            return task;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println(DBCommon.FAILED_TO_RETRIEVE_TASK_DATA);
            return new Task();
        }
    }

    public boolean checkName(String taskName){
        return false;
    }
}
