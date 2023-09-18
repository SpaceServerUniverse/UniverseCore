package space.yurisi.universecore.database.tables;

import space.yurisi.universecore.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTable {

    protected Connection connection;

    protected Connection getConnection(){
        return DatabaseConnection.getInstance().getConnection();
    }

    public boolean execute(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.execute(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    protected String getInsertStringFormat(String data, boolean last){
        if(last){
            return "'" + data + "'";
        }
        return "'" + data + "'" + ",";
    }


    protected String getNowTime(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}
