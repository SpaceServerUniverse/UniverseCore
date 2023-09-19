package space.yurisi.universecore.database;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.logging.javautil.JavaUtilLogCreator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import space.yurisi.universecore.database.models.User;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.JdbcSettings.JDBC_TIME_ZONE;

public class DatabaseConnector {

    private String jdbc;
    private String host;
    private int port;
    private String user;
    private String password;

    private SessionFactory sessionFactory;

    public DatabaseConnector(String host, int port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.jdbc = "jdbc:mysql://" + host + ":" + port;
        init();
    }

    private void init() {
        migrate();
        this.sessionFactory = buildSessionFactory();
    }

    private void migrate() {
        LogFactory.setLogCreator(new JavaUtilLogCreator());
        Flyway.configure(getClass().getClassLoader())
                .dataSource(jdbc, this.user, this.password)
                .baselineVersion("2023.09.20.01")
                .locations("classpath:db/migration/")
                .schemas("flyway_schema")
                .load().migrate();
    }

    private SessionFactory buildSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty(DRIVER, "com.mysql.cj.jdbc.Driver")
                .setProperty(URL, jdbc + "/SpaceServerUniverse")
                .setProperty(USER, this.user)
                .setProperty(PASS, this.password)
                .setProperty(POOL_SIZE, "1")
                .setProperty(SHOW_SQL, "false")
                .setProperty(FORMAT_SQL, "false")
                .setProperty(JDBC_TIME_ZONE, "Asia/Tokyo")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.hbm2ddl.auto", "none")
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
        }
    }
}
