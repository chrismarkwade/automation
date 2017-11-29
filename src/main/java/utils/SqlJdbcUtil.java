package utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chris Wade
 */

public class SqlJdbcUtil {
    
    private static final Logger log = LogManager.getLogger();
    private static SQLServerDataSource dataSource;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    
    private static String serverName = "localhost";
    private static int portNumber = 1433;
    private static String user = "*********";
    private static String password = "*********";
    
    private SqlJdbcUtil() {
        // Suppress default constructor for noninstantiability
    }
    
    private static SQLServerDataSource getDataSource() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(serverName);
        dataSource.setPortNumber(portNumber);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    private static Connection getConnection() {
        if (connection == null) {
            try {
                dataSource = getDataSource();
                connection = dataSource.getConnection();
                log.info("Connected to SQL Server");
            }
            catch (SQLException ex) {
                log.error(ex);
            }
        }
        return connection;
    }
    
    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                log.info("Connection to SQL Server closed");
            }
            catch (SQLException ex) {
                log.error(ex);
            }
        }
    }
    
    private static Statement getStatement() {
        try {
            connection = getConnection();
            statement = connection.createStatement();
        }
        catch (SQLException ex) {
            log.error(ex);
        }
        return statement;
    }
    
    private static void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException ex) {
                log.error(ex);
            }
        }
    }
    
    public static ResultSet executeQuery(String sql) {
        try {
            statement = getStatement();
            resultSet = statement.executeQuery(sql);
        }
        catch (SQLException ex) {
            log.error(ex);
        }
        return resultSet;
    }
    
    private static void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            }
            catch (SQLException ex) {
                log.error(ex);
            }
        }
    }
    
    public static List<String> getColumnValues(int columnIndex, ResultSet resultSet) {
        
        List<String> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String columnValue = resultSet.getString(columnIndex).trim();
                list.add(columnValue);
            }
        }
        catch (SQLException ex) {
            log.error(ex);
        }
        finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
        return list;
    }
    
    public static String getRandomRowColumnValue(int columnIndex, ResultSet resultSet) {
        List<String> list = getColumnValues(columnIndex, resultSet);
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }
    
    public static String getRowColumnValue(int rowIndex, int columnIndex, ResultSet resultSet) {
        List<String> list = getColumnValues(columnIndex, resultSet);
        return list.get(rowIndex - 1);
    }
    
    public static int getRowColumnIntegerValue(int rowIndex, int columnIndex, ResultSet resultSet) {
        List<String> list = getColumnValues(columnIndex, resultSet);
        String s = list.get(rowIndex - 1);
        return Integer.parseInt(s);
    }
    
    public static double getRowColumnDoubleValue(int rowIndex, int columnIndex, ResultSet resultSet) {
        List<String> list = getColumnValues(columnIndex, resultSet);
        String s = list.get(rowIndex - 1);
        return Double.parseDouble(s);
    }
    
}
