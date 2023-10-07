package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.GachaContent;
import space.yurisi.universecore.exception.GachaContentNotFoundException;

//ガチャとガチャアイテムを紐づけるテーブル
//ガチャによって同じガチャアイテムでも別レアリティにできるようにする

public class GachaContentRepository {

    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new GachaContent repository.
     *
     * @param sessionFactory session factory
     */
    public GachaContentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ガチャコンテンツを作成します。
     *
     * @param gacha_id Long
     * @param gacha_item_id  Long
     * @param rarelity   Long
     * @return gachaContent GachaContent
     */
    public GachaContent createGachaContent(Long gacha_id, Long gacha_item_id, Long rarelity) {
        GachaContent gachaContent = new GachaContent(null, gacha_id, gacha_item_id, rarelity, null, null);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(gachaContent);
        session.getTransaction().commit();
        session.close();
        return gachaContent;
    }

    /**
     * ガチャコンテンツを削除します。
     *
     * @param gachaContent GachaContent
     * @throws GachaContentNotFoundException
     */
    public void deleteGachaContent(GachaContent gachaContent) throws GachaContentNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(gachaContent);
        session.getTransaction().commit();
        session.close();
    }

}
