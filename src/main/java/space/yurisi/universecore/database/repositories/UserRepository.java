package space.yurisi.universecore.database.repositories;

import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.User;

import java.util.Date;
import java.util.UUID;

public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User createUser(Player player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        User user = new User(null, uuid, name, null, null, new Date(), new Date());

        Session session = this.sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.persist(user);//save
        session.getTransaction().commit();
        session.close();

        return user;
    }

    public User getUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User data = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return data;
    }

    public User getUserFromUUID(UUID uuid){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User data = session.createSelectionQuery("from User where uuid = ?1", User.class)
                .setParameter(1, uuid.toString()).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        return data;
    }

    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(user);//update
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(user); //delete
        session.getTransaction().commit();
        session.close();
    }
}