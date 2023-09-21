package space.yurisi.universecore;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.DatabaseConnector;
import space.yurisi.universecore.database.DatabaseManager;

public class UniverseCoreAPI {
    private static UniverseCoreAPI api;

    private final DatabaseManager manager;

    private final DatabaseConnector connector;

    private final SessionFactory sessionFactory;

    public UniverseCoreAPI(DatabaseConnector connector){
        this.connector = connector;
        this.sessionFactory = connector.getSessionFactory();
        this.manager = new DatabaseManager(sessionFactory);
        api = this;
    }

    public DatabaseManager getDatabaseManager(){
        return this.manager;
    }

    public static UniverseCoreAPI getInstance(){
        return api;
    }
}
