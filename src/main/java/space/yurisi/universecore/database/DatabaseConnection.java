package space.yurisi.universecore.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private final String username, password, url;

    public DatabaseConnection(String host, int port, String username, String password) {
        String database = "SpaceServerUniverse";
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        openConnection();
        instance = this;
    }

    private void openConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            synchronized (this) {
                if (connection != null && !connection.isClosed()) {
                    return;
                }
                try {
                    Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                connection = DriverManager.getConnection(this.url, this.username, this.password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        openConnection();
        return this.connection;
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }
}
