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

    /**
     * データベースマネージャーの取得
     *
     * @return manager データベースマネージャー
     */
    public DatabaseManager getDatabaseManager(){
        return this.manager;
    }

    /**
     * 自身のインスタンスを取得
     * @return api UniverseCoreAPI
     */
    public static UniverseCoreAPI getInstance(){
        return api;
    }
}
