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
        this.moneyHistoryRepository = new MoneyHistoryRepository(sessionFactory);
        this.moneyRepository = new MoneyRepository(sessionFactory, getMoneyHistoryRepository());
    }

    /**
     * ユーザーリポジトリを取得
     * @return UserRepository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * お金リポジトリの取得
     * @return MoneyRepository
     */
    public MoneyRepository getMoneyRepository() {
        return moneyRepository;
    }

    /**
     * お金履歴リポジトリを取得
     * @return MoneyHistoryRepository
     */
    public MoneyHistoryRepository getMoneyHistoryRepository() {
        return moneyHistoryRepository;
    }
}
