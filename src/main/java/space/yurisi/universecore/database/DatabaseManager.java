package space.yurisi.universecore.database;

import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.repositories.*;

public class DatabaseManager {

    private final UserRepository userRepository;
    private final MoneyRepository moneyRepository;
    private final MoneyHistoryRepository moneyHistoryRepository;
    private final LandRepository landRepository;
    private final LandPermissionRepository landPermissionRepository;
    private final MywarpRepository mywarpRepository;
    private final PlayerLevelRepository playerLevelRepository;
    private final PlayerNormalLevelRepository playerNormalLevelRepository;

    public DatabaseManager(SessionFactory sessionFactory) {
        this.userRepository = new UserRepository(sessionFactory);
        this.moneyHistoryRepository = new MoneyHistoryRepository(sessionFactory);
        this.moneyRepository = new MoneyRepository(sessionFactory, getMoneyHistoryRepository());
        this.landRepository = new LandRepository(sessionFactory);
        this.landPermissionRepository = new LandPermissionRepository(sessionFactory);
        this.mywarpRepository = new MywarpRepository(sessionFactory, getUserRepository());
        this.playerLevelRepository = new PlayerLevelRepository(sessionFactory);
        this.playerNormalLevelRepository = new PlayerNormalLevelRepository(sessionFactory);
    }

    /**
     * ユーザーリポジトリを取得
     *
     * @return UserRepository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * お金リポジトリの取得
     *
     * @return MoneyRepository
     */
    public MoneyRepository getMoneyRepository() {
        return moneyRepository;
    }

    /**
     * お金履歴リポジトリを取得
     *
     * @return MoneyHistoryRepository
     */
    public MoneyHistoryRepository getMoneyHistoryRepository() {
        return moneyHistoryRepository;
    }

    /**
     *
     * 土地保護のリポジトリを取得
     *
     * @return LandRepository
     */
    public LandRepository getLandRepository() {
        return landRepository;
    }

    /**
     * 土地保護の権限リポジトリを取得
     *
     * @return LandPermissionRepository
     */
    public LandPermissionRepository getLandPermissionRepository() {
        return landPermissionRepository;
    }

    /**
     * マイワープリポジトリを取得
     *
     * @return MywarpRepository
     */
    public MywarpRepository getMywarpRepository() {
        return mywarpRepository;
    }
  
    public PlayerLevelRepository getPlayerLevelRepository() {
        return playerLevelRepository;
    }

    public PlayerNormalLevelRepository getPlayerNormalLevelRepository() {
        return playerNormalLevelRepository;
    }
}
