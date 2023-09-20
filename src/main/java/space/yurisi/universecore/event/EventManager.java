package space.yurisi.universecore.event;

import org.bukkit.Bukkit;
import space.yurisi.universecore.UniverseCore;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.event.player.LoginEvent;

public class EventManager {
    public EventManager(UniverseCore main){
        init(main);
    }

    private void init(UniverseCore main){
        Bukkit.getPluginManager().registerEvents(new LoginEvent(UniverseCoreAPI.api().getDatabaseManager().getUserRepository()), main);
    }
}
