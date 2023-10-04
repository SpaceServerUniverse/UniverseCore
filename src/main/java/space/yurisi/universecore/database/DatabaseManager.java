package space.yurisi.universecore.database;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.repositories.*;

public class DatabaseManager {

    private final UserRepository userRepository;
    private final MoneyRepository moneyRepository;
    private final MoneyHistoryRepository moneyHistoryRepository;
    private final LandRepository landRepository;
    private final LandPermissionRepository landPermissionRepository;

    public DatabaseManager(SessionFactory sessionFactory) {
        this.userRepository = new UserRepository(sessionFactory);
        this.moneyHistoryRepository = new MoneyHistoryRepository(sessionFactory);
        this.moneyRepository = new MoneyRepository(sessionFactory, getMoneyHistoryRepository());
        this.landRepository = new LandRepository(sessionFactory);
        this.landPermissionRepository = new LandPermissionRepository(sessionFactory);
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

    /**
     * 土地保護のリポジトリを取得
     * @return LandRepository
     */
    public LandRepository getLandRepository() {
        return landRepository;
    }

    /**
     * 土地保護の権限リポジトリを取得
     * @return LandPermissionRepository
     */
    public LandPermissionRepository getLandPermissionRepository() {
        return landPermissionRepository;
    }
}
