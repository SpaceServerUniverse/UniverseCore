package space.yurisi.universecore.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import space.yurisi.universecore.database.repository.UserRepository;
import space.yurisi.universecore.database.model.User;

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
        if (this.userRepository.getUser(player) == null) {
            this.userRepository.createUser(player);
        }

        User data = this.userRepository.getUser(player);

        if(!Objects.equals(data.getName(), name)){
            data.setName(name);
            this.userRepository.updateUser(data);
        }
    }
}
