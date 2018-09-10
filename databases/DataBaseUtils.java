package databases;

import utils.SpecimenData;

import java.sql.*;

public class DataBaseUtils {

    private static Connection connection;
    private static Statement statement;

    public static SpecimenDataBase specimenDataBase;
    public static MaterialDataBase materialDataBase;
    public static InitialConditionDataBase initialConditionDataBase;
    public static BoundaryConditionDataBase boundaryConditionDataBase;
    public static TaskDataBase taskDataBase;
    public static ResultDataBase resultDataBase;

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:TEST.db");
        } catch (SQLException e) {
            System.out.println("Failed to connect DataBase");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("DataBase is connected!");
    }

    public static void createDB(){
        try {
            statement = connection.createStatement();
            specimenDataBase = new SpecimenDataBase(connection, statement);
            specimenDataBase.createSpecimensTable();
            materialDataBase = new MaterialDataBase(connection, statement);
            materialDataBase.createMaterialTable();
            initialConditionDataBase = new InitialConditionDataBase(connection, statement);
            boundaryConditionDataBase = new BoundaryConditionDataBase(connection, statement);
            taskDataBase = new TaskDataBase(connection, statement);
        } catch (SQLException e) {
            System.out.println("Failed to establish connection");
        }

        try {
            statement.execute(SQLStatements.getCreateSpecimensTableQuery());
        } catch (SQLException e) {
            System.out.println("Failed to create tables");
        }
        System.out.println("All tables is created!");
    }

    public static void closeDB(){
        try {
            statement.close();
            connection.close();
            System.out.println("Database connections are closed!");
        } catch (SQLException e) {
            System.out.println("Failed to close database connection");
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkStructure(String specimenName){
        String tableName = String.format("%s_%s", specimenName, "StructureData");
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        try {
            statement.execute(sqlQuery);
            return true;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean checkResults(SpecimenData specimenData){
        String tableName = String.format("%s_%s", specimenData.getSpecimenName(), specimenData.getTask());
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        try{
            statement.execute(sqlQuery);
            return true;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static void setResultsData(String specimenName, String taskName) {
        resultDataBase = new ResultDataBase(connection, specimenName, taskName);
    }
}
