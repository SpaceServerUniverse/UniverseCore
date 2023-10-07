package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.GachaInfo;
import space.yurisi.universecore.exception.GachaInfoNotFoundException;

//ガチャを格納するテーブル
//ガチャの名前とか、説明とか、価格とか、引けるかどうかを格納する

public class GachaInfoRepository {

    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new GachaInfo repository.
     *
     * @param sessionFactory session factory
     */
    public GachaInfoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ガチャを作成します。
     *
     * @param gacha_name        String
     * @param gacha_description String
     * @param price             Long
     * @param is_active         Boolean
     * @return gachaInfo GachaInfo
     */
    public GachaInfo createGachaInfo(String gacha_name, String gacha_description, Long price, Boolean is_active) {
        GachaInfo gachaInfo = new GachaInfo(null, gacha_name, gacha_description, price, is_active, null, null);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(gachaInfo);
        session.getTransaction().commit();
        session.close();
        return gachaInfo;
    }

    /**
     * ガチャを削除します。
     *
     * @param gachaInfo GachaInfo
     * @throws GachaInfoNotFoundException
     */
    public void deleteGachaInfo(GachaInfo gachaInfo) throws GachaInfoNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(gachaInfo);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * ガチャをプライマリーキーから取得します。
     * @param id Long
     * @return gachaInfo GachaInfo
     * @throws GachaInfoNotFoundException
     */
    public GachaInfo getGachaInfo(Long id) throws GachaInfoNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        GachaInfo gachaInfo = session.get(GachaInfo.class, id);
        session.getTransaction().commit();
        session.close();
        if (gachaInfo == null) {
            throw new GachaInfoNotFoundException("ガチャが見つかりませんでした。");
        }
        return gachaInfo;
    }
}
