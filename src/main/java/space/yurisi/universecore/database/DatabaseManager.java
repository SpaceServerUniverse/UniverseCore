package space.yurisi.universecore.database;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.repositories.UserRepository;

public class DatabaseManager {

    private UserRepository userRepository;


    public DatabaseManager(SessionFactory sessionFactory) {
        init(sessionFactory);
    }

    private void init(SessionFactory sessionFactory){
        this.userRepository = new UserRepository(sessionFactory);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
