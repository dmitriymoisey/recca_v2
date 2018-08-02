package databases;

public class SQLStatements {

    /**
     * Specimen Table Queries
     */
    public static String getCreateSpecimensTableQuery(){
        String query = "CREATE TABLE if not EXISTS 'specimens'" +
                " ('id' INTEGER PRIMARY KEY, " +
                "'name' text," +
                "'material' text," +
                "'cellNumberX' INT," +
                "'cellNumberY' INT," +
                "'cellNumberZ' INT," +
                "'numberOfGrains' INT," +
                "'angleRange' REAL," +
                "'initialCondition' text," +
                "'boundaryCondition' text," +
                "'task' text);";
        return query;
    }

    public static String getAddSpecimenTableQuery(){
        String query = "INSERT INTO specimens(`name`, `material`, `cellNumberX`, `cellNumberY`,`cellNumberZ`,`numberOfGrains`,`angleRange`,`initialCondition`,`boundaryCondition`,`task`) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return query;
    }

    public static String getGetAllSpecimenQuery(){
        String query = "SELECT name, material, cellNumberX, cellNumberY, cellNumberZ, numberOfGrains, angleRange, initialCondition, boundaryCondition, task FROM specimens";
        return query;
    }

    /**
     ******************************************************
     */


    /**
     * Material Table Queries
     */
    public static String getCreateMaterialsTableQuery(){
        String query = "CREATE TABLE if not EXISTS 'materials'" +
                "('id' INTEGER PRIMARY KEY," +
                "'name' text," +
                "'heatConductivity' REAL," +
                "'density' REAL," +
                "'heatExpansion' REAL," +
                "'heatCapacity' REAL," +
                "'phononPortion' REAL," +
                "'angleLimitHAGB' REAL," +
                "'energyHAGB' REAL," +
                "'maxMobility' REAL," +
                "'latticeParameter' REAL);";
        return query;
    }

    public static String getAddMaterialQuery(){
        String query = "INSERT INTO materials(`name`, `heatConductivity`, `density`, `heatExpansion`, `heatCapacity`, `phononPortion`, `angleLimitHAGB`, `energyHAGB`, `maxMobility`, `latticeParameter`) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        return query;
    }

    public static String getGetMaterialQuery(String materialName){
        String query = "SELECT name, heatConductivity, density, heatExpansion, heatCapacity," +
                "phononPortion, angleLimitHAGB, energyHAGB, maxMobility, latticeParameter " +
                "FROM materials WHERE name=="+materialName;
        return query;
    }

    public static String getGetAllMaterialsQuery(){
        return "SELECT name, heatConductivity, density, heatExpansion, heatCapacity," +
                "phononPortion, angleLimitHAGB, energyHAGB, maxMobility, latticeParameter FROM materials";
    }

    /**
     ******************************************************
     */


    /**
     * Initial Conditions Table Queries
     */
    public static String getCreateInitialConditionTableQuery(String specimenName){
        String query = "CREATE TABLE if not exists '"+specimenName+"_InitialCondition'" +
                "('id' INTEGER PRIMARY KEY," +
                "'Name' text," +
                "'Temperature' REAL," +
                "'ElasticEnergy' REAL," +
                "'DislocationDensity' REAL," +
                "'MomentX' REAL," +
                "'MomentY' REAL," +
                "'MomentZ' REAL);";
        return query;
    }

    public static String getAddInitialConditionQuery(String specimenName){
        String query = "INSERT INTO "+specimenName+"_InitialCondition(`Name`,`Temperature`,`ElasticEnergy`,`DislocationDensity`,`MomentX`,`MomentY`,`MomentZ`) " +
                "VALUES(?,?,?,?,?,?,?)";
        return query;
    }

    public static String getGetInitialConditionQuery(String specimenName, String conditionName){
        String query = "SELECT Name, Temperature, ElasticEnergy, DislocationDensity, MomentX, MomentY, MomentZ FROM "+specimenName+"_InitialCondition WHERE Name=="+conditionName;;
        return query;
    }

    public static String getGetAllInitialConditionQuery(String specimenName){
        String query = "SELECT Name, Temperature, ElasticEnergy, DislocationDensity, MomentX, MomentY, MomentZ FROM "+specimenName+"_InitialCondition";
        return query;
    }

    /**
     ******************************************************
     */


    /**
     * Boundary Conditions Table Queries
     */
    public static String getCreateBoundaryConditionTableQuery(String specimenName){
        String query = "CREATE TABLE if not exists '"+specimenName+"_BoundaryConditions'('id' INTEGER PRIMARY KEY, 'Name' text);";
        return query;
    }

    public static String getNewBoundaryConditionTableQuery(String specimenName, String boundaryCondName){
        String query = "CREATE TABLE if not exists '"+specimenName+"_BoundaryCondition"+"_"+boundaryCondName+
                "'('id' INTEGER PRIMARY KEY, " +
                "'Facet' text," +
                "'TemperatureAverage' REAL," +
                "'TemperatureDeviation' REAL," +
                "'TemperatureLoadTime' REAL," +
                "'ElasticEnergyAverage' REAL," +
                "'ElasticEnergyDeviation' REAL," +
                "'ElasticEnergyLoadTime' REAL," +
                "'DislocationDensityAverage' REAL," +
                "'DislocationDensityDeviation' REAL," +
                "'DislocationDensityLoadTime' REAL," +
                "'XmomentAverage' REAL," +
                "'XmomentDeviation' REAL," +
                "'XmomentLoadTime' REAL," +
                "'YmomentAverage' REAL," +
                "'YmomentDeviation' REAL," +
                "'YmomentLoadTime' REAL," +
                "'ZmomentAverage' REAL," +
                "'ZmomentDeviation' REAL," +
                "'ZmomentLoadTime' REAL);";
        return query;
    }

    public static String getAddBoundaryConditionQuery(String specimenName, String boundaryCondName){
        String query = "INSERT INTO "+specimenName+"_BoundaryCondition"+"_"+boundaryCondName+"" +
                "(`Facet`,`TemperatureAverage`,`TemperatureDeviation`,`TemperatureLoadTime`," +
                "`ElasticEnergyAverage`,`ElasticEnergyDeviation`,`ElasticEnergyLoadTime`," +
                "`DislocationDensityAverage`,`DislocationDensityDeviation`,`DislocationDensityLoadTime`," +
                "`XmomentAverage`,`XmomentDeviation`,`XmomentLoadTime`," +
                "`YmomentAverage`,`YmomentDeviation`,`YmomentLoadTime`," +
                "`ZmomentAverage`,`ZmomentDeviation`,`ZmomentLoadTime`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return query;
    }

    public static String getGetAllBoundaryConditionNamesQuery(String specimenName){
        String query = "SELECT Name FROM "+specimenName+"_BoundaryConditions";
        return query;
    }

    /**
     ******************************************************
     */


    /**
     * Tasks Table Queries
     */
    public static String getCreateTaskTableQuery(String specimenName){
        String query = "CREATE TABLE if not exists '"+specimenName + "_Task'" +
                "('id' INTEGER PRIMARY KEY," +
                "'Name' text," +
                "'TimeStep' REAL," +
                "'TotalTime' REAL);";
        return query;
    }

    public static String getAddTaskQuery(String specimenName){
        String query = "INSERT INTO "+specimenName+"_Task(`Name`,`TimeStep`,`TotalTime`) VALUES(?,?,?)";
        return query;
    }

    public static String getGetAllTaskQuery(String specimenName){
        String query = "SELECT Name, TimeStep, TotalTime FROM "+specimenName+"_Task";
        return query;
    }

    /**
     ******************************************************
     */


    /**
     * Results Table Queries
     * @return
     */
    public static String getResultQuery(String specimenName, String taskName, int timeStep){
        String tableName = specimenName+"_"+taskName+"_"+String.valueOf(timeStep) ;
        String query = "SELECT * FROM " + tableName;
        return query;
    }

    public static String getGetSpecimenStructureDataQuery(String specimenName){
        return "SELECT * FROM "+specimenName+"_StructureData";
    }

    public static String getGetTimeStepsListQuery(String specimenName, String taskName){
        return "SELECT TimeStep FROM "+specimenName+"_"+taskName+";";
    }

    /**
     ******************************************************
     */


    //TODO: дописать данные методы
    public static String getDeleteSpecimenQuery(){
        String query = "";
        return query;
    }
    public static String getDeleteMaterialQuery(){
        String query = "";
        return query;
    }
    public static String getDeleteInitialConditionQuery(){
        String query = "";
        return query;
    }
    public static String getDeleteBoundaryConditionQuery(){
        String query = "";
        return query;
    }
    public static String getDeleteTaskQuery(){
        String query = "";
        return query;
    }

}
