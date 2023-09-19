package space.yurisi.universecore.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import space.yurisi.universecore.database.repository.UserRepository;
import space.yurisi.universecore.database.model.User;

public class LoginEvent implements Listener {

    private final UserRepository userRepository;

    public LoginEvent(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        User data = this.userRepository.getUser(player);
        if (data == null) {
            this.userRepository.createUser(player);
        }
    }
}
