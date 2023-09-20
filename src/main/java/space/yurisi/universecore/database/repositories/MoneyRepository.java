package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.Money;
import space.yurisi.universecore.database.models.User;

import java.util.Date;

public class MoneyRepository {

    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new Money repository.
     *
     * @param sessionFactory session factory
     */
    public MoneyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ユーザーに基づきお金を作成します。
     *
     * @param user User
     * @return money Money
     */
    public Money createMoney(User user) {
        Money money = new Money(null, user.getId(), 1000L, new Date(), new Date());

        Session session = this.sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.persist(money);//save
        session.getTransaction().commit();
        session.close();

        return money;
    }

    /**
     * お金をプライマリーキーから取得します。
     * 存在しない場合はnullを返します。
     * @param id Long(PrimaryKey)
     * @return Money | null
     */
    public Money getMoney(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Money data = session.get(Money.class, id);
        session.getTransaction().commit();
        session.close();
        return data;
    }

    /**
     * お金をuser_idから取得します。
     * 存在しない場合はnullを返します。
     *
     * @param user_id Long
     * @return Money | null
     */
    public Money getMoneyFromUserId(Long user_id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Money data = session.createSelectionQuery("from Money where user_id = ?1", Money.class)
                .setParameter(1, user_id).getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        return data;
    }

    /**
     * 指定したプライマリーキーがデータベースに存在するかを返します
     * @param id Long(Primary key)
     * @return boolean
     */
    public boolean existsMoney(Long id){
        Money money = getMoney(id);
        return money != null;
    }

    /**
     * 指定したユーザーIDのカラムがデータベースに存在するかを返します
     * @param user_id Long
     * @return boolean
     */
    public boolean existsMoneyFromUserId(Long user_id){
        Money money = getMoneyFromUserId(user_id);
        return money != null;
    }

    /**
     * user_idからプライマリーキーのみを返します。
     * 存在しない場合はnullを返します。
     *
     * @param user_id Long
     * @return Long(PrimaryKey) long
     */
    public Long getPrimaryKeyFromUserId(Long user_id){
        Money money = this.getMoneyFromUserId(user_id);
        if(money == null){
            return null;
        }
        return money.getId();
    }

    /**
     * お金モデルに基づきデータをアップデートします。
     *
     * @param money Money
     */
    public void updateMoney(Money money) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(money);//update
        session.getTransaction().commit();
        session.close();
    }

    /**
     * お金モデルに基づきデータを削除します。
     *
     * @param money Money
     */
    public void deleteMoney(Money money) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(money); //delete
        session.getTransaction().commit();
        session.close();
    }
}
