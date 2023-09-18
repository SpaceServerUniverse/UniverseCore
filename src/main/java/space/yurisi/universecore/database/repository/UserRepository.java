package space.yurisi.universecore.database.repository;

import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User createUser(Player player) {
        String id = player.getUniqueId().toString();
        String name = player.getName();
        String datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Tokyo")));
        User user = new User(id, name, null, null, datetime, datetime);

        Session session = this.sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.persist(user);//save
        session.getTransaction().commit();
        session.close();

        return user;
    }

    public User getPlayerData(Player player) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User data = session.get(User.class, player.getUniqueId().toString());
        session.getTransaction().commit();
        session.close();
        return data;
    }

    public void updatePlayerData(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(user);//update
        session.getTransaction().commit();
        session.close();
    }

    public void deletePlayerData(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(user); //delete
        session.getTransaction().commit();
        session.close();
    }
}