package databases;

import utils.PlotData;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlotsDataBase {

    private Connection connection;
    private Statement statement;
    private String specimenName, taskName;
    private String tableName;

    public PlotsDataBase(Connection connection, String specimenName, String taskName){
        this.connection = connection;
        this.specimenName = specimenName;
        this.taskName = taskName;
        this.tableName = specimenName + "_" + taskName + "_" + DBCommon.PLOT_DATA;
    }

    public void createTable(){
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS ");
        try {
            this.statement = connection.createStatement();
            statement.execute(sqlQuery);
        }
        catch (SQLException ex){

        }
    }

    public boolean checkData(){
        return false;
    }

    public void addPlotData(PlotData plotData){

    }

    public PlotData getPlotData(){
        return new PlotData();
    }
}
