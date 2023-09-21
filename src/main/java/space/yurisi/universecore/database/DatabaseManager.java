package space.yurisi.universecore.database;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.repositories.MoneyHistoryRepository;
import space.yurisi.universecore.database.repositories.MoneyRepository;
import space.yurisi.universecore.database.repositories.UserRepository;

public class DatabaseManager {

    private final UserRepository userRepository;

    private final MoneyRepository moneyRepository;
    private final MoneyHistoryRepository moneyHistoryRepository;


    public DatabaseManager(SessionFactory sessionFactory) {
        this.userRepository = new UserRepository(sessionFactory);
        this.moneyRepository = new MoneyRepository(sessionFactory);
        this.moneyHistoryRepository = new MoneyHistoryRepository(sessionFactory);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public MoneyRepository getMoneyRepository() {
        return moneyRepository;
    }

    public MoneyHistoryRepository getMoneyHistoryRepository() {
        return moneyHistoryRepository;
    }
}
