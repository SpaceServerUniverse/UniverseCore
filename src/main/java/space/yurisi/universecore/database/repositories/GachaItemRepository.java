package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.GachaItem;
import space.yurisi.universecore.exception.GachaItemNotFoundException;

import java.util.ArrayList;

//ガチャアイテムの情報を格納するテーブル
//minecraft側のitemidとか、名前とか、アイテムの量とか、エンチャントの情報とかを格納する
//エンチャントは付与されるものを1、付与されないものを0としてStringに結合して格納する

public class GachaItemRepository {

    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new GachaItem repository.
     *
     * @param sessionFactory session factory
     */
    public GachaItemRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ガチャアイテムを作成します。
     *
     * @param item_id     Long
     * @param item_name   String
     * @param item_amount Long
     * @param enchantment       String
     * @return gachaItem GachaItem
     */
    public GachaItem createGachaItem(Long item_id, String item_name, Long item_amount, String enchantment) {
        GachaItem gachaItem = new GachaItem(null, item_id, item_name, item_amount, enchantment, null, null);
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(gachaItem);
        session.getTransaction().commit();
        session.close();
        return gachaItem;
    }

    /**
     * ガチャアイテムを取得します。
     *
     * @param id Long
     * @return gachaItem GachaItem
     * @throws GachaItemNotFoundException GachaItemNotFoundException
     */
    public GachaItem getGachaItem(Long id) throws GachaItemNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        GachaItem gachaItem = session.get(GachaItem.class, id);
        session.getTransaction().commit();
        session.close();
        if (gachaItem == null) {
            throw new GachaItemNotFoundException("アイテムが見つかりませんでした。");
        }
        return gachaItem;
    }

    /**
     * ガチャアイテムのエンチャントIDのリストを取得します。
     * エンチャントIDのリストが空の場合は-1を返します。
     *
     * @param gachaItem GachaItem
     * @throws GachaItemNotFoundException GachaItemNotFoundException
     * @return enchantmentIDList ArrayList<Integer>
     */
    public ArrayList<Integer> getEnchantmentIDList(GachaItem gachaItem) throws GachaItemNotFoundException {
        String enchantment = gachaItem.getEnchantment();
        String[] enchantmentList = enchantment.split(",");
        ArrayList<Integer> enchantmentIDList = new ArrayList<>();
        for (int i = 0; i < enchantmentList.length; i++) {
            if (enchantmentList[i].equals("1")) {
                enchantmentIDList.add(i);
            }
        }
        if (enchantmentIDList.isEmpty()) {
            enchantmentIDList.add(-1);
        }
        return enchantmentIDList;
    }

    /**
     * ガチャアイテムの量を取得します。
     * @param gachaItem GachaItem
     * @throws GachaItemNotFoundException GachaItemNotFoundException
     * @return gachaItemAmount Long
     */
    public Long getGachaItemAmount(GachaItem gachaItem) throws GachaItemNotFoundException {
        Long gachaItemAmount = gachaItem.getItem_amount();
        if (gachaItemAmount == null) {
            throw new GachaItemNotFoundException("アイテムが見つかりませんでした。");
        }
        return gachaItemAmount;
    }

    /**
     * ガチャアイテムを削除します。
     * @param gachaItem GachaItem
     * @throws GachaItemNotFoundException
     */
    public void deleteGachaItem(GachaItem gachaItem) throws GachaItemNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(gachaItem);
        session.getTransaction().commit();
        session.close();
    }
}
