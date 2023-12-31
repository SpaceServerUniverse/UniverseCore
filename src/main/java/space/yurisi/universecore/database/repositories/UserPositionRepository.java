package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.database.models.UserPosition;
import space.yurisi.universecore.exception.PositionNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;
import space.yurisi.universecore.exception.UserPositionNotFoundException;

public class UserPositionRepository {
    private final SessionFactory sessionFactory;

    public UserPositionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ユーザーの役職をユーザーIDから取得します。
     *
     * @param id primary_key
     * @return Name
     * @exception UserPositionNotFoundException ユーザーの役職が存在しない
     */
    public UserPosition getUserPositionFromUserId(Long id) throws UserPositionNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        UserPosition data = session.createSelectionQuery("from UserPosition where user_id = ?1", UserPosition.class)
                .setParameter(1, id).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new UserPositionNotFoundException("役職ユーザーデータが存在しませんでした。 ID:" + id);
        }
        return data;
    }

    /**
     * ユーザーの役職をユーザーモデルから取得します。
     *
     * @param user User
     * @return Name
     * @exception UserPositionNotFoundException ユーザーの役職が存在しない
     */
    public UserPosition getUserPositionFromUser(User user) throws UserPositionNotFoundException {
        Long id = user.getId();
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        UserPosition data = session.createSelectionQuery("from UserPosition where user_id = ?1", UserPosition.class)
                .setParameter(1, id).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new UserPositionNotFoundException("役職ユーザーデータが存在しませんでした。 ID:" + id);
        }
        return data;
    }
}
