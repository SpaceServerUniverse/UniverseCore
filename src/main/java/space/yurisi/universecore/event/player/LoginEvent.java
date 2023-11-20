package space.yurisi.universecore.event.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.database.repositories.UserRepository;
import space.yurisi.universecore.database.repositories.count.CountRepository;
import space.yurisi.universecore.exception.UserNotFoundException;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class LoginEvent implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        String name = player.getName();
        UUID uuid = player.getUniqueId();

        DatabaseManager databaseManager = UniverseCoreAPI.getInstance().getDatabaseManager();
        UserRepository userRepository = databaseManager.getUserRepository();
        if (!userRepository.existsUserFromUUID(uuid)) {
            userRepository.createUser(player);
        }

        User user;
        try{
            user = userRepository.getUserFromUUID(uuid);
        } catch (UserNotFoundException e){
            player.kick(Component.text("ユーザーデータの読み込み時にエラーが発生しました。管理者に報告してください。"));
            return;
        }


        if(!Objects.equals(user.getName(), name)){
            user.setName(name);
        }

        user.setUpdated_at(new Date());
        userRepository.updateUser(user);
    }
}
