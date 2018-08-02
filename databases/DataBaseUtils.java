package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseUtils {

    private static Connection connection;
    private static Statement statement;

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
        } catch (SQLException e) {
            System.out.println("Failed to establish connection");
        }
        try {
            statement.execute(SQLStatements.getCreateSpecimensTableQuery());
            statement.execute(SQLStatements.getCreateMaterialsTableQuery());
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

    /**
     * Specimens Data Base Utils
     */

    public static List<SpecimenData> getAllSpecimens(){
        try(Statement statement = DataBaseUtils.connection.createStatement()){
            List<SpecimenData> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQLStatements.getGetAllSpecimenQuery());
            while (resultSet.next()){
                list.add(new SpecimenData(
                        resultSet.getString("name"),
                        resultSet.getInt("cellNumberX"),
                        resultSet.getInt("cellNumberY"),
                        resultSet.getInt("cellNumberZ"),
                        resultSet.getInt("numberOfGrains"),
                        resultSet.getDouble("angleRange"),
                        resultSet.getString("material"),
                        resultSet.getString("initialCondition"),
                        resultSet.getString("boundaryCondition"),
                        resultSet.getString("task")
                ));
            }
            return list;
        }
        catch (SQLException e){
            System.out.println("Failed to retrieve specimens data from databases");
            return Collections.emptyList();
        }
    }

    public static void addSpecimen(SpecimenData specimen){
        try (PreparedStatement statement =
                     DataBaseUtils.connection.prepareStatement(SQLStatements.getAddSpecimenTableQuery()))
        {
            statement.setObject(1, specimen.getSpecimenName());
            statement.setObject(2, specimen.getMaterial());
            statement.setObject(3, specimen.getCellNumberX());
            statement.setObject(4, specimen.getCellNumberY());
            statement.setObject(5, specimen.getCellNumberZ());
            statement.setObject(6, specimen.getNumberOfGrains());
            statement.setObject(7, specimen.getAngleRange());
            statement.setObject(8, specimen.getInitialConditions());
            statement.setObject(9, specimen.getBoundaryConditions());
            statement.setObject(10, specimen.getTask());

            // Выполняем запрос
            statement.execute();
            System.out.println("New Specimen added to database");
        } catch (SQLException e) {
            System.out.println("Failed to add new specimen to database");
            System.out.println(e.getMessage());
        }
    }

    //TODO: implement
    public static boolean checkStructure(String specimenName){
        return true;
    }

    /**
     *****************************************************
     */

    public static List<MaterialData> getAllMaterials(){
        try(Statement statement = DataBaseUtils.connection.createStatement()){
            List<MaterialData> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQLStatements.getGetAllMaterialsQuery());
            while (resultSet.next()){
                list.add(new MaterialData(
                        resultSet.getString("name"),
                        resultSet.getDouble("heatConductivity"),
                        resultSet.getDouble("density"),
                        resultSet.getDouble("heatExpansion"),
                        resultSet.getDouble("heatCapacity"),
                        resultSet.getDouble("phononPortion"),
                        resultSet.getDouble("angleLimitHAGB"),
                        resultSet.getDouble("energyHAGB"),
                        resultSet.getDouble("maxMobility"),
                        resultSet.getDouble("latticeParameter")
                ));
            }
            return list;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve material data from database");
            return Collections.emptyList();
        }
    }

    public static void addMaterial(MaterialData materialData){
        try(PreparedStatement statement =
                    DataBaseUtils.connection.prepareStatement(SQLStatements.getAddMaterialQuery()))
        {
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
            statement.execute();
            System.out.println("New Material added to database");
        }
        catch (SQLException e){
            System.out.println("Failed to add new material database");
            System.out.println(e.getMessage());
        }
    }

    public static void createInitialConditionTable(String specimenName){
        try{
            statement = connection.createStatement();
            statement.execute(SQLStatements.getCreateInitialConditionTableQuery(specimenName));
            System.out.println("New Initial Condition Table is created");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to create new intial conditions table");
        }
    }

    public static List<InitialCondition> getAllInitialConditions(String specimenName){
        try(Statement statement = DataBaseUtils.connection.createStatement()){
            List<InitialCondition> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQLStatements.getGetAllInitialConditionQuery(specimenName));
            list.add(
                new InitialCondition(
                        resultSet.getString("Name"),
                        resultSet.getDouble("Temperature"),
                        resultSet.getDouble("ElasticEnergy"),
                        resultSet.getDouble("DislocationDensity"),
                        resultSet.getDouble("MomentX"),
                        resultSet.getDouble("MomentY"),
                        resultSet.getDouble("MomentZ")
                )
            );
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve initial conditions for "+specimenName+" from database");
            return Collections.emptyList();
        }
    }

    public static void addInitialCondition(InitialCondition initialCondition, String specimenName){
        try(PreparedStatement preparedStatement =
                    DataBaseUtils.connection.prepareStatement(SQLStatements.getAddInitialConditionQuery(specimenName)))
        {
            preparedStatement.setObject(1, initialCondition.getName());
            preparedStatement.setObject(2, initialCondition.getTemperature());
            preparedStatement.setObject(3, initialCondition.getElasticEnergy());
            preparedStatement.setObject(4, initialCondition.getDislocationDensity());
            preparedStatement.setObject(5, initialCondition.getMomentX());
            preparedStatement.setObject(6, initialCondition.getMomentY());
            preparedStatement.setObject(7, initialCondition.getMomentZ());
            preparedStatement.execute();
            System.out.println("New Intial Condition data is added to database");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to add new initial condition data to database");
            new Alert(Alert.AlertType.ERROR, "Failed to add new initial condition data to database");
        }
    }

    // NullPointerException
    public static InitialCondition getInitialCondition(String specimenName, String initCondName){
        try(Statement statement = connection.createStatement())
        {
            ResultSet resultSet =
                    statement.executeQuery(SQLStatements.getGetInitialConditionQuery(specimenName, initCondName));

            InitialCondition initialCondition = new InitialCondition(
                    resultSet.getString("Name"),
                    resultSet.getDouble(""),
                    resultSet.getDouble(""),
                    resultSet.getDouble(""),
                    resultSet.getDouble(""),
                    resultSet.getDouble(""),
                    resultSet.getDouble("")
            );
            return initialCondition;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve initial condition data from database");
            return null;
        }
    }

    public static void createBoundaryConditionTable(String specimenName){
        try{
            statement = connection.createStatement();
            statement.execute(SQLStatements.getCreateBoundaryConditionTableQuery(specimenName));
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to create new boundary conditions table");
        }
    }

    public static void addNewBoundaryConditionName(String specimenName, String conditionName, BoundaryCondition boundaryCondition){

        try{
            statement = connection.createStatement();
            // creating new table for this specific boundary condition
            statement.execute(SQLStatements.getNewBoundaryConditionTableQuery(specimenName, conditionName));
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to add new boundary condition table");
        }

        // populate created table with data
        try (PreparedStatement statement
                     = connection.prepareStatement(SQLStatements.getAddBoundaryConditionQuery(specimenName, conditionName)))
        {
            statement.setObject(1, boundaryCondition.getFacet());
            statement.setObject(2, boundaryCondition.getAverageTemperature());
            statement.setObject(3, boundaryCondition.getTemperatureRange());
            statement.setObject(4, boundaryCondition.getTemperatureLoadingTimePortion());
            statement.setObject(5, boundaryCondition.getAverageElasticEnergy());
            statement.setObject(6, boundaryCondition.getElasticEnergyRange());
            statement.setObject(7, boundaryCondition.getElasticEnergyLoadingTimePortion());
            statement.setObject(8, boundaryCondition.getAverageDislocationDensity());
            statement.setObject(9, boundaryCondition.getDislocationDensityRange());
            statement.setObject(10, boundaryCondition.getDislocationDensityLoadingTimePortion());
            statement.setObject(11, boundaryCondition.getAverageMomentX());
            statement.setObject(12, boundaryCondition.getMomentRangeX());
            statement.setObject(13, boundaryCondition.getMomentLoadingTimePortionX());
            statement.setObject(14, boundaryCondition.getAverageMomentY());
            statement.setObject(15, boundaryCondition.getMomentRangeY());
            statement.setObject(16, boundaryCondition.getMomentLoadingTimePortionY());
            statement.setObject(17, boundaryCondition.getAverageMomentZ());
            statement.setObject(18, boundaryCondition.getMomentRangeZ());
            statement.setObject(19, boundaryCondition.getMomentLoadingTimePortionZ());
            statement.execute();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to add new boundary condition");
        }
    }

    public static List<String> getAllBoundaryConditionNames(String specimenName){
        try (Statement statement = connection.createStatement()){
            List<String> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQLStatements.getGetAllBoundaryConditionNamesQuery(specimenName));
            while (resultSet.next()){
                list.add(resultSet.getString("Name"));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve boundary conditions data");
            return Collections.emptyList();
        }
    }

    public static void createTaskTable(String specimenName){
        try {
            statement = connection.createStatement();
            statement.execute(SQLStatements.getCreateTaskTableQuery(specimenName));
            System.out.println("New Task Table is created");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to create new task table");
        }
    }

    public static void addNewTask(String specimenName, Task task){
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(SQLStatements.getAddTaskQuery(specimenName)))
        {
            preparedStatement.setObject(1, task.getName());
            preparedStatement.setObject(2, task.getTimeStep());
            preparedStatement.setObject(3, task.getTotalTime());
            preparedStatement.execute();
            System.out.println("New Task is added to database");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to add new task to database");
        }
    }

    public static List<Task> getAllTasks(String specimenName){
        try(Statement statement = connection.createStatement()){
            List<Task> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQLStatements.getAddTaskQuery(specimenName));
            while (resultSet.next()){
                list.add(
                        new Task(resultSet.getString("Name"),
                                 resultSet.getDouble("TimeStep"),
                                 resultSet.getDouble("TotalTime"))
                );
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve task data from database");
            return Collections.emptyList();
        }
    }

    public static List<Element> getSpecimenStructureData(String specimenName){
        try(Statement statement = connection.createStatement()){
            List<Element> list = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(SQLStatements.getGetSpecimenStructureDataQuery(specimenName));

            while (resultSet.next()){
                list.add(
                        new Element(
                                resultSet.getInt("GrainIndex"),
                                resultSet.getInt("LocationType"),
                                resultSet.getDouble("CoordinateX"),
                                resultSet.getDouble("CoordinateY"),
                                resultSet.getDouble("CoordinateZ"),
                                0.0
                        )
                );
            }

            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failder to retrieve data from specimen structure database");
            return Collections.emptyList();
        }
    }

    public static List<Integer> getTimeStepsList(String specimenName, String taskName){
        try(Statement statement = connection.createStatement()){
            List<Integer> list = new ArrayList<>();
            ResultSet resultSet =
                    statement.executeQuery(SQLStatements.getGetTimeStepsListQuery(specimenName, taskName));
            while (resultSet.next()){
                list.add(resultSet.getInt("TimeStep"));
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve time steps from database");
            return Collections.emptyList();
        }
    }

    public static List<Element> getResult(String specimenName, String taskName, int timeStep){
        try(Statement statement = connection.createStatement()){
            List<Element> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQLStatements.getResultQuery(specimenName, taskName, timeStep));
            while (resultSet.next()){
                list.add(new Element(
                        resultSet.getInt("LocationType"),
                        resultSet.getInt("GrainIndex"),
                        resultSet.getDouble("CoordinateX"),
                        resultSet.getDouble("CoordinateY"),
                        resultSet.getDouble("CoordinateZ"),
                        resultSet.getDouble("Temperature"))
                );
            }
            return list;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("Failed to retrieve results data from data");
            return Collections.emptyList();
        }
    }

    //TODO: дописать эти методы
    public static MaterialData getMaterial(String materialName){
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLStatements.getGetMaterialQuery(materialName));
            MaterialData materialData = new MaterialData(
                    resultSet.getString("name"),
                    resultSet.getDouble("heatConductivity"),
                    resultSet.getDouble("density"),
                    resultSet.getDouble("heatExpansion"),
                    resultSet.getDouble("heatCapacity"),
                    resultSet.getDouble("phononPortion"),
                    resultSet.getDouble("angleLimitHAGB"),
                    resultSet.getDouble("energyHAGB"),
                    resultSet.getDouble("maxMobility"),
                    resultSet.getDouble("latticeParameter")
            );
            return materialData;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public static BoundaryCondition getBoundaryCondition(String specimenName, String conditionName){
        return null;
    }

    public static Task getTask(String specimenName, String taskName){
        return null;
    }

    public static void deleteSpecimen(){

    }
    public static void deleteMaterial(){}
    public static void deleteInitialCondition(){}
    public static void deleteBoundaryCondition(){}
    public static void deleteTask(){}

}
