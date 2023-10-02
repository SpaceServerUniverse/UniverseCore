package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.Land;
import space.yurisi.universecore.database.models.LandPermission;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.expection.LandPermissionNotFoundException;

import java.util.Date;


/**
 * The type Land repository.
 *
 * @author yurisi
 * @version 1.0.0
 */
public class LandPermissionRepository {
    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new User repository.
     *
     * @param sessionFactory session factory
     */
    public LandPermissionRepository (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 土地保護の権限データを作成します。
     *
     * @param user User
     * @param land Land
     * @return landPermission LandPermission
     */
    public LandPermission createLandPermission (User user, Land land) {
        LandPermission landPermission = new LandPermission (null, land.getId (), user.getId (), new Date ());

        Session session = this.sessionFactory.getCurrentSession ();

        session.beginTransaction ();
        session.persist (landPermission);//save
        session.getTransaction ().commit ();
        session.close ();

        return landPermission;
    }

    /**
     * 土地保護の権限データを取得します
     *
     * @param user User
     * @param land Land
     * @return Land | null
     * @throws LandPermissionNotFoundException 土地保護の権限データが存在しない
     */
    public LandPermission getLandPermission (User user, Land land) throws LandPermissionNotFoundException {
        Session session = this.sessionFactory.getCurrentSession ();
        session.beginTransaction ();
        LandPermission data = session.createSelectionQuery ("from LandPermission where land_id = ?1 and user_id = ?2", LandPermission.class)
                .setParameter (1, land.getId ())
                .setParameter (2, user.getId ())
                .getSingleResultOrNull ();
        session.getTransaction ().commit ();
        session.close ();
        if (data == null) {
            throw new LandPermissionNotFoundException ("土地保護の権限データが存在しませんでした。");
        }
        return data;
    }

    /**
     * 土地保護の権限データを削除します。
     *
     * @param landPermission LandPermission
     */
    public void deleteLandPermission (LandPermission landPermission) {
        Session session = this.sessionFactory.getCurrentSession ();
        session.beginTransaction ();
        session.remove (landPermission); //delete
        session.getTransaction ().commit ();
        session.close ();
    }
}