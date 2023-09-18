package space.yurisi.universecore;

import org.bukkit.plugin.java.JavaPlugin;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.universecore.event.EventManager;
import space.yurisi.universecore.file.Config;

public final class UniverseCore extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        this.config = new Config(this);
        new DatabaseManager(this);
        new EventManager(this);
    }

    public Config getPluginConfig(){
        return this.config;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
