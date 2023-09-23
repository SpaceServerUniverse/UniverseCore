package space.yurisi.universecore.database.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.expection.MywarpNotFoundException;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import space.yurisi.universecore.expection.UserNotFoundException;

import java.util.Date;

public class MywarpRepository{
    private final SessionFactory sessionFactory;

    private final UserRepository userRepository;


    /**
     * Instantiates a new Mywarp repository.
     *
     * @param sessionFactory session factory
     * @param userRepository UserRepository
     */
    public MywarpRepository(SessionFactory sessionFactory, UserRepository userRepository){
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
    }

    /**
     * マイワープを作成します。
     *
     * @param player Player
     * @param warp_name String
     * @param is_private Boolean
     * @return mywarp Mywarp
     */
    public Mywarp createMywarp(Player player, String warp_name, Boolean is_private){
        Location location = player.getLocation();

        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        Long user_id = null;
        try {
            user_id = userRepository.getPrimaryKeyFromPlayerName(player.getName());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        Long x = (long) Math.floor(location.getX());
        Long y = (long) Math.floor(location.getY());
        Long z = (long) Math.floor(location.getZ());
        String world_name = location.getWorld().getWorldFolder().getName();
        Mywarp mywarp = new Mywarp(null, user_id, warp_name, x, y, z, world_name, is_private, new Date(), new Date());

        session.persist(mywarp);
        session.getTransaction().commit();
        session.close();

        return mywarp;
    }

    /**
     * マイワープをプライマリーキーから取得します。
     *
     * @param id Long(PrimaryKey)
     * @return Mywarp
     * @exception MywarpNotFoundException マイワープデータが存在しない
     */
    public Mywarp getMywarp(Long id) throws MywarpNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Mywarp data = session.get(Mywarp.class, id);
        session.getTransaction().commit();
        session.close();

        if(data == null){
            throw new MywarpNotFoundException("マイワープデータが見つかりません");
        }

        return data;
    }

    /**
     * マイワープをuser_idから取得します。
     *
     * @param user_id Long
     * @return Mywarp
     * @exception MywarpNotFoundException マイワープデータが存在しない
     */
    public Mywarp getMywarpFromUserId(Long user_id) throws MywarpNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Mywarp data = session.get(Mywarp.class, user_id);
        session.getTransaction().commit();
        session.close();

        if(data == null){
            throw new MywarpNotFoundException("マイワープデータが見つかりません");
        }

        return data;
    }

    /**
     * マイワープを更新します。
     *
     * @param mywarp Mywarp
     * @return Mywarp
     * @exception MywarpNotFoundException マイワープデータが存在しない
     */
    public Mywarp updateMywarp(Mywarp mywarp) throws MywarpNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(mywarp);
        session.getTransaction().commit();
        session.close();
        return mywarp;
    }

    /**
     * マイワープを削除します。
     *
     * @param mywarp Mywarp
     * @exception MywarpNotFoundException マイワープデータが存在しない
     */
    public void deleteMywarp(Mywarp mywarp) throws MywarpNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(mywarp);
        session.getTransaction().commit();
        session.close();
    }
}