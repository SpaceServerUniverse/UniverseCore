package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.Money;
import space.yurisi.universecore.database.models.MoneyHistory;
import space.yurisi.universecore.exception.MoneyHistoryNotFoundException;
import space.yurisi.universecore.exception.MoneyNotFoundException;

import java.util.Date;
import java.util.List;

public class MoneyHistoryRepository {
    private final SessionFactory sessionFactory;

    /**
     * Instantiates a new Money repository.
     *
     * @param sessionFactory session factory
     */
    public MoneyHistoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * ユーザーとお金の変化に基づきお金履歴を作成します。
     *
     * @param money Money
     * @param change Long ユーザーのお金の変化値
     * @param reason String 理由(255文字以下)
     * @return money_history MoneyHistory
     */
    public MoneyHistory createMoneyHistory(Money money, Long change, String reason) {
        MoneyHistory money_history = new MoneyHistory(null, money.getUser_id(), change, money.getMoney(), reason, new Date(), new Date());

        Session session = this.sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.persist(money_history);//save
        session.getTransaction().commit();
        session.close();

        return money_history;
    }

    /**
     * お金履歴をプライマリーキーから取得します。
     *
     * @param id Long(PrimaryKey)
     * @return Money
     * @exception MoneyHistoryNotFoundException お金履歴データが存在しない
     */
    public MoneyHistory getMoneyHistory(Long id) throws MoneyHistoryNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        MoneyHistory data = session.get(MoneyHistory.class, id);
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new MoneyHistoryNotFoundException("お金履歴データが存在しませんでした。 ID:" + id);
        }
        return data;
    }

    /**
     * お金をuser_idから取得します。
     *
     * @param user_id Long
     * @return Money
     * @throws MoneyNotFoundException お金データが存在しない
     */
    public List<MoneyHistory> getMoneyHistoryFromUserId(Long user_id) throws MoneyNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MoneyHistory> data = session.createSelectionQuery("from Money where user_id = ?1", MoneyHistory.class)
                .setParameter(1, user_id).getResultList();
        session.getTransaction().commit();
        session.close();
        if (data.isEmpty()) {
            throw new MoneyNotFoundException("お金データが存在しませんでした。 user_id:" + user_id);
        }
        return data;
    }

    /**
     * 指定したプライマリーキーがデータベースに存在するかを返します
     *
     * @param id Long(Primary key)
     * @return boolean
     */
    public boolean existsMoneyHistory(Long id) {
        try {
            getMoneyHistory(id);
            return true;
        } catch (MoneyHistoryNotFoundException e) {
            return false;
        }
    }

    /**
     * 指定したユーザーIDのカラムがデータベースに存在するかを返します
     *
     * @param user_id Long
     * @return boolean
     */
    public boolean existsMoneyFromUserId(Long user_id) {
        try {
            getMoneyHistoryFromUserId(user_id);
            return true;
        } catch (MoneyNotFoundException e) {
            return false;
        }
    }
}
