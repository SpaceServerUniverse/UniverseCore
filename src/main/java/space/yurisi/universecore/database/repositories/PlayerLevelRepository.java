package space.yurisi.universecore.database.repositories;

import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.PlayerLevel;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.exception.PlayerLevelNotFoundException;

import java.util.Date;

public class PlayerLevelRepository {

    private final SessionFactory sessionFactory;

    public PlayerLevelRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ユーザーに基づきレベルを作成します。
     *
     * @param player Player
     * @param user UserModel
     * @return User | null
     */
    public PlayerLevel createPlayerLevel(Player player, User user) {
        PlayerLevel userLevel = new PlayerLevel(
                null, user.getId(), 0L, 0, new Date(), new Date()
        );

        Session session = this.sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.persist(userLevel);//save
        session.getTransaction().commit();
        session.close();

        return userLevel;
    }

    /**
     * レベルデータをプライマリーキーから取得します。
     *
     * @param id Long(PrimaryKey)
     * @return User | null
     * @exception PlayerLevelNotFoundException レベルデータが存在しない
     */
    public PlayerLevel getPlayerLevel(Long id) throws PlayerLevelNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        PlayerLevel data = session.get(PlayerLevel.class, id);
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new PlayerLevelNotFoundException("ユーザーデータが存在しませんでした。 ID:" + id);
        }
        return data;
    }

    /**
     * レベルデータをユーザーモデルから取得します。
     *
     * @param user User
     * @return User | null
     * @exception PlayerLevelNotFoundException レベルデータが存在しない
     */
    public PlayerLevel getPlayerLevelFromUser(User user) throws  PlayerLevelNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        PlayerLevel data = session.createSelectionQuery("from PlayerLevel where user_id = ?1", PlayerLevel.class)
                .setParameter(1, user.getId()).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new PlayerLevelNotFoundException("ユーザーレベルデータが存在しませんでした。 UserId:" + user.getId());
        }
        return data;
    }


    /**
     * 指定したレベルデータがデータベースに存在するかを返します
     *
     * @param id Long
     * @return boolean
     */
    public boolean existsPlayerLevel(Long id) {
        try {
            getPlayerLevel(id);
            return true;
        } catch (PlayerLevelNotFoundException e) {
            return false;
        }
    }

    /**
     * 指定したユーザーのレベルデータがデータベースに存在するかを返します
     *
     * @param user User
     * @return boolean
     */
    public boolean existsPlayerLevelFromUser(User user) {
        try {
            getPlayerLevelFromUser(user);
            return true;
        } catch (PlayerLevelNotFoundException e) {
            return false;
        }
    }

    /**
     * プレイヤーレベルモデルに基づきデータをアップデートします。
     *
     * @param level PlayerLevel
     */
    public void updatePlayerLevel(PlayerLevel level) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(level);//update
        session.getTransaction().commit();
        session.close();
    }

    /**
     * プレイヤーレベルモデルに基づきデータを削除します。
     *
     * @param level PlayerLevel
     */
    public void deletePlayerLevel(PlayerLevel level) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(level); //delete
        session.getTransaction().commit();
        session.close();
    }
}
