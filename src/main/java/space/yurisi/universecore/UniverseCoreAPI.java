package space.yurisi.universecore;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.DatabaseConnector;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.universecore.database.repositories.UserRepository;

public class UniverseCoreAPI {
    private static UniverseCoreAPI api;

    private DatabaseManager manager;

    private DatabaseConnector connector;

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

    public static UniverseCoreAPI api(){
        return api;
    }
}
