package space.yurisi.universecore.database;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.repositories.MoneyRepository;
import space.yurisi.universecore.database.repositories.UserRepository;

public class DatabaseManager {

    private UserRepository userRepository;

    private MoneyRepository moneyRepository;


    public DatabaseManager(SessionFactory sessionFactory) {
        init(sessionFactory);
    }

    private void init(SessionFactory sessionFactory){
        this.userRepository = new UserRepository(sessionFactory);
        this.moneyRepository = new MoneyRepository(sessionFactory);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public MoneyRepository getMoneyRepository() {
        return moneyRepository;
    }
}
