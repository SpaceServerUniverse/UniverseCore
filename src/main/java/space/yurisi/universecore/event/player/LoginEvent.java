package space.yurisi.universecore.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import space.yurisi.universecore.database.repositories.UserRepository;
import space.yurisi.universecore.database.models.User;

import java.util.Objects;

public class LoginEvent implements Listener {

    private final UserRepository userRepository;

    public LoginEvent(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        String name = player.getName();
        if (this.userRepository.getUserFromUUID(player.getUniqueId()) == null) {
            this.userRepository.createUser(player);
        }

        User data = this.userRepository.getUserFromUUID(player.getUniqueId());

        if(!Objects.equals(data.getName(), name)){
            data.setName(name);
            this.userRepository.updateUser(data);
        }
    }
}
