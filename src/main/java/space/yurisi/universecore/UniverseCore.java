package space.yurisi.universecore;

import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import space.yurisi.universecore.database.repository.UserRepository;
import space.yurisi.universecore.database.model.User;
import space.yurisi.universecore.event.EventManager;
import space.yurisi.universecore.file.Config;

import static org.hibernate.cfg.JdbcSettings.*;

public final class UniverseCore extends JavaPlugin {
    private SessionFactory sessionFactory;
    private UserRepository userRepository;
    private Config config;

    @Override
    public void onEnable() {
        this.config = new Config(this);
        this.sessionFactory = buildSessionFactory();
        this.userRepository = new UserRepository(getSessionFactory());
        new EventManager(this);
    }

    @Override
    public void onDisable() {
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
        }
    }

    private SessionFactory buildSessionFactory(){
        Config config = getPluginConfig();
        String url = "jdbc:mysql://"+config.getDBHost()+":"+config.getDBPort()+"/SpaceServerUniverse";
        return new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty(DRIVER, "com.mysql.cj.jdbc.Driver")
                .setProperty(URL, url)
                .setProperty(USER, config.getDBUserName())
                .setProperty(PASS, config.getDBUserPassword())
                .setProperty(POOL_SIZE, "1")
                .setProperty(SHOW_SQL, "false")
                .setProperty(FORMAT_SQL, "false")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public Config getPluginConfig(){
        return this.config;
    }
}
