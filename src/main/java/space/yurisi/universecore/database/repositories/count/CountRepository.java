package space.yurisi.universecore.database.repositories.count;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.database.models.count.Count;
import space.yurisi.universecore.exception.CountNotFoundException;

import java.util.List;

public class CountRepository {

    private final SessionFactory sessionFactory;

    public CountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Count createCount(User user){
        Count count = new Count(null, user.getId());

        Session session = this.sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.persist(count);
        session.getTransaction().commit();
        session.close();

        return count;
    }

    public Count getCount(User user) throws CountNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Count data = session.createSelectionQuery("from Count where user_id = ?1", Count.class)
                .setParameter(1, user.getId())
                .getSingleResultOrNull();
        session.getTransaction().commit();
        session.close();
        if (data == null) {
            throw new CountNotFoundException("カウントデータが存在しませんでした。");
        }
        return data;
    }

    public void updateCount(Count count){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(count);
        session.getTransaction().commit();
        session.close();
    }

    public boolean existsCount(User user){
        try{
            getCount(user);
            return true;
        }catch(CountNotFoundException exception){
            return false;
        }
    }

}
