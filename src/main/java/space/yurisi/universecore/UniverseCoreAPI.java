package space.yurisi.universecore;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.DatabaseConnector;
import space.yurisi.universecore.database.repositories.UserRepository;

public class UniverseCoreAPI {
    private static UniverseCoreAPI api;

    private DatabaseConnector connector;

    private final SessionFactory sessionFactory;

    private final UserRepository userRepository;

    public UniverseCoreAPI(DatabaseConnector connector){
        this.connector = connector;
        this.sessionFactory = connector.getSessionFactory();
        this.userRepository = new UserRepository(sessionFactory);
        api = this;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public static UniverseCoreAPI api(){
        return api;
    }
}
