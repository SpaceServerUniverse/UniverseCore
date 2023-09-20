package space.yurisi.universecore.database.repositories;

import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.expection.UserNotFoundException;

import java.util.Date;
import java.util.UUID;


/**
 * The type User repository.
 *
 * @author yurisi
 * @version 1.0.0
 */
public class UserRepository {
    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new User repository.
     *
     * @param sessionFactory session factory
     */
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * プレイヤーデータに基づきユーザーを作成します。
     *
     * @param player Player
     * @return User user
     */
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

    /**
     * プレイヤーをプライマリーキーから取得します。
     * 存在しない場合はnullを返します。
     *
     * @param id Long(PrimaryKey)
     * @return User | null
     */
    public User getUser(Long id) throws UserNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User data = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new UserNotFoundException("ユーザーデータが存在しませんでした。 ID:" + id);
        }
        return data;
    }

    /**
     * プレイヤーをUUIDから取得します。
     * 存在しない場合はnullを返します。
     *
     * @param uuid UUID
     * @return User | null
     */
    public User getUserFromUUID(UUID uuid) throws UserNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User data = session.createSelectionQuery("from User where uuid = ?1", User.class)
                .setParameter(1, uuid.toString()).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new UserNotFoundException("ユーザーデータが存在しませんでした。 UUID:" + uuid);
        }
        return data;
    }

    /**
     * 指定したプライマリーキーがデータベースに存在するかを返します
     *
     * @param id Long(Primary key)
     * @return boolean
     */
    public boolean existsUser(Long id) {
        try {
            getUser(id);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    /**
     * 指定したUUIDがデータベースに存在するかを返します
     *
     * @param uuid UUID
     * @return boolean
     */
    public boolean existsUserFromUUID(UUID uuid) {
        try {
            getUserFromUUID(uuid);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    /**
     * UUIDからプライマリーキーのみを返します。
     * 存在しない場合はnullを返します。
     *
     * @param uuid UUID
     * @return Long(PrimaryKey) long
     */
    public Long getPrimaryKeyFromUUID(UUID uuid) throws UserNotFoundException {
        User user = this.getUserFromUUID(uuid);
        return user.getId();
    }

    /**
     * ユーザーモデルに基づきデータをアップデートします。
     *
     * @param user User
     */
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(user);//update
        session.getTransaction().commit();
        session.close();
    }

    /**
     * ユーザーモデルに基づきデータを削除します。
     *
     * @param user User
     */
    public void deleteUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(user); //delete
        session.getTransaction().commit();
        session.close();
    }
}