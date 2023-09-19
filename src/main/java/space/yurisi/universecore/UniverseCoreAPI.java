package space.yurisi.universecore;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.repository.UserRepository;

public class UniverseCoreAPI {
    private static UniverseCoreAPI api;

    private final SessionFactory sessionFactory;

    private final UserRepository userRepository;

    public UniverseCoreAPI(UniverseCore main){
        this.sessionFactory = main.getSessionFactory();
        this.userRepository = main.getUserRepository();
        api = this;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public static UniverseCoreAPI api(){
        return api;
    }
}
