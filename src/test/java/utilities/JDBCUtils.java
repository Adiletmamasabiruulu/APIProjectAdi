package utilities;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {
    //method static
    //establishConnection()
    //runSQLQuery (String query)>> will  return listOfMaps
    //closeDatabase()
    //countRows(String query)>> will return int

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    //This method establishing  doing a connections with Oracle SQL Database
    public static void establishConnection() throws IOException, SQLException {
        connection= DriverManager.getConnection(
                Configuration.getProperties("dbHostname"),
                Configuration.getProperties("dbUsername"),
                Configuration.getProperties("dbPassword"));
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
    }

    /**
     * This method will run query to Database and will return the data as list of Maps
     * @param query
     * @return
     * @throws SQLException
     */
    public static List<Map<String,Object>> runSQLQuery(String query)throws SQLException{
        resultSet=statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        List<Map<String,Object>>ListOfMaps=new ArrayList<>();
        int columnCount=resultSetMetaData.getColumnCount();
        while (resultSet.next()){
            Map<String,Object>row=new HashMap<>();
            for(int i=1; i<=columnCount;i++){
                String columnName=resultSetMetaData.getColumnName(i);
                Object columnValue=resultSet.getObject(columnName);
                row.put(columnName,columnValue);
            }
            ListOfMaps.add(row);
        }
        return ListOfMaps;
    }

    /**
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public static int countRows(String query)throws SQLException{
        resultSet=statement.executeQuery(query);
        resultSet.last();
        return resultSet.getRow();
    }

    /**
     * This method will close Database connection,statement and resultSet
     * @throws SQLException
     */
    public  static void closeConnection()throws SQLException{
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(resultSet!=null){
            resultSet.close();
        }
    }

}
