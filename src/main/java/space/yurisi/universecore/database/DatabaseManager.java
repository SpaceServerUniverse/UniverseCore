package space.yurisi.universecore.database;

import space.yurisi.universecore.UniverseCore;
import space.yurisi.universecore.database.tables.UserTable;

public class DatabaseManager {

    private UniverseCore main;

    public DatabaseManager(UniverseCore main){
        this.main = main;
        createDatabaseConnection();
        createTales();
    }

    public void createDatabaseConnection(){
        new DatabaseConnection(
                main.getPluginConfig().getDBHost(),
                main.getPluginConfig().getDBPort(),
                main.getPluginConfig().getDBUserName(),
                main.getPluginConfig().getDBUserPassword()
        );
    }

    public void createTales(){
        new UserTable().createTable();
    }
}
