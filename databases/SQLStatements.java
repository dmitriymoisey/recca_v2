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
}
