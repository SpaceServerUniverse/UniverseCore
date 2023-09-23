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

    public MywarpRepository(SessionFactory sessionFactory, UserRepository userRepository){
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
    }

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

    public Mywarp updateMywarp(Mywarp mywarp) throws MywarpNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(mywarp);
        session.getTransaction().commit();
        session.close();
        return mywarp;
    }

    public void deleteMywarp(Mywarp mywarp) throws MywarpNotFoundException{
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(mywarp);
        session.getTransaction().commit();
        session.close();
    }
}